package com.gutotech.loteriasapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.model.Lotomania;
import com.gutotech.loteriasapi.service.LotomaniaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("lotomania")
@Api(tags = "Lotomania")
public class LotomaniaController {

	@Autowired
	private LotomaniaService service;

	@GetMapping
	@ApiOperation(value = "Retornas todos os resultados já realizados")
	public ResponseEntity<List<Lotomania>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{numero}")
	@ApiOperation(value = "Retorna o resultado do número do concurso específicado")
	public ResponseEntity<Lotomania> getResultById(@PathVariable("numero") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	@ApiOperation(value = "Retorna o resultado mais recente")
	public ResponseEntity<Lotomania> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
