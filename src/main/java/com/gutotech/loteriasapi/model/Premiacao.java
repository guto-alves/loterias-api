package com.gutotech.loteriasapi.model;

public class Premiacao {
    private String descricao;
    private int faixa;
    private int ganhadores;
    private double valorPremio;

    public Premiacao() {
    }

    public Premiacao(String acertos, int vencedores, double premio) {
        this.descricao = acertos;
        this.ganhadores = vencedores;
        this.valorPremio = premio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String acertos) {
        this.descricao = acertos;
    }

    public int getFaixa() {
        return faixa;
    }

    public void setFaixa(int faixa) {
        this.faixa = faixa;
    }

    public int getGanhadores() {
        return ganhadores;
    }

    public void setGanhadores(int vencedores) {
        this.ganhadores = vencedores;
    }

    public double getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(double premio) {
        this.valorPremio = premio;
    }

}
