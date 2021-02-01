package com.gutotech.loteriasapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.model.Quina;
import com.gutotech.loteriasapi.service.QuinaService;

@RestController
@RequestMapping("quina")
public class QuinaController {

	@Autowired
	private QuinaService service;

	@GetMapping
	public ResponseEntity<List<Quina>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{id}")
	public ResponseEntity<Quina> getResultById(@PathVariable("id") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	public ResponseEntity<Quina> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
