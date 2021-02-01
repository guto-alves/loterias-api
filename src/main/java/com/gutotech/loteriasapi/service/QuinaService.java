package com.gutotech.loteriasapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.loteriasapi.model.Quina;
import com.gutotech.loteriasapi.repository.QuinaRepository;

@Service
public class QuinaService {

	@Autowired
	private QuinaRepository repository;

	public List<Quina> findAll() {
		return repository.findAll();
	}

	public Quina findById(int id) {
		return repository.findById(id).get();
	}

	public Quina findLatest() {
		return repository.findTopByOrderByConcursoDesc();
	}

	public boolean notExists(int id) {
		return repository.existsById(id);
	}

	public void save(Quina quina) {
		repository.save(quina);
	}

}
