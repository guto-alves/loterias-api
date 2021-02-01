package com.gutotech.loteriasapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.model.Lotofacil;
import com.gutotech.loteriasapi.service.LotofacilService;

@RestController
@RequestMapping("lotofacil")
public class LotofacilController {

	@Autowired
	private LotofacilService service;

	@GetMapping
	public ResponseEntity<List<Lotofacil>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{id}")
	public ResponseEntity<Lotofacil> getResultById(@PathVariable("id") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	public ResponseEntity<Lotofacil> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
