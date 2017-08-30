package br.com.qgdostark.comandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.adapter.ProdutoAdapter;
import br.com.qgdostark.comandroid.dao.ProdutoDAO;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Categoria;
import br.com.qgdostark.comandroid.model.Produto;

public class ListaProdutosActivity extends AppCompatActivity implements ClickRecyclerView{

    Context context = ListaProdutosActivity.this;
    private RecyclerView mRecyclerViewItens;
    private LinearLayoutManager mlinearLayoutManager;
    private ProdutoAdapter mProdutoAdapter;
    private List<Produto> listProduto;
    private ProdutoDAO produtoDAO;
    public EditText search;

    private Categoria categoriaSerial;
    private String pedido_id;

    protected ActionBar action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        this.action = getSupportActionBar();
        this.action.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        categoriaSerial = (Categoria)intent.getSerializableExtra("categoria");
        pedido_id = intent.getStringExtra("pedido_id");
        this.action.setTitle(categoriaSerial.getNome().toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaProdutosActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("categoria_id", String.valueOf(categoriaSerial.getId()));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        loadRecyclerView();
    }

    public void initViews(){
        search = (EditText) findViewById( R.id.search);
        mRecyclerViewItens = (RecyclerView) findViewById(R.id.rv_list_generico);
    }

    public void loadRecyclerView(){

        String categoria_id;
        categoria_id = String.valueOf(categoriaSerial.getId());
        listProduto = new ArrayList<>();
        produtoDAO = new ProdutoDAO(context);
        if(categoria_id.equals("7")) {
            listProduto = produtoDAO.getItensAll();
            mProdutoAdapter = new ProdutoAdapter(this,listProduto ,this);
        } else {
            listProduto = produtoDAO.getByCategoriaItensAll(categoria_id);
            mProdutoAdapter = new ProdutoAdapter(this,listProduto, this);
        }

        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(getApplicationContext());
        mRecyclerViewItens.setLayoutManager(mLayoutManager);
        mRecyclerViewItens.setHasFixedSize(true);
        mRecyclerViewItens.setAdapter(mProdutoAdapter);

       addTextListener();
    }


    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {
        //TODO
        final Produto produto = mProdutoAdapter.getmProduto().get(position);
        if(isLongClick){
                    PopupMenu popup = new PopupMenu(context,view);
                    popup.inflate(R.menu.item_cardapio_popup_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_cardapio_popup_menu_excluir:
                                    produtoDAO.deleteItem(produto);
                                    onResume();
                                    break;
                                case R.id.item_cardapio_popup_menu_editar:
                                    Log.d("Editar", "" + produto.getNome().toString());
                                    Intent intent = new Intent(context, CadastroProdutoActivity.class);
                                    intent.putExtra("produto", produto);
                                    startActivity(intent);
                                    break;
                            }
                            return false;
                        }
                    });
                    popup.show();
        } else {
            if(pedido_id == null){
                //Não faz nada
            } else {
                Intent intent = new Intent(ListaProdutosActivity.this, FormPedidoActivity.class);
                intent.putExtra("produto", produto);
                intent.putExtra("pedido_id", pedido_id);
                startActivity(intent);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<Produto> filteredList = new ArrayList<>();

                for (int i = 0; i < listProduto.size(); i++) {

                    final String text = listProduto.get(i).getNome().toString().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(listProduto.get(i));
                    }
                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerViewItens.setLayoutManager(mLayoutManager);
                mProdutoAdapter = new ProdutoAdapter(ListaProdutosActivity.this,filteredList,ListaProdutosActivity.this);
                mRecyclerViewItens.setAdapter(mProdutoAdapter);
                mProdutoAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

}
