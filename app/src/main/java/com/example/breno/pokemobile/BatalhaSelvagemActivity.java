package com.example.breno.pokemobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.BatalhaSelvagemService;
import com.example.breno.pokemobile.Service.ConquistaService;
import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.Service.TreinadorService;
import com.example.breno.pokemobile.Service.UtilidadesService;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.BatalhaBg;
import com.example.breno.pokemobile.modelo.ConquistaTreinador;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

public class BatalhaSelvagemActivity extends AppCompatActivity {
    private Treinador treinador;
    private ItemTreinador itemTreinador;
    private ConquistaTreinador ct;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private BatalhaSelvagemService batalhaSelvagemService = new BatalhaSelvagemService();
    private UtilidadesService utilidadesService = new UtilidadesService();
    private TreinadorService treinadorService = new TreinadorService();
    private ConquistaService conquistaService = new ConquistaService();

    private PokemonTreinador pokemonTreinadorInimigo;
    private ProgressBar hpBarInimigo;
    private TextView hpTextInimigo;
    private ImageView pokemonInimigo;

    private PokemonTreinador pokemonTreinador;
    private ProgressBar hpBarJogador;
    private TextView hpTextJogador;
    private TextView lvlPokemon;

    private TextView mensagem;
    private ImageView prox;
    private ImageView pokebola;

    private Integer etapa;

