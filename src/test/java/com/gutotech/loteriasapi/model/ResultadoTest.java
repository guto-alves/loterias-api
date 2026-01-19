package com.gutotech.loteriasapi.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldHaveBooleanFieldNamedConcursoEspecial() throws Exception {
        Resultado resultado = new Resultado(new ResultadoId("megasena", 2700));
        resultado.setConcursoEspecial(true);

        String json = objectMapper.writeValueAsString(resultado);

        assertTrue(json.contains("\"concursoEspecial\":true"),
                "JSON should contain concursoEspecial field as boolean");
    }

    @Test
    void shouldSerializeConcursoEspecialAsTrue() throws Exception {
        Resultado resultado = new Resultado(new ResultadoId("megasena", 2700));
        resultado.setConcursoEspecial(true);

        String json = objectMapper.writeValueAsString(resultado);

        assertTrue(json.contains("\"concursoEspecial\":true"));
    }

    @Test
    void shouldSerializeConcursoEspecialAsFalse() throws Exception {
        Resultado resultado = new Resultado(new ResultadoId("megasena", 2699));
        resultado.setConcursoEspecial(false);

        String json = objectMapper.writeValueAsString(resultado);

        assertTrue(json.contains("\"concursoEspecial\":false"));
    }

    @Test
    void shouldDeserializeConcursoEspecialCorrectly() throws Exception {
        String json = "{\"concursoEspecial\":true}";

        Resultado resultado = objectMapper.readValue(json, Resultado.class);

        assertTrue(resultado.isConcursoEspecial());
    }

    @Test
    void concursoEspecialDefaultValueShouldBeFalse() {
        Resultado resultado = new Resultado();

        assertFalse(resultado.isConcursoEspecial(),
                "Default value of concursoEspecial should be false");
    }

    @Test
    void shouldSetAndGetConcursoEspecialCorrectly() {
        Resultado resultado = new Resultado();

        resultado.setConcursoEspecial(true);
        assertTrue(resultado.isConcursoEspecial());

        resultado.setConcursoEspecial(false);
        assertFalse(resultado.isConcursoEspecial());
    }
}

