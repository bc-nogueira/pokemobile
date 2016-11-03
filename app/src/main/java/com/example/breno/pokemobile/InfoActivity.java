package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.Jogador;

public class InfoActivity extends AppCompatActivity {
    Jogador j = new Jogador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        j = (Jogador) getIntent().getSerializableExtra("jogador");

        ImageView teste = (ImageView) findViewById(R.id.imageView2);
        teste.setImageResource(j.getIdAvatar());
        TextView nome = (TextView) findViewById(R.id.valorNome);
        nome.setText(j.getNome());
        TextView pokemons = (TextView) findViewById(R.id.valorPokemons);
        Integer quantPokemons = 0;
        pokemons.setText(quantPokemons.toString());
        TextView dinheiro = (TextView) findViewById(R.id.valorDinheiro);
        dinheiro.setText(j.getDinheiro().toString());
    }
}
