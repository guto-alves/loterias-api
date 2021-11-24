package com.gutotech.loteriasapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.exception.ResourceNotFoundException;
import com.gutotech.loteriasapi.service.ResultadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api")
@Api(tags = "Loterias")
public class ApiController {

	private final List<String> lotteries = Arrays.asList("mega-sena", "lotofacil", "quina", "lotomania", "timemania",
			"dupla-sena", "loteria-federal", "dia-de-sorte", "super-sete");

	private final String ALLOWABLE_VALUES = "mega-sena, lotofacil, quina, lotomania, timemania, dupla-sena, loteria-federal, dia-de-sorte, super-sete";

	private final String invalidLotteryMessageFormat = "'%s' não é o id de nenhuma das loterias suportadas. Loterias suportadas: "
			+ lotteries;

	@Autowired
	private ResultadoService resultadoService;

	@GetMapping
	@ApiOperation(value = "Retorna todas as loterias disponíveis para pesquisa.")
	public ResponseEntity<List<String>> getLotteries() {
		return ResponseEntity.ok(lotteries);
	}

	@GetMapping("{loteria}")
	@ApiOperation(value = "Retorna todos os resultados já realizados da loteria especificada.")
	public ResponseEntity<List<Resultado>> getAllResults(
			@ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) @PathVariable("loteria") String loteria) {
		if (!lotteries.contains(loteria)) {
			throw new ResourceNotFoundException(String.format(invalidLotteryMessageFormat, loteria));
		}

		return ResponseEntity.ok(resultadoService.findByLoteria(loteria));
	}

	@GetMapping("{loteria}/{concurso}")
	@ApiOperation(value = "Retorna o resultado da loteria e concurso especificado.")
	public ResponseEntity<Resultado> getResultById(
			@ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) @PathVariable("loteria") String loteria,
			@PathVariable("concurso") Integer concurso) {
		if (!lotteries.contains(loteria)) {
			throw new ResourceNotFoundException(String.format(invalidLotteryMessageFormat, loteria));
		}

		return ResponseEntity.ok(resultadoService.findByLoteriaAndConcurso(loteria, concurso));
	}

	@GetMapping("{loteria}/latest")
	@ApiOperation(value = "Retorna o resultado mais recente da loteria especificada.")
	public ResponseEntity<Resultado> getLatestResult(
			@ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) @PathVariable("loteria") String loteria) {
		if (!lotteries.contains(loteria)) {
			throw new ResourceNotFoundException(String.format(invalidLotteryMessageFormat, loteria));
		}

		return ResponseEntity.ok(resultadoService.findLatest(loteria));
	}

}
