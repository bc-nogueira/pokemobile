package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Breno on 02/11/2016.
 */

public class JogadorDAO {
    private SQLiteDatabase bd;

    public JogadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public void inserir(Jogador jogador) {
        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.getNome());
        valores.put("email", jogador.getEmail());
        valores.put("senha", jogador.getSenha());
        valores.put("dinheiro", jogador.getDinheiro());

        bd.insert("jogador", null, valores);
    }

    public void atualizar(Jogador jogador) {
        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.getNome());
        valores.put("email", jogador.getEmail());
        valores.put("dinheiro", jogador.getDinheiro());

        bd.update("jogador", valores, "_id = ?", new String[]{"" + jogador.getId()});
    }

    public void deletar(Jogador jogador) {
        bd.delete("jogador", "_id = " + jogador.getId(), null);
    }

    public List<Jogador> buscar() {
        List<Jogador> jogadores = new ArrayList<Jogador>();

        String[] colunas = new String[]{"_id", "nome", "email", "dinheiro"}; //Colunas que vão ser retornadas
        Cursor cursor = bd.query("jogador", colunas, null, null, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Jogador j = new Jogador();
                j.setId(cursor.getInt(0));
                j.setNome(cursor.getString(1));
                j.setEmail(cursor.getString(2));
                j.setDinheiro(cursor.getInt(3));

                jogadores.add(j);

            } while(cursor.moveToNext());
        }

        return jogadores;
    }
}
