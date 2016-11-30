package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokemonTreinador implements Serializable {
    private Integer idPokemonTreinador;
    private Pokemon pokemon;
    private Treinador treinador;
    private String apelido;
    private Integer hpTotal;
    private Integer hpAtual;
    private Integer level;
    private Integer experiencia;
    private Integer posFila;
    private Ataque ataque1;
    private Ataque ataque2;

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

    public Integer getHpTotal() {
        return hpTotal;
    }

    public void setHpTotal(Integer hpTotal) {
        this.hpTotal = hpTotal;
    }

    public Integer getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(Integer hpAtual) {
        this.hpAtual = hpAtual;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public Integer getPosFila() {
        return posFila;
    }

    public void setPosFila(Integer posFila) {
        this.posFila = posFila;
    }

    public Ataque getAtaque1() {
        return ataque1;
    }

    public void setAtaque1(Ataque ataque1) {
        this.ataque1 = ataque1;
    }

    public Ataque getAtaque2() {
        return ataque2;
    }

    public void setAtaque2(Ataque ataque2) {
        this.ataque2 = ataque2;
    }

}
