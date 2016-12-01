package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.breno.pokemobile.adapter.ItemMochilaAdapter;
import com.example.breno.pokemobile.db.ItemTreinadorDAO;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class ItemBatalhaActivity extends AppCompatActivity {
    private Treinador treinador;
    private PokemonTreinador pokemonTreinador;
    private PokemonTreinador pokemonInimigo;
    private boolean isSelvagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_batalha);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");
        pokemonTreinador = (PokemonTreinador) getIntent().getSerializableExtra("pokemonTreinador");
        pokemonInimigo = (PokemonTreinador) getIntent().getSerializableExtra("pokemonInimigo");
        isSelvagem = (boolean) getIntent().getSerializableExtra("isSelvagem");

        if (treinador != null) {

            ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(this);
            final ArrayList<ItemTreinador> itensTreinador =
                    itemTreinadorDAO.buscarItensTreinadorCuraRevivePorIdTreinador(treinador, this, isSelvagem);

            ListView listViewItensTreinador = (ListView) findViewById(R.id.itensListViewItemBatalha);

            final ItemMochilaAdapter itemMochilaAdapter = new ItemMochilaAdapter(this, itensTreinador);
            listViewItensTreinador.setAdapter(itemMochilaAdapter);

            listViewItensTreinador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(itensTreinador.get(position).getItem().getTipo().equals(TipoItem.CAPTURA) &&
                            !isSelvagem) {

                        Toast.makeText(view.getContext(),
                                "Não é possível capturar o pokemon de outro treinador.", Toast.LENGTH_SHORT).show();

                    } else {

                        ItemTreinador itemTreinador = itensTreinador.get(position);

                        Intent batalha;
                        if (isSelvagem) {
                            batalha = new Intent(ItemBatalhaActivity.this, BatalhaSelvagemActivity.class);
                        } else {
                            batalha = new Intent(ItemBatalhaActivity.this, BatalhaTreinadorActivity.class);
                        }

                        batalha.putExtra("treinador", treinador);
                        batalha.putExtra("itemTreinador", itemTreinador);
                        batalha.putExtra("pokemonTreinador", pokemonTreinador);
                        batalha.putExtra("pokemonInimigo", pokemonInimigo);
                        startActivity(batalha);

                    }

                }
            });

        }
    }
}
