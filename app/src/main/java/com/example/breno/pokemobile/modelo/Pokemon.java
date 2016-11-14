package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 14/11/2016.
 */

public class Pokemon {
    private String numero;
    private String nome;
    private TipoPokemon tipo;
    private Integer hpMinimo;
    private Integer hpMaximo;
    private String altura;
    private String peso;
    private String descricao;
    private Integer icone;

    public Pokemon() {}

    public Pokemon(String numero, String nome, TipoPokemon tipo, Integer hpMinimo, Integer hpMaximo, String altura,
                   String peso, String descricao, Integer icone) {
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
        this.hpMinimo = hpMinimo;
        this.hpMaximo = hpMaximo;
        this.altura = altura;
        this.peso = peso;
        this.descricao = descricao;
        this.icone = icone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
    }

    /*
    Tabela de intervalo sendo usados:
    HP 35 -> 10~15
    HP 40 -> 15~20
    HP 45 -> 20~25
     */

    public Integer getHpMinimo() {
        return hpMinimo;
    }

    public void setHpMinimo(Integer hpMinimo) {
        this.hpMinimo = hpMinimo;
    }

    public Integer getHpMaximo() {
        return hpMaximo;
    }

    public void setHpMaximo(Integer hpMaximo) {
        this.hpMaximo = hpMaximo;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

}
