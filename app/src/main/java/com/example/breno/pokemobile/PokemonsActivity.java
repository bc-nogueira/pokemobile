package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.breno.pokemobile.adapter.PokemonsAdapter;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class PokemonsActivity extends AppCompatActivity {
    private Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        PokemonTreinadorDAO ptDAO = new PokemonTreinadorDAO(this);
        ArrayList<PokemonTreinador> pts = ptDAO.buscarPorIdTreinador(treinador, this);

        ListView listPokemons = (ListView) findViewById(R.id.pokemonsListViewPokemons);

        PokemonsAdapter pokemonsAdapter = new PokemonsAdapter(this, pts);
        listPokemons.setAdapter(pokemonsAdapter);

    }
}
