package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 02/11/2016.
 */

public class Jogador {
    private Integer idJogador;
    private String nome;
    private String email;
    private String senha;
    private Integer gold;

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }
}
