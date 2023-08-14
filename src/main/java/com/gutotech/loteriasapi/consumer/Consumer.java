package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutotech.loteriasapi.model.Estado;
import com.gutotech.loteriasapi.model.MunicipioUFGanhadores;
import com.gutotech.loteriasapi.model.Premiacao;
import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;
import com.gutotech.loteriasapi.util.SSLHelper;

@Component
public class Consumer {

    private final String BASE_URL = "https://www.sorteonline.com.br/";

    public Resultado getResultado(String loteria, int concurso) throws IOException {
	return getResultado(loteria, String.valueOf(concurso));
    }

    public Resultado getResultado(String loteria, String concurso) throws IOException {
	return servicebus2(loteria, concurso);
    }

    private Resultado servicebus2(String loteria, String concurso) throws IOException {
	String baseUrl = "https://servicebus2.caixa.gov.br/portaldeloterias/api/";

	if (concurso == null) {
	    concurso = "";
	}

//	System.out.println(loteria + " - " + concurso);

	Document doc = SSLHelper.getConnection(baseUrl + loteria + "/" + concurso).get();
	JSONObject jsonObject = new JSONObject(doc.select("body").text());

	ResultadoId resultadoId = new ResultadoId(loteria, jsonObject.getInt("numero"));

	Resultado resultado = new Resultado(resultadoId);

	// DATA
	String data = jsonObject.getString("dataApuracao");
	resultado.setData(data);

	// LOCAL
	String local = jsonObject.getString("localSorteio") + " em "
		+ jsonObject.getString("nomeMunicipioUFSorteio");
	resultado.setLocal(local);

	// DEZENAS
	if (jsonObject.has("dezenasSorteadasOrdemSorteio")
		&& !jsonObject.isNull("dezenasSorteadasOrdemSorteio")) {
	    List<String> dezenasOrdemSorteio = jsonObject
		    .getJSONArray("dezenasSorteadasOrdemSorteio").toList().stream()
		    .map(obj -> obj.toString()).collect(Collectors.toList());
	    resultado.setDezenasOrdemSorteio(dezenasOrdemSorteio);
	}

	if (jsonObject.has("listaDezenas") && !jsonObject.isNull("listaDezenas")) {
	    List<String> dezenas = jsonObject.getJSONArray("listaDezenas").toList().stream()
		    .map(obj -> obj.toString()).collect(Collectors.toList());

	    if (jsonObject.has("listaDezenasSegundoSorteio")
		    && !jsonObject.isNull("listaDezenasSegundoSorteio")) {
		List<String> dezenas2 = jsonObject.getJSONArray("listaDezenasSegundoSorteio")
			.toList().stream().map(obj -> obj.toString()).toList();

		dezenas.addAll(dezenas2);
	    }

	    resultado.setDezenas(dezenas);
	}

	// TREVOS - +MILIONARIA
	if (jsonObject.has("trevosSorteados") && !jsonObject.isNull("trevosSorteados")) {
	    List<String> trevos = jsonObject.getJSONArray("trevosSorteados").toList().stream()
		    .map(obj -> obj.toString()).collect(Collectors.toList());

	    resultado.setTrevos(trevos);
	}

	// TIME DE CORACAO E MES DA SORTE
	if (jsonObject.has("nomeTimeCoracaoMesSorte")
		&& !jsonObject.isNull("nomeTimeCoracaoMesSorte")) {
	    String nomeTimeCoracaoMesSorte = jsonObject.getString("nomeTimeCoracaoMesSorte");

	    nomeTimeCoracaoMesSorte = !nomeTimeCoracaoMesSorte.contains("/") ? ""
		    : nomeTimeCoracaoMesSorte;

	    resultado.setTimeCoracao(nomeTimeCoracaoMesSorte);
	    resultado.setMesSorte(nomeTimeCoracaoMesSorte);
	}

	// PREMIACOES
	List<Premiacao> premiacoes = jsonObject.getJSONArray("listaRateioPremio").toList().stream()
		.map(obj -> {
		    JSONObject jsonObject2 = new JSONObject((Map) obj);

		    Premiacao premiacao = new Premiacao();
		    premiacao.setDescricao(jsonObject2.getString("descricaoFaixa"));
		    premiacao.setFaixa(jsonObject2.getInt("faixa"));
		    premiacao.setGanhadores(jsonObject2.getInt("numeroDeGanhadores"));
		    premiacao.setValorPremio(jsonObject2.getDouble("valorPremio"));
		    return premiacao;
		}).toList();

	resultado.getPremiacoes().addAll(premiacoes);

	// ESTADOS PREMIADOS
	List<MunicipioUFGanhadores> municipiosGanhadores = jsonObject
		.getJSONArray("listaMunicipioUFGanhadores").toList().stream().map(obj -> {
		    JSONObject jsonObject2 = new JSONObject((Map) obj);

		    MunicipioUFGanhadores mg = new MunicipioUFGanhadores();
		    mg.setGanhadores(jsonObject2.getInt("ganhadores"));
		    mg.setMunicipio(jsonObject2.getString("municipio"));
		    mg.setNomeFatansiaUL(jsonObject2.getString("nomeFatansiaUL"));
		    mg.setPosicao(jsonObject2.getInt("posicao"));
		    mg.setSerie(jsonObject2.getString("serie"));
		    mg.setUf(jsonObject2.getString("uf"));

		    return mg;
		}).toList();

	resultado.setLocalGanhadores(municipiosGanhadores);

	// OBSERVACAO
	if (jsonObject.has("observacao") && !jsonObject.isNull("observacao")) {
	    String observacao = jsonObject.getString("observacao");
	    resultado.setObservacao(observacao);
	}

	// ACUMULADO
	resultado.setAcumulou(jsonObject.getBoolean("acumulado"));

	// DATA PROXIMO CONCURSO
	String dataProxConcurso = jsonObject.getString("dataProximoConcurso");
	resultado.setDataProximoConcurso(dataProxConcurso);

	resultado.setValorArrecadado(jsonObject.getDouble("valorArrecadado"));
	resultado.setValorAcumuladoConcurso_0_5(jsonObject.getDouble("valorAcumuladoConcurso_0_5"));
	resultado.setValorAcumuladoConcursoEspecial(
		jsonObject.getDouble("valorAcumuladoConcursoEspecial"));
	resultado.setValorAcumuladoProximoConcurso(
		jsonObject.getDouble("valorAcumuladoProximoConcurso"));
	resultado.setValorEstimadoProximoConcurso(
		jsonObject.getDouble("valorEstimadoProximoConcurso"));

	return resultado;
    }

