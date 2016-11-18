package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonTreinadorService {

    public boolean verificaSeTodosEstaoMortos(Treinador treinador, Context ctx) {

        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(ctx);
        if(pokemonTreinadorDAO.verificarSeTodosEstaoMortos(treinador)) {
            return true;
        } else {
            return false;
        }

    }

    public String apelidoOuNome(PokemonTreinador pokemonTreinador) {

        if(pokemonTreinador.getApelido() != null) {
            return pokemonTreinador.getApelido();
        } else {
            return pokemonTreinador.getPokemon().getNome();
        }

    }

}
