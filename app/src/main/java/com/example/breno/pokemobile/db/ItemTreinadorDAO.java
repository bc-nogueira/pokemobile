package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.Treinador;

/**
 * Created by Breno on 13/11/2016.
 */

public class ItemTreinadorDAO {
    private SQLiteDatabase bd;

    public ItemTreinadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(Item item, Treinador treinador, Integer quantidade) throws SQLException {
        ContentValues valores = new ContentValues();
        valores.put("idItem", item.getIdItem());
        valores.put("idTreinador", treinador.getIdTreinador());
        valores.put("quantidade", quantidade);

        return bd.insertOrThrow("itemTreinador", null, valores);
    }
}
