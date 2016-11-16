package com.example.breno.pokemobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.PokemonTreinador;

import java.util.ArrayList;

/**
 * Created by Breno on 15/11/2016.
 */

public class PokemonsAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<PokemonTreinador> lista;

    public PokemonsAdapter(Context ctx, ArrayList<PokemonTreinador> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getTreinador().getIdTreinador();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PokemonTreinador pokemonTreinador = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.pokemon, null);

        ImageView icone = (ImageView) layout.findViewById(R.id.iconeImageViewPokemons);
        icone.setImageResource(pokemonTreinador.getPokemon().getIcone());

        TextView nome = (TextView) layout.findViewById(R.id.nomeTextViewPokemons);
        if(pokemonTreinador.getApelido() != null) {
            nome.setText(pokemonTreinador.getApelido());
        } else {
            nome.setText(pokemonTreinador.getPokemon().getNome());
        }

        ProgressBar hpBar = (ProgressBar) layout.findViewById(R.id.hpProgressBarPokemons);
        Double porcentagemHP = (pokemonTreinador.getHpAtual()/pokemonTreinador.getHpTotal()) * 100;
        hpBar.setProgress(porcentagemHP.intValue());

        TextView hpText = (TextView) layout.findViewById(R.id.hpTextViewPokemons);
        hpText.setText("HP: " + pokemonTreinador.getHpAtual().intValue() + " / " + pokemonTreinador.getHpTotal().intValue());

        return layout;
    }
}
