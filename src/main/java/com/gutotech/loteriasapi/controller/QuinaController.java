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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("quina")
@Api(tags = "Quina")
public class QuinaController {

	@Autowired
	private QuinaService service;

	@GetMapping
	@ApiOperation(value = "Retornas todos os resultados já realizados")
	public ResponseEntity<List<Quina>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{numero}")
	@ApiOperation(value = "Retorna o resultado do número do concurso específicado")
	public ResponseEntity<Quina> getResultById(@PathVariable("numero") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	@ApiOperation(value = "Retorna o resultado mais recente")
	public ResponseEntity<Quina> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
