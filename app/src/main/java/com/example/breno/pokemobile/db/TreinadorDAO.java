package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Breno on 03/11/2016.
 */

public class TreinadorDAO {
    private SQLiteDatabase bd;

    public TreinadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(Treinador treinador) throws SQLException {
        ContentValues valores = new ContentValues();
        valores.put("nome", treinador.getNome());
        valores.put("dinheiro", treinador.getDinheiro());
        valores.put("id_avatar", treinador.getIdAvatar());
        valores.put("id_jogador", treinador.getIdJogador());

        return bd.insertOrThrow("treinador", null, valores);
    }

    public List<Treinador> buscarPorIdJogador(Long idJogador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM treinador WHERE id_jogador = ?", new String[]{idJogador.toString()});

        List<Treinador> treinadores = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Treinador t = new Treinador();
                t.setIdTreinador(cursor.getLong(0));
                t.setNome(cursor.getString(1));
                t.setDinheiro(cursor.getInt(2));
                t.setIdAvatar(cursor.getInt(3));
                t.setIdJogador(cursor.getLong(4));

                treinadores.add(t);

            } while(cursor.moveToNext());
        }

        return treinadores;

    }
}
