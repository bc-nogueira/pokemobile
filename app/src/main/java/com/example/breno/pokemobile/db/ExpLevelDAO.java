package com.example.breno.pokemobile.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.ExpLevel;

/**
 * Created by Breno on 29/11/2016.
 */

public class ExpLevelDAO {
    private SQLiteDatabase bd;

    public ExpLevelDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public ExpLevel buscarPorLevel(Integer level) {
        Cursor cursor = bd.rawQuery("SELECT * FROM expLevel WHERE level = ?", new String[]{level.toString()});

        ExpLevel expLevel = new ExpLevel();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            expLevel.setLevel(cursor.getInt(0));
            expLevel.setExperiencia(cursor.getInt(1));

            return expLevel;
        } else {
            return null;
        }

    }
}
