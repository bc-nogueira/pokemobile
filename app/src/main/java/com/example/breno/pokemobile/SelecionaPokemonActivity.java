package com.example.breno.pokemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.breno.pokemobile.db.AtaqueDAO;
import com.example.breno.pokemobile.db.PokemonAtaqueDAO;
import com.example.breno.pokemobile.db.PokemonDAO;
import com.example.breno.pokemobile.db.PokemonTreinadorDAO;
import com.example.breno.pokemobile.modelo.Ataque;
import com.example.breno.pokemobile.modelo.Pokemon;
import com.example.breno.pokemobile.modelo.PokemonAtaque;
import com.example.breno.pokemobile.modelo.PokemonTreinador;
import com.example.breno.pokemobile.modelo.Treinador;

import java.util.ArrayList;
import java.util.Random;

public class SelecionaPokemonActivity extends AppCompatActivity {
    private Treinador treinador;
    private Integer posicaoAtual;
    private ArrayList<Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_pokemon);

        treinador = (Treinador) getIntent().getSerializableExtra("treinador");

        PokemonDAO pokemonDAO = new PokemonDAO(this);
        pokemons = new ArrayList<>();
        pokemons.add(pokemonDAO.buscarPorNumero("001"));
        pokemons.add(pokemonDAO.buscarPorNumero("004"));
        pokemons.add(pokemonDAO.buscarPorNumero("007"));
        pokemons.add(pokemonDAO.buscarPorNumero("025"));

        posicaoAtual = 0;

        ImageView pokemonIV = (ImageView) findViewById(R.id.iconeImageViewSelecionaPokemon);
        pokemonIV.setImageResource(pokemons.get(posicaoAtual).getIcone());

    }

    @Override
    public void onBackPressed() {
    }

    public void anterior(View v) {
        if(--posicaoAtual < 0) {
            posicaoAtual = pokemons.size() - 1;
        }
        ImageView pokemonIV = (ImageView) findViewById(R.id.iconeImageViewSelecionaPokemon);
        pokemonIV.setImageResource(pokemons.get(posicaoAtual).getIcone());
    }

    public void proximo(View v) {
        if(++posicaoAtual >= pokemons.size()) {
            posicaoAtual = 0;
        }
        ImageView pokemonIV = (ImageView) findViewById(R.id.iconeImageViewSelecionaPokemon);
        pokemonIV.setImageResource(pokemons.get(posicaoAtual).getIcone());
    }

    public void selecionar(View v) {
        Pokemon pokemon = pokemons.get(posicaoAtual);

        PokemonTreinador pt = new PokemonTreinador();
        pt.setPokemon(pokemon);
        pt.setTreinador(treinador);

        EditText apelido = (EditText) findViewById(R.id.apeloEditTextSelecionaPokemon);
        String apelidoPokemon = apelido.getText().toString();
        if(!apelidoPokemon.isEmpty()) {
            pt.setApelido(apelidoPokemon);
        }

        Random gerador = new Random();
        Integer hp = pokemon.getHpMinimo() + gerador.nextInt(pokemon.getHpMaximo() - pokemon.getHpMinimo() + 1);
        pt.setHpTotal(new Double(hp));
        pt.setHpAtual(new Double(hp));

        pt.setLevel(1);
        pt.setExperiencia(0.);

        pt.setPosFila(1);

        PokemonAtaqueDAO paDAO = new PokemonAtaqueDAO(this);
        PokemonAtaque pokemonAtaque = paDAO.buscarAtaquesPorPokemonLevel(pt);

        AtaqueDAO ataqueDAO = new AtaqueDAO(this);
        Ataque atq = ataqueDAO.buscarPorId(pokemonAtaque.getIdAtaque());
        pt.setAtaque1(atq);

        PokemonTreinadorDAO ptDAO = new PokemonTreinadorDAO(this);
        ptDAO.inserir(pt);

        Toast.makeText(this, "Pokemon selecionado.", Toast.LENGTH_SHORT).show();

        //Encaminhar para o menu principal
        Intent menuPrincipal = new Intent(SelecionaPokemonActivity.this, MenuPrincipalActivity.class);
        menuPrincipal.putExtra("treinador", treinador);
        //menuPrincipal.putExtra("pokemon", pokemon); --Verificar se é necessário
        startActivity(menuPrincipal);
    }
}
