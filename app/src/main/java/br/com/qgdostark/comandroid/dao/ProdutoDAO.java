package br.com.qgdostark.comandroid.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.model.Produto;
import br.com.qgdostark.comandroid.util.SqlUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by stark on 03/07/17.
 */

public class ProdutoDAO {

    public SQLiteDatabase db;
    private Context context;

    public ProdutoDAO(Context context) {
        db = SQLiteFactory.getInstanceDatabase(context);
        this.context = context;
    }

    /*Salva ou atualiza o item na tabela*/
    public boolean salvar(Produto produto) {
        try {
            ContentValues valores = new ContentValues();
            if (produto.getId() == null) {
                valores.put(SqlUtil.COLUNA_PRODUTO_NOME, produto.getNome());
                valores.put(SqlUtil.COLUNA_PRODUTO_DESCRICAO, produto.getDescricao());
                valores.put(SqlUtil.COLUNA_PRODUTO_VALOR, produto.getValor());
                valores.put(SqlUtil.COLUNA_PRODUTO_CATEGORIA, produto.getCategoria());
                valores.put(SqlUtil.COLUNA_PRODUTO_CATEGORIA_ID, produto.getCategoria_id());

                db.insert(SqlUtil.BD_PRODUTOS_NOME_TABLE, null, valores);
               // SQLiteFactory.closeDatabase();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /*Buscando todos os itens da tabela*/
    public List<Produto> getItensAll(){
        try {
            Cursor cursor = db.query(SqlUtil.BD_PRODUTOS_NOME_TABLE,null,null,null,null,null,null);
            return populaLista(cursor);
        }finally {
           // SQLiteFactory.closeDatabase();
        }
    }
    /*Buscando todos os itens da tabela*/
    public List<Produto> getByCategoriaItensAll(String categoria){
        try {
            Cursor cursor = db.query(SqlUtil.BD_PRODUTOS_NOME_TABLE,null,"categoria_id='"+categoria+"'",null, null,null,null);
            return populaLista(cursor);
        }finally {
           // SQLiteFactory.closeDatabase();
        }
    }


    /*Populando a lista com os itens buscados*/
    public List<Produto> populaLista(Cursor cursor){
        List<Produto> produtos = new ArrayList<>();
            if(cursor.moveToFirst()){
                do {
                    Produto produto;
                    produto = new Produto();

                    produto.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_ID)));
                    produto.setNome(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_NOME)));
                    produto.setDescricao(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_DESCRICAO)));
                    produto.setValor(Double.valueOf(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_VALOR))));
                    produto.setCategoria(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_CATEGORIA)));
                    produto.setCategoria_id(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_CATEGORIA_ID)));

                    produtos.add(produto);

                }while(cursor.moveToNext());
            }
        return produtos;
    }

    /*Apaga o item*/
    public void deleteItem(Produto item){
//        String[] params = new String[]{String.valueOf(item.getId())};
        String[] params = new String[]{item.getId().toString()};

        db.delete(SqlUtil.BD_PRODUTOS_NOME_TABLE , "id=?", params);
        //SQLiteFactory.closeDatabase();
        //Log.d(TAG,"Deletou ["+ item.getNome() +"] registro");
    }

    public void atualizar(Produto produto) {
        String[] params = new String[] {produto.getId().toString()};
        ContentValues valores = new ContentValues();
        valores.put(SqlUtil.COLUNA_PRODUTO_NOME, produto.getNome());
        valores.put(SqlUtil.COLUNA_PRODUTO_DESCRICAO, produto.getDescricao());
        valores.put(SqlUtil.COLUNA_PRODUTO_VALOR, produto.getValor());
        valores.put(SqlUtil.COLUNA_PRODUTO_CATEGORIA, produto.getCategoria());
        valores.put(SqlUtil.COLUNA_PRODUTO_CATEGORIA_ID, produto.getCategoria_id());

        db.update(SqlUtil.BD_PRODUTOS_NOME_TABLE ,valores,"id=?", params);
    }
}
