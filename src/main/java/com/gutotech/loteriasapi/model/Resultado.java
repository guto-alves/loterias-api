package com.gutotech.loteriasapi.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document("resultados")
@JsonPropertyOrder({"loteria", "concurso", "data", "local", "concursoEspecial", "dezenasOrdemSorteio", "dezenas",
        "trevos", "timeCoracao", "mesSorte", "premiacoes", "municipiosUFGanhadores",
        "estadosPremiados", "observacao", "acumulou", "proximoConcurso", "dataProximoConcurso"})
public class Resultado {
    @Id
    private ResultadoId id = new ResultadoId();
    private String data;
    private String local;

    private boolean concursoEspecial;

    private List<String> dezenasOrdemSorteio = new ArrayList<>();
    private List<String> dezenas = new ArrayList<>();
    private List<String> trevos = new ArrayList<>(); // somente +MILIONARIA
    private String timeCoracao; // somente TIMEMANIA
    private String mesSorte; // somente DIA DE SORTE

    private List<Premiacao> premiacoes = new ArrayList<>();
    private List<MunicipioUFGanhadores> localGanhadores = new ArrayList<>();
    private List<Estado> estadosPremiados = new ArrayList<>();

    private String observacao;

    private boolean acumulou;
    private String dataProximoConcurso;

    private double valorArrecadado;
    private double valorAcumuladoConcurso_0_5;
    private double valorAcumuladoConcursoEspecial;
    private double valorAcumuladoProximoConcurso;
    private double valorEstimadoProximoConcurso;

    public Resultado() {
    }

    public Resultado(ResultadoId id) {
        this.id = id;
    }

    public String getLoteria() {
        return id.getLoteria();
    }

    public int getConcurso() {
        return id.getConcurso();
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

    public boolean isConcursoEspecial() {
        return concursoEspecial;
    }

    public void setConcursoEspecial(boolean isConcursoEspecial) {
        this.concursoEspecial = isConcursoEspecial;
    }

    public List<String> getDezenas() {
        if (dezenas.size() == 12 && Loteria.DUPLA_SENA.toString().equals(getLoteria())) {
            List<String> s1 = new ArrayList<>(dezenas.subList(0, 6));
            List<String> s2 = new ArrayList<>(dezenas.subList(6, 12));
            s1.sort(Comparator.naturalOrder());
            s2.sort(Comparator.naturalOrder());
            dezenas.clear();
            dezenas.addAll(s1);
            dezenas.addAll(s2);
        } else if (!Loteria.DUPLA_SENA.toString().equals(getLoteria())
                && !Loteria.SUPER_SETE.toString().equals(getLoteria())
                && !Loteria.FEDERAL.toString().equals(getLoteria())) {
            dezenas.sort(Comparator.naturalOrder());
        }

        return dezenas;
    }

    public void setDezenas(List<String> dezenas) {
        this.dezenas = dezenas;
    }

    public List<String> getDezenasOrdemSorteio() {
        return dezenasOrdemSorteio;
    }

    public void setDezenasOrdemSorteio(List<String> dezenasSorteadasOrdemSorteio) {
        this.dezenasOrdemSorteio = dezenasSorteadasOrdemSorteio;
    }

    public List<String> getTrevos() {
        return trevos;
    }

    public void setTrevos(List<String> trevosSorteados) {
        this.trevos = trevosSorteados;
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

    public List<MunicipioUFGanhadores> getLocalGanhadores() {
        return localGanhadores;
    }

    public void setLocalGanhadores(List<MunicipioUFGanhadores> municipiosUFGanhadores) {
        this.localGanhadores = municipiosUFGanhadores;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isAcumulou() {
        return acumulou;
    }

    public void setAcumulou(boolean acumulado) {
        this.acumulou = acumulado;
    }

    public String getDataProximoConcurso() {
        return dataProximoConcurso;
    }

    public void setDataProximoConcurso(String dataProxConcurso) {
        this.dataProximoConcurso = dataProxConcurso;
    }

    public int getProximoConcurso() {
        return id.getConcurso() + 1;
    }

    public double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public double getValorAcumuladoConcurso_0_5() {
        return valorAcumuladoConcurso_0_5;
    }

    public void setValorAcumuladoConcurso_0_5(double valorAcumuladoConcurso_0_5) {
        this.valorAcumuladoConcurso_0_5 = valorAcumuladoConcurso_0_5;
    }

    public double getValorAcumuladoConcursoEspecial() {
        return valorAcumuladoConcursoEspecial;
    }

    public void setValorAcumuladoConcursoEspecial(double valorAcumuladoConcursoEspecial) {
        this.valorAcumuladoConcursoEspecial = valorAcumuladoConcursoEspecial;
    }

    public double getValorAcumuladoProximoConcurso() {
        return valorAcumuladoProximoConcurso;
    }

    public void setValorAcumuladoProximoConcurso(double valorAcumuladoProximoConcurso) {
        this.valorAcumuladoProximoConcurso = valorAcumuladoProximoConcurso;
    }

    public double getValorEstimadoProximoConcurso() {
        return valorEstimadoProximoConcurso;
    }

    public void setValorEstimadoProximoConcurso(double valorEstimadoProximoConcurso) {
        this.valorEstimadoProximoConcurso = valorEstimadoProximoConcurso;
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
