package com.gutotech.loteriasapi.consumer;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gutotech.loteriasapi.model.Lotofacil;
import com.gutotech.loteriasapi.model.MegaSena;
import com.gutotech.loteriasapi.model.Quina;
import com.gutotech.loteriasapi.service.LotofacilService;
import com.gutotech.loteriasapi.service.MegaSenaService;
import com.gutotech.loteriasapi.service.QuinaService;

@Component
@EnableScheduling
public class ScheduledConsumer {

	@Autowired
	private MegaSenaService megaSenaService;

	@Autowired
	private LotofacilService lotofacilService;

	@Autowired
	private QuinaService quinaService;

	@Scheduled(cron = "0 0 21 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults21() {
		System.out.println("The time is now " + Instant.now());
		updateResults();
	}
	
	@Scheduled(cron = "0 15 21 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults2115() {
		System.out.println("The time is now " + Instant.now());
		updateResults();
	}

	@Scheduled(cron = "0 0 22 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults22() {
		System.out.println("The time is now " + Instant.now());
		updateResults();
	}

	@Scheduled(cron = "0 0 0 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults00() {
		System.out.println("The time is now " + Instant.now());
		updateResults();
	}

	public void updateResults() {
		MegaSena latestMegaSena = MegaSenaConsumer.getLatestResult();
		if (latestMegaSena != null && megaSenaService.notExists(latestMegaSena.getConcurso())) {
			megaSenaService.save(latestMegaSena);
		}

		Lotofacil latestLotofacil = LotofacilConsumer.getLatestResult();
		if (latestLotofacil != null && lotofacilService.notExists(latestLotofacil.getConcurso())) {
			System.out.println("Saving latest Loto result");
			lotofacilService.save(latestLotofacil);
		}

		Quina latestQuina = QuinaConsumer.getLatestResult();
		if (latestQuina != null && quinaService.notExists(latestQuina.getConcurso())) {
			quinaService.save(latestQuina);
		}
	}
}
