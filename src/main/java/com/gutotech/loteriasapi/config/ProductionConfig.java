package com.gutotech.loteriasapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gutotech.loteriasapi.consumer.LotofacilConsumer;
import com.gutotech.loteriasapi.consumer.MegaSenaConsumer;
import com.gutotech.loteriasapi.consumer.QuinaConsumer;
import com.gutotech.loteriasapi.model.Lotofacil;
import com.gutotech.loteriasapi.model.MegaSena;
import com.gutotech.loteriasapi.model.Quina;
import com.gutotech.loteriasapi.service.LotofacilService;
import com.gutotech.loteriasapi.service.MegaSenaService;
import com.gutotech.loteriasapi.service.QuinaService;

@Configuration
@Profile("prod")
public class ProductionConfig implements CommandLineRunner {
	@Autowired
	private MegaSenaService megaSenaService;

	@Autowired
	private LotofacilService lotofacilService;

	@Autowired
	private QuinaService quinaService;

	@Override
	public void run(String... args) throws Exception {
		checkForNewResultsMegaSena();
		checkForNewResultsLotofacil();
		checkForNewResultsQuina();
	}

	private void checkForNewResultsMegaSena() throws Exception {
		MegaSena myLatestMegaSenaResult = megaSenaService.findLatest();

		List<MegaSena> megaSenaResults = MegaSenaConsumer.getAllResults();
		MegaSena latestMegaSenaResult = megaSenaResults.get(megaSenaResults.size() - 1);

		if (myLatestMegaSenaResult.getConcurso() != latestMegaSenaResult.getConcurso()) {
			List<MegaSena> newResults = new ArrayList<>();

			for (int i = myLatestMegaSenaResult.getConcurso(); i < megaSenaResults.size(); i++) {
				newResults.add(megaSenaResults.get(i));
			}

			megaSenaService.saveAll(newResults);
		}
	}

	private void checkForNewResultsLotofacil() {
		Lotofacil myLatestLotofacilResult = lotofacilService.findLatest();

		List<Lotofacil> lotofacilResults = LotofacilConsumer.getAllResults();
		Lotofacil latestLotofacilResult = lotofacilResults.get(lotofacilResults.size() - 1);

		if (myLatestLotofacilResult.getConcurso() != latestLotofacilResult.getConcurso()) {
			List<Lotofacil> newResults = new ArrayList<>();

			for (int i = myLatestLotofacilResult.getConcurso(); i < lotofacilResults.size(); i++) {
				newResults.add(lotofacilResults.get(i));
			}

			lotofacilService.saveAll(newResults);
		}
	}

	private void checkForNewResultsQuina() {
		Quina myLatestQuinaResult = quinaService.findLatest();

		List<Quina> quinaResults = QuinaConsumer.getAllResults();
		Quina latestQuinaResult = quinaResults.get(quinaResults.size() - 1);

		if (myLatestQuinaResult.getConcurso() != latestQuinaResult.getConcurso()) {
			List<Quina> newResults = new ArrayList<>();

			for (int i = myLatestQuinaResult.getConcurso(); i < quinaResults.size(); i++) {
				newResults.add(quinaResults.get(i));
			}

			quinaService.saveAll(newResults);
		}
	}

}
