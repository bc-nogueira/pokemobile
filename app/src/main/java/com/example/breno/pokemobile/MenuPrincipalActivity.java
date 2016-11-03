package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.breno.pokemobile.modelo.Jogador;

public class MenuPrincipalActivity extends AppCompatActivity {
    Jogador j = new Jogador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        j = (Jogador) getIntent().getSerializableExtra("jogador");

        if(j != null) {
            TextView nomeJogador = (TextView) findViewById(R.id.nomeJogador);
            nomeJogador.setText(j.getNome());
        } //else -> ver o que fazer - o certo é não chegar aqui sem um valor
    }

    @Override
    public void onBackPressed() {
    }

    public void logout(View v) {
        j = null;
        Intent main = new Intent(MenuPrincipalActivity.this, MainActivity.class);
        startActivity(main);
    }

    public void abrirInfo(View v) {
        Intent intent = new Intent(MenuPrincipalActivity.this, InfoActivity.class);
        intent.putExtra("jogador", j);
        startActivity(intent);
    }
}
