package com.gutotech.loteriasapi.consumer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gutotech.loteriasapi.model.MegaSena;
import com.gutotech.loteriasapi.model.Premiacao;

public class MegaSenaConsumer {
	private static final String URL = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbwMPI0sDBxNXAOMwrzCjA0sjIEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wNnUwNHfxcnSwBgIDUyhCvA5EawAjxsKckMjDDI9FQE-F4ca/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K8DBC0QPVN93KQ10G1/res/id=historicoHTML/c=cacheLevelPage/=/";

	private static final int TOTAL_INFOS = 22;

	private static final Field[] fields = MegaSena.getFields();

	public static List<MegaSena> getAllResults() throws Exception {
		List<MegaSena> results = new ArrayList<>();

		MegaSena result = new MegaSena();
		int infoIndex = 0;

		Document doc = Jsoup.connect(URL).maxBodySize(0).get();

		Elements tds = doc.getElementsByTag("tbody").get(1).select("td[align]");

		for (int i = 0; i < tds.size(); i++) {
			Element td = tds.get(i);

			boolean hasTable = td.getElementsByTag("table").size() > 0;

			if (!hasTable) {
				if (infoIndex == 0) {
					fields[infoIndex].set(result, Integer.parseInt(td.text()));
				} else if (infoIndex < 3) {
					fields[infoIndex].set(result, td.text());
				} else if (infoIndex >= 3 && infoIndex <= 8) {
					result.getDezenas().add(td.text().substring(1));
				} else if (infoIndex >= 9 && infoIndex <= 11) { // vencedores
					int premiacaoIndex = infoIndex - 9;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setVencedores(Integer.parseInt(td.text()));
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else if (infoIndex >= 12 && infoIndex <= 14) { // premiacao
					int premiacaoIndex = infoIndex - 12;
					Premiacao premiacao = result.getPremiacoes().get(premiacaoIndex);
					premiacao.setPremio(td.text());
					result.getPremiacoes().set(premiacaoIndex, premiacao);
				} else if (infoIndex >= 16 && infoIndex <= 21) {
					fields[infoIndex - 10].set(result, td.text());
				}
			}

			if (++infoIndex == TOTAL_INFOS) {
				results.add(0, result);
				result = new MegaSena();
				infoIndex = 0;
			}
		}

		return results;
	}

	public static MegaSena getLatestResult() {
		try {
			List<MegaSena> results = getAllResults();
			return results.get(results.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
