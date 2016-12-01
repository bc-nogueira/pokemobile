package com.example.breno.pokemobile.modelo;

import com.example.breno.pokemobile.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Breno on 03/11/2016.
 */

public class Treinador implements Serializable {
    private Long idTreinador;
    private String nome;
    private Integer dinheiro;
    private Integer idAvatar;
    private Long idJogador;
    private ArrayList<Item> itens;
    private Integer batalhasTreinador;
    private Integer batalhasTreinadorVencidas;
    private Integer batalhasSelvagem;
    private Integer batalhasSelvagemVencidas;

    public Treinador() {}

    public Treinador(String nome, Integer dinheiro, Integer idAvatar, Long idJogador) {
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.idAvatar = idAvatar;
        this.idJogador = idJogador;
        this.batalhasTreinador = 0;
        this.batalhasTreinadorVencidas = 0;
        this.batalhasSelvagem = 0;
        this.batalhasSelvagemVencidas = 0;
    }

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

    public Integer getBatalhasTreinador() {
        return batalhasTreinador;
    }

    public void setBatalhasTreinador(Integer batalhasTreinador) {
        this.batalhasTreinador = batalhasTreinador;
    }

    public Integer getBatalhasTreinadorVencidas() {
        return batalhasTreinadorVencidas;
    }

    public void setBatalhasTreinadorVencidas(Integer batalhasTreinadorVencidas) {
        this.batalhasTreinadorVencidas = batalhasTreinadorVencidas;
    }

    public Integer getBatalhasSelvagem() {
        return batalhasSelvagem;
    }

    public void setBatalhasSelvagem(Integer batalhasSelvagem) {
        this.batalhasSelvagem = batalhasSelvagem;
    }

    public Integer getBatalhasSelvagemVencidas() {
        return batalhasSelvagemVencidas;
    }

    public void setBatalhasSelvagemVencidas(Integer batalhasSelvagemVencidas) {
        this.batalhasSelvagemVencidas = batalhasSelvagemVencidas;
    }

    public String toString() {
        return "Nome: " + nome + " Dinheiro: " + dinheiro;
    }

    public static Integer getImagemTreinador(Integer numero) {
        switch (numero) {
            case 0:
                return (R.drawable.treinador1);
            case 1:
                return (R.drawable.treinador2);
            case 2:
                return (R.drawable.treinador3);
            case 3:
                return (R.drawable.treinador4);
            case 4:
                return (R.drawable.treinador5);
            case 5:
                return (R.drawable.treinador6);
            case 6:
                return (R.drawable.treinadora1);
            case 7:
                return (R.drawable.treinadora2);
            case 8:
                return (R.drawable.treinadora3);
            case 9:
                return (R.drawable.treinadora4);
            case 10:
                return (R.drawable.treinadora5);
            case 11:
                return (R.drawable.treinadora6);
            default:
                return (R.drawable.treinador6);
        }

    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }
}
