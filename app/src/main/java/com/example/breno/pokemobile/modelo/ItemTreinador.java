package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 13/11/2016.
 */

public class ItemTreinador {
    private Long idItemTreinador;
    private Item item;
    private Treinador treinador;
    private Integer quantidade;

    public Long getIdItemTreinador() {
        return idItemTreinador;
    }

    public void setIdItemTreinador(Long idItemTreinador) {
        this.idItemTreinador = idItemTreinador;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}