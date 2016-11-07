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
import com.example.breno.pokemobile.modelo.Item;

import java.util.ArrayList;

/**
 * Created by Breno on 06/11/2016.
 */

public class ItemAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Item> lista;

    public ItemAdapter(Context ctx, ArrayList<Item> lista) {
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
        return lista.get(position).getIdItem();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item, null);

        ImageView icone = (ImageView) layout.findViewById(R.id.iconeImageViewItem);
        icone.setImageResource(item.getIcone());

        TextView nome = (TextView) layout.findViewById(R.id.nomeTextViewItem);
        nome.setText(item.getNome());

        TextView preco = (TextView) layout.findViewById(R.id.precoTextViewItem);
        preco.setText(item.getPreco().toString() + "G");

        TextView descricao = (TextView) layout.findViewById(R.id.descricaoTextViewItem);
        descricao.setText(item.getDescricao());

        return layout;
    }

    public void mudarCorFundo(View v, int position) {
        v.setBackgroundColor(Color.GRAY);
    }

    public void resetarCorFundo(View v, int position) {
        v.setBackgroundColor(Color.WHITE);
    }
}
