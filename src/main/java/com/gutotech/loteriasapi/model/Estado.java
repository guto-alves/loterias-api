package com.gutotech.loteriasapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Estado {
	@JsonProperty("NomeEstado")
	private String nome;

	@JsonProperty("SiglaEstado")
	private String uf;

	@JsonProperty("Quantidade")
	private String vencedores;

	@JsonProperty("Latitude")
	private String latitude;

	@JsonProperty("Longitude")
	private String longitude;

	@JsonProperty("PremiacaoPorCidade")
	private List<Cidade> cidades = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getVencedores() {
		return vencedores;
	}

	public void setVencedores(String vencedores) {
		this.vencedores = vencedores;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public static class Cidade {
		@JsonProperty("NomeCidade")
		private String cidade;

		@JsonProperty("Quantidade")
		private String vencedores;

		@JsonProperty("Latitude")
		private String latitude;

		@JsonProperty("Longitude")
		private String longitude;

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getVencedores() {
			return vencedores;
		}

		public void setVencedores(String vencedores) {
			this.vencedores = vencedores;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

	}

}
