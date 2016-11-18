package com.example.breno.pokemobile.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.TipoPokemon;

import java.util.ArrayList;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokemonDAO {
    private SQLiteDatabase bd;

    public PokemonDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public ArrayList<Pokemon> buscar() {
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        Cursor cursor = bd.rawQuery("SELECT * FROM pokemon", null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Pokemon p = new Pokemon();
                p.setNumero(cursor.getString(0));
                p.setNome(cursor.getString(1));
                p.setTipoComString(cursor.getString(2));
                p.setEstagioEvolucao(cursor.getInt(3));
                p.setHpMinimo(cursor.getInt(4));
                p.setHpMaximo(cursor.getInt(5));
                p.setAltura(cursor.getString(6));
                p.setPeso(cursor.getString(7));
                p.setDescricao(cursor.getString(8));
                p.setIcone(cursor.getInt(9));
                p.setIconeTipo(cursor.getInt(10));
                p.setIconeFrente(cursor.getInt(11));
                p.setIconeCostas(cursor.getInt(12));

                pokemons.add(p);

            } while(cursor.moveToNext());
        }

        return pokemons;

    }

    public Pokemon buscarPorNumero(String numero) {
        Cursor cursor = bd.rawQuery("SELECT * FROM pokemon WHERE numero = ?", new String[]{numero});

        Pokemon p = new Pokemon();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            p.setNumero(cursor.getString(0));
            p.setNome(cursor.getString(1));
            p.setTipoComString(cursor.getString(2));
            p.setEstagioEvolucao(cursor.getInt(3));
            p.setHpMinimo(cursor.getInt(4));
            p.setHpMaximo(cursor.getInt(5));
            p.setAltura(cursor.getString(6));
            p.setPeso(cursor.getString(7));
            p.setDescricao(cursor.getString(8));
            p.setIcone(cursor.getInt(9));
            p.setIconeTipo(cursor.getInt(10));
            p.setIconeFrente(cursor.getInt(11));
            p.setIconeCostas(cursor.getInt(12));

            return p;
        } else {
            return null;
        }
    }
}
