package com.gutotech.loteriasapi.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledConsumer.class);

    private final String zone = "America/Sao_Paulo";

    @Autowired
    private LoteriasUpdate loteriasUpdate;

    @Scheduled(cron = "0 0 12 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 0 21 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 15 21 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 0 22 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 10 23 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 20 0 * * MON-SAT", zone = zone)
    @Scheduled(cron = "0 0 1 * * MON-SAT", zone = zone)
    public void checkForUpdates() {
        logger.info("Checking for updates");
        try {
            loteriasUpdate.checkForUpdates();
            logger.info("Finished checking for updates");
        } catch (Exception e) {
            logger.error("Error during checkForUpdates", e);
        }
    }
}
