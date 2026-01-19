package com.gutotech.loteriasapi.rest;

import com.gutotech.loteriasapi.model.Loteria;
import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.exception.ResourceNotFoundException;
import com.gutotech.loteriasapi.service.ResultadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@Api(tags = "Loterias")
public class ApiRestController {

    private static final String INVALID_LOTTERY_MESSAGE = "'%s' não é o id de nenhuma das loterias suportadas. Loterias suportadas: %s";
    private static final String RESULT_NOT_FOUND_MESSAGE = "Resultado não encontrado para \"%s\", concurso \"%d\"";
    private static final String NO_RESULTS_FOUND_MESSAGE = "Nenhum resultado encontrado para a loteria %s";

    private final List<String> lotteries = Loteria.asList();

    private final String ALLOWABLE_VALUES = "maismilionaria, megasena, lotofacil, quina,"
            + " lotomania, timemania, duplasena, federal, diadesorte, supersete";

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping
    @ApiOperation(value = "Retorna todas as loterias disponíveis para pesquisa.")
    public ResponseEntity<List<String>> getLotteries() {
        return ResponseEntity.ok(Loteria.asList());
    }

    @GetMapping("{loteria}")
    @ApiOperation(value = "Retorna todos os resultados já realizados da loteria especificada.")
    public ResponseEntity<List<Resultado>> getResultsByLottery(
            @PathVariable @ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) String loteria) {
        if (!lotteries.contains(loteria)) {
            throw new ResourceNotFoundException(INVALID_LOTTERY_MESSAGE.formatted(loteria, lotteries));
        }
        return ResponseEntity.ok(resultadoService.findByLoteria(loteria));
    }

    @GetMapping("{loteria}/{concurso}")
    @ApiOperation(value = "Retorna o resultado da loteria e concurso especificado.")
    public ResponseEntity<Resultado> getResultById(
            @PathVariable @ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) String loteria,
            @PathVariable Integer concurso) {
        if (!lotteries.contains(loteria)) {
            throw new ResourceNotFoundException(INVALID_LOTTERY_MESSAGE.formatted(loteria, lotteries));
        }

        Resultado resultado = resultadoService.findByLoteriaAndConcurso(loteria, concurso);
        if (resultado == null) {
            throw new ResourceNotFoundException(RESULT_NOT_FOUND_MESSAGE.formatted(loteria, concurso));
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("{loteria}/latest")
    @ApiOperation(value = "Retorna o resultado mais recente da loteria especificada.")
    public ResponseEntity<Resultado> getLatestResult(
            @PathVariable @ApiParam(allowableValues = ALLOWABLE_VALUES, required = true) String loteria) {
        if (!lotteries.contains(loteria)) {
            throw new ResourceNotFoundException(INVALID_LOTTERY_MESSAGE.formatted(loteria, lotteries));
        }
        Resultado resultado = resultadoService.findLatest(loteria);
        if (resultado == null) {
            throw new ResourceNotFoundException(NO_RESULTS_FOUND_MESSAGE.formatted(loteria));
        }
        return ResponseEntity.ok(resultado);
    }

}
