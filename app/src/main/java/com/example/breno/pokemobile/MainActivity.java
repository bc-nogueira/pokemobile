package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.activity_main);
        relLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Button novo = (Button) findViewById(R.id.novo);
                Button carregar = (Button) findViewById(R.id.carregar);
                TextView textoInicial = (TextView) findViewById(R.id.textView);
                textoInicial.setVisibility(View.INVISIBLE);
                novo.setVisibility(View.VISIBLE);
                carregar.setVisibility(View.VISIBLE);
            }
        });
    }

    public void irCadastro(View v) {
        Intent cadastroIntent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(cadastroIntent);
    }

    public void irLogin(View v) {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
