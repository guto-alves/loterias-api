package com.gutotech.loteriasapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Estado {
	private String nome;
	private String uf;
	private String vencedores;
	private String latitude;
	private String longitude;
	private List<Cidade> cidades = new ArrayList<>();

	@JsonGetter("nome")
	public String getNome() {
		return nome;
	}

	@JsonSetter("NomeEstado")
	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonGetter("uf")
	public String getUf() {
		return uf;
	}

	@JsonSetter("SiglaEstado")
	public void setUf(String uf) {
		this.uf = uf;
	}

	@JsonGetter("vencedores")
	public String getVencedores() {
		return vencedores;
	}

	@JsonSetter("Quantidade")
	public void setVencedores(String vencedores) {
		this.vencedores = vencedores;
	}

	@JsonGetter("latitude")
	public String getLatitude() {
		return latitude;
	}

	@JsonSetter("Latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@JsonGetter("longitude")
	public String getLongitude() {
		return longitude;
	}

	@JsonSetter("Longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@JsonGetter("cidades")
	public List<Cidade> getCidades() {
		return cidades;
	}

	@JsonSetter("PremiacaoPorCidade")
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public static class Cidade {
		private String cidade;
		private String vencedores;
		private String latitude;
		private String longitude;

		@JsonGetter("cidade")
		public String getCidade() {
			return cidade;
		}

		@JsonSetter("NomeCidade")
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		@JsonGetter("vencedores")
		public String getVencedores() {
			return vencedores;
		}

		@JsonSetter("Quantidade")
		public void setVencedores(String vencedores) {
			this.vencedores = vencedores;
		}

		@JsonGetter("latitude")
		public String getLatitude() {
			return latitude;
		}

		@JsonSetter("Latitude")
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		@JsonGetter("longitude")
		public String getLongitude() {
			return longitude;
		}

		@JsonSetter("Longitude")
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
	}

}
