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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("lotofacil")
@Api(tags = "Lotofácil")
public class LotofacilController {

	@Autowired
	private LotofacilService service;

	@GetMapping
	@ApiOperation("Retorna todos os resultados já realizados")
	public ResponseEntity<List<Lotofacil>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{numero}")
	@ApiOperation("Retorna um resultado específico")
	public ResponseEntity<Lotofacil> getResultById(@PathVariable("numero") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	@ApiOperation("Retorna o último o resultado")
	public ResponseEntity<Lotofacil> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
