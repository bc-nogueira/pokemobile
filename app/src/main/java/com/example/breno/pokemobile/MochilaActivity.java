package com.example.breno.pokemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.breno.pokemobile.adapter.ItemMochilaAdapter;
import com.example.breno.pokemobile.db.ItemTreinadorDAO;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class MochilaActivity extends AppCompatActivity {
    private Treinador treinador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mochila);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        if (treinador != null) {

            ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(this);
            final ArrayList<ItemTreinador> itensTreinador = itemTreinadorDAO.listaIdsItensPorIdTreinador(treinador, this);

            ListView listViewItensTreinador = (ListView) findViewById(R.id.itensListViewMochila);

            final ItemMochilaAdapter itemMochilaAdapter = new ItemMochilaAdapter(this, itensTreinador);

            listViewItensTreinador.setAdapter(itemMochilaAdapter);

            listViewItensTreinador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ItemTreinador itemTreinador = itensTreinador.get(position);

                    if(!itemTreinador.getItem().getTipo().equals(TipoItem.CAPTURA)) {

                        Toast.makeText(getApplicationContext(),
                                "Colocar poção e revive para funcionar.", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(),
                                "Item de captura não pode ser usado aqui.", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

    }

    public void deletarItem(View v) {
        Long idItemTreinador = (Long) v.getTag();

        ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(this);
        itemTreinadorDAO.deletar(idItemTreinador);

        this.recreate();

    }
}
