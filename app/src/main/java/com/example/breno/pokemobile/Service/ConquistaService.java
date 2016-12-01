package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.ConquistaDAO;
import com.example.breno.pokemobile.db.ConquistaTreinadorDAO;
import com.example.breno.pokemobile.modelo.Conquista;
import com.example.breno.pokemobile.modelo.ConquistaTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 01/12/2016.
 */

public class ConquistaService {
    private ConquistaTreinadorService conquistaTreinadorService = new ConquistaTreinadorService();

    public ConquistaTreinador alcancouConquistaQuantPokemon(Treinador treinador, Context ctx) {

        ConquistaDAO conquistaDAO = new ConquistaDAO(ctx);
        ConquistaTreinadorDAO conquistaTreinadorDAO = new ConquistaTreinadorDAO(ctx);
        ArrayList<Conquista> conquistas = conquistaDAO.buscarPorCampo("pokemons");

        for(Conquista c : conquistas) {

            if(!conquistaTreinadorService.possuiConquista(c, treinador, ctx)) {

                ConquistaTreinador ct = new ConquistaTreinador();
                ct.setConquista(c);
                ct.setTreinador(treinador);

                conquistaTreinadorDAO.inserir(ct);

                return ct;
            }

        }

        return null;

    }

}
