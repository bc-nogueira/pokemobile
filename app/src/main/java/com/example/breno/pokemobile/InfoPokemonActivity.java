package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

public class InfoPokemonActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinador pokemonTreinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");
        pokemonTreinador = (PokemonTreinador) getIntent().getSerializableExtra("pokemonTreinador");

        TextView nomePokemon = (TextView) findViewById(R.id.nomePokemonTextViewInfoPokemon);
        nomePokemon.setText(pokemonTreinador.getPokemon().getNome());

        TextView lvlPokemon = (TextView) findViewById(R.id.levelPokemonTextViewInfoPokemon);
        lvlPokemon.setText("Lvl. " + pokemonTreinador.getLevel());

        ImageView iconePokemon = (ImageView) findViewById(R.id.iconePokemonImageViewInfoPokemon);
        iconePokemon.setImageResource(pokemonTreinador.getPokemon().getIcone());

        TextView hpPokemon = (TextView) findViewById(R.id.hpPokemonTextViewInfoPokemon);
        hpPokemon.setText("HP:  " + pokemonTreinador.getHpTotal());

//        TextView agiPokemon = (TextView) findViewById(R.id.agiPokemonTextViewInfoPokemon);
//        agiPokemon.setText("AGI: " + pokemonTreinador.getHpTotal());

//        TextView atqPokemon = (TextView) findViewById(R.id.atqPokemonTextViewInfoPokemon);
//        atqPokemon.setText("ATQ: " + pokemonTreinador.getHpTotal());

//        TextView defPokemon = (TextView) findViewById(R.id.defPokemonTextViewInfoPokemon);
//        defPokemon.setText("DEF: " + pokemonTreinador.getHpTotal());

        ImageView iconeAtaque1 = (ImageView) findViewById(R.id.iconeTipoAtaque1);
        iconeAtaque1.setImageResource(pokemonTreinador.getAtaque1().getIconeElemento());

        TextView nomeAtaque1 = (TextView) findViewById(R.id.nomeAtaque1);
        nomeAtaque1.setText(pokemonTreinador.getAtaque1().getNomeAtaque());

        ImageView iconeAtaque2 = (ImageView) findViewById(R.id.iconeTipoAtaque2);
        TextView nomeAtaque2 = (TextView) findViewById(R.id.nomeAtaque2);
        if(pokemonTreinador.getAtaque2() != null) {

            iconeAtaque2.setImageResource(pokemonTreinador.getAtaque2().getIconeElemento());
            nomeAtaque2.setText(pokemonTreinador.getAtaque2().getNomeAtaque());

        } else {

            iconeAtaque2.setVisibility(View.INVISIBLE);
            nomeAtaque2.setVisibility(View.INVISIBLE);

        }

    }
}
