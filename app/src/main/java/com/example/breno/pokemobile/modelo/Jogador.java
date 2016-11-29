package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 02/11/2016.
 */

public class Jogador implements Serializable {
    private Long idJogador;
    private String usuario;
    private String senha;

    public Jogador() {}

    public Jogador(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
