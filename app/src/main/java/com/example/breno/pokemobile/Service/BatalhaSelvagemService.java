package com.example.breno.pokemobile.Service;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.breno.pokemobile.ProgressBarAnimation;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.PokemonTreinador;

import java.util.Random;

/**
 * Created by Breno on 28/11/2016.
 */

public class BatalhaSelvagemService {

    public PokemonTreinador realizarAtaque(PokemonTreinador pokemonAtacante, PokemonTreinador pokemonAtacado, Ataque ataque,
                                           TextView mensagem, String selvagem, ProgressBar hpBarAtacado, TextView hpTextAtacado) {

        //Seleciona qual ataque usar
        if(ataque == null) {
            ataque = this.selecionarAtaque(pokemonAtacante);
        }

        mensagem.setText(pokemonAtacante.getPokemon().getNome() + "\n"+ selvagem + "usa\n" + ataque.getNomeAtaque() + ".");

        //Calcula dano
        Integer dano = this.gerarNumeroAleatorio(9) * ataque.getDanoBase() + 1;

        //Atualiza HP do pokemon
        if((pokemonAtacado.getHpAtual() - dano) > 0) {
            pokemonAtacado.setHpAtual(pokemonAtacado.getHpAtual() - dano);
        } else {
            pokemonAtacado.setHpAtual(0.);
        }

        this.atualizarHP(pokemonAtacado, dano, hpBarAtacado, hpTextAtacado);

        return pokemonAtacado;
    }

    public int gerarNumeroAleatorio(int intervalo) {
        Random gerador = new Random();
        return gerador.nextInt(intervalo);
    }

    public Ataque selecionarAtaque(PokemonTreinador pokemon) {
        Ataque ataque;
        if (pokemon.getAtaque2() == null) {
            ataque = pokemon.getAtaque1();
        } else {
            //TODO: Sortear entre ataque1 ou ataque2
            if(this.gerarNumeroAleatorio(2) == 1) {
                ataque = pokemon.getAtaque1();
            } else {
                ataque = pokemon.getAtaque2();
            }
        }
        return ataque;
    }

    public void atualizarHP(PokemonTreinador pokemonAtacado, Integer dano, ProgressBar hpBarAtacado, TextView hpTextAtacado) {
        Double porcentagemFinal = (pokemonAtacado.getHpAtual()/pokemonAtacado.getHpTotal()) * 100;
        Integer porcentagemAtual = hpBarAtacado.getProgress();

        ProgressBarAnimation anim;
        if((pokemonAtacado.getHpAtual()) > 0) {
            anim = new ProgressBarAnimation
                    (hpBarAtacado, porcentagemAtual.floatValue(), porcentagemFinal.floatValue());
        } else {
            anim = new ProgressBarAnimation
                    (hpBarAtacado, porcentagemAtual.floatValue(), new Float(0));
        }

        anim.setDuration(500);
        hpBarAtacado.startAnimation(anim);

        hpTextAtacado.setText("HP: " + pokemonAtacado.getHpAtual().intValue() + " / " + pokemonAtacado.getHpTotal().intValue());
    }

    public PokemonTreinador adicionarExperiencia(PokemonTreinador pokemon, Integer level) {

        pokemon.setExperiencia(pokemon.getExperiencia() + 10 * level);

        return pokemon;
    }

}
