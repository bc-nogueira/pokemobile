package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void abrirInfo(View v) {
        Intent intent = new Intent(MenuPrincipalActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}
