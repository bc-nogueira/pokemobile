package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokemonTreinadorDAO {
    private SQLiteDatabase bd;

    public PokemonTreinadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public void inserir(PokemonTreinador pt) {
        ContentValues valores = new ContentValues();
        valores.put("idPokemon", pt.getPokemon().getNumero());
        valores.put("idTreinador", pt.getTreinador().getIdTreinador());
        valores.put("apelido", pt.getApelido());
        valores.put("hp_atual", pt.getHpAtual());
        valores.put("hp_total", pt.getHpTotal());
        valores.put("nivel", pt.getNivel());
        valores.put("experiencia", pt.getExperiencia());

        bd.insertOrThrow("pokemonTreinador", null, valores);
    }

    public ArrayList<PokemonTreinador> buscarPorIdTreinador(Treinador treinador, Context ctx) {
        Cursor cursor = bd.rawQuery("SELECT * FROM pokemonTreinador WHERE idTreinador = ?",
                new String[]{treinador.getIdTreinador().toString()});

        PokemonDAO pokemonDAO = new PokemonDAO(ctx);

        ArrayList<PokemonTreinador> pokemonsTreinador = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                PokemonTreinador pt = new PokemonTreinador();
                pt.setPokemon(pokemonDAO.buscarPorNumero(cursor.getString(0)));
                pt.setTreinador(treinador);
                pt.setApelido(cursor.getString(2));
                pt.setHpAtual(cursor.getDouble(3));
                pt.setHpTotal(cursor.getDouble(4));
                pt.setNivel(cursor.getInt(5));
                pt.setExperiencia(cursor.getDouble(6));

                pokemonsTreinador.add(pt);

            } while(cursor.moveToNext());
        }

        return pokemonsTreinador;
    }

    public void atualizarHpAtual(PokemonTreinador pokemonTreinador) {
        ContentValues valores = new ContentValues();
        valores.put("hp_atual", pokemonTreinador.getHpAtual());

        bd.update("pokemonTreinador", valores, "idPokemon = ? and idTreinador = ?",
                new String[]{pokemonTreinador.getPokemon().getNumero(), pokemonTreinador.getTreinador().getIdTreinador().toString()});
    }

}