    private MediaPlayer mpTackle;
    private MediaPlayer mpLevelUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!BatalhaBg.isSoundPlaying()){
            BatalhaBg.playLoop(getApplicationContext(), R.raw.battle1);
        }
        this.mpTackle =  MediaPlayer.create(getApplicationContext(), R.raw.tackle);
        this.mpLevelUp =  MediaPlayer.create(getApplicationContext(), R.raw.levelup);
        setContentView(R.layout.activity_batalha_selvagem);
        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
        prox = (ImageView) findViewById(R.id.proxMensagemBatalhaSelvagem);
        pokebola = (ImageView) findViewById(R.id.pokebolaImageViewBatalhaSelvagem);

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

            etapa = 0;

            this.desabilitarButtons();

            mensagem.setText("Um " + pokemonTreinadorInimigo.getPokemon().getNome() + "\nselvagem apareceu.");
        }

        //Dados para a View do pokemon do treinador
        ImageView pokemon = (ImageView) findViewById(R.id.pokemonCostasBatalhaSelvagem);
        pokemon.setImageResource(pokemonTreinador.getPokemon().getIconeCostas());

        TextView nomePokemon = (TextView) findViewById(R.id.nomeTextViewBatalhaSelvagem);
        nomePokemon.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador));

        lvlPokemon = (TextView) findViewById(R.id.lvlTextViewBatalhaSelvagem);
        lvlPokemon.setText("Lv." + pokemonTreinador.getLevel().toString());

        hpBarJogador = (ProgressBar) findViewById(R.id.hpProgressBarBatalhaSelvagem);
        Double porcentagemHP = pokemonTreinadorService.calcularPorcentagemHP(pokemonTreinador);
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

        //Dados para a View do pokemon inimigo
        pokemonInimigo = (ImageView) findViewById(R.id.pokemonFrenteBatalhaSelvagem);
        pokemonInimigo.setImageResource(pokemonTreinadorInimigo.getPokemon().getIconeFrente());

        TextView nomePokemonInimigo = (TextView) findViewById(R.id.nomeInimigoTextViewBatalhaSelvagem);
        nomePokemonInimigo.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinadorInimigo));

        TextView lvlPokemonInimigo = (TextView) findViewById(R.id.lvlInimigoTextViewBatalhaSelvagem);
        lvlPokemonInimigo.setText("Lv." + pokemonTreinadorInimigo.getLevel().toString());

        hpBarInimigo = (ProgressBar) findViewById(R.id.hpInimigoProgressBarBatalhaSelvagem);
        Double porcentagemHPInimigo = pokemonTreinadorService.calcularPorcentagemHP(pokemonTreinadorInimigo);
        hpBarInimigo.setProgress(porcentagemHPInimigo.intValue());

        hpTextInimigo = (TextView) findViewById(R.id.hpInimigoTextViewBatalhaSelvagem);
        hpTextInimigo.setText("HP: " + pokemonTreinadorInimigo.getHpAtual().intValue() + " / " + pokemonTreinadorInimigo.getHpTotal().intValue());

        if(itemTreinador != null) {

            if(itemTreinador.getItem().getTipo().equals(TipoItem.CURA)) {

                pokemonTreinador = batalhaSelvagemService.curarPokemon(pokemonTreinador,
                        itemTreinador, hpBarJogador, hpTextJogador, getApplicationContext());
                mensagem.setText("O HP foi recuperado.");
                prox.setVisibility(View.VISIBLE);
                etapa = 1;

            } else if(itemTreinador.getItem().getTipo().equals(TipoItem.CAPTURA)) {

                pokemonInimigo.setVisibility(View.INVISIBLE);
                pokebola.setImageResource(itemTreinador.getItem().getIcone());
                pokebola.setVisibility(View.VISIBLE);
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
                if(utilidadesService.gerarNumeroAleatorio(10) > 7) {
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
            case 3:
                if(batalhaSelvagemService.capturarPokemon(treinador, itemTreinador, pokemonTreinadorInimigo, getApplicationContext())) {

                    mensagem.setText("Você conseguiu capturar o pokemon.");

                    ct = conquistaService.alcancouConquistaQuantPokemon(treinador, getApplicationContext());

                    if(ct != null) {

                        etapa = 10;

                    } else {
                        etapa = 4;
                    }

                    prox.setVisibility(View.VISIBLE);

                } else {

                    mensagem.setText("Você não conseguiu capturar o pokemon.");
                    prox.setVisibility(View.VISIBLE);
                    pokebola.setVisibility(View.INVISIBLE);
                    pokemonInimigo.setVisibility(View.VISIBLE);
                    etapa = 1;

                }
                break;
            case 4:
                this.irMenuPrincipal();
                break;
            case 5:
                mensagem.setText("Seu\n" + pokemonTreinadorService.apelidoOuNome(pokemonTreinador) + "\nmorreu.");

                etapa = 9;
                break;
            case 7:
                pokemonTreinador = batalhaSelvagemService.
                        adicionarExperiencia(pokemonTreinador, pokemonTreinadorInimigo.getLevel(), mensagem);

                if(pokemonTreinadorService.verificaSeEvoluiu(pokemonTreinador, getApplicationContext())) {
                    etapa = 8;
                } else {
                    etapa = 9;
                }
                break;
            case 8:
                mensagem.setText(pokemonTreinadorService.apelidoOuNome(pokemonTreinador) +
                        "\nevoluiu para o\nlevel " + pokemonTreinador.getLevel() + "!");
                lvlPokemon.setText("Lv." + pokemonTreinador.getLevel());
                //Setar o hp
                hpTextJogador.setText
                        ("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());
                etapa = 9;
                mpLevelUp.start();
                break;
            case 9:
                this.irMenuPrincipal();
                break;
            case 10:
                Toast.makeText(getApplicationContext(), "CONQUISTA: " + ct.getConquista().getMensagem(),
                        Toast.LENGTH_SHORT).show();
                etapa = 4;
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
            etapa = 7;
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
            TextView mensagem = (TextView) findViewById(R.id.mensagemTextViewBatalhaSelvagem);
            mensagem.setText("Não foi possível fugir.");

            this.desabilitarButtons();
            prox.setVisibility(View.VISIBLE);
            etapa = 1;
        }
    }

    public void irMenuPrincipal() {
        BatalhaBg.stop();
        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
        pokemonTreinadorDAO.atualizarHpAtualHpTotalExpLvl(pokemonTreinador);

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
