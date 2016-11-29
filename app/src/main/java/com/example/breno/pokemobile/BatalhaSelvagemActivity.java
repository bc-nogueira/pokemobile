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

import com.example.breno.pokemobile.Service.BatalhaSelvagemService;
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
    private BatalhaSelvagemService batalhaSelvagemService = new BatalhaSelvagemService();

    private PokemonTreinador pokemonTreinadorInimigo;
    private ProgressBar hpBarInimigo;
    private TextView hpTextInimigo;

    private PokemonTreinador pokemonTreinador;
    private ProgressBar hpBarJogador;
    private TextView hpTextJogador;

    private TextView mensagem;
    private ImageView prox;

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

        this.desabilitarButtons();

        mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        mensagem.setText("Um " + pokemonTreinadorInimigo.getPokemon().getNome() + "\nselvagem apareceu.");

        prox = (ImageView) findViewById(R.id.proxMensagemBatalhaSelvagem);

        etapa = 0;
    }

    @Override
    public void onBackPressed() {
    }

    public void proximaEtapa(View v) {
        switch (etapa) {
            case 0:
                if(batalhaSelvagemService.gerarNumeroAleatorio(10) > 7) {
                    mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\nselvagem toma\na iniciativa.");
                    etapa = 1;

                } else {
                    mensagem.setText("Sua vez. O que deseja fazer?");
                    habilitarButtons();
                    prox.setVisibility(View.INVISIBLE);
                    break;
                }
                break;
            case 1:
                pokemonTreinador = batalhaSelvagemService.realizarAtaque(pokemonTreinadorInimigo, pokemonTreinador, null,
                        mensagem, "selvagem ", hpBarJogador, hpTextJogador);

                if(pokemonTreinador.getHpAtual() == 0) {
                    etapa = 8;
                } else {
                    etapa = 2;
                }
                break;
            case 2:
                mensagem.setText("Sua vez. O que deseja fazer?");
                habilitarButtons();
                prox.setVisibility(View.INVISIBLE);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 8:
                mensagem.setText("Seu\n" + pokemonTreinador.getPokemon().getNome() + "\nmorreu.");
                etapa = 10;
                break;
            case 9:
                mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\nselvagem\nmorreu.");
                pokemonTreinador = batalhaSelvagemService.adicionarExperiencia(pokemonTreinador, pokemonTreinadorInimigo.getLevel());
                etapa = 10;
                break;
            case 10:
                this.irMenuPrincipal();
                break;
        }


    }

    public void usarAtaque1(View v) {
        pokemonTreinadorInimigo = batalhaSelvagemService.realizarAtaque(pokemonTreinador, pokemonTreinadorInimigo,
                pokemonTreinador.getAtaque1(), mensagem, "", hpBarInimigo, hpTextInimigo);

        prox.setVisibility(View.VISIBLE);
        this.desabilitarButtons();

        if(pokemonTreinadorInimigo.getHpAtual() == 0) {
            etapa = 9;
        } else {
            etapa = 1;
        }
    }

    public void usarAtaque2(View v) {
        pokemonTreinadorInimigo = batalhaSelvagemService.realizarAtaque(pokemonTreinador, pokemonTreinadorInimigo,
                pokemonTreinador.getAtaque2(), mensagem, "", hpBarInimigo, hpTextInimigo);

        prox.setVisibility(View.VISIBLE);
        this.desabilitarButtons();

        if(pokemonTreinadorInimigo.getHpAtual() == 0) {
            etapa = 9;
        } else {
            etapa = 1;
        }
    }

    public void fugir(View v) {
        if(utilidadesService.gerarNumeroAleatorio(10) < 7) {
            Toast.makeText(this, "Você fugiu!", Toast.LENGTH_SHORT).show();
            irMenuPrincipal();
        } else {
            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
            mensagem.setText("Não foi possível fugir.");
        }
    }

    public void irMenuPrincipal() {

        Intent menuPrincipal = new Intent(BatalhaSelvagemActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        startActivity(menuPrincipal);

        //TODO: Salvar HP do pokemon do treinador

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
