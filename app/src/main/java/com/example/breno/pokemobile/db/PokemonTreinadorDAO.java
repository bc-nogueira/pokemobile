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
        valores.put("nivel", pt.getLevel());
        valores.put("experiencia", pt.getExperiencia());
        valores.put("pos_fila", pt.getPosFila());

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
                pt.setIdPokemonTreinador(cursor.getInt(0));
                pt.setPokemon(pokemonDAO.buscarPorNumero(cursor.getString(1)));
                pt.setTreinador(treinador);
                pt.setApelido(cursor.getString(3));
                pt.setHpAtual(cursor.getDouble(4));
                pt.setHpTotal(cursor.getDouble(5));
                pt.setLevel(cursor.getInt(6));
                pt.setExperiencia(cursor.getDouble(7));
                pt.setPosFila(cursor.getInt(8));

                pokemonsTreinador.add(pt);

            } while(cursor.moveToNext());
        }

        return pokemonsTreinador;
    }

    public boolean verificarSeTodosEstaoMortos(Treinador treinador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM pokemonTreinador WHERE idTreinador = ? and pos_fila not null and hp_atual > 0",
                new String[]{treinador.getIdTreinador().toString()});

        if(cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public PokemonTreinador buscarPrimeiroNaFilaPorId(Treinador treinador, Context ctx) {
        Cursor cursor = bd.rawQuery("SELECT * FROM pokemonTreinador WHERE idTreinador = ? and pos_fila not null",
                new String[]{treinador.getIdTreinador().toString()});

        PokemonDAO pokemonDAO = new PokemonDAO(ctx);
        PokemonTreinador pt = new PokemonTreinador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                pt.setIdPokemonTreinador(cursor.getInt(0));
                pt.setPokemon(pokemonDAO.buscarPorNumero(cursor.getString(1)));
                pt.setTreinador(treinador);
                pt.setApelido(cursor.getString(3));
                pt.setHpAtual(cursor.getDouble(4));
                pt.setHpTotal(cursor.getDouble(5));
                pt.setLevel(cursor.getInt(6));
                pt.setExperiencia(cursor.getDouble(7));
                pt.setPosFila(cursor.getInt(8));

                if(pt.getHpAtual() != 0) {
                    return pt;
                }

            } while(cursor.moveToNext());
        }
        return null;

    }

    public void atualizarHpAtual(PokemonTreinador pokemonTreinador) {
        ContentValues valores = new ContentValues();
        valores.put("hp_atual", pokemonTreinador.getHpAtual());

        bd.update("pokemonTreinador", valores, "idPokemonTreinador = ?",
                new String[]{pokemonTreinador.getIdPokemonTreinador().toString()});
    }

}
