package com.example.domingas.consultoriomedico.Modelo;

public class Consulta {
    private String Uid;
    private String nome;
    private String dor;
    private String dores;
    private String febre;
    private String cansaco;
    private String coluna;
    private String duracao;


    public Consulta() {
    }

    public String getCansaco() {
        return cansaco;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        duracao = duracao;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDor() {
        return this.dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getDores() {
        return this.dores;
    }

    public void setDores(String dores) {
        this.dores = dores;
    }

    public String getFebre() {
        return febre;
    }

    public void setFebre(String febre) {
        this.febre = febre;
    }



    public void setCansaco(String cansaco) {
        this.cansaco = cansaco;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        coluna = coluna;
    }

    @Override
    public String toString() {
        return nome;
    }
}
