package br.com.qgdostark.comandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.dao.ProdutoDAO;
import br.com.qgdostark.comandroid.model.Produto;
import br.com.qgdostark.comandroid.helper.FormularioHelperProduto;

public class CadastroProdutoActivity extends AppCompatActivity {

    protected ActionBar action;

    private ProdutoDAO produtoDAO;
    private FormularioHelperProduto helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produtos");
        this.action = getSupportActionBar();
        this.action.setDisplayHomeAsUpEnabled(true);

        helper =  new FormularioHelperProduto(this);

        Intent intent = getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");
        if(produto != null){
            helper.preencheFormulario(produto);
        }

        produtoDAO = new ProdutoDAO(getContext());

    }

    public Context getContext() {
        return this;
    }

    /*Criando Menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_item_cardapio_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.form_item_salvar:
                Produto produto = helper.pegaProduto();
                if(produto.getNome() == null ||
                        produto.getDescricao() == null ||
                        produto.getValor() == 0.0){
                    break;

                } else {
                    if (produto.getId() == null) {
                        produtoDAO.salvar(produto);
                    } else {
                        produtoDAO.atualizar(produto);
                    }
                    finish();
                }
                //break;
            case android.R.id.home :
                finish();
                return true;
            }

        return super.onOptionsItemSelected(item);
    }
}

