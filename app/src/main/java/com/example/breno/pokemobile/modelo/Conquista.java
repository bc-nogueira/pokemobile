package com.example.breno.pokemobile.modelo;

/**
 * Created by Breno on 01/12/2016.
 */

public class Conquista {
    private Long idConquista;
    private String descricao;
    private String mensagem;
    private String campo;
    private Integer objetivo;

    public Conquista() {
    }

    public Conquista(String descricao, String mensagem, String campo, Integer objetivo) {
        this.descricao = descricao;
        this.mensagem = mensagem;
        this.campo = campo;
        this.objetivo = objetivo;
    }

    public Long getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(Long idConquista) {
        this.idConquista = idConquista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

}
