package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gutotech.loteriasapi.model.Lotofacil;
import com.gutotech.loteriasapi.model.Premiacao;

public class LotofacilConsumer {
	private static final String URL = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/lotofacil/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbz8vTxNDRy9_Y2NQ13CDA0sTIEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wBmoxN_FydLAGAgNTKEK8DkRrACPGwpyQyMMMj0VAcySpRM!/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K85260Q5OIRSC42046/res/id=historicoHTML/c=cacheLevelPage/=/";

	public static final int TOTAL_INFOS = 30;

	private static Field[] fields = Lotofacil.getFields();

	private static Elements selectRows() throws IOException {
		Document doc = Jsoup.connect(URL).maxBodySize(0).get();
		Elements trs = doc.select("tr[bgcolor]");
		return trs;
	}

	private static Lotofacil toResult(Element row) throws Exception {
		Elements tds = row.select("td[rowspan]");

		if (tds.size() < TOTAL_INFOS) {
			return null;
		}

		// Organizes the elements to use Reflection
		Element arrecadacaoTotal = tds.remove(17);
		tds.add(arrecadacaoTotal);

		Lotofacil result = new Lotofacil();
		int resultInfoIndex = 0;

		for (Element td : tds) {
			boolean hasTable = td.getElementsByTag("table").size() > 0;

			if (!hasTable) {
				if (resultInfoIndex == 0) { // concurso
					fields[resultInfoIndex].set(result, Integer.parseInt(td.text()));
				} else if (resultInfoIndex == 1) { // data
					fields[resultInfoIndex].set(result, td.text());
				} else if (resultInfoIndex >= 2 && resultInfoIndex <= 16) { // dezenas
					result.getDezenas().add(td.text().substring(1));
				} else if (resultInfoIndex >= 17 && resultInfoIndex <= 21) { // ganhadores
					int premiacaoIndex = resultInfoIndex - 17;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setVencedores(Integer.parseInt(td.text()));
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else if (resultInfoIndex >= 22 && resultInfoIndex <= 26) { // premiacao
					int premiacaoIndex = resultInfoIndex - 22;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setPremio(td.text());
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else { // index 27 to 31
					fields[resultInfoIndex - 23].set(result, td.text());
				}

				if (++resultInfoIndex == TOTAL_INFOS) {
					return result;
				}
			}
		}

		return null;
	}

	public static List<Lotofacil> getAllResults() {
		List<Lotofacil> results = new ArrayList<>();

		try {
			Elements rows = selectRows();

			for (Element row : rows) {
				Lotofacil result = toResult(row);

				if (result != null) {
					results.add(0, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public static Lotofacil getLatestResult() {
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
