package br.com.qgdostark.comandroid.model;

/**
 * Created by stark on 16/07/17.
 */

public class ProdutoPedido {

    private Long id;
    private Integer produto_id;
    private Integer pedido_id;
    private String nome;
    private double subtotal;
    private int quantidade;

    public ProdutoPedido(){}

    public ProdutoPedido(String nome,Integer produto_id, int quantidade, double valor, Integer pedido_id) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.subtotal = valor;
        this.pedido_id = pedido_id;
        this.produto_id = produto_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Integer pedido) {
        this.pedido_id = pedido;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return  "Nome: " + getNome() + "\n" +
                "Produto_id: " + getProduto_id() + "\n" +
                "Pedido_id: " + getPedido_id() + "\n" +
                "Quantidade: " + getQuantidade() + "\n " +
                "Subtotal:" + getSubtotal();
    }
}
