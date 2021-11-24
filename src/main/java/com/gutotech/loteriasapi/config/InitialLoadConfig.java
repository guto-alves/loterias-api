package com.gutotech.loteriasapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gutotech.loteriasapi.consumer.Consumer;

@Configuration
public class InitialLoadConfig implements CommandLineRunner {

	@Autowired
	private Consumer consumer;
	
	@Override
	public void run(String... args) throws Exception {
		consumer.checkForUpdates();
	}

}