    private Resultado sorteOnline(String loteria, String concurso) throws IOException {
	if (concurso == null) {
	    concurso = "";
	}

	System.out.println(loteria + " - " + concurso);

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	Document doc = SSLHelper.getConnection(BASE_URL + loteria + "/resultados/" + concurso)
		.get();
	Element resultElement = doc.getElementById("DivDeVisibilidade[0]");

	Resultado resultado = new Resultado(
		new ResultadoId(loteria, concurso.equals("") ? 0 : Integer.parseInt(concurso)));

	// Nome da Loteria

	// Data
	String data = resultElement.getElementsByClass("header-resultados__datasorteio").text();
	if (data.toUpperCase().equals("HOJE")) {
	    data = dateFormat.format(new Date());
	}
	resultado.setData(data);

	// Local
	resultado.setLocal(
		resultElement.getElementsByClass("header-resultados__local-sorteio").text());

	// Dezenas
	Elements numbers = resultElement.select(".bg");
	for (Element element : numbers) {
	    try {
		Integer.parseInt(element.text());
	    } catch (Exception e) {
		continue;
	    }
	    resultado.getDezenas().add(element.text());
	}

	// Premiacoes
	Elements premiacoesTrs = resultElement.select(".block-table .result .tr");
	for (Element tr : premiacoesTrs) {
	    if (tr.classNames().size() > 1) {
		continue;
	    }

	    Premiacao premiacao = new Premiacao();

	    premiacao.setDescricao(tr.getElementsByClass("td").get(0).text());

	    try {
		premiacao.setGanhadores(Integer.parseInt(
			tr.getElementsByClass("td").get(1).text().replaceAll("[^\\d.]", "")));
	    } catch (Exception e) {
		premiacao.setGanhadores(0);
	    }

//	    premiacao.setValorPremio(tr.getElementsByClass("td").get(2).text());

	    resultado.getPremiacoes().add(premiacao);
	}

	// Estados premiados
	Element buttonWin = resultElement.getElementsByClass("button-win").first();
	if (buttonWin != null) {
	    String json = buttonWin.attr("data-estados-premiados");
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    List<Estado> estados = objectMapper.readValue(json, new TypeReference<List<Estado>>() {
	    });
	    resultado.setEstadosPremiados(estados);
	}

	// Acumulado
	resultado.setAcumulou(resultElement.getElementsByClass("acumulado").size() > 0);

	// Data Proximo Concurso
	String dataProxConcurso = resultElement
		.getElementsByClass("foother-resultados__data-sorteio").text();

	if (dataProxConcurso != null) {
	    if (dataProxConcurso.toLowerCase().equals("hoje")) {
		dataProxConcurso = dateFormat.format(new Date());
	    } else if (dataProxConcurso.toLowerCase().equals("amanhã")) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		dataProxConcurso = dateFormat.format(calendar.getTime());
	    }
	}
	resultado.setDataProximoConcurso(dataProxConcurso);

	// Time de coracao
	if (resultElement.getElementsByClass("lnr-heart").size() > 0) {
	    resultado.setTimeCoracao(resultElement.getElementsByClass("time-coracao").text());
	}

	// Mes de sorte
	if (resultElement.getElementsByClass("lnr-calendar-full").size() > 0) {
	    resultado.setMesSorte(resultElement.getElementsByClass("time-coracao").text());
	}

	return resultado;
    }

}
