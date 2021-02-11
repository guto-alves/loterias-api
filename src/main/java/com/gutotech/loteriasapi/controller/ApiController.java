package com.gutotech.loteriasapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.service.LotofacilService;
import com.gutotech.loteriasapi.service.LotomaniaService;
import com.gutotech.loteriasapi.service.MegaSenaService;
import com.gutotech.loteriasapi.service.QuinaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v0")
@Api(tags = "Loterias")
public class ApiController {

	private final List<String> lotteries = Arrays.asList("megasena", "lotofacil", "quina", "lotomania");

	@Autowired
	private MegaSenaService megaSenaService;

	@Autowired
	private LotofacilService lotofacilService;

	@Autowired
	private QuinaService quinaService;

	@Autowired
	private LotomaniaService lotomaniaService;

	@GetMapping
	public ResponseEntity<List<String>> getLotteries() {
		return ResponseEntity.ok(lotteries);
	}

	@GetMapping("{loteria}")
	@ApiOperation(value = "Retorna todos os resultados já realizados da loteria especificada.")
	public ResponseEntity<Object> getAllResults(@PathVariable("loteria") String loteria) {
		if (!loteria.contains(loteria)) {
			return ResponseEntity.ok(null);
		}

		Object body = null;

		if (loteria.equals("megasena")) {
			body = megaSenaService.findAll();
		} else if (loteria.equals("lotofacil")) {
			body = lotofacilService.findAll();
		} else if (loteria.equals("quina")) {
			body = quinaService.findAll();
		} else if (loteria.equals("lotomania")) {
			body = lotomaniaService.findAll();
		}

		return ResponseEntity.ok(body);
	}

	@GetMapping("{loteria}/{concurso}")
	@ApiOperation(value = "Retorna o resultado da loteria e concurso especificado.")
	public ResponseEntity<Object> getResultById(
			@PathVariable("loteria") String loteria,
			@PathVariable("concurso") Integer id) {
		if (!loteria.contains(loteria)) {
			return ResponseEntity.ok(null);
		}

		Object body = null;

		if (loteria.equals("megasena")) {
			body = megaSenaService.findById(id);
		} else if (loteria.equals("lotofacil")) {
			body = lotofacilService.findById(id);
		} else if (loteria.equals("quina")) {
			body = quinaService.findById(id);
		} else if (loteria.equals("lotomania")) {
			body = lotomaniaService.findById(id);
		}

		return ResponseEntity.ok(body);
	}

	@GetMapping("{loteria}/latest")
	@ApiOperation(value = "Retorna o resultado mais recente da loteria especificada.")
	public ResponseEntity<Object> getResultLatest(@PathVariable("loteria") String loteria) {
		Object body = null;

		if (loteria.equals("megasena")) {
			body = megaSenaService.findLatest();
		} else if (loteria.equals("lotofacil")) {
			body = lotofacilService.findLatest();
		} else if (loteria.equals("quina")) {
			body = quinaService.findLatest();
		} else if (loteria.equals("lotomania")) {
			body = lotomaniaService.findLatest();
		}

		return ResponseEntity.ok(body);
	}
}
