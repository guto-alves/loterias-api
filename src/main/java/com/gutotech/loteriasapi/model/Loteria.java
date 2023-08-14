package com.gutotech.loteriasapi.model;

import java.util.List;
import java.util.stream.Collectors;

public enum Loteria {
    MAIS_MILIONARIA("maismilionaria"), 
    MEGA_SENA("megasena"), 
    LOTOFACIL("lotofacil"), 
    QUINA("quina"), 
    LOTOMANIA("lotomania"), 
    TIMEMANIA("timemania"),
    DUPLA_SENA("duplasena"), 
    FEDERAL("federal"),
    DIA_DE_SORTE("diadesorte"),
    SUPER_SETE("supersete");

    private final String nome;

    Loteria(String nome) {
	this.nome = nome;
    }

    @Override
    public String toString() {
	return nome;
    }

    public static List<String> asList() {
	return List.of(Loteria.values()).stream().map(Loteria::toString)
		.collect(Collectors.toList());
    }

}
