package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.breno.pokemobile.modelo.Item;

import java.util.ArrayList;

/**
 * Created by Breno on 06/11/2016.
 */

public class ItemDAO {
    private SQLiteDatabase bd;

    public ItemDAO(Context ctx) {
        BDCore auxBD = new BDCore(ctx);
        bd = auxBD.getWritableDatabase();
    }

    public long inserir(Item item) throws SQLException {
        ContentValues valores = new ContentValues();
        valores.put("nome", item.getNome());
        valores.put("descricao", item.getDescricao());
        valores.put("tipo", item.getTipo().toString());
        valores.put("preco", item.getPreco());
        valores.put("efeito_captura", item.getEfeitoCaptura());
        valores.put("efeito_cura", item.getEfeitoCura());
        valores.put("icone", item.getIcone());

        return bd.insertOrThrow("item", null, valores);
    }

    public ArrayList<Item> buscar() {
        ArrayList<Item> itens = new ArrayList<Item>();

        Cursor cursor = bd.rawQuery("SELECT * FROM item", null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Item i = new Item();
                i.setIdItem(cursor.getLong(0));
                i.setNome(cursor.getString(1));
                i.setDescricao(cursor.getString(2));
                i.setTipoComString(cursor.getString(3));
                i.setPreco(cursor.getInt(4));
                i.setEfeitoCaptura(cursor.getDouble(5));
                i.setEfeitoCura(cursor.getInt(6));
                i.setIcone(cursor.getInt(7));

                itens.add(i);

            } while(cursor.moveToNext());
        }

        return itens;
    }

    public Item buscarPorId(Long idItem) {
        Cursor cursor = bd.rawQuery("SELECT * FROM item WHERE _id = ?", new String[]{idItem.toString()});

        Item i = new Item();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            i.setIdItem(cursor.getLong(0));
            i.setNome(cursor.getString(1));
            i.setDescricao(cursor.getString(2));
            i.setTipoComString(cursor.getString(3));
            i.setPreco(cursor.getInt(4));
            i.setEfeitoCaptura(cursor.getDouble(5));
            i.setEfeitoCura(cursor.getInt(6));
            i.setIcone(cursor.getInt(7));

            return i;
        } else {
            return null;
        }

    }

}
