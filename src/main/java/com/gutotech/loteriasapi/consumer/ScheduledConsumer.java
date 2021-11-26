package com.gutotech.loteriasapi.consumer;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledConsumer {

	@Autowired
	private LoteriasUpdate loteriasUpdate;
	
	@Scheduled(cron = "0 0 12 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults12() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}

	@Scheduled(cron = "0 0 21 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults21() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}

	@Scheduled(cron = "0 15 21 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults2115() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}

	@Scheduled(cron = "0 0 22 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults22() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}

	@Scheduled(cron = "0 20 0 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults0020() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}
	
	@Scheduled(cron = "0 0 1 * * MON-SAT", zone = "America/Sao_Paulo")
	public void checkForNewResults01() {
		System.out.println("The time is now " + Instant.now());
		checkForUpdates();
	}

	public void checkForUpdates() {
		try {
			loteriasUpdate.checkForUpdates();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
