package br.com.qgdostark.comandroid.helper;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.qgdostark.comandroid.activity.FormPedidoActivity;
import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.model.Produto;
import br.com.qgdostark.comandroid.model.ProdutoPedido;

/**
 * Created by stark on 04/08/17.
 */

public class FormularioHelperPedido {

    private final TextView campoNome;
    private final TextView campoDescricao;
    private final TextView campoValor;
    private final TextView campoQuant;
    private final TextView campoValorTotal;
    private final Button btnSoma;
    private final Button btnSub;

    private int idItem;
    private String nomeItem;
    private String descItem;
    private double valorItem;

    private int auxQuantItem;
    private double auxVlrTotal;
    private double total;

    private String pedido_id;
    private ProdutoPedido produtoPedido;
    private Produto produtoSerial;


    public FormularioHelperPedido(FormPedidoActivity activity){

        Intent intent = activity.getIntent();
        produtoSerial = (Produto)intent.getSerializableExtra("produto");
        pedido_id = activity.getIntent().getStringExtra("pedido_id");

        /*Pegando dados que foram passados pela outra activity*/
        idItem = Integer.valueOf(produtoSerial.getId());
        nomeItem = produtoSerial.getNome();
        descItem = produtoSerial.getDescricao();
        valorItem = Double.valueOf(produtoSerial.getValor());

        campoNome = (TextView) activity.findViewById(R.id.form_pedido_titulo);
        campoDescricao = (TextView) activity.findViewById(R.id.form_pedido_descricao);
        campoValor = (TextView) activity.findViewById(R.id.form_pedido_valor);
        campoQuant = (TextView) activity.findViewById(R.id.form_pedido_quantidade);
        campoValorTotal = (TextView) activity.findViewById(R.id.form_pedido_valor_total);

        btnSoma = (Button) activity.findViewById(R.id.form_pedido_btn_soma);
        btnSub = (Button) activity.findViewById(R.id.form_pedido_btn_sub);

        /*Setando valores aos componentes*/
        campoNome.setText(nomeItem);
        campoDescricao.setText(descItem);
        campoValor.setText(String.format("R$ %.2f",valorItem));
        campoValorTotal.setText(String.format("R$ %.2f",valorItem));

        auxVlrTotal = valorItem;
        auxQuantItem = 1;
        total = 0;
        campoQuant.setText(" " + auxQuantItem);

        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auxQuantItem +=1;
                total = auxVlrTotal * auxQuantItem;

                campoQuant.setText(" " + auxQuantItem);
                campoValorTotal.setText(String.format("R$ %.2f", total));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxQuantItem -=1;

                if(auxQuantItem <= 1){
                    auxQuantItem = 1;
                }
                total = auxVlrTotal * auxQuantItem;

                campoQuant.setText(" " + auxQuantItem);
                campoValorTotal.setText(String.format("R$ %.2f", total));
            }
        });

        produtoPedido = new ProdutoPedido();
    }



    public ProdutoPedido pegaPedido(){
        if(total == 0){
            total = valorItem;
        }
        produtoPedido.setNome(campoNome.getText().toString());
        produtoPedido.setProduto_id(produtoSerial.getId());
        produtoPedido.setQuantidade(auxQuantItem);
        produtoPedido.setSubtotal(total);
        produtoPedido.setPedido_id(Integer.valueOf(pedido_id));

        return produtoPedido;
    }

    public void preencheFormulario(ProdutoPedido produtoPedido){
        campoNome.setText(produtoPedido.getNome());
        campoDescricao.setText(descItem);
        campoValor.setText(String.format("R$ %.2f",valorItem));
        campoValorTotal.setText(String.format("R$ %.2f",produtoPedido.getSubtotal()));

        this.produtoPedido = produtoPedido;
    }


}
