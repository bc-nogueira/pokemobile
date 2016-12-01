package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.adapter.PokemonArmazemAdapter;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class ArmazemActivity extends AppCompatActivity {
    private Treinador treinador;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();

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

                PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
                if(pokemons.get(position).getPosFila() != null) {
                    //Não pode deixar equipe sem pokemon
                    if(pokemonTreinadorDAO.buscarPorIdTreinadorNaFila(treinador, getApplicationContext()).size() == 1) {
                        Toast.makeText(getApplicationContext(),
                                "Sua equipe precisa de pelo menos 1 pokemon.", Toast.LENGTH_SHORT).show();
                    } else {
                        pokemons.get(position).setPosFila(null);
                        pokemonTreinadorDAO.atualizarPosFila(pokemons.get(position));

                        //Necessário reordenar a lista
                        pokemonTreinadorService.reordenarFila(treinador, getApplicationContext());

                        pokemonArmazemAdapter.notifyDataSetChanged();
                    }
                } else {
                    if(pokemonTreinadorDAO.buscarPorIdTreinadorNaFila(treinador, getApplicationContext()).size() == 6) {
                        Toast.makeText(getApplicationContext(),
                                "Sua equipe não pode ter mais de 6 pokemons.", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<PokemonTreinador> pokemonsNaFila =
                                pokemonTreinadorDAO.buscarPorIdTreinadorNaFila(treinador, getApplicationContext());
                        Integer posPraColocar = pokemonsNaFila.size() + 1;
                        pokemons.get(position).setPosFila(posPraColocar);
                        pokemonTreinadorDAO.atualizarPosFila(pokemons.get(position));
                        pokemonArmazemAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

    }
}
