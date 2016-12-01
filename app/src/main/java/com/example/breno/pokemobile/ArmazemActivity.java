package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
        final ArrayList<PokemonTreinador> pokemons = pokemonTreinadorDAO.buscarPorIdTreinador(treinador, getApplicationContext());

        final ListView listPokemons = (ListView) findViewById(R.id.pokemonsListViewArmazem);

        PokemonsAdapter pokemonsAdapter = new PokemonsAdapter(this, pokemons);
        listPokemons.setAdapter(pokemonsAdapter);

        listPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });





    }
}
