package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.db.JogadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class MenuPrincipalActivity extends AppCompatActivity {
    private Treinador treinador;
    private Jogador jogador;
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        if(treinador != null) {
            TextView nomeJogador = (TextView) findViewById(R.id.nomeTextViewMenuPrincipal);
            nomeJogador.setText(treinador.getNome());
        } //else -> ver o que fazer - o certo é não chegar aqui sem um valor
    }

    @Override
    public void onBackPressed() {
    }

    public void logout(View v) {
        treinador = null;
        Intent main = new Intent(MenuPrincipalActivity.this, MainActivity.class);
        startActivity(main);
    }

    public void batalhaSelvagem(View v) {

        if(pokemonTreinadorService.verificaSeTodosEstaoMortos(treinador, this)) {

            Toast.makeText(this, "Todos os seus pokemons estão mortos.", Toast.LENGTH_SHORT).show();

        } else {
            Intent selvagem = new Intent(MenuPrincipalActivity.this, BatalhaSelvagemActivity.class);
            selvagem.putExtra("treinador", treinador);
            startActivity(selvagem);
        }

    }

    public void abrirPokedex(View v) {
        Intent pokedex = new Intent(MenuPrincipalActivity.this, PokedexActivity.class);
        pokedex.putExtra("treinador", treinador);
        startActivity(pokedex);
    }

    public void abrirPokemons(View v) {
        Intent pokemons = new Intent(MenuPrincipalActivity.this, PokemonsActivity.class);
        pokemons.putExtra("treinador", treinador);
        startActivity(pokemons);
    }

    public void abrirMochila(View v) {
        Intent mochila = new Intent(MenuPrincipalActivity.this, MochilaActivity.class);
        mochila.putExtra("treinador", treinador);
        startActivity(mochila);
    }

    public void abrirLoja(View v) {
        Intent loja = new Intent(MenuPrincipalActivity.this, LojaActivity.class);
        loja.putExtra("treinador", treinador);
        startActivity(loja);
    }

    public void abrirInfo(View v) {
        Intent intent = new Intent(MenuPrincipalActivity.this, InfoActivity.class);
        intent.putExtra("treinador", treinador);
        startActivity(intent);
    }

    public void mudarTreinador(View v) {
        JogadorDAO jogadorDAO = new JogadorDAO(this);
        jogador = jogadorDAO.buscarPorId(treinador.getIdJogador());

        treinador = null;

        Intent selecionar = new Intent(MenuPrincipalActivity.this, SelecionaTreinadorActivity.class);
        selecionar.putExtra("jogador", jogador);
        startActivity(selecionar);
    }

    public void abrirArmazem(View v) {
        Intent armazem = new Intent(MenuPrincipalActivity.this, ArmazemActivity.class);
        armazem.putExtra("treinador", treinador);
        startActivity(armazem);
    }

}
