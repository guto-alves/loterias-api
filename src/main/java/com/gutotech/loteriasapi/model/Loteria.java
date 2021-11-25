package com.gutotech.loteriasapi.model;

import java.util.List;
import java.util.stream.Collectors;

public enum Loteria {
	MEGA_SENA("mega-sena"), 
	LOTOFÁCIL("lotofacil"), 
	QUINA("quina"), 
	LOTOMANIA("lotomania"), 
	TIMEMANIA("timemania"),
	DUPLA_SENA("dupla-sena"), 
	FEDERAL("loteria-federal"),
	DIA_DE_SORTE("dia-de-sorte"),
	SUPER_SETE("super-sete");

	private String nome;

	Loteria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public static List<String> asList() {
		return List.of(Loteria.values())
				.stream()
				.map(Loteria::getNome)
				.collect(Collectors.toList());
	}

}
