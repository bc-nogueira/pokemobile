package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonAtaque {
    private String numPokemon;
    private Integer idAtaque;
    private Integer lvlAprendido;

    public PokemonAtaque() {
    }

    public PokemonAtaque(String numPokemon, Integer idAtaque, Integer lvlAprendido) {
        this.numPokemon = numPokemon;
        this.idAtaque = idAtaque;
        this.lvlAprendido = lvlAprendido;
    }

    public String getIdPokemon() {
        return numPokemon;
    }

    public void setIdPokemon(String numPokemon) {
        this.numPokemon = numPokemon;
    }

    public Integer getIdAtaque() {
        return idAtaque;
    }

    public void setIdAtaque(Integer idAtaque) {
        this.idAtaque = idAtaque;
    }

    public Integer getLvlAprendido() {
        return lvlAprendido;
    }

    public void setLvlAprendido(Integer lvlAprendido) {
        this.lvlAprendido = lvlAprendido;
    }

}
