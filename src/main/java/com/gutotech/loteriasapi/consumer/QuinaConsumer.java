package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gutotech.loteriasapi.model.Premiacao;
import com.gutotech.loteriasapi.model.Quina;

public class QuinaConsumer {
	private static final String URL = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/quina/!ut/p/a1/jc69DoIwAATgZ_EJepS2wFgoaUswsojYxXQyTfgbjM9vNS4Oordd8l1yxJGBuNnfw9XfwjL78dmduIikhYFGA0tzSFZ3tG_6FCmP4BxBpaVhWQuA5RRWlUZlxR6w4r89vkTi1_5E3CfRXcUhD6osEAHA32Dr4gtsfFin44Bgdw9WWSwj/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K85260Q5OIRSC420O4/res/id=historicoHTML/c=cacheLevelPage/=/";

	private static final int TOTAL_INFOS = 20;

	private static Field[] fields = Quina.getFields();

	private static Elements selectRows() throws IOException {
		Document doc = Jsoup.connect(URL).maxBodySize(0).get();
		Elements trs = doc.select("tr");
		return trs;
	}

	private static Quina toResult(Element row) throws Exception {
		Elements tds = row.select("td[rowspan]");

		if (tds.size() < TOTAL_INFOS) {
			return null;
		}

		// Organizes the elements to use Reflection
		Element arrecadacaoTotalElement = tds.get(7);
		Element cidadesVencedoresElement = tds.get(9);
		Element rateioQuinaElement = tds.get(10);
		Element rateioQuadraElement = tds.get(12);
		Element rateioTernoElement = tds.get(14);
		Element rateioDuqueElement = tds.get(16);

		tds.removeAll(Arrays.asList(arrecadacaoTotalElement, cidadesVencedoresElement, rateioQuinaElement,
				rateioQuadraElement, rateioTernoElement, rateioDuqueElement));

		tds.addAll(Arrays.asList(arrecadacaoTotalElement, cidadesVencedoresElement, rateioQuinaElement,
				rateioQuadraElement, rateioTernoElement, rateioDuqueElement));

		Quina result = new Quina();
		int infoIndex = 0;

		for (Element td : tds) {
			boolean hasTable = td.getElementsByTag("table").size() > 0;

			if (!hasTable) {
				if (infoIndex == 0) { // concurso
					fields[infoIndex].set(result, Integer.parseInt(td.text()));
				} else if (infoIndex == 1) { // data
					fields[infoIndex].set(result, td.text());
				} else if (infoIndex >= 2 && infoIndex <= 6) {
					result.getDezenas().add(td.text().substring(1));
				} else if (infoIndex >= 7 && infoIndex <= 10) { // vencedores
					int premiacaoIndex = infoIndex - 7;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setVencedores(Integer.parseInt(td.text()));
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else if (infoIndex >= 16) { // premiacao
					int premiacaoIndex = infoIndex - 16;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setPremio(td.text());
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else { // infoIndex 11 up to 15
					fields[infoIndex - 7].set(result, td.text());
				}

				if (++infoIndex == TOTAL_INFOS) {
					return result;
				}
			}
		}

		return null;
	}

	public static List<Quina> getAllResults() {
		List<Quina> results = new ArrayList<>();

		try {
			Elements rows = selectRows();

			for (Element row : rows) {
				Quina result = toResult(row);

				if (result != null) {
					results.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public static Quina getLatestResult() {
		try {
			Elements rows = selectRows();

			Element lastRow = rows.get(rows.size() - 1);

			return toResult(lastRow);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
