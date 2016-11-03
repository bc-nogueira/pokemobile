package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class MenuPrincipalActivity extends AppCompatActivity {
    Treinador t = new Treinador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        t = (Treinador) getIntent().getSerializableExtra("treinador");

        if(t != null) {
            TextView nomeJogador = (TextView) findViewById(R.id.nomeJogador);
            nomeJogador.setText(t.getNome());
        } //else -> ver o que fazer - o certo é não chegar aqui sem um valor
    }

    @Override
    public void onBackPressed() {
    }

    public void logout(View v) {
        t = null;
        Intent main = new Intent(MenuPrincipalActivity.this, MainActivity.class);
        startActivity(main);
    }

    public void abrirInfo(View v) {
        Intent intent = new Intent(MenuPrincipalActivity.this, InfoActivity.class);
        intent.putExtra("treinador", t);
        startActivity(intent);
    }
}
