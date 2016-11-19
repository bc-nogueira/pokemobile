package com.example.breno.pokemobile.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Ataque;

/**
 * Created by Breno on 18/11/2016.
 */

public class AtaqueDAO {
    private SQLiteDatabase bd;

    public AtaqueDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public Ataque buscarPorId(Integer idAtaque) {
        Cursor cursor = bd.rawQuery("SELECT * FROM ataque WHERE _id = ?", new String[]{idAtaque.toString()});

        Ataque a = new Ataque();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            a.setIdAtaque(cursor.getInt(0));
            a.setNomeAtaque(cursor.getString(1));
            a.setElemento(cursor.getString(2));
            a.setIconeElemento(cursor.getInt(3));
            a.setDanoBase(cursor.getInt(4));

            return a;
        } else {
            return null;
        }

    }
}
