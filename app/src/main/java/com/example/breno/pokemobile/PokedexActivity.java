package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.breno.pokemobile.adapter.PokedexAdapter;
import com.example.breno.pokemobile.db.PokemonDAO;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class PokedexActivity extends AppCompatActivity {
    private Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        PokemonDAO pokemonDAO = new PokemonDAO(this);
        final ArrayList<Pokemon> pokemons = pokemonDAO.buscar();

        ListView listViewPokemons = (ListView) findViewById(R.id.pokemonsListViewPokedex);

        PokedexAdapter pokedexAdapter = new PokedexAdapter(this, pokemons);
        listViewPokemons.setAdapter(pokedexAdapter);

        listViewPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalhes = new Intent(PokedexActivity.this, DetalhesPokemonActivity.class);
                detalhes.putExtra("treinador", treinador);
                detalhes.putExtra("pokemon", pokemons.get(position));
                startActivity(detalhes);

            }
        });

    }
}
