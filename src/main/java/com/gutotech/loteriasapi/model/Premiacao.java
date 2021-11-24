package com.gutotech.loteriasapi.model;

public class Premiacao {
	private String acertos;
	private int vencedores;
	private String premio;

	public Premiacao() {
	}

	public Premiacao(String acertos, int vencedores, String premio) {
		this.acertos = acertos;
		this.vencedores = vencedores;
		this.premio = premio;
	}

	public String getAcertos() {
		return acertos;
	}

	public void setAcertos(String acertos) {
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
