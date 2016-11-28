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
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private UtilidadesService utilidadesService = new UtilidadesService();

    private PokemonTreinador pokemonTreinadorInimigo;
    private ProgressBar hpBarInimigo;
    private TextView hpTextInimigo;

    private PokemonTreinador pokemonTreinador;
    private ProgressBar hpBarJogador;
    private TextView hpTextJogador;

    private Integer etapa;

    private Ataque ataqueJogador;
    private Ataque ataqueInimigo;

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

        hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaSelvagem);
        Double porcentagemHPInimigo = (pokemonTreinadorInimigo.getHpAtual()/pokemonTreinadorInimigo.getHpTotal()) * 100;
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());

        hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaSelvagem);
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());

        //Coloca pokemon jogador
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
        pokemonTreinador = pokemonTreinadorDAO.buscarPrimeiroNaFilaPorId(treinador, this);

        ImageView pokemon = (ImageView) findViewById(R.id.pokemonCostasBatalhaSelvagem);
        pokemon.setImageResource(pokemonTreinador.getPokemon().getIconeCostas());

        TextView nomePokemon = (TextView) findViewById(R.id.nomeTextViewBatalhaSelvagem);
        nomePokemon.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador));

        hpBarJogador = (ProgressBar) findViewById(R.id.hpProgressBarBatalhaSelvagem);
        Double porcentagemHP = (pokemonTreinador.getHpAtual()/pokemonTreinador.getHpTotal()) * 100;
        hpBarJogador.setProgress(porcentagemHP.intValue());

        hpTextJogador = (TextView) findViewById(R.id.hpTextViewBatalhaSelvagem);
        hpTextJogador.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());

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

        etapa = 0;

        //TODO: Controlar rodadas. Iniciar com numero random.
        //TODO: Proibir e liberar uso de botoes de acordo com rodada.
        //TODO: Mostrar mensagem de acordo com rodada.

    }

    @Override
    public void onBackPressed() {
    }

    public void proximaEtapa(View v) {
        TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        ImageView prox = (ImageView) findViewById(R.id.proxMensagemBatalhaSelvagem);

        switch (etapa) {
            case 0:
                //Verifica de quem é a vez
                if(10 > 7) {
//                if(utilidadesService.gerarNumeroAleatorio(10) > 7) {
                    mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\ntoma a \niniciativa.");
                    etapa = 1;

                } else {
                    mensagem.setText("Sua vez. O que deseja fazer?");
                    habilitarButtons();
                    prox.setVisibility(View.INVISIBLE);
                    etapa = 4;
                }
                break;
            case 1:
                //TODO: Ver qual ataque usar
                if(pokemonTreinadorInimigo.getAtaque2() == null) {
                    ataqueInimigo = pokemonTreinadorInimigo.getAtaque1();
                } else {
                    //TODO: Sortear entre ataque1 ou ataque2
                }
                mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + " usou " + ataqueInimigo.getNomeAtaque() + ".");
                //TODO: calcular dano
                Integer dano = utilidadesService.gerarNumeroAleatorio(10) * ataqueInimigo.getDanoBase();
                //TODO: retirar HP do pokemon do jogador
                pokemonTreinador.setHpAtual(pokemonTreinador.getHpTotal() - dano);
                //TODO: Passar isso para um método
                Double porcentagemFinal = (pokemonTreinador.getHpAtual()/pokemonTreinador.getHpTotal()) * 100;
                Integer porcentagemAtual = hpBarJogador.getProgress();
                ProgressBarAnimation anim = new ProgressBarAnimation
                        (hpBarJogador, porcentagemAtual.floatValue(), porcentagemFinal.floatValue());
                anim.setDuration(500);
                hpBarJogador.startAnimation(anim);

                hpTextJogador.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());

                etapa = 2;
                break;
            case 2:
                mensagem.setText("Sua vez. O que deseja fazer?");
                habilitarButtons();
                prox.setVisibility(View.INVISIBLE);
                etapa = 4;
                break;
            case 3:
                //TODO: Sortear qual ataque usar
                mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + " \nusa" +
                        "\n" + pokemonTreinador.getAtaque1().getNomeAtaque());
                etapa = 3;
                break;
            case 4:

            case 5:
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
