package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalha_selvagem);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        //Coloca pokemon inimigo


        //Coloca pokemon jogador
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
        PokemonTreinador pokemonTreinador = pokemonTreinadorDAO.buscarPrimeiroNaFilaPorId(treinador, this);

        ImageView pokemon = (ImageView) findViewById(R.id.pokemonCostasBatalhaSelvagem);
        pokemon.setImageResource(pokemonTreinador.getPokemon().getIconeCostas());

        TextView nomePokemon = (TextView) findViewById(R.id.nomeTextViewBatalhaSelvagem);
        nomePokemon.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador));

        ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpProgressBarBatalhaSelvagem);
        Double porcentagemHP = (pokemonTreinador.getHpAtual()/pokemonTreinador.getHpTotal()) * 100;
        hpBar.setProgress(porcentagemHP.intValue());

        TextView hpText = (TextView) findViewById(R.id.hpTextViewBatalhaSelvagem);
        hpText.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());

        System.out.println();

    }

    @Override
    public void onBackPressed() {
    }
}
