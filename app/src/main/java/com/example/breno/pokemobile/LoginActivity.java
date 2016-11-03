package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.breno.pokemobile.db.JogadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        EditText email = (EditText) findViewById(R.id.emailLoginEdit);
        EditText senha = (EditText) findViewById(R.id.senhaLoginEdit);

        if(isCamposPreenchidos(email.getText().toString(), senha.getText().toString())) {
            JogadorDAO jogadorDAO = new JogadorDAO(this);
            Jogador jogadorAtual = jogadorDAO.buscarPorEmail(email.getText().toString());

            if(jogadorAtual != null) {

                if(isSenhaCorreta(jogadorAtual, senha.getText().toString())) {

                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent menuPrincipal = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                    menuPrincipal.putExtra("jogador", jogadorAtual);
                    startActivity(menuPrincipal);

                } else {
                    Toast.makeText(this, "A senha está incorreta.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Jogador inexistente.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isCamposPreenchidos(String email, String senha) {
        if(email.equals("") || senha.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isSenhaCorreta(Jogador jogador, String senha) {
        if(senha.equals(jogador.getSenha())) {
            return true;
        } else {
            return false;
        }
    }
}
