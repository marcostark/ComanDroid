package br.com.qgdostark.comandroid.helper;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.com.qgdostark.comandroid.activity.CadastroProdutoActivity;
import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.dao.CategoriaDAO;
import br.com.qgdostark.comandroid.model.Categoria;
import br.com.qgdostark.comandroid.model.Produto;
import br.com.qgdostark.comandroid.util.Validator;

/**
 * Created by stark on 04/08/17.
 */

public class FormularioHelperProduto {

    private final EditText campoNome;
    private final EditText campoDescricao;
    private final EditText campoValor;
    private Spinner spinnerCategorias;
    private Produto produto;
    private Categoria categoria;


    public FormularioHelperProduto(CadastroProdutoActivity activity){
        CategoriaDAO categoriaDAO = new CategoriaDAO(activity);
        List<Categoria> categorias = categoriaDAO.getItensAll();


        campoNome = (EditText) activity.findViewById(R.id.edit_nome);
        campoDescricao = (EditText) activity.findViewById((R.id.edit_descricao));
        campoValor = (EditText) activity.findViewById((R.id.edit_valor));

        spinnerCategorias = (Spinner)activity.findViewById(R.id.spinner_categoria);
        ArrayAdapter<Categoria> adapter =  new ArrayAdapter<Categoria>(activity,
                android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapter);

        AdapterView.OnItemSelectedListener select = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoria = (Categoria) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        spinnerCategorias.setOnItemSelectedListener(select);



        produto = new Produto();

    }

    public Produto pegaProduto(){

        if(campoNome.getText().toString().isEmpty() ||
                campoDescricao.getText().toString().isEmpty() ||
                campoValor.getText().toString().isEmpty()){
            Validator.validadeNotNull(campoValor, "Insira um valor");
            Validator.validadeNotNull(campoDescricao, "Insira uma descrição");
            Validator.validadeNotNull(campoNome, "Insira um nome");

        } else {
            produto.setNome(campoNome.getText().toString());
            produto.setDescricao(campoDescricao.getText().toString());
            produto.setValor(Double.valueOf(campoValor.getText().toString()));
            produto.setCategoria(spinnerCategorias.getSelectedItem().toString());
            produto.setCategoria_id(String.valueOf(categoria.getId()));
        }

        return produto;
    }

    public void preencheFormulario(Produto produto){

        campoNome.setText(produto.getNome());
        campoDescricao.setText(produto.getDescricao());
        campoValor.setText(String.valueOf(produto.getValor()));

        this.produto = produto;
    }

}
