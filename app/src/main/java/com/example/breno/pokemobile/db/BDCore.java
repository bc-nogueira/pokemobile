package com.example.breno.pokemobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                + DINHEIRO + " integer,"
                + ID_AVATAR + " integer,"
                + ID_JOGADOR_FK + " integer"
                + ")";

        bd.execSQL(sqlTreinador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        String sql = "DROP TABLE " + TABELA_JOGADOR;
        bd.execSQL(sql);
        String sql2 = "DROP TABLE " + TABELA_TREINADOR;
        bd.execSQL(sql2);
        onCreate(bd);
    }
}
