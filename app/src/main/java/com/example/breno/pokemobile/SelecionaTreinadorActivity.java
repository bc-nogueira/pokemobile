package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.breno.pokemobile.adapter.TreinadorAdapter;
import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class SelecionaTreinadorActivity extends AppCompatActivity {
    private Jogador jogador;
    private ArrayList<Treinador> treinadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_treinador);

        jogador = (Jogador) getIntent().getSerializableExtra("jogador");

        TreinadorDAO treinadorDAO = new TreinadorDAO(this);
        treinadores = treinadorDAO.buscarPorIdJogador(jogador.getIdJogador());

        ListView listaViewTreinadores = (ListView) findViewById(R.id.listView);

        listaViewTreinadores.setAdapter(new TreinadorAdapter(this, treinadores));

        listaViewTreinadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selecionarTreinador(position);
            }
        });

    }

    public void selecionarTreinador(Integer position) {
        Treinador treinador = treinadores.get(position);

        Intent menuPrincipal = new Intent(SelecionaTreinadorActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        startActivity(menuPrincipal);
    }

    public void deletarTreinador(View v) {
        Long idTreinador = (Long) v.getTag();

        TreinadorDAO treinadorDAO = new TreinadorDAO(this);
        treinadorDAO.excluir(idTreinador);

        this.recreate();;
    }

    public void criarNovoTreinador(View v) {
        if(treinadores.size() < 4) {
            Intent criarTreinador = new Intent(SelecionaTreinadorActivity.this, CriaTreinadorActivity.class);
            criarTreinador.putExtra("jogador", jogador);
            startActivity(criarTreinador);
        } else {
            Toast.makeText(this, "Só é possível ter 4 treinadores.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sair(View v) {
        treinadores = null;
        jogador = null;

        Intent main = new Intent(SelecionaTreinadorActivity.this, MainActivity.class);
        startActivity(main);
    }

}
