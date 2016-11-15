package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class InfoActivity extends AppCompatActivity {
    Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        ImageView avatar = (ImageView) findViewById(R.id.avatarImageViewInfo);
        avatar.setImageResource(treinador.getImagemTreinador(treinador.getIdAvatar()));

        TextView nome = (TextView) findViewById(R.id.nomeTextViewInfo);
        nome.setText(treinador.getNome());

        TextView pokemons = (TextView) findViewById(R.id.pokemonsTextViewInfo);
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
        Integer quantPokemons = pokemonTreinadorDAO.buscarPorIdTreinador(treinador, this).size();
        pokemons.setText(quantPokemons.toString());

        TextView dinheiro = (TextView) findViewById(R.id.dinheiroTextViewInfo);
        dinheiro.setText(treinador.getDinheiro().toString());
    }
}
