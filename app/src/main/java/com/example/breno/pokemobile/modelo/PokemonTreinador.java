package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokemonTreinador {
    private Integer idPokemonTreinador;
    private Pokemon pokemon;
    private Treinador treinador;
    private String apelido;
    private Double hpTotal;
    private Double hpAtual;
    private Integer level;
    private Double experiencia;
    private Integer posFila;

    public Integer getIdPokemonTreinador() {
        return idPokemonTreinador;
    }

    public void setIdPokemonTreinador(Integer idPokemonTreinador) {
        this.idPokemonTreinador = idPokemonTreinador;
    }

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Double experiencia) {
        this.experiencia = experiencia;
    }

    public Integer getPosFila() {
        return posFila;
    }

    public void setPosFila(Integer posFila) {
        this.posFila = posFila;
    }

}
