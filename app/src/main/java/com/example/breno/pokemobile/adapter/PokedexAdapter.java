package com.example.breno.pokemobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.Pokemon;

import java.util.ArrayList;

/**
 * Created by Breno on 14/11/2016.
 */

public class PokedexAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Pokemon> lista;

    public PokedexAdapter(Context ctx, ArrayList<Pokemon> lista) {
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
        return Long.parseLong(lista.get(position).getNumero());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pokemon pokemon = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.pokemon_pokedex, null);

        ImageView icone = (ImageView) layout.findViewById(R.id.iconeImageViewPokedex);
        icone.setImageResource(pokemon.getIcone());

        TextView numero = (TextView) layout.findViewById(R.id.numeroTextViewPokedex);
        numero.setText(pokemon.getNumero());

        TextView nome = (TextView) layout.findViewById(R.id.nomeTextViewPokedex);
        nome.setText(pokemon.getNome());

        ImageView capturado = (ImageView) layout.findViewById(R.id.capturadoImageViewPokedex);
//        capturado.setVisibility(View.VISIBLE);

        return layout;
    }
}
