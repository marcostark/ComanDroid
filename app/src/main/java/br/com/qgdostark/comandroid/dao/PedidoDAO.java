package br.com.qgdostark.comandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.model.Pedido;
import br.com.qgdostark.comandroid.util.SqlUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by stark on 16/07/17.
 */

public class PedidoDAO {


    public SQLiteDatabase db;
    private Context context;

    public PedidoDAO(Context context) {
        db = SQLiteFactory.getInstanceDatabase(context);
        this.context = context;
    }

    /*Salva ou atualiza o item na tabela*/
    public boolean salvarOuAtualizar(Pedido pedido) {
        try {
            ContentValues valores = new ContentValues();
            if (pedido.getId() == null) {
                valores.put(SqlUtil.COLUNA_PEDIDO_DATAVENDA, pedido.getDataDaVenda());
                valores.put(SqlUtil.COLUNA_PEDIDO_MESA, pedido.getMesa_id());
                valores.put(SqlUtil.COLUNA_PEDIDO_VALORTOTAL, pedido.getValorTotal());
                valores.put(SqlUtil.COLUNA_PEDIDO_ISPRINTER, pedido.isPrinter());
                valores.put(SqlUtil.COLUNA_PEDIDO_ISFATURADO, pedido.isFaturado());

                db.insert(SqlUtil.BD_PEDIDOS_NOME_TABLE, null, valores);
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
    public List<Pedido> getItensAll(){
        try {
            Cursor cursor = db.query(SqlUtil.BD_PEDIDOS_NOME_TABLE,null,null,null,null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }
    /*Buscando todos os itens da tabela*/
    public Pedido getByMesa(String mesa_id){

        try {

            Cursor cursor = db.query(SqlUtil.BD_PEDIDOS_NOME_TABLE,null,"mesa_id='"+mesa_id+"'",null, null,null,null);
            Pedido pedido = new Pedido();
            if(cursor.moveToFirst()) {
                do {
                    pedido.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ID)));
                    pedido.setDataDaVenda(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_DATAVENDA)));
                    pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_VALORTOTAL)));
                    pedido.setMesa_id(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_MESA)));
                    pedido.setPrinter(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ISPRINTER)));
                    pedido.setFaturado(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ISFATURADO)));

                } while (cursor.moveToNext());
            }
            return pedido;
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    /*Populando a lista com os itens buscados*/
    public List<Pedido> populaLista(Cursor cursor){
        List<Pedido> pedidos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Pedido pedido;
                pedido = new Pedido();

                pedido.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ID)));
                pedido.setDataDaVenda(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_DATAVENDA)));
                pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_VALORTOTAL)));
                pedido.setMesa_id(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_MESA)));
                pedido.setPrinter(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ISPRINTER)));
                pedido.setFaturado(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_PEDIDO_ISFATURADO)));

                pedidos.add(pedido);

            }while(cursor.moveToNext());
        }
        return pedidos;
    }

    public void atualiza(Pedido pedido) {
        String[] params = new String[] {pedido.getId().toString()};
        ContentValues valores = new ContentValues();

        valores.put(SqlUtil.COLUNA_PEDIDO_DATAVENDA, pedido.getDataDaVenda());
        valores.put(SqlUtil.COLUNA_PEDIDO_MESA, pedido.getMesa_id());
        valores.put(SqlUtil.COLUNA_PEDIDO_VALORTOTAL, pedido.getValorTotal());
        valores.put(SqlUtil.COLUNA_PEDIDO_ISPRINTER, pedido.isPrinter());
        valores.put(SqlUtil.COLUNA_PEDIDO_ISFATURADO, pedido.isFaturado());

        db.update(SqlUtil.BD_PEDIDOS_NOME_TABLE ,valores,"id=?", params);
    }

    public List<Pedido> getPedidoByDate(String strData) {
        try {
            Cursor cursor = db.query(SqlUtil.BD_PEDIDOS_NOME_TABLE,null,"dataDaVenda='"+strData+"' AND status_faturado = 1",null, null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }
}
