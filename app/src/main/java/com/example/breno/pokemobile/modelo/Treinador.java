package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 03/11/2016.
 */

public class Treinador implements Serializable {
    private Long idTreinador;
    private String nome;
    private Integer dinheiro;
    private Integer idAvatar;
    private Long idJogador;

    public Long getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(Long idTreinador) {
        this.idTreinador = idTreinador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

}
