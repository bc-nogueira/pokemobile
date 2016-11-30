package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.ExpLevelDAO;
import com.example.breno.pokemobile.modelo.ExpLevel;
import com.example.breno.pokemobile.modelo.PokemonTreinador;

/**
 * Created by Breno on 30/11/2016.
 */

public class ExpLevelService {

    public Integer buscarExpRequeridaLvl(PokemonTreinador pokemon, Context ctx) {
        ExpLevelDAO expLevelDAO = new ExpLevelDAO(ctx);
        ExpLevel expLevel = expLevelDAO.buscarPorLevel(pokemon.getLevel());
        return expLevel.getExperiencia();
    }

}
