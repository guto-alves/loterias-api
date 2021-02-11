package com.gutotech.loteriasapi.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("lotomania")
public class Lotomania {
	@Id
	private int concurso;
	private String data;
	private List<String> dezenas = new ArrayList<>();
	private List<Premiacao> premiacoes = new ArrayList<>();

	private String valorAcumulado;
	private String estimativaPremio;
	private String valorAcumuladoEspecial;
	private String arrecadacaoTotal;
	private String cidadeVencedor = ""; // TODO: This field is not being filled yet

	public Lotomania() {
		premiacoes.add(new Premiacao("20 Acertos", 20));
		premiacoes.add(new Premiacao("19 Acertos", 19));
		premiacoes.add(new Premiacao("18 Acertos", 18));
		premiacoes.add(new Premiacao("17 Acertos", 17));
		premiacoes.add(new Premiacao("16 Acertos", 16));
		premiacoes.add(new Premiacao("15 Acertos", 15));
		premiacoes.add(new Premiacao("Nenhum Acerto", 0));
	}

	public static Field[] getFields() {
		Field[] fields = Lotomania.class.getDeclaredFields();

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

	public String getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(String valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
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
