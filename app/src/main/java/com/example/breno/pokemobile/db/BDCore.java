package com.example.breno.pokemobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonAtaque;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Elemento;

import java.util.Scanner;

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
    private static final String ICONE_FRENTE = "icone_frente";
    private static final String ICONE_COSTAS = "icone_costas";

    //Atributos da tabela PokemonTreinador
    private static final String TABELA_POKEMON_TREINADOR = "pokemonTreinador";
    private static final String ID_POKEMON_TREINADOR = "idPokemonTreinador";
    private static final String ID_POKEMON_FK = "idPokemon";
    private static final String ID_TREINADOR_FK2 = "idTreinador";
    private static final String APELIDO = "apelido";
    private static final String HP_ATUAL = "hp_atual";
    private static final String HP_TOTAL = "hp_total";
    private static final String NIVEL = "nivel";
    private static final String EXPERIENCIA = "experiencia";
    private static final String POS_FILA = "pos_fila";
    private static final String ID_ATAQUE1_FK = "idAtaque1";
    private static final String ID_ATAQUE2_FK = "idAtaque2";

    //Atributos da tabela Ataque
    private static final String TABELA_ATAQUE = "ataque";
    private static final String ID_ATAQUE = "_id";
    private static final String NOME_ATAQUE = "nome";
    private static final String ELEMENTO = "elemento";
    private static final String ICONE_ELEMENTO = "iconeElemento";
    private static final String DANO_BASE = "dano_base";

    //Atributos da tabela PokemonAtaque
    private static final String TABELA_POKEMON_ATAQUE = "pokemonAtaque";
    private static final String ID_POKEMON_FK2 = "idPokemon";
    private static final String ID_ATAQUE_FK = "idAtaque";
    private static final String LVL_APRENDIDO = "lvl_aprendido";

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
                + EFEITO_CURA + " integer,"
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
                + ICONE_TIPO + " integer not null,"
                + ICONE_FRENTE + " integer not null,"
                + ICONE_COSTAS + " integer not null"
                + ")";

        bd.execSQL(sqlPokemon);
        populaPokemon(bd);

        String sqlPokemonTreinador = "CREATE TABLE " + TABELA_POKEMON_TREINADOR + "("
                + ID_POKEMON_TREINADOR + " integer primary key autoincrement,"
                + ID_POKEMON_FK + " text,"
                + ID_TREINADOR_FK2 + " integer not null,"
                + APELIDO + " text,"
                + HP_ATUAL + " real not null,"
                + HP_TOTAL + " real not null,"
                + NIVEL + " integer not null,"
                + EXPERIENCIA + " real not null,"
                + POS_FILA + " integer,"
                + ID_ATAQUE1_FK + " integer,"
                + ID_ATAQUE2_FK + " integer"
                + ")";
        bd.execSQL(sqlPokemonTreinador);

        String sqlAtaque = "CREATE TABLE " + TABELA_ATAQUE + "("
                + ID_ATAQUE + " integer primary key autoincrement,"
                + NOME_ATAQUE + " text not null,"
                + ELEMENTO + " text not null,"
                + ICONE_ELEMENTO + " integer not null,"
                + DANO_BASE + " integer not null"
                + ")";
        bd.execSQL(sqlAtaque);
        populaAtaque(bd);

        String sqlPokemonAtaque = "CREATE TABLE " + TABELA_POKEMON_ATAQUE + "("
                + ID_POKEMON_FK2 + " text not null,"
                + ID_ATAQUE_FK + " integer not null,"
                + LVL_APRENDIDO + " integer not null"
                + ")";
        bd.execSQL(sqlPokemonAtaque);
        populaPokemonAtaque(bd);

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
        String sqlDropPokemonTreinador = "DROP TABLE " + TABELA_POKEMON_TREINADOR;
        bd.execSQL(sqlDropPokemonTreinador);
        String sqlDropAtaque = "DROP TABLE " + TABELA_ATAQUE;
        bd.execSQL(sqlDropAtaque);
        String sqlDropPokemonAtaque = "DROP TABLE " + TABELA_POKEMON_ATAQUE;
        bd.execSQL(sqlDropPokemonAtaque);

        onCreate(bd);
    }

    private void populaItem(SQLiteDatabase bd) {
        Item item = new Item("Potion", "Recupera 20 de HP.", TipoItem.CURA, 50, null, 20, R.drawable.pocao_pequena);
        ContentValues valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Super Potion", "Recupera 50 de HP.", TipoItem.CURA, 100, null, 50, R.drawable.pocao_grande);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Revive", "Revive o Pokemon e recupera um pouco de HP.", TipoItem.REVIVE, 100, null, 10, R.drawable.revive);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Super Revive", "Revive o Pokemon e recupera HP.", TipoItem.REVIVE, 200, null, 25, R.drawable.super_revive);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Pokeball", "Pokebola para básica para captura de Pokemons.",
                TipoItem.CAPTURA, 25, 0.25, null, R.drawable.pokeball);
        valores = preencheValoresItem(item);
        try {
            bd.insertOrThrow("item", null, valores);
        } catch (SQLException ex) {
        }

        item = new Item("Ultraball", "Proporciona uma chance maior na captura de Pokemons.",
                TipoItem.CAPTURA, 100, 0.5, null, R.drawable.ultralball);
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

    public void insere_pokemon(SQLiteDatabase bd, String numero, String nome, Elemento tipo, Integer estagioEvolucao, Integer hpMinimo,
                                Integer hpMaximo, String altura, String peso, String descricao, Integer icone, Integer iconeTipo,
                                Integer iconeFrente, Integer iconeCostas) {
        Pokemon pokemon = new Pokemon(numero, nome, tipo, estagioEvolucao, hpMinimo, hpMaximo,
                                      altura, peso, descricao, icone, iconeTipo, iconeFrente, iconeCostas);
        ContentValues valores = preencheValoresPokemon(pokemon);
        try {
            bd.insertOrThrow("pokemon", null, valores);
        } catch (SQLException ex) {
            System.out.println();
        }
    }

    public void populaPokemon(SQLiteDatabase bd) {
        insere_pokemon(bd, "001", "Bulbasaur", Elemento.GRAMA, 1, 20, 25, "0,7m", "6,9kg",
                "For some time after its birth, it grows by gaining nourishment from the seed on its back.",
                R.drawable.bulbasaur, R.drawable.grama, R.drawable.bulbasaur_frente, R.drawable.bulbasaur_costas);
        insere_pokemon(bd, "002", "Ivysaur", Elemento.GRAMA, 2, 25, 35, "1,0 m", "13kg",
                "When the bud on its back starts swelling, a sweet aroma wafts to indicate the flowers coming bloom.",
                R.drawable.ivysaur, R.drawable.grama, R.drawable.ivysaur_frente, R.drawable.ivysaur_costas);
       insere_pokemon(bd, "004", "Charmander", Elemento.FOGO, 1, 15, 20, "0,6m", "8,5kg",
                "The fire on the tip of its tail is a measure of its life. If healthy, its tail burns intensely.",
                R.drawable.charmander, R.drawable.fogo, R.drawable.charmander_frente, R.drawable.charmander_costas);
        insere_pokemon(bd, "005", "Charmeleon", Elemento.FOGO, 1, 20, 35, "1,1m", "19,0kg",
                "In the rocky mountains where Charmeleon live, their fiery tails shine at night like stars.",
                R.drawable.charmeleon, R.drawable.fogo, R.drawable.charmeleon_frente, R.drawable.charmeleon_costas);

        insere_pokemon(bd, "007", "Squirtle", Elemento.AGUA, 1, 20, 25, "0,5m", "9,0kg",
                "It shelters itself in its shell then strikes back with spouts of water at every opportunity.",
                R.drawable.squirtle, R.drawable.agua, R.drawable.squirtle_frente, R.drawable.squirtle_costas);
        insere_pokemon(bd, "008", "Wartortle", Elemento.AGUA, 1, 25, 35, "1,0m", "22,5kg",
                "It is said to live 10,000 years. Its furry tail is popular as a symbol of longevity.",
                R.drawable.wartortle, R.drawable.agua, R.drawable.wartortle_frente, R.drawable.wartortle_costas);

        insere_pokemon(bd, "016", "Pidgey", Elemento.NORMAL, 1, 15, 20, "0,3m", "1,8kg",
                "It is docile and prefers to avoid conflict. If disturbed, however, it can ferociously strike back.",
                R.drawable.pidgey, R.drawable.neutro, R.drawable.pidgey_frente, R.drawable.pidgey_costas);

        insere_pokemon(bd, "025", "Pikachu", Elemento.ELETRICO, 1, 10, 15, "0,4m", "6,0kg",
                "It occasionally uses an electric shock to recharge a fellow Pikachu that is in a weakened state.",
                R.drawable.pikachu, R.drawable.eletrico, R.drawable.pikachu_frente_battle, R.drawable.pikachu_costas_battle);

        insere_pokemon(bd, "041", "Zubat", Elemento.VENENOSO, 1, 10, 15, "0,8m", "7,5kg",
                "It does not need eyes, because it emits ultrasonic waves to check its surroundings while it flies.",
                R.drawable.zubat, R.drawable.venenoso, R.drawable.zubat_frente, R.drawable.zubat_costas);

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
        valores.put("icone_frente", pokemon.getIconeFrente());
        valores.put("icone_costas", pokemon.getIconeCostas());

        return valores;
    }

    private void populaAtaque(SQLiteDatabase bd) {
        Ataque ataque = new Ataque("Tackle", Elemento.NORMAL, R.drawable.neutro, 1);
        ContentValues valores = preencheValoresAtaque(ataque);
        try {
            bd.insertOrThrow("ataque", null, valores);
        } catch (SQLException ex) {
        }
    }

    public  ContentValues preencheValoresAtaque(Ataque ataque) {
        ContentValues valores = new ContentValues();
        valores.put("nome", ataque.getNomeAtaque());
        valores.put("elemento", ataque.getElemento().toString());
        valores.put("iconeElemento", ataque.getIconeElemento());
        valores.put("dano_base", ataque.getDanoBase());

        return valores;
    }

    private void populaPokemonAtaque(SQLiteDatabase bd) {
        PokemonAtaque pokemonAtaque = new PokemonAtaque("001", 1, 1);
        ContentValues valores = preencheValoresPokemonAtaque(pokemonAtaque);
        try {
            bd.insertOrThrow("pokemonAtaque", null, valores);
        } catch (SQLException ex) {
        }

        pokemonAtaque = new PokemonAtaque("004", 1, 1);
        valores = preencheValoresPokemonAtaque(pokemonAtaque);
        try {
            bd.insertOrThrow("pokemonAtaque", null, valores);
        } catch (SQLException ex) {
        }

        pokemonAtaque = new PokemonAtaque("007", 1, 1);
        valores = preencheValoresPokemonAtaque(pokemonAtaque);
        try {
            bd.insertOrThrow("pokemonAtaque", null, valores);
        } catch (SQLException ex) {
        }

        pokemonAtaque = new PokemonAtaque("025", 1, 1);
        valores = preencheValoresPokemonAtaque(pokemonAtaque);
        try {
            bd.insertOrThrow("pokemonAtaque", null, valores);
        } catch (SQLException ex) {
        }
    }

    public  ContentValues preencheValoresPokemonAtaque(PokemonAtaque pokemonAtaque) {
        ContentValues valores = new ContentValues();
        valores.put("idPokemon", pokemonAtaque.getIdPokemon());
        valores.put("idAtaque", pokemonAtaque.getIdAtaque());
        valores.put("lvl_aprendido", pokemonAtaque.getLvlAprendido());

        return valores;
    }

}
