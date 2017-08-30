package br.com.qgdostark.comandroid.model;

import java.io.Serializable;

/**
 * Created by stark on 23/06/17.
 */

public class Categoria implements Serializable {

    private int id;
    private String thumb;
    private String nome;
    private String descricao;


    public Categoria(){}
    public Categoria(int id, String nome,String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public Categoria(int id, String nome,String thumb, String descricao) {
        this.id = id;
        this.thumb = thumb;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return ""+getNome();
    }
}
