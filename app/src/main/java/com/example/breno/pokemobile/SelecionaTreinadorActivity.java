package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.breno.pokemobile.adapter.TreinadorAdapter;
import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class SelecionaTreinadorActivity extends AppCompatActivity {
    Jogador j = new Jogador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_treinador);

        j = (Jogador) getIntent().getSerializableExtra("jogador");

        TreinadorDAO treinadorDAO = new TreinadorDAO(this);
        ArrayList<Treinador> treinadores = treinadorDAO.buscarPorIdJogador(j.getIdJogador());

        ListView listaViewTreinadores = (ListView) findViewById(R.id.listView);

        listaViewTreinadores.setAdapter(new TreinadorAdapter(this, treinadores));
    }
}
