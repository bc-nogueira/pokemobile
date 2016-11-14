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

import java.util.ArrayList;

/**
 * Created by Breno on 13/11/2016.
 */

public class ItemMochilaAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<ItemTreinador> lista;

    public ItemMochilaAdapter(Context ctx, ArrayList<ItemTreinador> lista) {
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
        return lista.get(position).getItem().getIdItem();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemTreinador itemTreinador = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_mochila, null);

        ImageView icone = (ImageView) layout.findViewById(R.id.iconeImageViewMochila);
        icone.setImageResource(itemTreinador.getItem().getIcone());

        TextView nome = (TextView) layout.findViewById(R.id.nomeTextViewMochila);
        nome.setText(itemTreinador.getItem().getNome());

        TextView quantidade = (TextView) layout.findViewById(R.id.quantTextViewMochila);
        quantidade.setText(itemTreinador.getQuantidade().toString());

        ImageView deletar = (ImageView) layout.findViewById(R.id.deleteImageViewMochila);
        deletar.setTag(new Long(itemTreinador.getIdItemTreinador()));

        return layout;
    }
}
