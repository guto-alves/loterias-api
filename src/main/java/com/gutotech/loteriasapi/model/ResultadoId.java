package com.gutotech.loteriasapi.model;

import java.io.Serializable;
import java.util.Objects;

public class ResultadoId implements Serializable {
    private String loteria;
    private int concurso;

    public ResultadoId() {
    }

    public ResultadoId(String loteria, int concurso) {
	this.loteria = loteria;
	this.concurso = concurso;
    }

    public String getLoteria() {
	return loteria;
    }

    public void setLoteria(String loteria) {
	this.loteria = loteria;
    }

    public int getConcurso() {
	return concurso;
    }

    public void setConcurso(int concurso) {
	this.concurso = concurso;
    }

    @Override
    public int hashCode() {
	return Objects.hash(concurso, loteria);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof ResultadoId)) {
	    return false;
	}
	ResultadoId other = (ResultadoId) obj;
	return concurso == other.concurso && Objects.equals(loteria, other.loteria);
    }

}
