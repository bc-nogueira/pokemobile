package com.example.breno.pokemobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.BatalhaSelvagemService;
import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.Service.UtilidadesService;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaTreinadorActivity extends AppCompatActivity {
    private Treinador treinador;
    private ItemTreinador itemTreinador;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private BatalhaSelvagemService batalhaSelvagemService = new BatalhaSelvagemService();
    private UtilidadesService utilidadesService = new UtilidadesService();

    private PokemonTreinador pokemonTreinador;
    private ImageView pokemon;
    private TextView nomePokemon;
    private ProgressBar hpBarJogador;
    private TextView hpTextJogador;
    private TextView lvlPokemon;
    private Button ataque1;
    private Button ataque2;
    private LinearLayout border1;

    private PokemonTreinador pokemonTreinadorInimigo;
    private ProgressBar hpBarInimigo;
    private TextView hpTextInimigo;
    private ImageView pokemonInimigo;
    private TextView nomePokemonInimigo;
    private TextView lvlPokemonInimigo;
    private LinearLayout border2;

    private TextView mensagem;
    private ImageView prox;
    private ImageView pokebola;

    private Integer etapa;

    private MediaPlayer mpBg;
    private MediaPlayer mpTackle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mpBg = MediaPlayer.create(getApplicationContext(), R.raw.battle1);
        this.mpBg.setLooping(true);
        this.mpTackle =  MediaPlayer.create(getApplicationContext(), R.raw.tackle);
        setContentView(R.layout.activity_batalha_treinador);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaTreinador);
        prox = (ImageView) findViewById(R.id.proxMensagemBatalhaTreinador);
        pokebola = (ImageView) findViewById(R.id.pokebolaImageViewBatalhaTreinador);

        //Dados para a View do pokemon do treinador
        pokemon = (ImageView) findViewById(R.id.pokemonCostasBatalhaTreinador);
        nomePokemon = (TextView) findViewById(R.id.nomeTextViewBatalhaTreinador);
        lvlPokemon = (TextView) findViewById(R.id.lvlTextViewBatalhaTreinador);
        hpBarJogador = (ProgressBar) findViewById(R.id.hpProgressBarBatalhaTreinador);
        hpTextJogador = (TextView) findViewById(R.id.hpTextViewBatalhaTreinador);
        ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaTreinador);
        ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaTreinador);
        border1 = (LinearLayout) findViewById(R.id.border1);

        //Dados para a View do pokemon inimigo
        pokemonInimigo = (ImageView) findViewById(R.id.pokemonFrenteBatalhaTreinador);
        nomePokemonInimigo = (TextView) findViewById(R.id.nomeInimigoTextViewBatalhaTreinador);
        lvlPokemonInimigo = (TextView) findViewById(R.id.lvlInimigoTextViewBatalhaTreinador);
        hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaTreinador);
        hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaTreinador);
        border2 = (LinearLayout) findViewById(R.id.border2);


        if((ItemTreinador) getIntent().getSerializableExtra("itemTreinador") != null) {

            itemTreinador = (ItemTreinador) getIntent().getSerializableExtra("itemTreinador");
            pokemonTreinador = (PokemonTreinador) getIntent().getSerializableExtra("pokemonTreinador");
            pokemonTreinadorInimigo = (PokemonTreinador) getIntent().getSerializableExtra("pokemonInimigo");

            this.desabilitarButtons();
            prox.setVisibility(View.INVISIBLE);

        } else {

            //Prepara o pokemon do jogador
            PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(this);
            pokemonTreinador = pokemonTreinadorDAO.buscarPrimeiroNaFilaPorId(treinador, this);

            //Prepara o pokemon do inimigo
            pokemonTreinadorInimigo = pokemonTreinadorService.criaPokemonBatalhaSelvagem(this, pokemonTreinador);

            etapa = -1;

            this.desabilitarButtons();

            pokemon.setVisibility(View.INVISIBLE);
            nomePokemon.setVisibility(View.INVISIBLE);
            lvlPokemon.setVisibility(View.INVISIBLE);
            hpBarJogador.setVisibility(View.INVISIBLE);
            hpTextJogador.setVisibility(View.INVISIBLE);
            pokemonInimigo.setVisibility(View.INVISIBLE);
            nomePokemonInimigo.setVisibility(View.INVISIBLE);
            lvlPokemonInimigo.setVisibility(View.INVISIBLE);
            hpBarInimigo.setVisibility(View.INVISIBLE);
            hpTextInimigo.setVisibility(View.INVISIBLE);
            border1.setVisibility(View.INVISIBLE);
            border2.setVisibility(View.INVISIBLE);

            ImageView iconeTreinador = (ImageView) findViewById(R.id.iconeTreinadorImageViewBatalhaTreinador);
            iconeTreinador.setVisibility(View.VISIBLE);

            mensagem.setText("Um treinador apareceu.");
            mpBg.start();
            //TODO: Colocar para gerar um treinador aleatoriamente, tanto nome, quanto icone, dos dois sexos.

        }

        //Dados para a View do pokemon do treinador
        pokemon.setImageResource(pokemonTreinador.getPokemon().getIconeCostas());
        nomePokemon.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador));
        lvlPokemon.setText("Lv." + pokemonTreinador.getLevel().toString());
        Double porcentagemHP = pokemonTreinadorService.calcularPorcentagemHP(pokemonTreinador);
        hpBarJogador.setProgress(porcentagemHP.intValue());
        hpTextJogador.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());
        ataque1.setText(pokemonTreinador.getAtaque1().getNomeAtaque());
        if(pokemonTreinador.getAtaque2() != null) {
            ataque2.setText(pokemonTreinador.getAtaque2().getNomeAtaque());
        } else {
            ataque2.setVisibility(View.INVISIBLE);
        }

        //Dados para a View do pokemon inimigo
        pokemonInimigo.setImageResource(pokemonTreinadorInimigo.getPokemon().getIconeFrente());
        nomePokemonInimigo.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinadorInimigo));
        lvlPokemonInimigo.setText("Lv." + pokemonTreinadorInimigo.getLevel().toString());
        Double porcentagemHPInimigo = pokemonTreinadorService.calcularPorcentagemHP(pokemonTreinadorInimigo);
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());



        if(itemTreinador != null) {

            if(itemTreinador.getItem().getTipo().equals(TipoItem.CURA)) {

                pokemonTreinador = batalhaSelvagemService.curarPokemon(pokemonTreinador,
                        itemTreinador, hpBarJogador, hpTextJogador, getApplicationContext());
                mensagem.setText("O HP foi recuperado.");
                prox.setVisibility(View.VISIBLE);
                etapa = 1;

            } /*else if(itemTreinador.getItem().getTipo().equals(TipoItem.CAPTURA)) {

                pokemonInimigo.setVisibility(View.INVISIBLE);
                pokebola.setImageResource(itemTreinador.getItem().getIcone());
                pokebola.setVisibility(View.VISIBLE);
                etapa = 3;
                prox.setVisibility(View.VISIBLE);
                mensagem.setText("Tentando\ncapturar o pokemon...");

            } */

        }

    }

    public void proximaEtapa(View v) {
        switch (etapa) {
            case -1:
                pokemon.setVisibility(View.VISIBLE);
                nomePokemon.setVisibility(View.VISIBLE);
                lvlPokemon.setVisibility(View.VISIBLE);
                hpBarJogador.setVisibility(View.VISIBLE);
                hpTextJogador.setVisibility(View.VISIBLE);
                pokemonInimigo.setVisibility(View.VISIBLE);
                nomePokemonInimigo.setVisibility(View.VISIBLE);
                lvlPokemonInimigo.setVisibility(View.VISIBLE);
                hpBarInimigo.setVisibility(View.VISIBLE);
                hpTextInimigo.setVisibility(View.VISIBLE);
                border1.setVisibility(View.VISIBLE);
                border2.setVisibility(View.VISIBLE);
                ImageView iconeTreinador = (ImageView) findViewById(R.id.iconeTreinadorImageViewBatalhaTreinador);
                iconeTreinador.setVisibility(View.INVISIBLE);

                mensagem.setText("Treinador usa\n" + pokemonTreinadorInimigo.getPokemon().getNome() + ".");

                etapa = 0;

                break;
            case 0:
                if(utilidadesService.gerarNumeroAleatorio(10) > 7) {
                    mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\ntoma\na iniciativa.");
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
                        mensagem, true, hpBarJogador, hpTextJogador);
                this.mpTackle.start();
                if(pokemonTreinador.getHpAtual() == 0) {
                    etapa = 5;
                } else {
                    etapa = 2;
                }
                break;
            case 2:
                mensagem.setText("Sua vez. O que deseja fazer?");
                habilitarButtons();
                prox.setVisibility(View.INVISIBLE);
                break;
//            case 3:
//                if(batalhaSelvagemService.capturarPokemon(treinador, itemTreinador, pokemonTreinadorInimigo, getApplicationContext())) {
//
//                    mensagem.setText("Você conseguiu capturar o pokemon.");
//                    prox.setVisibility(View.VISIBLE);
//                    etapa = 4;
//
//                } else {
//
//                    mensagem.setText("Você não conseguiu capturar o pokemon.");
//                    prox.setVisibility(View.VISIBLE);
//                    pokebola.setVisibility(View.INVISIBLE);
//                    pokemonInimigo.setVisibility(View.VISIBLE);
//                    etapa = 1;
//
//                }
//                break;
            case 4:
                this.irMenuPrincipal();
                break;
            case 5:
                mensagem.setText("Seu\n" + pokemonTreinadorService.apelidoOuNome(pokemonTreinador) + "\nmorreu.");

                etapa = 9;
                break;
            case 6:
                mensagem.setText(pokemonTreinadorInimigo.getPokemon().getNome() + "\nmorreu.");
                break;
            case 7:
                pokemonTreinador = batalhaSelvagemService.
                        adicionarExperiencia(pokemonTreinador, pokemonTreinadorInimigo.getLevel(), mensagem);

                if(pokemonTreinadorService.verificaSeEvoluiu(pokemonTreinador, getApplicationContext())) {
                    etapa = 8;
                } else {
                    etapa = 10;
                }
                break;
            case 8:
                mensagem.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador) +
                        "\nevoluiu para o\nlevel " + pokemonTreinador.getLevel() + "!");
                lvlPokemon.setText("Lv." + pokemonTreinador.getLevel());
                //Setar o hp
                hpTextJogador.setText
                        ("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());
                etapa = 10;
                break;
            case 9:
                this.irMenuPrincipal();
                break;
            case 10:
                Integer dinheiro = 100 + utilidadesService.gerarNumeroAleatorio(5)*10;
                mensagem.setText("Você ganhou\n" + dinheiro + "G.");
                treinador.setDinheiro(treinador.getDinheiro() + dinheiro);
                TreinadorDAO treinadorDAO = new TreinadorDAO(getApplicationContext());
                treinadorDAO.atualizarDinheiro(treinador);
                etapa = 9;
                break;
        }

    }

    public void usarAtaque1(View v) {
        pokemonTreinadorInimigo = batalhaSelvagemService.realizarAtaque(pokemonTreinador, pokemonTreinadorInimigo,
                pokemonTreinador.getAtaque1(), mensagem, false, hpBarInimigo, hpTextInimigo);

        prox.setVisibility(View.VISIBLE);
        this.desabilitarButtons();

        if(pokemonTreinadorInimigo.getHpAtual() == 0) {
            etapa = 7;
        } else {
            etapa = 1;
        }
        this.mpTackle.start();
    }

    public void usarAtaque2(View v) {
        pokemonTreinadorInimigo = batalhaSelvagemService.realizarAtaque(pokemonTreinador, pokemonTreinadorInimigo,
                pokemonTreinador.getAtaque2(), mensagem, false, hpBarInimigo, hpTextInimigo);

        prox.setVisibility(View.VISIBLE);
        this.desabilitarButtons();

        if(pokemonTreinadorInimigo.getHpAtual() == 0) {
            etapa = 6;
        } else {
            etapa = 1;
        }
        this.mpTackle.start();
    }

    public void fugir(View v) {
        if(utilidadesService.gerarNumeroAleatorio(10) < 7) {
            Toast.makeText(this, "Você fugiu!", Toast.LENGTH_SHORT).show();
            irMenuPrincipal();
        } else {
            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaTreinador);
            mensagem.setText("Não foi possível fugir.");

            this.desabilitarButtons();
            prox.setVisibility(View.VISIBLE);
            etapa = 1;
        }
    }

    public void irMenuPrincipal() {
        this.mpBg.stop();
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
        pokemonTreinadorDAO.atualizarHpAtualHpTotalExpLvl(pokemonTreinador);

        Intent menuPrincipal = new Intent(BatalhaTreinadorActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        startActivity(menuPrincipal);
    }

    public void itemBatalha(View v) {
        Intent itemBatalha = new Intent(BatalhaTreinadorActivity.this, ItemBatalhaActivity.class);
        itemBatalha.putExtra("treinador", treinador);
        itemBatalha.putExtra("pokemonTreinador", pokemonTreinador);
        itemBatalha.putExtra("pokemonInimigo", pokemonTreinadorInimigo);
        itemBatalha.putExtra("isSelvagem", false);
        startActivity(itemBatalha);
    }

    public void habilitarButtons() {
        Button ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaTreinador);
        ataque1.setEnabled(true);

        Button ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaTreinador);
        ataque2.setEnabled(true);

        Button item = (Button) findViewById(R.id.itemButtonBatalhaTreinador);
        item.setEnabled(true);

        Button fugir = (Button) findViewById(R.id.fugirButtonBatalhaTreinador);
        fugir.setEnabled(true);
    }

    public void desabilitarButtons() {
        Button ataque1 = (Button) findViewById(R.id.ataque1ButtonBatalhaTreinador);
        ataque1.setEnabled(false);

        Button ataque2 = (Button) findViewById(R.id.ataque2ButtonBatalhaTreinador);
        ataque2.setEnabled(false);

        Button item = (Button) findViewById(R.id.itemButtonBatalhaTreinador);
        item.setEnabled(false);

        Button fugir = (Button) findViewById(R.id.fugirButtonBatalhaTreinador);
        fugir.setEnabled(false);
    }
}
