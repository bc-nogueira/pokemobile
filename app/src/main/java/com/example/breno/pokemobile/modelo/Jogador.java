package com.example.breno.pokemobile.modelo;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Breno on 02/11/2016.
 */

public class Jogador implements Serializable {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Integer dinheiro;
    private Integer idAvatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Integer dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Integer getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(Integer idAvatar) {
        this.idAvatar = idAvatar;
    }
}
