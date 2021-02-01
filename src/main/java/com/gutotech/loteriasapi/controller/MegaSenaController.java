package com.gutotech.loteriasapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.model.MegaSena;
import com.gutotech.loteriasapi.service.MegaSenaService;

@RestController
@RequestMapping("megasena")
public class MegaSenaController {

	@Autowired
	private MegaSenaService service;

	@GetMapping
	public ResponseEntity<List<MegaSena>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{id}")
	public ResponseEntity<MegaSena> getResultById(@PathVariable("id") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	public ResponseEntity<MegaSena> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
