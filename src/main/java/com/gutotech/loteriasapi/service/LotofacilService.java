package com.gutotech.loteriasapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.loteriasapi.model.Lotofacil;
import com.gutotech.loteriasapi.repository.LotofacilRepository;

@Service
public class LotofacilService {

	@Autowired
	private LotofacilRepository repository;
	
	public List<Lotofacil> findAll() {
		return repository.findAll();
	}
	
	public Lotofacil findById(int id) {
		return repository.findById(id).get();
	}
	
	public Lotofacil findLatest() {
		return repository.findTopByOrderByConcursoDesc();
	}
	
	public boolean notExists(int id) {
		return repository.existsById(id);
	}
	
	public void save(Lotofacil lotofacil) {
		repository.save(lotofacil);
	}
	
}
