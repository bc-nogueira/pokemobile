package com.example.breno.pokemobile.Service;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.breno.pokemobile.ProgressBarAnimation;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.Random;

/**
 * Created by Breno on 28/11/2016.
 */

public class BatalhaSelvagemService {
    private ItemTreinadorService itemTreinadorService = new ItemTreinadorService();
    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();
    private UtilidadesService utilidadesService = new UtilidadesService();

    public PokemonTreinador realizarAtaque(PokemonTreinador pokemonAtacante, PokemonTreinador pokemonAtacado, Ataque ataque,
                                           TextView mensagem, boolean selvagem, ProgressBar hpBarAtacado, TextView hpTextAtacado) {

        //Seleciona qual ataque usar
        if(ataque == null) {
            ataque = this.selecionarAtaque(pokemonAtacante);
        }

        if(selvagem) {
            mensagem.setText(pokemonAtacante.getPokemon().getNome() + "\nselvagem usa\n" + ataque.getNomeAtaque() + ".");
        } else {
            if(pokemonAtacante.getApelido() != null) {
                mensagem.setText(pokemonAtacante.getApelido() + "\nusa\n" + ataque.getNomeAtaque() + ".");
            } else {
                mensagem.setText(pokemonAtacante.getPokemon().getNome() + "\nusa\n" + ataque.getNomeAtaque() + ".");
            }
        }


        //Calcula dano
        Integer dano = utilidadesService.gerarNumeroAleatorio(10) * ataque.getDanoBase();

        //Atualiza HP do pokemon
        if((pokemonAtacado.getHpAtual() - dano) > 0) {
            pokemonAtacado.setHpAtual(pokemonAtacado.getHpAtual() - dano);
        } else {
            pokemonAtacado.setHpAtual(0);
        }

        this.diminuirHP(pokemonAtacado, hpBarAtacado, hpTextAtacado);

        return pokemonAtacado;
    }

    public Ataque selecionarAtaque(PokemonTreinador pokemon) {
        Ataque ataque;
        if (pokemon.getAtaque2() == null) {
            ataque = pokemon.getAtaque1();
        } else {
            if(utilidadesService.gerarNumeroAleatorio(2) == 1) {
                ataque = pokemon.getAtaque1();
            } else {
                ataque = pokemon.getAtaque2();
            }
        }
        return ataque;
    }

    public PokemonTreinador curarPokemon(PokemonTreinador pokemon, ItemTreinador item,
                                         ProgressBar hpBar, TextView hpText, Context ctx) {

        pokemon.setHpAtual(pokemon.getHpAtual() + item.getItem().getEfeitoCura());

        this.aumentarHP(pokemon, hpBar, hpText);

        //Diminui o item ou remove
        itemTreinadorService.diminuirItem(item, ctx);

        return pokemon;

    }

    public boolean capturarPokemon(Treinador treinador, ItemTreinador item, PokemonTreinador pokemon, Context ctx) {

        Double bonusCapturaHP = calculaBonusCapturaHP(pokemon.getHpAtual().doubleValue() / pokemon.getHpTotal().doubleValue());

        Double bonusTotal = bonusCapturaHP + item.getItem().getEfeitoCaptura();

        Integer numSorteado = utilidadesService.gerarNumeroAleatorio(10);

        itemTreinadorService.diminuirItem(item, ctx);

        if(numSorteado < (bonusTotal*10)) {
            pokemon.setTreinador(treinador);
            pokemon.setExperiencia(0.);

            PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(ctx);
            pokemonTreinadorDAO.inserir(pokemon);

            return true;

        } else {

            return false;

        }

    }

    private Double calculaBonusCapturaHP(Double porcentagem) {

        if(porcentagem < 0.25) {
            return 0.5;
        } else if(porcentagem >= 0.25 && porcentagem < 0.75 ) {
            return 0.25;
        } else {
            return 0.;
        }

    }

    public void aumentarHP(PokemonTreinador pokemon, ProgressBar hpBar, TextView hpText) {
        if(pokemon.getHpAtual() > pokemon.getHpTotal()) {
            pokemon.setHpAtual(pokemon.getHpTotal());
        }

        Double porcentagemFinal = pokemonTreinadorService.calcularPorcentagemHP(pokemon);
        Integer porcentagemAtual = hpBar.getProgress();
        ProgressBarAnimation anim = new ProgressBarAnimation
                (hpBar, porcentagemAtual.floatValue(), porcentagemFinal.floatValue());
        anim.setDuration(1000);
        hpBar.startAnimation(anim);

        hpText.setText("HP: " + pokemon.getHpAtual().intValue() + " / " + pokemon.getHpTotal().intValue());
    }

    public void diminuirHP(PokemonTreinador pokemon, ProgressBar hpBar, TextView hpText) {
        Double porcentagemFinal = pokemonTreinadorService.calcularPorcentagemHP(pokemon);
        Integer porcentagemAtual = hpBar.getProgress();

        ProgressBarAnimation anim;
        if((pokemon.getHpAtual()) > 0) {
            anim = new ProgressBarAnimation
                    (hpBar, porcentagemAtual.floatValue(), porcentagemFinal.floatValue());
        } else {
            anim = new ProgressBarAnimation
                    (hpBar, porcentagemAtual.floatValue(), new Float(0));
        }

        anim.setDuration(500);
        hpBar.startAnimation(anim);

        hpText.setText("HP: " + pokemon.getHpAtual().intValue() + " / " + pokemon.getHpTotal().intValue());
    }

    public PokemonTreinador adicionarExperiencia(PokemonTreinador pokemon, Integer level, TextView mensagem) {

        mensagem.setText(pokemonTreinadorService.apelidoOuNome(pokemon) + "\nganhou " + (10 * level) + "\nde experiência.");

        pokemon.setExperiencia(pokemon.getExperiencia() + 10 * level);

        return pokemon;
    }

}
