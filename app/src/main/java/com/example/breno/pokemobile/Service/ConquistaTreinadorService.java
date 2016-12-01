package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.ConquistaTreinadorDAO;
import com.example.breno.pokemobile.modelo.Conquista;
import com.example.breno.pokemobile.modelo.ConquistaTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 01/12/2016.
 */

public class ConquistaTreinadorService {

    public boolean possuiConquista(Conquista conquista, Treinador treinador, Context ctx) {

        ConquistaTreinadorDAO conquistaTreinadorDAO = new ConquistaTreinadorDAO(ctx);

        ConquistaTreinador c = conquistaTreinadorDAO.
                buscarPorIdConquistaEIdTreinador(conquista, treinador, ctx);

        if(c == null) {
            return false;
        } else {
            return true;
        }

    }

}
