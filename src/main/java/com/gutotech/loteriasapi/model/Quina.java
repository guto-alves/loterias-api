package com.gutotech.loteriasapi.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("quina")
public class Quina {
	@Id
	private int concurso;
	private String data;
	private List<String> dezenas = new ArrayList<>();
	private List<Premiacao> premiacoes = new ArrayList<>();

	private String acumulado; // TODO: Stores "NÃO" ou "NÃO", switch it to boolean variable
	private String valorAcumulado;
	private String valorEstimado;
	private String valorAcumuladoEspecialSaoJoao;
	private String arrecadacaoTotal;
	private String cidadeVencedor = ""; // TODO: This field is not being filled yet

	public Quina() {
		premiacoes.add(new Premiacao("Quina", 5));
		premiacoes.add(new Premiacao("Quadra", 4));
		premiacoes.add(new Premiacao("Terno", 3));
		premiacoes.add(new Premiacao("Duque", 2));
	}

	public static Field[] getFields() {
		Field[] fields = Quina.class.getDeclaredFields();

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

	public String getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}

	public void setArrecadacaoTotal(String arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}

	public List<Premiacao> getPremiacoes() {
		return premiacoes;
	}

	public void setPremiacoes(List<Premiacao> premiacoes) {
		this.premiacoes = premiacoes;
	}

	public String getCidadeVencedor() {
		return cidadeVencedor;
	}

	public void setCidadeVencedor(String cidadeVencedor) {
		this.cidadeVencedor = cidadeVencedor;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}

	public String getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(String valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

	public String getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(String valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	public String getValorAcumuladoEspecialSaoJoao() {
		return valorAcumuladoEspecialSaoJoao;
	}

	public void setValorAcumuladoEspecialSaoJoao(String valorAcumuladoEspecialSaoJoao) {
		this.valorAcumuladoEspecialSaoJoao = valorAcumuladoEspecialSaoJoao;
	}

}
