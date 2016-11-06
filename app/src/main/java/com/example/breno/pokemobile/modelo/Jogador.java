package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 02/11/2016.
 */

public class Jogador implements Serializable {
    private Long idJogador;
    private String email;
    private String senha;

    public Jogador() {}

    public Jogador(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
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

}
