package com.gutotech.loteriasapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.loteriasapi.model.Lotomania;
import com.gutotech.loteriasapi.repository.LotomaniaRepository;

@Service
public class LotomaniaService {

	@Autowired
	private LotomaniaRepository repository;

	public List<Lotomania> findAll() {
		return repository.findAll();
	}

	public Lotomania findById(int id) {
		return repository.findById(id).get();
	}

	public Lotomania findLatest() {
		return repository.findTopByOrderByConcursoDesc();
	}

	public boolean notExists(int id) {
		return !repository.existsById(id);
	}

	public void save(Lotomania quina) {
		repository.save(quina);
	}

	public void saveAll(List<Lotomania> newResults) {
		repository.saveAll(newResults);
	}

}
