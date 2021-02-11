package com.gutotech.loteriasapi.model;

public class Premiacao {
	private String nome;
	private int acertos;
	private int vencedores;
	private String premio = "0,00";

	public Premiacao() {
	}

	public Premiacao(String nome, int acertos) {
		this.nome = nome;
		this.acertos = acertos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public int getVencedores() {
		return vencedores;
	}

	public void setVencedores(int vencedores) {
		this.vencedores = vencedores;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

}
