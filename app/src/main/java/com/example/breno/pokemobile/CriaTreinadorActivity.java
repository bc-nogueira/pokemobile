package com.example.breno.pokemobile;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class CriaTreinadorActivity extends AppCompatActivity {
    private Jogador jogador;
    private Treinador treinador;
    private Integer imagemAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_treinador);

        jogador = (Jogador) getIntent().getSerializableExtra("jogador");

        ImageView treinadorIV = (ImageView) findViewById(R.id.avatarImageViewCriarTreinador);
        treinadorIV.setImageResource(treinador.getImagemTreinador(imagemAtual));
    }

    public void anterior(View v) {
        ImageView treinadorIV = (ImageView) findViewById(R.id.avatarImageViewCriarTreinador);
        if(imagemAtual == 0) {
            imagemAtual = 12;
        }
        treinadorIV.setImageResource(treinador.getImagemTreinador(--imagemAtual));
    }

    public void proximo(View v) {
        ImageView treinadorIV = (ImageView) findViewById(R.id.avatarImageViewCriarTreinador);
        if(imagemAtual == 11) {
            imagemAtual = -1;
        }
        treinadorIV.setImageResource(treinador.getImagemTreinador(++imagemAtual));
    }

    public void criarTreinador(View v) {
        EditText nome = (EditText) findViewById(R.id.nomeEditTextCriarTreinador);

        if(nome.getText().toString().equals("")) {

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        } else {

            treinador = new Treinador(nome.getText().toString(), 200, imagemAtual, jogador.getIdJogador());

            TreinadorDAO treinadorDAO = new TreinadorDAO(this);
            try {

                treinadorDAO.inserir(treinador);

                Toast.makeText(this, "Treinador criado com sucesso!", Toast.LENGTH_SHORT).show();

                //Encaminhar para o menu principal
                Intent intent = new Intent(CriaTreinadorActivity.this, MenuPrincipalActivity.class);
                intent.putExtra("treinador", treinador);
                startActivity(intent);

            } catch (SQLException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
}
