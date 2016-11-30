package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonAtaque;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonAtaqueDAO {
    private SQLiteDatabase bd;

    public PokemonAtaqueDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(PokemonAtaque pokemonAtaque) {
        ContentValues valores = new ContentValues();
        valores.put("idPokemon", pokemonAtaque.getIdPokemon());
        valores.put("idAtaque", pokemonAtaque.getIdAtaque());
        valores.put("lvl_aprendido", pokemonAtaque.getLvlAprendido());

        return bd.insertOrThrow("pokemonAtaque", null, valores);
    }

    public PokemonAtaque buscarAtaquesPorPokemonLevel(PokemonTreinador pokemonTreinador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM pokemonAtaque WHERE idPokemon = ? and lvl_aprendido <= ?",
                new String[]{pokemonTreinador.getPokemon().getNumero(), pokemonTreinador.getLevel().toString()});

//        ArrayList<PokemonAtaque> ataques = new ArrayList<>();
        PokemonAtaque pa = new PokemonAtaque();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

//            do {
//                PokemonAtaque pa = new PokemonAtaque();
                pa.setIdPokemon(cursor.getString(0));
                pa.setIdAtaque(cursor.getInt(1));
                pa.setLvlAprendido(cursor.getInt(2));

//                ataques.add(pa);

//            } while(cursor.moveToNext());
        }

        return pa;

    }

}
