package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.breno.pokemobile.adapter.PokemonArmazemAdapter;
import com.example.breno.pokemobile.adapter.PokemonsAdapter;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class ArmazemActivity extends AppCompatActivity {
    private Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazem);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        final PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
        final ArrayList<PokemonTreinador> pokemons = pokemonTreinadorDAO.buscarPorIdTreinador(treinador, getApplicationContext());

        final GridView gv = (GridView) findViewById(R.id.armazemGridView);

        final PokemonArmazemAdapter pokemonArmazemAdapter = new PokemonArmazemAdapter(this, pokemons);
        gv.setAdapter(pokemonArmazemAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(pokemons.get(position).getPosFila() != null) {
                    pokemons.get(position).setPosFila(null);
                    PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
                    pokemonTreinadorDAO.atualizarPosFila(pokemons.get(position));
                    pokemonArmazemAdapter.notifyDataSetChanged();
                } else {
                    PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
                    ArrayList<PokemonTreinador> pokemonsNaFila =
                            pokemonTreinadorDAO.buscarPorIdTreinadorNaFila(treinador,getApplicationContext());
                    Integer posPraColocar = pokemonsNaFila.size();
                    pokemons.get(position).setPosFila(posPraColocar);
                    pokemonTreinadorDAO.atualizarPosFila(pokemons.get(position));
                    pokemonArmazemAdapter.notifyDataSetChanged();
                }

            }
        });

    }
}
