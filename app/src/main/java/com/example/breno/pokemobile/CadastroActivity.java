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

public class CadastroActivity extends AppCompatActivity {
    private Jogador jogador;
    private Treinador treinador;
    private Integer imagemAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ImageView treinadorIV = (ImageView) findViewById(R.id.treinadorCriar);
        treinadorIV.setImageResource(Treinador.getImagemTreinador(imagemAtual));
    }

    public void anterior(View v) {
        ImageView treinadorIV = (ImageView) findViewById(R.id.treinadorCriar);
        if(imagemAtual == 0) {
            imagemAtual = 12;
        }
        treinadorIV.setImageResource(Treinador.getImagemTreinador(--imagemAtual));
    }

    public void proximo(View v) {
        ImageView treinadorIV = (ImageView) findViewById(R.id.treinadorCriar);
        if(imagemAtual == 11) {
            imagemAtual = -1;
        }
        treinadorIV.setImageResource(Treinador.getImagemTreinador(++imagemAtual));
    }

    public void cadastrar(View v) {
        EditText nome = (EditText) findViewById(R.id.nomeCriarEdit);
        EditText email = (EditText) findViewById(R.id.emailCriarEdit);
        EditText senha = (EditText) findViewById(R.id.senhaCriarEdit);

        if(nome.getText().toString().equals("") || email.getText().toString().equals("") || senha.getText().toString().equals("")) {

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        } else {

            jogador = new Jogador(email.getText().toString(), senha.getText().toString());
            treinador = new Treinador(nome.getText().toString(), 200, imagemAtual, null);

            JogadorDAO jogadorDAO = new JogadorDAO(this);

            try {
                Long idJogador = jogadorDAO.inserir(jogador);
                treinador.setIdJogador(idJogador);

                TreinadorDAO treinadorDAO = new TreinadorDAO(this);
                Long idTreinador = treinadorDAO.inserir(treinador);

                treinador.setIdTreinador(idTreinador);

                Toast.makeText(this, "Jogador criado com sucesso!", Toast.LENGTH_SHORT).show();

                //Encaminhar para o menu principal
//                Intent intent = new Intent(CadastroActivity.this, MenuPrincipalActivity.class);
//                intent.putExtra("treinador", treinador);
//                startActivity(intent);

                Intent selecionaPokemon = new Intent(CadastroActivity.this, SelecionaPokemonActivity.class);
                selecionaPokemon.putExtra("treinador", treinador);
                startActivity(selecionaPokemon);

            } catch (SQLException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

}
