package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.ItemTreinadorDAO;
import com.example.breno.pokemobile.modelo.ItemTreinador;

/**
 * Created by Breno on 29/11/2016.
 */

public class ItemTreinadorService {

    public void diminuirItem(ItemTreinador itemTreinador, Context ctx) {

        itemTreinador.setQuantidade(itemTreinador.getQuantidade() - 1);
        ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(ctx);
        if(itemTreinador.getQuantidade() >= 1) {
            //Atualizar
            itemTreinadorDAO.atualizarQuantidade(itemTreinador);
        } else {
            //Remove
            itemTreinadorDAO.deletar(itemTreinador.getIdItemTreinador());
        }

    }

}
