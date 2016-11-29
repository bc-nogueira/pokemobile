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
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private ItemTreinador itemTreinador;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private BatalhaSelvagemService batalhaSelvagemService = new BatalhaSelvagemService();

    private PokemonTreinador pokemonTreinadorInimigo;
    private ProgressBar hpBarInimigo;
    private TextView hpTextInimigo;
    private ImageView pokemonInimigo;

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

        mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        prox = (ImageView) findViewById(R.id.proxMensagemBatalhaSelvagem);

        if((ItemTreinador) getIntent().getSerializableExtra("itemTreinador") != null) {

            itemTreinador = (ItemTreinador) getIntent().getSerializableExtra("itemTreinador");
            pokemonTreinador = (PokemonTreinador) getIntent().getSerializableExtra("pokemonTreinador");
            pokemonTreinadorInimigo = (PokemonTreinador) getIntent().getSerializableExtra("pokemonInimigo");

            this.desabilitarButtons();
            prox.setVisibility(View.INVISIBLE);

        } else {

            //Prepara o pokemon do inimigo
            pokemonTreinadorInimigo = pokemonTreinadorService.criaPokemonBatalhaSelvagem(this);

            //Prepara o pokemon do jogador
            PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
            pokemonTreinador = pokemonTreinadorDAO.buscarPrimeiroNaFilaPorId(treinador, this);

            etapa = 0;

            this.desabilitarButtons();

            mensagem.setText("Um " + pokemonTreinadorInimigo.getPokemon().getNome() + "\nselvagem apareceu.");

        }

        //Dados para a View do pokemon inimigo
        pokemonInimigo = (ImageView) findViewById(R.id.pokemonFrenteBatalhaSelvagem);
        pokemonInimigo.setImageResource(pokemonTreinadorInimigo.getPokemon().getIconeFrente());

        TextView nomePokemonInimigo = (TextView) findViewById(R.id.nomeInimigoTextViewBatalhaSelvagem);
        nomePokemonInimigo.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinadorInimigo));

        hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaSelvagem);
        Double porcentagemHPInimigo = (pokemonTreinadorInimigo.getHpAtual()/pokemonTreinadorInimigo.getHpTotal()) * 100;
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());

        hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaSelvagem);
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());

        //Dados para a View do pokemon do treinador
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

        if(itemTreinador != null) {

            if(itemTreinador.getItem().getTipo().equals(TipoItem.CURA)) {

                pokemonTreinador = batalhaSelvagemService.curarPokemon(pokemonTreinador,
                        itemTreinador, hpBarJogador, hpTextJogador, getApplicationContext());
                mensagem.setText("O HP foi recuperado.");
                prox.setVisibility(View.VISIBLE);
                etapa = 1;

            } else if(itemTreinador.getItem().getTipo().equals(TipoItem.CAPTURA)) {

                pokemonInimigo.setVisibility(View.INVISIBLE);
                etapa = 3;
                prox.setVisibility(View.VISIBLE);
                mensagem.setText("Tentando\ncapturar o pokemon...");

            }

        }

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
                if(batalhaSelvagemService.capturarPokemon(treinador, itemTreinador, pokemonTreinadorInimigo, getApplicationContext())) {

                    mensagem.setText("Você conseguiu capturar o pokemon.");
                    prox.setVisibility(View.VISIBLE);
                    etapa = 4;

                } else {

                    mensagem.setText("Você não conseguiu capturar o pokemon.");
                    prox.setVisibility(View.VISIBLE);
                    pokemonInimigo.setVisibility(View.VISIBLE);
                    etapa = 1;

                }
                break;
            case 4:
                this.irMenuPrincipal();
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
        if(batalhaSelvagemService.gerarNumeroAleatorio(10) < 7) {
            Toast.makeText(this, "Você fugiu!", Toast.LENGTH_SHORT).show();
            irMenuPrincipal();
        } else {
            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
            mensagem.setText("Não foi possível fugir.");

            this.desabilitarButtons();
            prox.setVisibility(View.VISIBLE);
            etapa = 1;
        }
    }

    public void irMenuPrincipal() {

        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
        pokemonTreinadorDAO.atualizarHpAtual(pokemonTreinador);

        Intent menuPrincipal = new Intent(BatalhaSelvagemActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        startActivity(menuPrincipal);

    }

    public void itemBatalha(View v) {
        Intent itemBatalha = new Intent(BatalhaSelvagemActivity.this, ItemBatalhaActivity.class);
        itemBatalha.putExtra("treinador", treinador);
        itemBatalha.putExtra("pokemonTreinador", pokemonTreinador);
        itemBatalha.putExtra("pokemonInimigo", pokemonTreinadorInimigo);
        itemBatalha.putExtra("isSelvagem", true);
        startActivity(itemBatalha);
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
