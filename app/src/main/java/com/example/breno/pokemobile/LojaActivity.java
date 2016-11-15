package com.example.breno.pokemobile;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.adapter.ItemAdapter;
import com.example.breno.pokemobile.db.ItemDAO;
import com.example.breno.pokemobile.db.ItemTreinadorDAO;
import com.example.breno.pokemobile.db.TreinadorDAO;
import com.example.breno.pokemobile.modelo.Item;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class LojaActivity extends AppCompatActivity {
    private Treinador treinador;

    ArrayList<Item> itens;

    Item itemAtual;
    Integer quant;
    Integer valorTotal;
    Integer posSelecionada = -1;
    View viewAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        if(treinador != null) {

            TextView dinheiro = (TextView) findViewById(R.id.dinheiroTextViewLoja);
            dinheiro.setText(treinador.getDinheiro().toString() + "G");

            ItemDAO itemDAO = new ItemDAO(this);
            itens = itemDAO.buscar();

            ListView listViewItens = (ListView) findViewById(R.id.itensListViewLoja);

            final ItemAdapter itemAdapter = new ItemAdapter(this, itens);

            listViewItens.setAdapter(itemAdapter);

            listViewItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (posSelecionada == -1) {
                        posSelecionada = position;

                        itemAdapter.mudarCorFundo(view, posSelecionada);

                        viewAnterior = view;

                    } else {
                        itemAdapter.resetarCorFundo(viewAnterior, posSelecionada);

                        posSelecionada = position;
                        viewAnterior = view;

                        itemAdapter.mudarCorFundo(view, posSelecionada);
                    }

                    itemAtual = itens.get(position);

                    ImageView retirar = (ImageView) findViewById(R.id.menosLoja);
                    retirar.setVisibility(View.VISIBLE);

                    TextView quantidade = (TextView) findViewById(R.id.quantLoja);
                    quant = 0;
                    quantidade.setText(quant.toString());
                    quantidade.setVisibility(View.VISIBLE);

                    ImageView adicionar = (ImageView) findViewById(R.id.maisLoja);
                    adicionar.setVisibility(View.VISIBLE);

                    TextView total = (TextView) findViewById(R.id.totalLoja);
                    valorTotal = itemAtual.getPreco() * quant;
                    total.setText(valorTotal.toString() + "G");
                    total.setVisibility(View.VISIBLE);

                    ImageView cancelar = (ImageView) findViewById(R.id.cancelLoja);
                    cancelar.setVisibility(View.VISIBLE);

                    ImageView confirmar = (ImageView) findViewById(R.id.confirmLoja);
                    confirmar.setVisibility(View.VISIBLE);

                }
            });

        }

    }

    @Override
    public void onBackPressed() {
        Intent menuPrincipal = new Intent(LojaActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        startActivity(menuPrincipal);
    }

    public void remover(View v) {

        if(quant > 0) {
            quant--;

            TextView quantidade = (TextView) findViewById(R.id.quantLoja);
            quantidade.setText(quant.toString());

            TextView total = (TextView) findViewById(R.id.totalLoja);
            valorTotal = itemAtual.getPreco() * quant;
            total.setText(valorTotal.toString() + "G");
        }

    }

    public void adicionar(View v) {
        quant++;

        TextView quantidade = (TextView) findViewById(R.id.quantLoja);
        quantidade.setText(quant.toString());

        TextView total = (TextView) findViewById(R.id.totalLoja);
        valorTotal = itemAtual.getPreco() * quant;
        total.setText(valorTotal.toString() + "G");

    }

    public void cancelar(View v) {
        itemAtual = null;
        quant = 0;
        valorTotal = 0;

        ItemAdapter itemAdapter = new ItemAdapter(this, itens);
        itemAdapter.resetarCorFundo(viewAnterior, posSelecionada);
        posSelecionada = -1;
        viewAnterior = null;

        ImageView retirar = (ImageView) findViewById(R.id.menosLoja);
        retirar.setVisibility(View.INVISIBLE);

        TextView quantidade = (TextView) findViewById(R.id.quantLoja);
        quantidade.setVisibility(View.INVISIBLE);

        ImageView adicionar = (ImageView) findViewById(R.id.maisLoja);
        adicionar.setVisibility(View.INVISIBLE);

        TextView total = (TextView) findViewById(R.id.totalLoja);
        total.setVisibility(View.INVISIBLE);

        ImageView cancelar = (ImageView) findViewById(R.id.cancelLoja);
        cancelar.setVisibility(View.INVISIBLE);

        ImageView confirmar = (ImageView) findViewById(R.id.confirmLoja);
        confirmar.setVisibility(View.INVISIBLE);

    }

    public void confirmar(View v) {

        if(quant > 0) {

            if (valorTotal <= treinador.getDinheiro()) {

                ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(this);

                //Verifica se esse itemTreinador já existe
                ItemTreinador it = itemTreinadorDAO.buscaPorIdItemIdTreinador(itemAtual, treinador);

                if(it == null) {
                    it = new ItemTreinador();
                    it.setItem(itemAtual);
                    it.setTreinador(treinador);
                    it.setQuantidade(quant);
                    itemTreinadorDAO.inserir(it);

                } else {

                    it.setQuantidade(it.getQuantidade() + quant);
                    itemTreinadorDAO.atualizarQuantidade(it);

                }

                //TODO: Atualizar dinheiro Jogador
                treinador.setDinheiro(treinador.getDinheiro() - valorTotal);
                TreinadorDAO treinadorDAO = new TreinadorDAO(this);
                treinadorDAO.atualizarDinheiro(treinador);

                //Depois de tudo feito, resetar os valores
                itemAtual = null;
                quant = 0;
                valorTotal = 0;

                ItemAdapter itemAdapter = new ItemAdapter(this, itens);
                itemAdapter.resetarCorFundo(viewAnterior, posSelecionada);
                posSelecionada = -1;
                viewAnterior = null;

                this.recreate();

            } else {

                Toast.makeText(this, "Você não tem dinheiro suficiente.", Toast.LENGTH_SHORT).show();

            }

        }

    }

}
