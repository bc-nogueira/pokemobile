package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Conquista;
import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 01/12/2016.
 */

public class ConquistaDAO {
    private SQLiteDatabase bd;

    public ConquistaDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public ArrayList<Conquista> buscarPorCampo(String campo) {
        ArrayList<Conquista> conquistas = new ArrayList<Conquista>();

        Cursor cursor = bd.rawQuery("SELECT * FROM conquista WHERE campo = ?",  new String[]{campo});

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Conquista c = new Conquista();
                c.setIdConquista(cursor.getLong(0));
                c.setDescricao(cursor.getString(1));
                c.setMensagem(cursor.getString(2));
                c.setCampo(cursor.getString(3));
                c.setObjetivo(cursor.getInt(4));

                conquistas.add(c);

            } while(cursor.moveToNext());
        }

        return conquistas;
    }

    public Conquista buscarPorId(Long idConquista) {
        Cursor cursor = bd.rawQuery("SELECT * FROM conquista WHERE idConquista = ?", new String[]{idConquista.toString()});

        Conquista c = new Conquista();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            c.setIdConquista(cursor.getLong(0));
            c.setDescricao(cursor.getString(1));
            c.setMensagem(cursor.getString(2));
            c.setCampo(cursor.getString(3));
            c.setObjetivo(cursor.getInt(4));

            return c;
        } else {
            return null;
        }
    }
}
