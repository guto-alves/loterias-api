package com.gutotech.loteriasapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.loteriasapi.model.MegaSena;
import com.gutotech.loteriasapi.repository.MegaSenaRepository;

@Service
public class MegaSenaService {

	@Autowired
	private MegaSenaRepository repository;

	public List<MegaSena> findAll() {
		return repository.findAll();
	}

	public MegaSena findById(int id) {
		return repository.findById(id).get();
	}

	public MegaSena findLatest() {
		return repository.findTopByOrderByConcursoDesc();
	}

	public boolean notExists(int id) {
		return !repository.existsById(id);
	}

	public void save(MegaSena lotofacil) {
		repository.save(lotofacil);
	}

}
