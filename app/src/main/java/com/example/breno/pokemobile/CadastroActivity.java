package com.example.breno.pokemobile;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.breno.pokemobile.db.JogadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    private List<Integer> imagensTreinadores;
    private int imagemAtual;

    Jogador j = new Jogador();

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

    public void cadastrar(View v) {
        EditText nome = (EditText) findViewById(R.id.nomeCriarEdit);
        EditText email = (EditText) findViewById(R.id.emailCriarEdit);
        EditText senha = (EditText) findViewById(R.id.senhaCriarEdit);

        if(nome.getText().toString().equals("") || email.getText().toString().equals("") || senha.getText().toString().equals("")) {

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        } else {

            j.setNome(nome.getText().toString());
            j.setEmail(email.getText().toString());
            j.setSenha(senha.getText().toString());
            j.setDinheiro(0);
            j.setIdAvatar(imagensTreinadores.get(imagemAtual));

            JogadorDAO jogadorDAO = new JogadorDAO(this);
            //        jogadorDAO.inserir(j);

            try {
                jogadorDAO.inserir(j);

                Toast.makeText(this, "Jogador criado com sucesso!", Toast.LENGTH_SHORT).show();

                //Encaminhar para o menu principal
                Intent intent = new Intent(CadastroActivity.this, MenuPrincipalActivity.class);
                intent.putExtra("jogador", j);
                startActivity(intent);

            } catch (SQLException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

}
