package br.com.qgdostark.comandroid.model;

import android.graphics.Interpolator;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by stark on 16/07/17.
 */

public class Mesa implements Serializable {
    private Integer id;
    private String nomeMesa;
    private int status;

    public Mesa(){}
    public Mesa(String nomeMesa, int status) {
        this.nomeMesa = nomeMesa;
        this.status = status;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMesa() {
        return nomeMesa;
    }

    public void setNomeMesa(String nomeMesa) {
        this.nomeMesa = nomeMesa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "id: " + getId() + "\n" +
        "Nome: " +getNomeMesa() + "\n" +
                "Status: " + getStatus();
    }
}
