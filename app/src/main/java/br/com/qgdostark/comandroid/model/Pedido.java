package br.com.qgdostark.comandroid.model;

import java.io.Serializable;

/**
 * Created by stark on 18/07/17.
 */

public class Pedido implements Serializable {
    private Integer id;
    private String dataDaVenda;
    private double valorTotal;
    private Integer mesa_id;
    private int faturado;
    private int isPrinter;

    public Pedido(){}

    public Pedido(String dataDaVenda,
                  double valorTotal,Integer mesa_id) {
        this.dataDaVenda = dataDaVenda;
        this.valorTotal = valorTotal;
        this.mesa_id = mesa_id;
    }

    public Pedido(String dataDaVenda,
                  double valorTotal,Integer mesa_id, int isPrinter, int faturado) {
        this.dataDaVenda = dataDaVenda;
        this.valorTotal = valorTotal;
        this.isPrinter = isPrinter;
        this.mesa_id = mesa_id;
        this.faturado = faturado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(String dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int isPrinter() {
        return isPrinter;
    }

    public void setPrinter(int printer) {
        isPrinter = printer;
    }

    public Integer getMesa_id() {
        return mesa_id;
    }

    public void setMesa_id(Integer mesa_id) {
        this.mesa_id = mesa_id;
    }

    public int isFaturado() {
        return faturado;
    }

    public void setFaturado(int faturado) {
        this.faturado = faturado;
    }

    @Override
    public String toString() {
        return  "id: " + getId().toString() + "\n" +
                "Data: " + getDataDaVenda().toString() + "\n" +
                "Valor Total: " + Double.valueOf(getValorTotal()) + "\n" +
                "Enviado ? : " + isPrinter() + "\n " +
                "Faturado ? : " + isFaturado() + "\n " +
                "Mesa_ID:" + String.valueOf(getMesa_id());
    }
}

