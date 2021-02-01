package com.gutotech.loteriasapi.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("lotofacil")
public class Lotofacil {
	@Id
	private int concurso;
	private String data;
	private List<String> dezenas = new ArrayList<>();
	private List<Premiacao> premiacoes = new ArrayList<>();

	private String acumulado15;
	private String estimativaPremio;
	private String valorAcumuladoEspecial;
	private String arrecadacaoTotal;
	private String cidadeVencedor = ""; // TODO: This field is not being filled yet

	public Lotofacil() {
		premiacoes.add(new Premiacao("15 Acertos", 15));
		premiacoes.add(new Premiacao("14 Acertos", 14));
		premiacoes.add(new Premiacao("13 Acertos", 13));
		premiacoes.add(new Premiacao("12 Acertos", 12));
		premiacoes.add(new Premiacao("11 Acertos", 11));
	}

	public static Field[] getFields() {
		Field[] fields = Lotofacil.class.getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
		}

		return fields;
	}

	public int getConcurso() {
		return concurso;
	}

	public void setConcurso(int concurso) {
		this.concurso = concurso;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<String> getDezenas() {
		return dezenas;
	}

	public void setDezenas(List<String> dezenas) {
		this.dezenas = dezenas;
	}

	public List<Premiacao> getPremiacoes() {
		return premiacoes;
	}

	public void setPremiacoes(List<Premiacao> premiacoes) {
		this.premiacoes = premiacoes;
	}

	public String getAcumulado15() {
		return acumulado15;
	}

	public void setAcumulado15(String acumulado15) {
		this.acumulado15 = acumulado15;
	}

	public String getEstimativaPremio() {
		return estimativaPremio;
	}

	public void setEstimativaPremio(String estimativaPremio) {
		this.estimativaPremio = estimativaPremio;
	}

	public String getValorAcumuladoEspecial() {
		return valorAcumuladoEspecial;
	}

	public void setValorAcumuladoEspecial(String valorAcumuladoEspecial) {
		this.valorAcumuladoEspecial = valorAcumuladoEspecial;
	}

	public String getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}

	public void setArrecadacaoTotal(String arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}

	public String getCidadeVencedor() {
		return cidadeVencedor;
	}

	public void setCidadeVencedor(String cidadeVencedor) {
		this.cidadeVencedor = cidadeVencedor;
	}

}
