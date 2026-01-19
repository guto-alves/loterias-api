package com.gutotech.loteriasapi.consumer;

import com.gutotech.loteriasapi.model.Resultado;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerTest {

    @Mock
    private HttpConnectionService httpConnectionService;

    private Consumer consumer;

    @BeforeEach
    void setUp() {
        consumer = new Consumer(httpConnectionService);
    }

    @Test
    void shouldMarkConcursoEspecialWhenIndicatorIs2() throws Exception {
        // Arrange
        String jsonResponse = createJsonResponse(2); // indicadorConcursoEspecial = 2
        Document mockDocument = createMockDocument(jsonResponse);
        when(httpConnectionService.get(anyString())).thenReturn(mockDocument);

        // Act
        Resultado resultado = consumer.getResultado("megasena", 2700);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isConcursoEspecial(),
                "Contest should be marked as special when indicator = 2");
    }

    @Test
    void shouldNotMarkConcursoEspecialWhenIndicatorIs0() throws Exception {
        // Arrange
        String jsonResponse = createJsonResponse(0); // indicadorConcursoEspecial = 0
        Document mockDocument = createMockDocument(jsonResponse);
        when(httpConnectionService.get(anyString())).thenReturn(mockDocument);

        // Act
        Resultado resultado = consumer.getResultado("megasena", 2699);

        // Assert
        assertNotNull(resultado);
        assertFalse(resultado.isConcursoEspecial(),
                "Contest should NOT be marked as special when indicator = 0");
    }

    @Test
    void shouldNotMarkConcursoEspecialWhenIndicatorIs1() throws Exception {
        // Arrange
        String jsonResponse = createJsonResponse(1); // indicadorConcursoEspecial = 1
        Document mockDocument = createMockDocument(jsonResponse);
        when(httpConnectionService.get(anyString())).thenReturn(mockDocument);

        // Act
        Resultado resultado = consumer.getResultado("megasena", 2698);

        // Assert
        assertNotNull(resultado);
        assertFalse(resultado.isConcursoEspecial(),
                "Contest should NOT be marked as special when indicator = 1");
    }

    @Test
    void shouldUseDefaultValueWhenIndicadorConcursoEspecialDoesNotExist() throws Exception {
        // Arrange - JSON without indicadorConcursoEspecial field
        String jsonResponse = createJsonResponseWithoutIndicator();
        Document mockDocument = createMockDocument(jsonResponse);
        when(httpConnectionService.get(anyString())).thenReturn(mockDocument);

        // Act
        Resultado resultado = consumer.getResultado("megasena", 2650);

        // Assert
        assertNotNull(resultado);
        assertFalse(resultado.isConcursoEspecial(),
                "Contest should have default value false when indicator does not exist");
    }

    // Helper methods to create test JSON
    private String createJsonResponse(int indicadorConcursoEspecial) {
        return """
            {
            "numero": 2700,
            "dataApuracao": "15/01/2025",
            "localSorteio": "ESPAÇO DA SORTE",
            "nomeMunicipioUFSorteio": "SÃO PAULO, SP",
            "indicadorConcursoEspecial": %d,
            "listaDezenas": ["01", "15", "23", "33", "45", "52"],
            "listaRateioPremio": [],
            "listaMunicipioUFGanhadores": [],
            "acumulado": false,
            "dataProximoConcurso": "18/01/2025",
            "valorArrecadado": 100000.0,
            "valorAcumuladoConcurso_0_5": 0.0,
            "valorAcumuladoConcursoEspecial": 5000000.0,
            "valorAcumuladoProximoConcurso": 0.0,
            "valorEstimadoProximoConcurso": 3000000.0
            }""".formatted(indicadorConcursoEspecial);
    }

    private String createJsonResponseWithoutIndicator() {
        return """
            {
            "numero": 2650,
            "dataApuracao": "10/12/2024",
            "localSorteio": "ESPAÇO DA SORTE",
            "nomeMunicipioUFSorteio": "SÃO PAULO, SP",
            "listaDezenas": ["05", "12", "25", "30", "41", "58"],
            "listaRateioPremio": [],
            "listaMunicipioUFGanhadores": [],
            "acumulado": true,
            "dataProximoConcurso": "12/12/2024",
            "valorArrecadado": 90000.0,
            "valorAcumuladoConcurso_0_5": 0.0,
            "valorAcumuladoConcursoEspecial": 4000000.0,
            "valorAcumuladoProximoConcurso": 1000000.0,
            "valorEstimadoProximoConcurso": 2500000.0
            }""";
    }

    private Document createMockDocument(String jsonContent) {
        Document document = Document.createShell("");
        document.body().text(jsonContent);
        return document;
    }
}

