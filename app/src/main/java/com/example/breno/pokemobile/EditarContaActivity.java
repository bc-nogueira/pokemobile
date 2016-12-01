package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.breno.pokemobile.db.JogadorDAO;
import com.example.breno.pokemobile.modelo.Jogador;
import com.example.breno.pokemobile.modelo.Treinador;

public class EditarContaActivity extends AppCompatActivity {
    private Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");



    }

    public void alterar(View v) {

        EditText senhaAntigaET = (EditText) findViewById(R.id.senhaAntigaEditTextEditarConta);
        String senhaAntiga = senhaAntigaET.getText().toString();

        EditText senhaNovaET = (EditText) findViewById(R.id.senhaNovaEditTextEditarConta);
        String senhaNova = senhaNovaET.getText().toString();

        EditText confirmarET = (EditText) findViewById(R.id.confirmarEditTextEditarConta);
        String confirmar = confirmarET.getText().toString();

        JogadorDAO jogadorDAO = new JogadorDAO(getApplicationContext());

        Jogador jogador = jogadorDAO.buscarPorId(treinador.getIdJogador());

        if(senhaAntiga.isEmpty() || senhaNova.isEmpty() || confirmar.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Favor preencher todos os campos.", Toast.LENGTH_SHORT).show();
        } else if(!senhaAntiga.equals(jogador.getSenha())) {
            Toast.makeText(getApplicationContext(), "Senha antiga incorreta.", Toast.LENGTH_SHORT).show();
        } else if(senhaAntiga.equals(senhaNova)) {
            Toast.makeText(getApplicationContext(), "Senha nova igual a senha antiga.", Toast.LENGTH_SHORT).show();
        } else if(!senhaNova.equals(confirmar)) {
            Toast.makeText(getApplicationContext(), "Nova senha e confirmação não estão iguais.", Toast.LENGTH_SHORT).show();
        } else {
            jogador.setSenha(senhaNova);
            jogadorDAO.atualizarSenha(jogador);
            Toast.makeText(getApplicationContext(), "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
            senhaAntigaET.setText("");
            senhaNovaET.setText("");
            confirmarET.setText("");
        }

    }
}
