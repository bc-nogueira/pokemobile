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
import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    private List<Integer> imagensTreinadores;
    private int imagemAtual;

    private Integer teste = 0;

    Jogador j = new Jogador();
    Treinador t = new Treinador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        imagensTreinadores = new ArrayList<>();
//        imagensTreinadores.add(R.drawable.treinador1);
//        imagensTreinadores.add(R.drawable.treinador2);
//        imagensTreinadores.add(R.drawable.treinador3);
//        imagensTreinadores.add(R.drawable.treinadora1);
//        imagensTreinadores.add(R.drawable.treinadora2);
//        imagensTreinadores.add(R.drawable.treinadora3);
        imagensTreinadores.add(0);
        imagensTreinadores.add(1);
        imagensTreinadores.add(2);
        imagensTreinadores.add(3);
        imagensTreinadores.add(4);
        imagensTreinadores.add(5);



        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        treinador.setImageResource(t.getImagemTreinador(teste));
        imagemAtual = 0;
    }

    public void proximo(View v) {
        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        if(teste == 5) {
            teste = -1;
        }
        treinador.setImageResource(t.getImagemTreinador(++teste));
    }

    public void anterior(View v) {
        ImageView treinador = (ImageView) findViewById(R.id.treinadorCriar);
        if(teste == 0) {
            teste = 6;
        }
        treinador.setImageResource(t.getImagemTreinador(--imagemAtual));
    }

    public void cadastrar(View v) {
        EditText nome = (EditText) findViewById(R.id.nomeCriarEdit);
        EditText email = (EditText) findViewById(R.id.emailCriarEdit);
        EditText senha = (EditText) findViewById(R.id.senhaCriarEdit);

        if(nome.getText().toString().equals("") || email.getText().toString().equals("") || senha.getText().toString().equals("")) {

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        } else {

            j.setEmail(email.getText().toString());
            j.setSenha(senha.getText().toString());

            t.setNome(nome.getText().toString());
            t.setDinheiro(200);
            t.setIdAvatar(teste);

            JogadorDAO jogadorDAO = new JogadorDAO(this);

            try {
                Long idJogador = jogadorDAO.inserir(j);
                t.setIdJogador(idJogador);

                TreinadorDAO treinadorDAO = new TreinadorDAO(this);
                treinadorDAO.inserir(t);

                Toast.makeText(this, "Jogador criado com sucesso!", Toast.LENGTH_SHORT).show();

                //Encaminhar para o menu principal
                Intent intent = new Intent(CadastroActivity.this, MenuPrincipalActivity.class);
                intent.putExtra("treinador", t);
                startActivity(intent);

            } catch (SQLException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

}
