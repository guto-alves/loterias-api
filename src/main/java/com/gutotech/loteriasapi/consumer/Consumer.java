package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutotech.loteriasapi.model.Estado;
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
		if (concurso == null) {
			concurso = "";
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM//yyyy");

		Document doc = SSLHelper.getConnection(BASE_URL + loteria + "/resultados/" + concurso).get();
		Element resultElement = doc.getElementById("DivDeVisibilidade[0]");

		Resultado resultado = new Resultado(
				new ResultadoId(loteria, concurso.equals("") ? 0 : Integer.parseInt(concurso)));

		// Nome da Loteria
		resultado.setNome(resultElement.select(".title .name").text());

		// Concurso
		resultado.setConcurso(
				Integer.valueOf(resultElement.getElementsByClass("header-resultados__nro-concurso").text()));

		// Data
		String data = resultElement.getElementsByClass("header-resultados__datasorteio").text();
		if (data.toUpperCase().equals("HOJE")) {
			data = dateFormat.format(new Date());
		}
		resultado.setData(data);

		// Local
		resultado.setLocal(resultElement.getElementsByClass("header-resultados__local-sorteio").text());

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

			premiacao.setAcertos(tr.getElementsByClass("td").get(0).text());

			try {
				premiacao.setVencedores(Integer.parseInt(tr.getElementsByClass("td").get(1).children().get(0).text()));
			} catch (Exception e) {
				premiacao.setVencedores(0);
			}

			premiacao.setPremio(tr.getElementsByClass("td").get(2).text());

			resultado.getPremiacoes().add(premiacao);
		}
		
		// Estados premiados
		Element buttonWin = resultElement.getElementsByClass("button-win").first();
		if (buttonWin != null) {
			String json = buttonWin.attr("data-estados-premiados");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<Estado> estados = objectMapper.readValue(json, new TypeReference<List<Estado>>() {});	
			resultado.setEstadosPremiados(estados);
		}

		// Acumulado
		resultado.setAcumulou(resultElement.getElementsByClass("acumulado").size() > 0);
		resultado.setAcumuladaProxConcurso(resultElement.getElementsByClass("estimative").select(".value").text());

		// Data Proximo Concurso
		String dataProxConcurso = resultElement.getElementsByClass("foother-resultados__data-sorteio").text();

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
		resultado.setDataProxConcurso(dataProxConcurso);

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
