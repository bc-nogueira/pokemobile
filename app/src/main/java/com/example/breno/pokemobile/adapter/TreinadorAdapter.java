package com.example.breno.pokemobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breno.pokemobile.R;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

/**
 * Created by Breno on 06/11/2016.
 */

public class TreinadorAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Treinador> lista;

    public TreinadorAdapter(Context ctx, ArrayList<Treinador> lista) {
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
        return lista.get(position).getIdTreinador();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Treinador treinador = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.treinador, null);

        ImageView avatar = (ImageView) layout.findViewById(R.id.avatarTreinador);
        avatar.setImageResource(treinador.getImagemTreinador(treinador.getIdAvatar()));

        TextView nomeTreinador = (TextView) layout.findViewById(R.id.nomeTreinador);
        nomeTreinador.setText(treinador.getNome());

        TextView dinheiroTreinador = (TextView) layout.findViewById(R.id.dinheiroTreinador);
        dinheiroTreinador.setText(treinador.getDinheiro().toString());

        TextView pokemonsTreinador = (TextView) layout.findViewById(R.id.pokemonsTreinador);
        pokemonsTreinador.setText("0");

        return layout;
    }
}
