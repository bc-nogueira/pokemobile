package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Conquista;
import com.example.breno.pokemobile.modelo.ConquistaTreinador;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

/**
 * Created by Breno on 01/12/2016.
 */

public class ConquistaTreinadorDAO {
    private SQLiteDatabase bd;

    public ConquistaTreinadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public void inserir(ConquistaTreinador ct) {
        ContentValues valores = new ContentValues();
        valores.put("idConquista", ct.getConquista().getIdConquista());
        valores.put("idTreinador", ct.getTreinador().getIdTreinador());

        bd.insertOrThrow("conquistaTreinador", null, valores);
    }

    public ConquistaTreinador buscarPorIdConquistaEIdTreinador(Conquista conquista, Treinador treinador, Context ctx) {
        Cursor cursor = bd.rawQuery("SELECT * FROM conquistaTreinador WHERE idConquista = ? and idTreinador = ?",
                new String[]{conquista.getIdConquista().toString(), treinador.getIdTreinador().toString()});

        ConquistaTreinador ct = new ConquistaTreinador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            ConquistaDAO conquistaDAO = new ConquistaDAO(ctx);
            TreinadorDAO treinadorDAO = new TreinadorDAO(ctx);

            ct.setConquista(conquistaDAO.buscarPorId(cursor.getLong(0)));
            ct.setTreinador(treinadorDAO.buscarPorId(cursor.getLong(1)));

            return ct;
        } else {
            return null;
        }

    }
}
