package com.gutotech.loteriasapi.consumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledConsumer {

    @Autowired
    private LoteriasUpdate loteriasUpdate;

    private final String zone = "America/Sao_Paulo";
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private String getCurrentDate() {
	return dateFormat.format(new Date());
    }

    @Scheduled(cron = "0 0 12 * * MON-SAT", zone = zone)
    public void checkForNewResults12() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 0 21 * * MON-SAT", zone = zone)
    public void checkForNewResults21() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 15 21 * * MON-SAT", zone = zone)
    public void checkForNewResults2115() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 0 22 * * MON-SAT", zone = zone)
    public void checkForNewResults22() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 10 23 * * MON-SAT", zone = zone)
    public void checkForNewResults2310() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 20 0 * * MON-SAT", zone = zone)
    public void checkForNewResults0020() {
	checkForUpdates();
    }

    @Scheduled(cron = "0 0 1 * * MON-SAT", zone = zone)
    public void checkForNewResults01() {
	checkForUpdates();
    }

    public void checkForUpdates() {
	System.out.println("checkForUpdates " + getCurrentDate());
	
	try {
	    loteriasUpdate.checkForUpdates();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
