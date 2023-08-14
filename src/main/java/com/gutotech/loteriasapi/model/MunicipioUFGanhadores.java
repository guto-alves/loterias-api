package com.gutotech.loteriasapi.model;

import java.util.Objects;

public class MunicipioUFGanhadores {
    private int ganhadores;
    private String municipio;
    private String nomeFatansiaUL;
    private String serie;
    private int posicao;
    private String uf;

    public MunicipioUFGanhadores() {
    }

    public int getGanhadores() {
	return ganhadores;
    }

    public void setGanhadores(int ganhadores) {
	this.ganhadores = ganhadores;
    }

    public String getMunicipio() {
	return municipio;
    }

    public void setMunicipio(String municipio) {
	this.municipio = municipio;
    }

    public String getNomeFatansiaUL() {
	return nomeFatansiaUL;
    }

    public void setNomeFatansiaUL(String nomeFatansiaUL) {
	this.nomeFatansiaUL = nomeFatansiaUL;
    }

    public String getSerie() {
	return serie;
    }

    public void setSerie(String serie) {
	this.serie = serie;
    }

    public int getPosicao() {
	return posicao;
    }

    public void setPosicao(int posicao) {
	this.posicao = posicao;
    }

    public String getUf() {
	return uf;
    }

    public void setUf(String uf) {
	this.uf = uf;
    }

    @Override
    public int hashCode() {
	return Objects.hash(municipio, uf);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof MunicipioUFGanhadores)) {
	    return false;
	}
	MunicipioUFGanhadores other = (MunicipioUFGanhadores) obj;
	return Objects.equals(municipio, other.municipio) && Objects.equals(uf, other.uf);
    }

}
