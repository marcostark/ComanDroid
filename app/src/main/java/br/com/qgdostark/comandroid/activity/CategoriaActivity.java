package br.com.qgdostark.comandroid.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.adapter.CategoriaAdapter;
import br.com.qgdostark.comandroid.dao.CategoriaDAO;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Categoria;
import br.com.qgdostark.comandroid.model.Pedido;

public class CategoriaActivity extends AppCompatActivity implements ClickRecyclerView {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinerManager;
    private List<Categoria> mCategoria = new ArrayList();
    protected ActionBar action;
    private Pedido pedidoSerial;
    private CategoriaDAO categoriaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        this.action = getSupportActionBar();
        this.action.setTitle("Categorias");
        this.action.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        pedidoSerial = (Pedido) intent.getSerializableExtra("pedido");

        setaRecyclerView();

    }

    private void setaRecyclerView() {

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_list_generico);

        mRecyclerView.setHasFixedSize(true); //Tamanho do recycler não irá mudar
        mLinerManager = new LinearLayoutManager(this);
        mLinerManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinerManager);

        categoriaDAO = new CategoriaDAO(this);

        mCategoria = categoriaDAO.getItensAll();
        CategoriaAdapter adapter = new CategoriaAdapter(this, mCategoria, this);
        adapter.setClickRecyclerView(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {
        List<Categoria> categorias = mCategoria;
        Categoria categoria = mCategoria.get(position);

        if(isLongClick){
            Toast.makeText(this, "Categoria:" + categoria.getNome().toString(), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ListaProdutosActivity.class);
            if(pedidoSerial != null){
                intent.putExtra("pedido_id", String.valueOf(pedidoSerial.getId()));
                //startActivity(intent);
            }// else {
//                intent.putExtra("categoria", categoria);
//            }
            intent.putExtra("categoria", categoria);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}