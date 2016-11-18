package com.example.breno.pokemobile.Service;

import android.content.Context;

import com.example.breno.pokemobile.db.PokemonDAO;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Breno on 18/11/2016.
 */

public class PokemonTreinadorService {

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

    public PokemonTreinador criaPokemonBatalhaSelvagem(Context ctx) {
        PokemonDAO pokemonDAO = new PokemonDAO(ctx);

        ArrayList<Pokemon> pokemonsDisponiveis = pokemonDAO.buscar();
        Collections.shuffle(pokemonsDisponiveis);

        Pokemon pokemon = pokemonsDisponiveis.get(0);

        PokemonTreinador pokemonTreinador = new PokemonTreinador();
        pokemonTreinador.setPokemon(pokemon);

        Random gerador = new Random();
        Integer hp = pokemon.getHpMinimo() + gerador.nextInt(pokemon.getHpMaximo() - pokemon.getHpMinimo() + 1);
        pokemonTreinador.setHpAtual(new Double(hp));
        pokemonTreinador.setHpTotal(new Double(hp));

        pokemonTreinador.setLevel(1);


        return pokemonTreinador;
    }

}
