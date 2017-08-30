package br.com.qgdostark.comandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.model.ProdutoPedido;
import br.com.qgdostark.comandroid.util.SqlUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by stark on 31/07/17.
 */

public class ProdutoPedidoDAO {


    public SQLiteDatabase db;
    private Context context;

    public ProdutoPedidoDAO(Context context) {
        db = SQLiteFactory.getInstanceDatabase(context);
        this.context = context;
    }


    public boolean salvarOuAtualizar(ProdutoPedido produtoPedido) {
            try {
                ContentValues valores = new ContentValues();
                if (produtoPedido.getId() == null) {
                    valores.put(SqlUtil.COLUNA_PRODUTO_PEDIDO_NOME, produtoPedido.getNome());
                    valores.put(SqlUtil.COLUNA_PRODUTO_PEDIDO_QUANTIDADE, produtoPedido.getQuantidade());
                    valores.put(SqlUtil.COLUNA_PRODUTO_PEDIDO_SUBTOTAL, produtoPedido.getSubtotal());
                    valores.put(SqlUtil.COLUNA_PRODUTO_PEDIDO_PEDIDO_ID, produtoPedido.getPedido_id());
                    valores.put(SqlUtil.COLUNA_PRODUTO_PEDIDO_PRODUTO_ID, produtoPedido.getProduto_id());

                    db.insert(SqlUtil.BD_PRODUTO_PEDIDO_NOME_TABLE, null, valores);
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
    public List<ProdutoPedido> getProdutoPedidoAll(){
        try {
            Cursor cursor = db.query(SqlUtil.BD_PRODUTO_PEDIDO_NOME_TABLE,null,null,null,null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    /*Buscando todos os itens da tabela*/
    public List<ProdutoPedido> getByMesaAll(String pedido_id){
        try {
            Cursor cursor = db.query(SqlUtil.BD_PRODUTO_PEDIDO_NOME_TABLE,null,"pedido_id='"+pedido_id+"'",null, null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    /*Populando a lista com os itens buscados*/
    public List<ProdutoPedido> populaLista(Cursor cursor) {
        List<ProdutoPedido> produtoPedidos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ProdutoPedido produtoPedido;
                produtoPedido = new ProdutoPedido();
                produtoPedido.setId(cursor.getLong(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_ID)));
                produtoPedido.setNome(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_NOME)));
                produtoPedido.setSubtotal(cursor.getDouble(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_SUBTOTAL)));
                produtoPedido.setQuantidade(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_QUANTIDADE)));
                produtoPedido.setProduto_id(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_PRODUTO_ID)));
                produtoPedido.setPedido_id(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PRODUTO_PEDIDO_PEDIDO_ID)));

                produtoPedidos.add(produtoPedido);

            } while (cursor.moveToNext());
        }
        return produtoPedidos;
    }

    /*Apaga o item*/
    public void deleteItem(String item){
//        String[] params = new String[]{String.valueOf(item.getId())};
        String[] params = new String[]{item};

        db.delete(SqlUtil.BD_PRODUTO_PEDIDO_NOME_TABLE, "id=?", params);
        //SQLiteFactory.closeDatabase();
        //Log.d(TAG,"Deletou ["+ item.getNome() +"] registro");
    }


    public void atualizar(ProdutoPedido produtoPedido) {

    }
}

