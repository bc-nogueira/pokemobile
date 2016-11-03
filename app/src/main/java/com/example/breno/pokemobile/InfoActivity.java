package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class InfoActivity extends AppCompatActivity {
    Treinador t = new Treinador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        t = (Treinador) getIntent().getSerializableExtra("treinador");

        ImageView avatar = (ImageView) findViewById(R.id.imageView2);
        avatar.setImageResource(t.getIdAvatar());
        TextView nome = (TextView) findViewById(R.id.valorNome);
        nome.setText(t.getNome());
        TextView pokemons = (TextView) findViewById(R.id.valorPokemons);
        Integer quantPokemons = 0;
        pokemons.setText(quantPokemons.toString());
        TextView dinheiro = (TextView) findViewById(R.id.valorDinheiro);
        dinheiro.setText(t.getDinheiro().toString());
    }
}
