package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonTreinadorAtaque {
    private PokemonTreinador pokemonTreinador;
    private Ataque ataque;

    public PokemonTreinador getPokemonTreinador() {
        return pokemonTreinador;
    }

    public void setPokemonTreinador(PokemonTreinador pokemonTreinador) {
        this.pokemonTreinador = pokemonTreinador;
    }

    public Ataque getAtaque() {
        return ataque;
    }

    public void setAtaque(Ataque ataque) {
        this.ataque = ataque;
    }

}
