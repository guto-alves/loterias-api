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

import com.gutotech.loteriasapi.model.Lotomania;
import com.gutotech.loteriasapi.model.Premiacao;

public class LotomaniaConsumer {
	private static final String URL = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/lotomania/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbz8vTxNDRy9_Y2NQ13CDA38jYEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wBmoxN_FydLAGAgNTKEK8DkRrACPGwpyQyMMMj0VAajYsZo!/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K85260Q5OIRSC42045/res/id=historicoHTML/c=cacheLevelPage/=/";

	private static final int TOTAL_INFOS = 38;

	private static Field[] fields = Lotomania.getFields();

	private static Elements selectRows() throws IOException {
		Document doc = Jsoup.connect(URL).maxBodySize(0).get();
		Elements trs = doc.select("tr");
		return trs;
	}

	private static Lotomania toResult(Element row) throws Exception {
		Elements tds = row.select("td[rowspan]");

		if (tds.size() < TOTAL_INFOS) {
			return null;
		}

		// Organizes the elements to use Reflection
		Element arrecadacaoTotalElement = tds.get(22);
		Element cidadesVencedoresElement = tds.get(24);

		tds.removeAll(Arrays.asList(arrecadacaoTotalElement, cidadesVencedoresElement));

		tds.addAll(Arrays.asList(arrecadacaoTotalElement, cidadesVencedoresElement));

		Lotomania result = new Lotomania();
		int infoIndex = 0;

		for (Element td : tds) {
			boolean hasTable = td.getElementsByTag("table").size() > 0;

			if (!hasTable) {
				System.out.println(td.text());
				if (infoIndex == 0) { // concurso
					fields[infoIndex].set(result, Integer.parseInt(td.text()));
				} else if (infoIndex == 1) { // data
					fields[infoIndex].set(result, td.text());
				} else if (infoIndex >= 2 && infoIndex <= 21) {
					result.getDezenas().add(td.text().substring(1));
				} else if (infoIndex >= 22 && infoIndex <= 27) { // vencedores
					int premiacaoIndex = infoIndex - 22;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setVencedores(Integer.parseInt(td.text()));
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else if (infoIndex >= 28 && infoIndex <= 33) { // premiacao
					int premiacaoIndex = infoIndex - 28;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setPremio(td.text());
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else { // infoIndex 34 up to 37
					fields[infoIndex - 30].set(result, td.text());
				}

				if (++infoIndex == TOTAL_INFOS) {
					return result;
				}
			}
		}

		return null;
	}

	public static List<Lotomania> getAllResults() {
		List<Lotomania> results = new ArrayList<>();

		try {
			Elements rows = selectRows();

			for (Element row : rows) {
				Lotomania result = toResult(row);

				if (result != null) {
					results.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public static Lotomania getLatestResult() {
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
