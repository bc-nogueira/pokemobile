package com.example.breno.pokemobile.modelo;

import java.io.Serializable;

/**
 * Created by Breno on 06/11/2016.
 */

public class Item implements Serializable {
    private Long idItem;
    private String nome;
    private String descricao;
    private TipoItem tipo;
    private Integer preco;
    private Double efeitoCaptura;
    private Double efeitoCura;
    private Integer icone;

    public Item() {}

    public Item(String nome, String descricao, TipoItem tipo, Integer preco, Double efeitoCaptura, Double efeitoCura, Integer icone) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
        this.efeitoCaptura = efeitoCaptura;
        this.efeitoCura = efeitoCura;
        this.icone = icone;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public void setTipoComString(String tipo) {
        switch (tipo) {
            case "CAPTURA":
                this.tipo = TipoItem.CAPTURA;
                break;
            case "CURA":
                this.tipo = TipoItem.CURA;
                break;
            case "REVIVE":
                this.tipo = TipoItem.REVIVE;
                break;
        }
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public Double getEfeitoCaptura() {
        return efeitoCaptura;
    }

    public void setEfeitoCaptura(Double efeitoCaptura) {
        this.efeitoCaptura = efeitoCaptura;
    }

    public Double getEfeitoCura() {
        return efeitoCura;
    }

    public void setEfeitoCura(Double efeitoCura) {
        this.efeitoCura = efeitoCura;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

}
