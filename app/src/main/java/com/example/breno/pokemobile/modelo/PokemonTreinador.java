package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokemonTreinador {
    private Pokemon pokemon;
    private Treinador treinador;
    private String apelido;
    private Double hpTotal;
    private Double hpAtual;
    private Integer nivel;
    private Double experiencia;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Double getHpTotal() {
        return hpTotal;
    }

    public void setHpTotal(Double hpTotal) {
        this.hpTotal = hpTotal;
    }

    public Double getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(Double hpAtual) {
        this.hpAtual = hpAtual;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Double experiencia) {
        this.experiencia = experiencia;
    }

}
