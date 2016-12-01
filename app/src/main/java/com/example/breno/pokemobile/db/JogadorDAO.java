package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Jogador;

import java.util.ArrayList;

/**
 * Created by Breno on 02/11/2016.
 */

public class JogadorDAO {
    private SQLiteDatabase bd;

    public JogadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(Jogador jogador) throws SQLException {
        ContentValues valores = new ContentValues();
        valores.put("usuario", jogador.getUsuario());
        valores.put("senha", jogador.getSenha());

        return bd.insertOrThrow("jogador", null, valores);
    }

    public void atualizar(Jogador jogador) {
        ContentValues valores = new ContentValues();
        valores.put("usuario", jogador.getUsuario());

        bd.update("jogador", valores, "_id = ?", new String[]{"" + jogador.getIdJogador()});
    }

    public void atualizarSenha(Jogador jogador) {
        ContentValues valores = new ContentValues();
        valores.put("senha", jogador.getSenha());

        bd.update("jogador", valores, "_id = ?", new String[]{"" + jogador.getIdJogador()});
    }

    public void deletar(Jogador jogador) {
        bd.delete("jogador", "_id = " + jogador.getIdJogador(), null);
    }

    public ArrayList<Jogador> buscar() {
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

        Cursor cursor = bd.rawQuery("SELECT * FROM jogador", null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Jogador j = new Jogador();
                j.setIdJogador(cursor.getLong(0));
                j.setUsuario(cursor.getString(1));

                jogadores.add(j);

            } while(cursor.moveToNext());
        }

        return jogadores;
    }

    public Jogador buscarPorId(Long idJogador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM jogador WHERE _id = ?", new String[]{idJogador.toString()});

        Jogador j = new Jogador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            j.setIdJogador(cursor.getLong(0));
            j.setUsuario(cursor.getString(1));
            j.setSenha(cursor.getString(2));

            return j;
        } else {
            return null;
        }

    }

    public Jogador buscarPorUsuario(String usuario) {
        Cursor cursor = bd.rawQuery("SELECT * FROM jogador WHERE usuario = ?", new String[]{usuario});

        Jogador j = new Jogador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            j.setIdJogador(cursor.getLong(0));
            j.setUsuario(cursor.getString(1));
            j.setSenha(cursor.getString(2));

            return j;
        } else {
            return null;
        }

    }
}
