package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.TipoPokemon;

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
    private static final String DESCRICAO_ITEM = "descricao";
    private static final String TIPO = "tipo";
    private static final String PRECO = "preco";
    private static final String EFEITO_CAPTURA = "efeito_captura";
    private static final String EFEITO_CURA = "efeito_cura";
    private static final String ICONE_ITEM = "icone";

    //Atributos da tabela ItemTreinador
    private static final String TABELA_ITEM_TREINADOR = "itemTreinador";
    private static final String ID_ITEM_TREINADOR = "_id";
    private static final String ID_ITEM_FK = "idItem";
    private static final String ID_TREINADOR_FK = "idTreinador";
    private static final String QUANTIDADE = "quantidade";

    //Atributos da tabela Pokemon
    private static final String TABELA_POKEMON = "pokemon";
    private static final String NUMERO = "numero";
    private static final String NOME_POKEMON = "nome";
    private static final String TIPO_POKEMON = "tipo";
    private static final String ESTAGIO_EVOLUCAO = "estagio_evolucao";
    private static final String HP_MINIMO = "hp_minimo";
    private static final String HP_MAXIMO = "hp_maximo";
    private static final String ALTURA = "altura";
    private static final String PESO = "peso";
    private static final String DESCRICAO_POKEMON = "descricao";
    private static final String ICONE_POKEMON = "icone";
    private static final String ICONE_TIPO = "icone_tipo";

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
                + DESCRICAO_ITEM + " text not null,"
                + TIPO + " text not null,"
                + PRECO + " integer not null,"
                + EFEITO_CAPTURA + " real,"
                + EFEITO_CURA + " real,"
                + ICONE_ITEM + " integer not null"
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

        String sqlPokemon = "CREATE TABLE " + TABELA_POKEMON + "("
                + NUMERO + " text primary key,"
                + NOME_POKEMON + " text not null,"
                + TIPO_POKEMON + " text not null,"
                + ESTAGIO_EVOLUCAO + " integer not null,"
                + HP_MINIMO + " integer not null,"
                + HP_MAXIMO + " integer not null,"
                + ALTURA + " text not null,"
                + PESO + " text not null,"
                + DESCRICAO_POKEMON + " text not null,"
                + ICONE_POKEMON + " integer not null,"
                + ICONE_TIPO + " integer not null"
                + ")";

        bd.execSQL(sqlPokemon);
        populaPokemon(bd);

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        String sqlDropJogador = "DROP TABLE " + TABELA_JOGADOR;
        bd.execSQL(sqlDropJogador);
        String sqlDropTreinador = "DROP TABLE " + TABELA_TREINADOR;
        bd.execSQL(sqlDropTreinador);
        String sqlDropItem = "DROP TABLE " + TABELA_ITEM;
        bd.execSQL(sqlDropItem);
        String sqlDropItemTreinador = "DROP TABLE " + TABELA_ITEM_TREINADOR;
        bd.execSQL(sqlDropItemTreinador);
        String sqlDropPokemon = "DROP TABLE " + TABELA_POKEMON;
        bd.execSQL(sqlDropPokemon);

        onCreate(bd);
    }

    private void populaItem(SQLiteDatabase bd) {
        Item item = new Item("Poção Pequena", "Recupera 20 de HP.", TipoItem.CURA, 50, null, 0.2, R.drawable.pocao_pequena);
        ContentValues valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Poção Grande", "Recupera 50 de HP.", TipoItem.CURA, 100, null, 0.5, R.drawable.pocao_grande);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Revive", "Revive o Pokemon e recupera um pouco de HP.", TipoItem.REVIVE, 100, null, 0.1, R.drawable.revive);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Super Revive", "Revive o Pokemon e recupera HP.", TipoItem.REVIVE, 200, null, 0.4, R.drawable.super_revive);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Pokeball", "Pokebola para básica para captura de Pokemons.",
                TipoItem.CAPTURA, 25, 0., null, R.drawable.pokeball);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Ultraball", "Proporciona uma chance maior na captura de Pokemons.",
                TipoItem.CAPTURA, 100, 0.25, null, R.drawable.ultralball);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }


    }

    public  ContentValues preencheValoresItem(Item item) {
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

    public void populaPokemon(SQLiteDatabase bd) {
        Pokemon pokemon = new Pokemon("001", "Bulbasaur", TipoPokemon.GRAMA, 1, 20, 25, "0,7m", "6,9kg",
                "For some time after its birth, it grows by gaining nourishment from the seed on its back.",
                R.drawable.bulbasaur, R.drawable.grama);
        ContentValues valores = preencheValoresPokemon(pokemon);
        try {
            bd.insertOrThrow("pokemon", null, valores);
        } catch (SQLException ex) {
            System.out.println();
        }

        pokemon = new Pokemon("004", "Charmander", TipoPokemon.FOGO, 1, 15, 20, "0,6m", "8,5kg",
                "The fire on the tip of its tail is a measure of its life. If healthy, its tail burns intensely.",
                R.drawable.charmander, R.drawable.fogo);
        valores = preencheValoresPokemon(pokemon);
        try {
            bd.insertOrThrow("pokemon", null, valores);
        } catch (SQLException ex) {
        }

        pokemon = new Pokemon("007", "Squirtle", TipoPokemon.AGUA, 1, 20, 25, "0,5m", "9,0kg",
                "It shelters itself in its shell then strikes back with spouts of water at every opportunity.",
                R.drawable.squirtle, R.drawable.agua);
        valores = preencheValoresPokemon(pokemon);
        try {
            bd.insertOrThrow("pokemon", null, valores);
        } catch (SQLException ex) {
        }

        pokemon = new Pokemon("025", "Pikachu", TipoPokemon.ELETRICO, 1, 10, 15, "0,4m", "6,0kg",
                "It occasionally uses an electric shock to recharge a fellow Pikachu that is in a weakened state.",
                R.drawable.pikachu, R.drawable.eletrico);
        valores = preencheValoresPokemon(pokemon);
        try {
            bd.insertOrThrow("pokemon", null, valores);
        } catch (SQLException ex) {
        }

    }

    public  ContentValues preencheValoresPokemon(Pokemon pokemon) {
        ContentValues valores = new ContentValues();
        valores.put("numero", pokemon.getNumero());
        valores.put("nome", pokemon.getNome());
        valores.put("tipo", pokemon.getTipo().toString());
        valores.put("estagio_evolucao", pokemon.getEstagioEvolucao());
        valores.put("hp_minimo", pokemon.getHpMinimo());
        valores.put("hp_maximo", pokemon.getHpMaximo());
        valores.put("altura", pokemon.getAltura());
        valores.put("peso", pokemon.getPeso());
        valores.put("descricao", pokemon.getDescricao());
        valores.put("icone", pokemon.getIcone());
        valores.put("icone_tipo", pokemon.getIconeTipo());

        return valores;
    }
}
