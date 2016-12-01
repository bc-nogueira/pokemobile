package com.example.breno.pokemobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.modelo.PokemonTreinador;

import java.util.ArrayList;

/**
 * Created by Breno on 30/11/2016.
 */

public class PokemonArmazemAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<PokemonTreinador> lista;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();

    public PokemonArmazemAdapter(Context ctx, ArrayList<PokemonTreinador> lista) {
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

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.pokemon_armazem, null);

        if(lista.get(position).getPosFila() != null) {
            layout.setBackgroundColor(Color.GRAY);
        }

        ImageView icone = (ImageView) layout.findViewById(R.id.iconeImageViewPokemonArmazem);
        icone.setImageResource(lista.get(position).getPokemon().getIcone());

        TextView nome = (TextView) layout.findViewById(R.id.nomeTextViewPokemonArmazem);
        nome.setText(pokemonTreinadorService.apelidoOuNome(lista.get(position)));

        TextView lvl = (TextView) layout.findViewById(R.id.lvlTextViewPokemonArmazem);
        lvl.setText("Lv. " + lista.get(position).getLevel());

        return layout;
    }
}
