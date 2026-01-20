package com.gutotech.loteriasapi.rest;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;
import com.gutotech.loteriasapi.rest.exception.ExceptionsHandler;
import com.gutotech.loteriasapi.service.ResultadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiRestController.class)
class ApiRestControllerTests {

    @MockBean
    private ResultadoService resultadoService;

    @Autowired
    private ApiRestController apiRestController;

    private MockMvc mockMvc;

    private List<Resultado> megasenaResults;
    private Resultado megasenaResult1;
    private Resultado megasenaResult2;
    private Resultado lotofacilResult1;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(apiRestController)
                .setControllerAdvice(new ExceptionsHandler())
                .build();

        // Setup test data for MegaSena
        megasenaResult1 = new Resultado(new ResultadoId("megasena", 1));
        megasenaResult1.setData("31/03/1996");
        megasenaResult1.setLocal("São Paulo, SP");
        megasenaResult1.setDezenas(Arrays.asList("04", "05", "30", "33", "41", "52"));
        megasenaResult1.setAcumulou(false);
        megasenaResult1.setConcursoEspecial(false);

        megasenaResult2 = new Resultado(new ResultadoId("megasena", 2));
        megasenaResult2.setData("06/04/1996");
        megasenaResult2.setLocal("São Paulo, SP");
        megasenaResult2.setDezenas(Arrays.asList("01", "02", "03", "04", "05", "06"));
        megasenaResult2.setAcumulou(true);
        megasenaResult2.setConcursoEspecial(false);

        megasenaResults = Arrays.asList(megasenaResult2, megasenaResult1);

