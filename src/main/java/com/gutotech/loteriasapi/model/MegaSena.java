package com.gutotech.loteriasapi.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("mega_sena")
public class MegaSena {
	@Id
	private int concurso;
	private String local;
	private String data;
	private List<String> dezenas = new ArrayList<>();
	private List<Premiacao> premiacoes = new ArrayList<>();

	private String cidadeVencedor = ""; // TODO: This field is not being filled yet

	private String valorArrecadado;
	private String valorEstimado;
	private String valorAcumulado;
	private String acumulado; // TODO: Stores "NÃO" ou "NÃO", switch it to boolean variable
	private String sorteioEspecial; // TODO: Stores "NÃO" ou "NÃO", switch it to boolean variable
	private String observacoes;

	public MegaSena() {
		premiacoes.add(new Premiacao("Sena", 6));
		premiacoes.add(new Premiacao("Quina", 5));
		premiacoes.add(new Premiacao("Quadra", 4));
	}

	public static Field[] getFields() {
		Field[] fields = MegaSena.class.getDeclaredFields();

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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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

	public String getCidadeVencedor() {
		return cidadeVencedor;
	}

	public void setCidadeVencedor(String cidadeVencedor) {
		this.cidadeVencedor = cidadeVencedor;
	}

	public String getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(String valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public String getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(String valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	public String getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(String valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}

	public String getSorteioEspecial() {
		return sorteioEspecial;
	}

	public void setSorteioEspecial(String sorteioEspecial) {
		this.sorteioEspecial = sorteioEspecial;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
