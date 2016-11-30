package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.AtaqueDAO;
import com.example.breno.pokemobile.db.ExpLevelDAO;
import com.example.breno.pokemobile.db.PokemonAtaqueDAO;
import com.example.breno.pokemobile.db.PokemonDAO;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.ExpLevel;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonAtaque;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonTreinadorService {
    private UtilidadesService utilidadesService = new UtilidadesService();

    public boolean verificaSeTodosEstaoMortos(Treinador treinador, Context ctx) {

        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(ctx);
        if(pokemonTreinadorDAO.verificarSeTodosEstaoMortos(treinador)) {
            return true;
        } else {
            return false;
        }

    }

    public String apelidoOuNome(PokemonTreinador pokemonTreinador) {

        if(pokemonTreinador.getApelido() != null) {
            return pokemonTreinador.getApelido();
        } else {
            return pokemonTreinador.getPokemon().getNome();
        }

    }

    public PokemonTreinador criaPokemonBatalhaSelvagem(Context ctx, PokemonTreinador pokemonTreinador) {
        PokemonDAO pokemonDAO = new PokemonDAO(ctx);

        ArrayList<Pokemon> pokemonsDisponiveis = pokemonDAO.buscar();

        pokemonsDisponiveis = this.removeFortes(pokemonsDisponiveis, pokemonTreinador);

        Collections.shuffle(pokemonsDisponiveis);

        Pokemon pokemon = pokemonsDisponiveis.get(0);

        PokemonTreinador pokemonInimigo = new PokemonTreinador();
        pokemonInimigo.setPokemon(pokemon);

        Random gerador = new Random();
        Integer hp = pokemon.getHpMinimo() + gerador.nextInt(pokemon.getHpMaximo() - pokemon.getHpMinimo() + 1);
        pokemonInimigo.setHpAtual(hp);
        pokemonInimigo.setHpTotal(hp);

        pokemonInimigo = this.determinarLvl(pokemonTreinador, pokemonInimigo);

        PokemonAtaqueDAO pokemonAtaqueDAO = new PokemonAtaqueDAO(ctx);
        AtaqueDAO ataqueDAO = new AtaqueDAO(ctx);

        PokemonAtaque pokemonAtaque = pokemonAtaqueDAO.buscarAtaquesPorPokemonLevel(pokemonInimigo);
        Ataque ataque = ataqueDAO.buscarPorId(pokemonAtaque.getIdAtaque());
        pokemonInimigo.setAtaque1(ataque);

        return pokemonInimigo;
    }

    public ArrayList<Pokemon> removeFortes(ArrayList<Pokemon> pokemonsDisponiveis, PokemonTreinador pokemonTreinador) {
        //Remove evolucoes de lvl muito alto
        Iterator<Pokemon> pokemons = pokemonsDisponiveis.iterator();
        while(pokemons.hasNext()) {
            Pokemon p = pokemons.next();
            int diferencaPraCima = p.getLevelParaEvoluir() - pokemonTreinador.getLevel();

            if(diferencaPraCima > 1) {
                pokemons.remove();
            }
        }
        return pokemonsDisponiveis;
    }

    public PokemonTreinador determinarLvl(PokemonTreinador pokemonTreinador, PokemonTreinador pokemonInimigo) {
        if(pokemonTreinador.getLevel() == 1) {
            pokemonInimigo.setLevel(utilidadesService.gerarNumeroAleatorio(2));
        } else {
            switch (utilidadesService.gerarNumeroAleatorio(3)) {
                case 1:
                    pokemonInimigo.setLevel(pokemonTreinador.getLevel() - 1);
                    break;
                case 2:
                    pokemonInimigo.setLevel(pokemonTreinador.getLevel());
                    break;
                case 3:
                    pokemonInimigo.setLevel(pokemonTreinador.getLevel() + 1);
                    break;
            }
        }

        //Acerta HP
        for(int i = 2; i <= pokemonInimigo.getLevel(); i++) {
            pokemonInimigo = this.aumentarHP(pokemonInimigo);
        }
        pokemonInimigo.setHpAtual(pokemonInimigo.getHpTotal());
        return pokemonInimigo;
    }

    public PokemonTreinador aumentarHP(PokemonTreinador pokemon) {
        Double valor = Math.ceil(pokemon.getHpTotal() * 0.1);
        pokemon.setHpTotal(pokemon.getHpTotal() + valor.intValue());
        return pokemon;
    }

    public boolean verificaSeEvoluiu(PokemonTreinador pokemon, Context ctx) {

        ExpLevelDAO epDAO = new ExpLevelDAO(ctx);

        ExpLevel ep = epDAO.buscarPorLevel(pokemon.getLevel());

        if(pokemon.getExperiencia() >= ep.getExperiencia()) {

            pokemon.setLevel(pokemon.getLevel() + 1);
            pokemon.setExperiencia(pokemon.getExperiencia() - ep.getExperiencia());
            pokemon = this.aumentarHP(pokemon);

            return true;

        } else {

            return false;

        }

    }

    public Double calcularPorcentagemHP(PokemonTreinador pokemon) {
        return (pokemon.getHpAtual().doubleValue()/pokemon.getHpTotal().doubleValue()) * 100;
    }

}
