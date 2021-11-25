package com.gutotech.loteriasapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document("resultados")
@JsonPropertyOrder({ "loteria", "nome", "concurso", "data", "local", "dezenas", "premiacoes", "acumulado",
		"acumuladaProxConcurso", "dataProxConcurso", "proxConcurso", "timeCoracao", "mesSorte" })
public class Resultado {
	@Id
	private ResultadoId id = new ResultadoId();
	private String nome;
	private String data;
	private String local;
	private List<String> dezenas = new ArrayList<>();
	private List<Premiacao> premiacoes = new ArrayList<>();
	private List<Estado> estadosPremiados = new ArrayList<>();
	private boolean acumulado;
	private String acumuladaProxConcurso;
	private String dataProxConcurso;
	private String timeCoracao; // somente para Timemania
	private String mesSorte; // somente para Dia de Sorte

	public Resultado() {
	}

	public Resultado(ResultadoId id) {
		this.id = id;
	}

	public String getLoteria() {
		return id.getLoteria();
	}

	public void setLoteria(String loteria) {
		this.id.setLoteria(loteria);
	}

	public int getConcurso() {
		return id.getConcurso();
	}

	public void setConcurso(int concurso) {
		this.id.setConcurso(concurso);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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

	public List<Estado> getEstadosPremiados() {
		return estadosPremiados;
	}

	public void setEstadosPremiados(List<Estado> estadosPremiados) {
		this.estadosPremiados = estadosPremiados;
	}

	public boolean isAcumulado() {
		return acumulado;
	}

	public void setAcumulado(boolean acumulado) {
		this.acumulado = acumulado;
	}

	public String getAcumuladaProxConcurso() {
		return acumuladaProxConcurso;
	}

	public void setAcumuladaProxConcurso(String acumuladaProxConcurso) {
		this.acumuladaProxConcurso = acumuladaProxConcurso;
	}

	public String getDataProxConcurso() {
		return dataProxConcurso;
	}

	public void setDataProxConcurso(String dataProxConcurso) {
		this.dataProxConcurso = dataProxConcurso;
	}

	public int getProxConcurso() {
		return id.getConcurso() + 1;
	}

	public String getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(String timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

	public String getMesSorte() {
		return mesSorte;
	}

	public void setMesSorte(String mesSorte) {
		this.mesSorte = mesSorte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Resultado)) {
			return false;
		}
		Resultado other = (Resultado) obj;
		return Objects.equals(id, other.id);
	}

}
