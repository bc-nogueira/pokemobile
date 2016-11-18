package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 18/11/2016.
 */

public class Ataque {
    private Integer idAtaque;
    private String nomeAtaque;
    private Elemento elemento;
    private Integer danoBase;

    public Ataque() {
    }

    public Ataque(String nomeAtaque, Elemento elemento, Integer danoBase) {
        this.nomeAtaque = nomeAtaque;
        this.elemento = elemento;
        this.danoBase = danoBase;
    }

    public String getNomeAtaque() {
        return nomeAtaque;
    }

    public void setNomeAtaque(String nomeAtaque) {
        this.nomeAtaque = nomeAtaque;
    }

    public Integer getIdAtaque() {
        return idAtaque;
    }

    public void setIdAtaque(Integer idAtaque) {
        this.idAtaque = idAtaque;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public void setElemento(String elemento) {
        switch (elemento) {
            case "AGUA":
                this.elemento = Elemento.AGUA;
                break;
            case "ELETRICO":
                this.elemento = Elemento.ELETRICO;
                break;
            case "FOGO":
                this.elemento = Elemento.FOGO;
                break;
            case "GRAMA":
                this.elemento = Elemento.GRAMA;
                break;
            case "NORMAL":
                this.elemento = Elemento.NORMAL;
                break;
        }
    }

    public Integer getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(Integer danoBase) {
        this.danoBase = danoBase;
    }

}