        // Setup test data for Lotofacil
        lotofacilResult1 = new Resultado(new ResultadoId("lotofacil", 1));
        lotofacilResult1.setData("29/09/2003");
        lotofacilResult1.setLocal("São Paulo, SP");
        lotofacilResult1.setDezenas(Arrays.asList("18", "20", "25", "23", "10", "11", "24", "14", "06", "02", "13",
                "09", "05", "16", "03"));
        lotofacilResult1.setAcumulou(false);
        lotofacilResult1.setConcursoEspecial(true);
    }

    @Test
    void testGetLotteries() throws Exception {
        mockMvc.perform(get("/api")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$.[0]").value("maismilionaria"))
                .andExpect(jsonPath("$.[1]").value("megasena"))
                .andExpect(jsonPath("$.[2]").value("lotofacil"));
    }

    @Test
    void testGetResultsByLotterySuccess() throws Exception {
        given(this.resultadoService.findByLoteria("megasena")).willReturn(megasenaResults);

        mockMvc.perform(get("/api/megasena")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].loteria").value("megasena"))
                .andExpect(jsonPath("$.[0].concurso").value(2))
                .andExpect(jsonPath("$.[1].loteria").value("megasena"))
                .andExpect(jsonPath("$.[1].concurso").value(1));
    }

    @Test
    void testGetResultsByLotteryNotFound() throws Exception {
        mockMvc.perform(get("/api/invalidlottery")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetResultsByLotteryEmpty() throws Exception {
        given(this.resultadoService.findByLoteria("quina")).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/quina")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testGetResultByIdSuccess() throws Exception {
        given(this.resultadoService.findByLoteriaAndConcurso("megasena", 1)).willReturn(megasenaResult1);

        mockMvc.perform(get("/api/megasena/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loteria").value("megasena"))
                .andExpect(jsonPath("$.concurso").value(1))
                .andExpect(jsonPath("$.data").value("31/03/1996"))
                .andExpect(jsonPath("$.dezenas", hasSize(6)))
                .andExpect(jsonPath("$.dezenas[0]").value("04"))
                .andExpect(jsonPath("$.acumulou").value(false))
                .andExpect(jsonPath("$.concursoEspecial").value(false))
                .andExpect(jsonPath("$.timeCoracao").doesNotExist());
    }

    @Test
    void testGetResultByIdWithSpecialContest() throws Exception {
        given(this.resultadoService.findByLoteriaAndConcurso("lotofacil", 1)).willReturn(lotofacilResult1);

        mockMvc.perform(get("/api/lotofacil/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loteria").value("lotofacil"))
                .andExpect(jsonPath("$.concurso").value(1))
                .andExpect(jsonPath("$.concursoEspecial").value(true))
                .andExpect(jsonPath("$.dezenas", hasSize(15)));
    }

    @Test
    void testGetResultByIdNotFound() throws Exception {
        given(this.resultadoService.findByLoteriaAndConcurso("megasena", 999999)).willReturn(null);

        mockMvc.perform(get("/api/megasena/999999")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetResultByIdInvalidLottery() throws Exception {
        mockMvc.perform(get("/api/invalidlottery/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetLatestResultSuccess() throws Exception {
        given(this.resultadoService.findLatest("megasena")).willReturn(megasenaResult2);

        mockMvc.perform(get("/api/megasena/latest")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loteria").value("megasena"))
                .andExpect(jsonPath("$.concurso").value(2))
                .andExpect(jsonPath("$.acumulou").value(true));
    }

    @Test
    void testGetLatestResultNotFound() throws Exception {
        given(this.resultadoService.findLatest("quina")).willReturn(null);

        mockMvc.perform(get("/api/quina/latest")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetLatestResultInvalidLottery() throws Exception {
        mockMvc.perform(get("/api/invalidlottery/latest")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetResultByIdAllLotteries() throws Exception {
        List<String> lotteries = Arrays.asList("maismilionaria", "megasena", "lotofacil", "quina",
                "lotomania", "timemania", "duplasena", "federal", "diadesorte", "supersete");

        for (String lottery : lotteries) {
            Resultado result = new Resultado(new ResultadoId(lottery, 1));
            result.setDezenas(Arrays.asList("01", "02", "03"));
            given(this.resultadoService.findByLoteriaAndConcurso(lottery, 1)).willReturn(result);

            mockMvc.perform(get("/api/" + lottery + "/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.loteria").value(lottery))
                    .andExpect(jsonPath("$.concurso").value(1));
        }
    }

    @Test
    void testGetLatestResultAllLotteries() throws Exception {
        List<String> lotteries = Arrays.asList("maismilionaria", "megasena", "lotofacil", "quina",
                "lotomania", "timemania", "duplasena", "federal", "diadesorte", "supersete");

        for (String lottery : lotteries) {
            Resultado result = new Resultado(new ResultadoId(lottery, 100));
            result.setData("19/01/2026");
            result.setDezenas(Arrays.asList("10", "20", "30"));
            given(this.resultadoService.findLatest(lottery)).willReturn(result);

            mockMvc.perform(get("/api/" + lottery + "/latest")
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.loteria").value(lottery))
                    .andExpect(jsonPath("$.concurso").value(100))
                    .andExpect(jsonPath("$.data").value("19/01/2026"));
        }
    }

    @Test
    void testGetResultsByLotteryMultipleResults() throws Exception {
        List<Resultado> results = Arrays.asList(
                createResultado("lotofacil", 3, "15/09/2003", false),
                createResultado("lotofacil", 2, "08/09/2003", false),
                createResultado("lotofacil", 1, "29/09/2003", true)
        );

        given(this.resultadoService.findByLoteria("lotofacil")).willReturn(results);

        mockMvc.perform(get("/api/lotofacil")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].concurso").value(3))
                .andExpect(jsonPath("$.[0].concursoEspecial").value(false))
                .andExpect(jsonPath("$.[1].concurso").value(2))
                .andExpect(jsonPath("$.[2].concurso").value(1))
                .andExpect(jsonPath("$.[2].concursoEspecial").value(true));
    }

    @Test
    void testGetResultWithAccumulatedPrize() throws Exception {
        Resultado resultado = new Resultado(new ResultadoId("megasena", 100));
        resultado.setData("19/01/2026");
        resultado.setAcumulou(true);
        resultado.setDezenas(Arrays.asList("05", "12", "23", "34", "45", "56"));
        resultado.setValorEstimadoProximoConcurso(50000000.00);

        given(this.resultadoService.findByLoteriaAndConcurso("megasena", 100)).willReturn(resultado);

        mockMvc.perform(get("/api/megasena/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acumulou").value(true))
                .andExpect(jsonPath("$.valorEstimadoProximoConcurso").value(50000000.00))
                .andExpect(jsonPath("$.proximoConcurso").value(101));
    }

    @Test
    void testGetResultWithSpecialContestFalse() throws Exception {
        Resultado resultado = new Resultado(new ResultadoId("lotofacil", 2));
        resultado.setData("08/09/2003");
        resultado.setConcursoEspecial(false);
        resultado.setDezenas(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15"));

        given(this.resultadoService.findByLoteriaAndConcurso("lotofacil", 2)).willReturn(resultado);

        mockMvc.perform(get("/api/lotofacil/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.concursoEspecial").value(false));
    }

    @Test
    void testGetResultsOrderedByConcursoDescending() throws Exception {
        List<Resultado> results = Arrays.asList(
                createResultado("quina", 5, "05/01/2026", false),
                createResultado("quina", 4, "04/01/2026", false),
                createResultado("quina", 3, "03/01/2026", false),
                createResultado("quina", 2, "02/01/2026", false),
                createResultado("quina", 1, "01/01/2026", false)
        );

        given(this.resultadoService.findByLoteria("quina")).willReturn(results);

        mockMvc.perform(get("/api/quina")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].concurso").value(5))
                .andExpect(jsonPath("$.[1].concurso").value(4))
                .andExpect(jsonPath("$.[2].concurso").value(3))
                .andExpect(jsonPath("$.[3].concurso").value(2))
                .andExpect(jsonPath("$.[4].concurso").value(1));
    }

    @Test
    void testGetResultByIdWithZeroConcurso() throws Exception {
        given(this.resultadoService.findByLoteriaAndConcurso("megasena", 0)).willReturn(null);

        mockMvc.perform(get("/api/megasena/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void testGetResultByIdWithNegativeConcurso() throws Exception {
        given(this.resultadoService.findByLoteriaAndConcurso("megasena", -1)).willReturn(null);

        mockMvc.perform(get("/api/megasena/-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void testErrorResponseStructure() throws Exception {
        mockMvc.perform(get("/api/nonexistentlottery/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.timestamp").isNumber())
                .andExpect(jsonPath("$.path").value("/api/nonexistentlottery/1"));
    }

    private Resultado createResultado(String loteria, int concurso, String data, boolean concursoEspecial) {
        Resultado resultado = new Resultado(new ResultadoId(loteria, concurso));
        resultado.setData(data);
        resultado.setConcursoEspecial(concursoEspecial);
        resultado.setDezenas(Arrays.asList("01", "02", "03", "04", "05"));
        return resultado;
    }

}
