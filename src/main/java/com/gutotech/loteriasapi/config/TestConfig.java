package com.gutotech.loteriasapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gutotech.loteriasapi.consumer.LotofacilConsumer;
import com.gutotech.loteriasapi.consumer.LotomaniaConsumer;
import com.gutotech.loteriasapi.consumer.MegaSenaConsumer;
import com.gutotech.loteriasapi.consumer.QuinaConsumer;
import com.gutotech.loteriasapi.repository.LotofacilRepository;
import com.gutotech.loteriasapi.repository.LotomaniaRepository;
import com.gutotech.loteriasapi.repository.MegaSenaRepository;
import com.gutotech.loteriasapi.repository.QuinaRepository;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private MegaSenaRepository megaSenaRepository;

	@Autowired
	private LotofacilRepository lotofacilRepository;

	@Autowired
	private QuinaRepository quinaRepository;
	
	@Autowired
	private LotomaniaRepository lotomaniaRepository;

	@Override
	public void run(String... args) throws Exception {
		megaSenaRepository.deleteAll();
		megaSenaRepository.saveAll(MegaSenaConsumer.getAllResults());

		lotofacilRepository.deleteAll();
		lotofacilRepository.saveAll(LotofacilConsumer.getAllResults());

		quinaRepository.deleteAll();
		quinaRepository.saveAll(QuinaConsumer.getAllResults());

		lotomaniaRepository.deleteAll();
		lotomaniaRepository.saveAll(LotomaniaConsumer.getAllResults());
	}

}
