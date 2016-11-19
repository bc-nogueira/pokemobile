package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.Service.UtilidadesService;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private UtilidadesService utilidadesService = new UtilidadesService();

    private PokemonTreinador pokemonTreinadorInimigo;
    private PokemonTreinador pokemonTreinador;

//    private Integer vez = 0; //0-> jogador - 1-> inimigo

    private Integer rodada;
    /*
    0 -> Começo, preparando views e mensagem inicial informa qual pokemon apareceu. Botões desabilitados.
    1 ->
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalha_selvagem);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        //Coloca pokemon inimigo
        pokemonTreinadorInimigo = pokemonTreinadorService.criaPokemonBatalhaSelvagem(this);

        ImageView pokemonInimigo = (ImageView) findViewById(R.id.pokemonFrenteBatalhaSelvagem);
        pokemonInimigo.setImageResource(pokemonTreinadorInimigo.getPokemon().getIconeFrente());

        TextView nomePokemonInimigo = (TextView) findViewById(R.id.nomeInimigoTextViewBatalhaSelvagem);
        nomePokemonInimigo.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinadorInimigo));

        ProgressBar hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaSelvagem);
        Double porcentagemHPInimigo = (pokemonTreinadorInimigo.getHpAtual()/pokemonTreinadorInimigo.getHpTotal()) * 100;
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());

        TextView hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaSelvagem);
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());

        //Coloca pokemon jogador
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
        pokemonTreinador = pokemonTreinadorDAO.buscarPrimeiroNaFilaPorId(treinador, this);

        ImageView pokemon = (ImageView) findViewById(R.id.pokemonCostasBatalhaSelvagem);
        pokemon.setImageResource(pokemonTreinador.getPokemon().getIconeCostas());

        TextView nomePokemon = (TextView) findViewById(R.id.nomeTextViewBatalhaSelvagem);
        nomePokemon.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador));

        ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpProgressBarBatalhaSelvagem);
        Double porcentagemHP = (pokemonTreinador.getHpAtual()/pokemonTreinador.getHpTotal()) * 100;
        hpBar.setProgress(porcentagemHP.intValue());

        TextView hpText = (TextView) findViewById(R.id.hpTextViewBatalhaSelvagem);
        hpText.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());

        Button ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaSelvagem);
        ataque1.setText(pokemonTreinador.getAtaque1().getNomeAtaque());

        Button ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaSelvagem);
        if(pokemonTreinador.getAtaque2() != null) {
            ataque2.setText(pokemonTreinador.getAtaque2().getNomeAtaque());
        } else {
            ataque2.setVisibility(View.INVISIBLE);
        }

        desabilitarButtons();

        TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        mensagem.setText("Um " + pokemonTreinadorInimigo.getPokemon().getNome() + " apareceu.");

        rodada = 0;

        //TODO: Controlar rodadas. Iniciar com numero random.
        //TODO: Proibir e liberar uso de botoes de acordo com rodada.
        //TODO: Mostrar mensagem de acordo com rodada.

    }

    @Override
    public void onBackPressed() {
    }

    public void proximaRodada(View v) {
        TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        ImageView prox = (ImageView) findViewById(R.id.proxMensagemBatalhaSelvagem);

        switch (rodada) {
            case 0:
                //Verifica de quem é a vez
                if(utilidadesService.gerarNumeroAleatorio(10) < 7) {
                    mensagem.setText("Sua vez. O que deseja fazer?");
                    habilitarButtons();
                    prox.setVisibility(View.INVISIBLE);
                    rodada = 1;
                } else {
                    mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\ntoma a \niniciativa.");
                    rodada = 2;
                }
                break;
            case 1:
                mensagem.setText("Seria vez jogador.");
                break;
            case 2:
                //TODO: Sortear qual ataque usar
                mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + " \nusa" +
                        "\n" + pokemonTreinador.getAtaque1().getNomeAtaque());
                rodada = 3;
                break;
            case 3:
                mensagem.setText("Sua vez. O que deseja fazer?");
                habilitarButtons();
                prox.setVisibility(View.INVISIBLE);
                break;
        }


    }

    public void fugir(View v) {

        if(utilidadesService.gerarNumeroAleatorio(10) < 7) {

            Toast.makeText(this, "Você fugiu!", Toast.LENGTH_SHORT).show();

            Intent menuPrincipal = new Intent(BatalhaSelvagemActivity.this, MenuPrincipalActivity.class);
            menuPrincipal.putExtra("treinador", treinador);
            startActivity(menuPrincipal);

        } else {

            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
            mensagem.setText("Não foi possível fugir.");

        }

    }

    public void habilitarButtons() {
        Button ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaSelvagem);
        ataque1.setEnabled(true);

        Button ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaSelvagem);
        ataque2.setEnabled(true);

        Button item = (Button) findViewById(R.id.itemButtonBatalhaSelvagem);
        item.setEnabled(true);

        Button fugir = (Button) findViewById(R.id.fugirButtonBatalhaSelvagem);
        fugir.setEnabled(true);
    }

    public void desabilitarButtons() {
        Button ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaSelvagem);
        ataque1.setEnabled(false);

        Button ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaSelvagem);
        ataque2.setEnabled(false);

        Button item = (Button) findViewById(R.id.itemButtonBatalhaSelvagem);
        item.setEnabled(false);

        Button fugir = (Button) findViewById(R.id.fugirButtonBatalhaSelvagem);
        fugir.setEnabled(false);
    }


}
