package br.com.qgdostark.comandroid.model;

import java.io.Serializable;

/**
 * Created by stark on 23/06/17.
 */

public class Produto implements Serializable {

    private Integer id;
    private int thumb;
    private String nome;
    private String descricao;
    private double valor;
    private String categoria;
    private String categoria_id;

    public Produto(){}

    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Produto(String nome, String descricao, double valor, String categoria, String categoria_id) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.categoria_id = categoria_id;

    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome()+ "\n" +
                "Descricao: " + getDescricao()+ "\n" +
                "Valor: " + getValor()+ "\n" +
                "Categoria: " + getCategoria()+ "\n" +
        "Categoria_id: " + getCategoria_id()+ "\n";
    }
}

