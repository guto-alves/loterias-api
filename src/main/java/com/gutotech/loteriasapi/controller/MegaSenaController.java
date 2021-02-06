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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("megasena")
@Api(tags = "Mega Sena")
public class MegaSenaController {

	@Autowired
	private MegaSenaService service;

	@GetMapping
	@ApiOperation(value = "Retorna todos os resultados já realizados")
	public ResponseEntity<List<MegaSena>> getAllResults() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("concurso/{numero}")
	@ApiOperation(value = "Retorna o resultado do número do concurso especificado")
	public ResponseEntity<MegaSena> getResultById(@PathVariable("numero") int id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("latest")
	@ApiOperation(value = "Retorna o resultado mais recente")
	public ResponseEntity<MegaSena> getLatestResult() {
		return ResponseEntity.ok(service.findLatest());
	}

}
