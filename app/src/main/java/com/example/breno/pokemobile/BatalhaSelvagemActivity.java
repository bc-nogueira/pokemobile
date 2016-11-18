package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.Service.UtilidadesService;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private UtilidadesService utilidadesService = new UtilidadesService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalha_selvagem);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        //Coloca pokemon inimigo
        PokemonTreinador pokemonTreinadorInimigo = pokemonTreinadorService.criaPokemonBatalhaSelvagem(this);

        ImageView pokemonInimigo = (ImageView) findViewById(R.id.pokemonFrenteBatalhaSelvagem);
        pokemonInimigo.setImageResource(pokemonTreinadorInimigo.getPokemon().getIconeFrente());

        TextView nomePokemonInimigo = (TextView) findViewById(R.id.nomeInimigoTextViewBatalhaSelvagem);
        nomePokemonInimigo.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinadorInimigo));

        ProgressBar hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaSelvagem);
        Double porcentagemHPInimigo = (pokemonTreinadorInimigo.getHpAtual()/pokemonTreinadorInimigo.getHpTotal()) * 100;
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());

        TextView hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaSelvagem);
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());

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

    }

    @Override
    public void onBackPressed() {
    }

    public void fugir(View v) {

        if(utilidadesService.gerarNumeroAleatorio(10) < 6) {

            Toast.makeText(this, "Você fugiu!", Toast.LENGTH_SHORT).show();

            Intent menuPrincipal = new Intent(BatalhaSelvagemActivity.this, MenuPrincipalActivity.class);
            menuPrincipal.putExtra("treinador", treinador);
            startActivity(menuPrincipal);

        } else {

            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
            mensagem.setText("Não foi possível fugir.");

        }

    }


}
