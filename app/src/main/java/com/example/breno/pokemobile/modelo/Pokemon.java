package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 14/11/2016.
 */

public class Pokemon implements Serializable {
    private String numero;
    private String nome;
    private TipoPokemon tipo;
    private Integer estagioEvolucao;
    private Integer hpMinimo;
    private Integer hpMaximo;
    private String altura;
    private String peso;
    private String descricao;
    private Integer icone;
    private Integer iconeTipo;

    public Pokemon() {}

    public Pokemon(String numero, String nome, TipoPokemon tipo, Integer estagioEvolucao, Integer hpMinimo, Integer hpMaximo,
                   String altura, String peso, String descricao, Integer icone, Integer iconeTipo) {
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
        this.estagioEvolucao = estagioEvolucao;
        this.hpMinimo = hpMinimo;
        this.hpMaximo = hpMaximo;
        this.altura = altura;
        this.peso = peso;
        this.descricao = descricao;
        this.icone = icone;
        this.iconeTipo = iconeTipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public String getTipoParaDemonstrar() {
        switch (getTipo()) {
            case AGUA:
                return "Água";
            case ELETRICO:
                return "Elétrico";
            case FOGO:
                return "Fogo";
            case GRAMA:
                return "Grama";
            default:
                return "";
        }
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
    }

    public void setTipoComString(String tipo) {
        switch (tipo) {
            case "AGUA":
                this.tipo = TipoPokemon.AGUA;
                break;
            case "ELETRICO":
                this.tipo = TipoPokemon.ELETRICO;
                break;
            case "FOGO":
                this.tipo = TipoPokemon.FOGO;
                break;
            case "GRAMA":
                this.tipo = TipoPokemon.GRAMA;
                break;
        }
    }

    public Integer getEstagioEvolucao() {
        return estagioEvolucao;
    }

    public void setEstagioEvolucao(Integer estagioEvolucao) {
        this.estagioEvolucao = estagioEvolucao;
    }

    /*
    Tabela de intervalo sendo usados:
    HP 35 -> 10~15
    HP 40 -> 15~20
    HP 45 -> 20~25
     */

    public Integer getHpMinimo() {
        return hpMinimo;
    }

    public void setHpMinimo(Integer hpMinimo) {
        this.hpMinimo = hpMinimo;
    }

    public Integer getHpMaximo() {
        return hpMaximo;
    }

    public void setHpMaximo(Integer hpMaximo) {
        this.hpMaximo = hpMaximo;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

    public Integer getIconeTipo() {
        return iconeTipo;
    }

    public void setIconeTipo(Integer iconeTipo) {
        this.iconeTipo = iconeTipo;
    }
}