package com.example.breno.pokemobile.db;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.TipoItem;

/**
 * Created by Breno on 02/11/2016.
 */

public class BDCore extends SQLiteOpenHelper {

    //Informações do Banco de Dados
    private static final String NOME_BD = "pokemon.db";
    private static final int VERSAO_BD = 1;

    //Atributos da tabela Jogador
    private static final String TABELA_JOGADOR = "jogador";
    private static final String ID_JOGADOR = "_id";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    //Atributos da tabela Treinador
    private static final String TABELA_TREINADOR = "treinador";
    private static final String ID_TREINADOR = "_id";
    private static final String NOME = "nome";
    private static final String DINHEIRO = "dinheiro";
    private static final String ID_AVATAR = "id_avatar";
    private static final String ID_JOGADOR_FK = "id_jogador";

    //Atributos da tabela Item
    private static final String TABELA_ITEM = "item";
    private static final String ID_ITEM = "_id";
    private static final String NOME_ITEM = "nome";
    private static final String DESCRICAO = "descricao";
    private static final String TIPO = "tipo";
    private static final String PRECO = "preco";
    private static final String EFEITO_CAPTURA = "efeito_captura";
    private static final String EFEITO_CURA = "efeito_cura";
    private static final String ICONE = "icone";

    //Atributos da tabela ItemTreinador
    private static final String TABELA_ITEM_TREINADOR = "itemTreinador";
    private static final String ID_ITEM_TREINADOR = "_id";
    private static final String ID_ITEM_FK = "idItem";
    private static final String ID_TREINADOR_FK = "idTreinador";
    private static final String QUANTIDADE = "quantidade";

    public BDCore(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sqlJogador = "CREATE TABLE " + TABELA_JOGADOR + "("
                + ID_JOGADOR + " integer primary key autoincrement,"
                + EMAIL + " text not null unique,"
                + SENHA + " text not null"
                + ")";

        bd.execSQL(sqlJogador);

        String sqlTreinador = "CREATE TABLE " + TABELA_TREINADOR + "("
                + ID_TREINADOR + " integer primary key autoincrement,"
                + NOME + " text not null,"
                + DINHEIRO + " integer not null,"
                + ID_AVATAR + " integer not null,"
                + ID_JOGADOR_FK + " integer not null"
                + ")";

        bd.execSQL(sqlTreinador);

        String sqlItem = "CREATE TABLE " + TABELA_ITEM + "("
                + ID_ITEM + " integer primary key autoincrement,"
                + NOME_ITEM + " text not null,"
                + DESCRICAO + " text not null,"
                + TIPO + " text not null,"
                + PRECO + " integer not null,"
                + EFEITO_CAPTURA + " real,"
                + EFEITO_CURA + " real,"
                + ICONE + " integer not null"
                + ")";

        bd.execSQL(sqlItem);
        populaItem(bd);

        String sqlItemTreinador = "CREATE TABLE " + TABELA_ITEM_TREINADOR + "("
                + ID_ITEM_TREINADOR + " integer primary key autoincrement,"
                + ID_ITEM_FK + " integer not null,"
                + ID_TREINADOR_FK + " integer not null,"
                + QUANTIDADE + " integer not null"
                + ")";

        bd.execSQL(sqlItemTreinador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        String sql = "DROP TABLE " + TABELA_JOGADOR;
        bd.execSQL(sql);
        String sql2 = "DROP TABLE " + TABELA_TREINADOR;
        bd.execSQL(sql2);
        String sql3 = "DROP TABLE " + TABELA_ITEM;
        bd.execSQL(sql3);
        onCreate(bd);
    }

    private void populaItem(SQLiteDatabase bd) {
        Item item = new Item("Poção Pequena", "Recupera 20 de HP.", TipoItem.CURA, 50, null, 0.2, R.drawable.pocao_pequena);
        ContentValues valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Poção Grande", "Recupera 50 de HP.", TipoItem.CURA, 100, null, 0.5, R.drawable.pocao_grande);
        valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Revive", "Revive o Pokemon e recupera um pouco de HP.", TipoItem.REVIVE, 100, null, 0.1, R.drawable.revive);
        valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Super Revive", "Revive o Pokemon e recupera HP.", TipoItem.REVIVE, 200, null, 0.4, R.drawable.super_revive);
        valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Pokeball", "Pokebola para básica para captura de Pokemons.",
                TipoItem.CAPTURA, 25, 0., null, R.drawable.pokeball);
        valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Ultraball", "Proporciona uma chance maior na captura de Pokemons.",
                TipoItem.CAPTURA, 100, 0.25, null, R.drawable.ultralball);
        valores = preencheValores(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }


    }

    public  ContentValues preencheValores(Item item) {
        ContentValues valores = new ContentValues();
        valores.put("nome", item.getNome());
        valores.put("descricao", item.getDescricao());
        valores.put("tipo", item.getTipo().toString());
        valores.put("preco", item.getPreco());
        valores.put("efeito_captura", item.getEfeitoCaptura());
        valores.put("efeito_cura", item.getEfeitoCura());
        valores.put("icone", item.getIcone());

        return valores;
    }
}
