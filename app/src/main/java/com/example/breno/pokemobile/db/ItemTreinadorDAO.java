package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 13/11/2016.
 */

public class ItemTreinadorDAO {
    private SQLiteDatabase bd;

    public ItemTreinadorDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(Item item, Treinador treinador, Integer quantidade) throws SQLException {
        ContentValues valores = new ContentValues();
        valores.put("idItem", item.getIdItem());
        valores.put("idTreinador", treinador.getIdTreinador());
        valores.put("quantidade", quantidade);

        return bd.insertOrThrow("itemTreinador", null, valores);
    }

    public ArrayList<ItemTreinador> listaIdsItensPorIdTreinador(Treinador treinador, Context ctx) {
        Cursor cursor = bd.rawQuery("SELECT * FROM itemTreinador WHERE idTreinador = ?",
                new String[]{treinador.getIdTreinador().toString()});

        ItemDAO itemDAO = new ItemDAO(ctx);

        ArrayList<ItemTreinador> itensJogador = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                ItemTreinador it = new ItemTreinador();
                it.setIdItemTreinador(cursor.getLong(0));
                it.setItem(itemDAO.buscarPorId(cursor.getLong(1)));
                it.setTreinador(treinador);
                it.setQuantidade(cursor.getInt(3));

                itensJogador.add(it);

            } while(cursor.moveToNext());
        }

        return itensJogador;
    }

    public ItemTreinador buscaPorIdItemIdTreinador(Item item, Treinador treinador) {
        Cursor cursor = bd.rawQuery("SELECT * FROM itemTreinador WHERE idItem = ? AND idTreinador = ?",
                new String[]{item.getIdItem().toString(), treinador.getIdTreinador().toString()});

        ItemTreinador it = new ItemTreinador();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            it.setIdItemTreinador(cursor.getLong(0));
            it.setItem(item);
            it.setTreinador(treinador);
            it.setQuantidade(cursor.getInt(3));

            return it;

        } else {
            return null;
        }

    }

    public void atualizarQuantidade(ItemTreinador itemTreinador) {
        ContentValues valores = new ContentValues();
        valores.put("quantidade", itemTreinador.getQuantidade());

        bd.update("itemTreinador", valores, "_id = ?", new String[]{"" + itemTreinador.getIdItemTreinador()});

    }

    public void deletar(Long idItemTreinador) {
        bd.delete("itemTreinador", "_id = " + idItemTreinador, null);
    }

}
