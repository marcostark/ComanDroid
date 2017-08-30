package br.com.qgdostark.comandroid.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.dao.ProdutoPedidoDAO;
import br.com.qgdostark.comandroid.model.ProdutoPedido;
import br.com.qgdostark.comandroid.helper.FormularioHelperPedido;

public class FormPedidoActivity extends AppCompatActivity {

    protected ActionBar action;
    private FormularioHelperPedido helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_pedido);

        this.action = getSupportActionBar();
        this.action.setDisplayHomeAsUpEnabled(true);

        helper =  new FormularioHelperPedido(this);

//        Intent intent = getIntent();
//        Pedido pedido = (Pedido) intent.getSerializableExtra("pedido");
//        if(pedido != null){
//            helper.preencheFormulario(pedido);
//        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_item_cardapio_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.form_item_salvar:

                //Aqui responsavel por criar o pedido
                ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO(this);
                ProdutoPedido produtoPedido = helper.pegaPedido();

                if(produtoPedido.getId() == null){
                    produtoPedidoDAO.salvarOuAtualizar(produtoPedido);
                } else {
                    Log.d("ProdP", "Atualizar");
                    //pedidoDAO.atualizar(produtoPedido);
                }
                finish();
                break;
            case android.R.id.home :
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
