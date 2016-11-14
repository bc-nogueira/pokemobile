package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.Treinador;

public class DetalhesPokemonActivity extends AppCompatActivity {
    private Treinador treinador;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");
        pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");

        if((treinador != null) && (pokemon != null)) {

            ImageView icone = (ImageView) findViewById(R.id.iconeImageViewDetalhes);
            icone.setImageResource(pokemon.getIcone());

            TextView numero = (TextView) findViewById(R.id.numeroTextViewDetalhes);
            numero.setText(pokemon.getNumero());

            TextView nome = (TextView) findViewById(R.id.nomeTextViewDetalhes);
            nome.setText(pokemon.getNome());

            ImageView iconeTipo = (ImageView) findViewById(R.id.iconeTipoImageViewDetalhes);
            iconeTipo.setImageResource(pokemon.getIconeTipo());

            TextView tipo = (TextView) findViewById(R.id.tipoTextViewDetalhes);
            tipo.setText(pokemon.getTipoParaDemonstrar());

            TextView altura = (TextView) findViewById(R.id.alturaTextViewDetalhes);
            altura.setText(pokemon.getAltura().toString());

            TextView peso = (TextView) findViewById(R.id.pesoTextViewDetalhes);
            peso.setText(pokemon.getPeso().toString());

            TextView descricao = (TextView) findViewById(R.id.descricaoTextViewDetalhes);
            descricao.setText(pokemon.getDescricao());

        }

    }
}
