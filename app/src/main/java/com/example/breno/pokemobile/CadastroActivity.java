package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    private List<Integer> imagensTreinadores;
    private int imagemAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        imagensTreinadores = new ArrayList<>();
        imagensTreinadores.add(R.drawable.treinador1);
        imagensTreinadores.add(R.drawable.treinador2);
        imagensTreinadores.add(R.drawable.treinador3);
        imagensTreinadores.add(R.drawable.treinadora1);
        imagensTreinadores.add(R.drawable.treinadora2);
        imagensTreinadores.add(R.drawable.treinadora3);

        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        treinador.setImageResource(imagensTreinadores.get(0));
        imagemAtual = 0;
    }

    public void proximo(View v) {
        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        if(imagemAtual == (imagensTreinadores.size()-1)) {
            imagemAtual = -1;
        }
        treinador.setImageResource(imagensTreinadores.get(++imagemAtual));
    }

    public void anterior(View v) {
        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        if(imagemAtual == 0) {
            imagemAtual = imagensTreinadores.size();
        }
        treinador.setImageResource(imagensTreinadores.get(--imagemAtual));
    }

}
