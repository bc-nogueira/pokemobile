package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

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
        valores.put("batalhas_treinador", treinador.getBatalhasTreinador());
        valores.put("batalhas_treinador_vencidas", treinador.getBatalhasTreinadorVencidas());
        valores.put("batalhas_selvagem", treinador.getBatalhasSelvagem());
        valores.put("batalhas_selvagem_vencidas", treinador.getBatalhasSelvagemVencidas());

        return bd.insertOrThrow("treinador", null, valores);
    }

    public ArrayList<Treinador> buscarPorIdJogador(Long idJogador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM treinador WHERE id_jogador = ?", new String[]{idJogador.toString()});

        ArrayList<Treinador> treinadores = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Treinador t = new Treinador();
                t.setIdTreinador(cursor.getLong(0));
                t.setNome(cursor.getString(1));
                t.setDinheiro(cursor.getInt(2));
                t.setIdAvatar(cursor.getInt(3));
                t.setIdJogador(cursor.getLong(4));
                t.setBatalhasTreinador(cursor.getInt(5));
                t.setBatalhasTreinadorVencidas(cursor.getInt(6));
                t.setBatalhasSelvagem(cursor.getInt(7));
                t.setBatalhasSelvagemVencidas(cursor.getInt(8));

                treinadores.add(t);

            } while(cursor.moveToNext());
        }

        return treinadores;

    }

    public Treinador buscarPorId(Long idTreinador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM treinador WHERE _id = ?", new String[]{idTreinador.toString()});

        Treinador t = new Treinador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            t.setIdTreinador(cursor.getLong(0));
            t.setNome(cursor.getString(1));
            t.setDinheiro(cursor.getInt(2));
            t.setIdAvatar(cursor.getInt(3));
            t.setIdJogador(cursor.getLong(4));
            t.setBatalhasTreinador(cursor.getInt(5));
            t.setBatalhasTreinadorVencidas(cursor.getInt(6));
            t.setBatalhasSelvagem(cursor.getInt(7));
            t.setBatalhasSelvagemVencidas(cursor.getInt(8));

            return t;
        } else {
            return null;
        }
    }

    public void excluir(Long idTreinador) {
        bd.delete("treinador", "_id = " + idTreinador, null);
    }

    public void atualizarDinheiro(Treinador treinador) {
        ContentValues valores = new ContentValues();
        valores.put("dinheiro", treinador.getDinheiro());

        bd.update("treinador", valores, "_id = ?", new String[]{"" + treinador.getIdTreinador()});
    }

}
