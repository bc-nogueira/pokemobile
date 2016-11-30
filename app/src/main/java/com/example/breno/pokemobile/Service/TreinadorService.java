package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Treinador;

/**
 * Created by Breno on 30/11/2016.
 */

public class TreinadorService {

    public Integer quantosPokemonsPossui(Treinador treinador, Context ctx) {
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(ctx);
        return pokemonTreinadorDAO.buscarPorIdTreinador(treinador, ctx).size();
    }

}
