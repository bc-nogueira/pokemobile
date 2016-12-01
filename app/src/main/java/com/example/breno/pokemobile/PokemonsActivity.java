package com.example.breno.pokemobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breno.pokemobile.Service.PokemonTreinadorService;
import com.example.breno.pokemobile.adapter.PokemonsAdapter;
import com.example.breno.pokemobile.db.ItemTreinadorDAO;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.ItemTreinador;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.TipoItem;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;

public class PokemonsActivity extends AppCompatActivity {
    private Treinador treinador;
    private ItemTreinador itemTreinador;

    private PokemonTreinadorService pokemonTreinadorService = new PokemonTreinadorService();

    private Integer posOrigem = null;
    private PokemonTreinador pokemonMovido1;
    private Integer posDestino = null;
    private PokemonTreinador pokemonMovido2;
    private View viewFundoTrocado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        //Só recebe se o usuário selecionar algum item para usar da mochila
        itemTreinador = (ItemTreinador) getIntent().getSerializableExtra("itemTreinador");

        PokemonTreinadorDAO ptDAO = new PokemonTreinadorDAO(this);
        final ArrayList<PokemonTreinador> pts = ptDAO.buscarPorIdTreinadorNaFila(treinador, this);

        final ListView listPokemons = (ListView) findViewById(R.id.pokemonsListViewPokemons);

        final PokemonsAdapter pokemonsAdapter = new PokemonsAdapter(this, pts);
        listPokemons.setAdapter(pokemonsAdapter);

        if (itemTreinador != null) {

            listPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if ((itemTreinador.getItem().getTipo().equals(TipoItem.REVIVE)) && (pts.get(position).getHpAtual() > 0)) {

                        Toast.makeText(getApplicationContext(), "Esse pokemon não está morto.", Toast.LENGTH_SHORT).show();

                    } else if ((itemTreinador.getItem().getTipo().equals(TipoItem.CURA)) &&
                            (pts.get(position).getHpAtual().equals(pts.get(position).getHpTotal()))) {

                        Toast.makeText(getApplicationContext(), "O HP do pokemon está cheio.", Toast.LENGTH_SHORT).show();

                    } else if ((itemTreinador.getItem().getTipo().equals(TipoItem.REVIVE)) ||
                            (itemTreinador.getItem().getTipo().equals(TipoItem.CURA))) {

                        pts.get(position).setHpAtual(pts.get(position).getHpAtual() + itemTreinador.getItem().getEfeitoCura());

                        if (pts.get(position).getHpAtual() > pts.get(position).getHpTotal()) {
                            pts.get(position).setHpAtual(pts.get(position).getHpTotal());
                        }

                        //Recupera o HP do pokemon
                        PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
                        pokemonTreinadorDAO.atualizarHpAtual(pts.get(position));

                        ProgressBar hpBar = (ProgressBar) view.findViewById(R.id.hpProgressBarPokemons);
                        Double porcentagemFinal = pokemonTreinadorService.calcularPorcentagemHP(pts.get(position));
                        Integer porcentagemAtual = hpBar.getProgress();
                        ProgressBarAnimation anim = new ProgressBarAnimation
                                (hpBar, porcentagemAtual.floatValue(), porcentagemFinal.floatValue());
                        anim.setDuration(1000);
                        hpBar.startAnimation(anim);

                        TextView hpText = (TextView) view.findViewById(R.id.hpTextViewPokemons);
                        hpText.setText
                                ("HP: " + pts.get(position).getHpAtual().intValue() + " / " + pts.get(position).getHpTotal().intValue());

                        //Diminui o item ou remove
                        itemTreinador.setQuantidade(itemTreinador.getQuantidade() - 1);
                        ItemTreinadorDAO itemTreinadorDAO = new ItemTreinadorDAO(getApplicationContext());
                        if (itemTreinador.getQuantidade() >= 1) {
                            //Atualizar
                            itemTreinadorDAO.atualizarQuantidade(itemTreinador);
                        } else {
                            //Remove
                            itemTreinadorDAO.deletar(itemTreinador.getIdItemTreinador());
                        }

                    }

                    Intent voltarMochila = new Intent(PokemonsActivity.this, MochilaActivity.class);
                    voltarMochila.putExtra("treinador", treinador);
                    startActivity(voltarMochila);

                }
            });

        } else {
            listPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent info = new Intent(PokemonsActivity.this, InfoPokemonActivity.class);
                    info.putExtra("treinador", treinador);
                    info.putExtra("pokemonTreinador", pts.get(position));
                    startActivity(info);


                }
            });

        }

        //Colocar aqui: ver se precisa testar o item
        if(itemTreinador == null) {
            listPokemons.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    if (posOrigem == null) {
                        viewFundoTrocado = view;
                        view.setBackgroundColor(Color.GRAY);
                        posOrigem = position + 1;
                        pokemonMovido1 = pts.get(position);
                    } else {
                        view.setBackgroundColor(Color.WHITE);
                        posDestino = position + 1;
                        if (posOrigem == posDestino) {
                            posOrigem = null;
                            posDestino = null;
                            pokemonMovido1 = null;
                        } else {
                            pokemonMovido2 = pts.get(position);

                            pokemonMovido1.setPosFila(posDestino);
                            pokemonMovido2.setPosFila(posOrigem);

                            PokemonTreinadorDAO pokemonTreinadorDAO = new PokemonTreinadorDAO(getApplicationContext());
                            pokemonTreinadorDAO.atualizarPosFila(pokemonMovido1);
                            pokemonTreinadorDAO.atualizarPosFila(pokemonMovido2);

                            viewFundoTrocado.setBackgroundColor(Color.WHITE);
                            viewFundoTrocado = null;

                            Intent gambiarra = new Intent(PokemonsActivity.this, PokemonsActivity.class);
                            gambiarra.putExtra("treinador", treinador);
                            startActivity(gambiarra);

                        }
                    }

                    return true;
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        if(itemTreinador != null) {
            Intent mochila = new Intent(PokemonsActivity.this, MochilaActivity.class);
            mochila.putExtra("treinador", treinador);
            startActivity(mochila);
        } else {
            Intent menuPrincipal = new Intent(PokemonsActivity.this, MenuPrincipalActivity.class);
            menuPrincipal.putExtra("treinador", treinador);
            startActivity(menuPrincipal);
        }
    }
}
