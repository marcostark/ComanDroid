package br.com.qgdostark.comandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.model.Mesa;
import br.com.qgdostark.comandroid.util.SqlUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by stark on 17/07/17.
 */

public class MesaDAO {
    public SQLiteDatabase db;
    private Context context;

    public MesaDAO(Context context) {
        db = SQLiteFactory.getInstanceDatabase(context);
        this.context = context;
    }

    /*Salva ou atualiza o item na tabela*/
    public boolean salvarOuAtualizar(Mesa mesa) {
        try {
            ContentValues valores = new ContentValues();
            if (mesa.getId() == null) {
                valores.put(SqlUtil.COLUNA_MESA_NOME, mesa.getNomeMesa());
                valores.put(SqlUtil.COLUNA_MESA_STATUS, mesa.getStatus());

                db.insert(SqlUtil.BD_MESA_NOME_TABLE, null, valores);
//                SQLiteFactory.closeDatabase(); //Ver isso
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Mesa> getOpenMesa(){
        try {
            Cursor cursor = db.query(SqlUtil.BD_MESA_NOME_TABLE,null,"status='"+0+"'",null, null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    public List<Mesa> getProdutosAll() {
        Log.d("Lista", "getprodutos");
        try{
            Cursor cursor = db.query(SqlUtil.BD_MESA_NOME_TABLE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            return populaLista(cursor);
        } finally {
          // SQLiteFactory.closeDatabase();
        }
    }

    public List<Mesa> populaLista(Cursor cursor){
        List<Mesa> mesas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Mesa mesa;
                mesa = new Mesa();
                mesa.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_ID)));
                mesa.setNomeMesa(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_NOME)));
                mesa.setStatus(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_STATUS)));
                mesas.add(mesa);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return mesas;
    }

    public List<Mesa> getMesaById(String id) {
        Log.d("Lista", "getprodutos");
        try{
            Cursor cursor = db.query(SqlUtil.BD_MESA_NOME_TABLE,null,"id='"+id+"'",null, null,null,null);
            return populaLista(cursor);
        } finally {
            // SQLiteFactory.closeDatabase();
        }
    }


    public Mesa getByName(String nomeMesa) {

        try {

            Cursor cursor = db.query(SqlUtil.BD_MESA_NOME_TABLE,null,"desc_mesa='"+nomeMesa+"' AND status = 0",null, null,null,null);
            Mesa mesa = new Mesa();
            if(cursor.moveToFirst()) {
                do {
                    mesa.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_ID)));
                    mesa.setNomeMesa(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_NOME)));
                    mesa.setStatus(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_MESA_STATUS)));

                } while (cursor.moveToNext());
            }
            return mesa;
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    public void atualiza(Mesa mesa) {
        String[] params = new String[] {mesa.getId().toString()};
        ContentValues valores = new ContentValues();

        Log.d("Fechar Mesa Atualizada", mesa.toString());

        valores.put(SqlUtil.COLUNA_MESA_NOME, mesa.getNomeMesa());
        valores.put(SqlUtil.COLUNA_MESA_STATUS, mesa.getStatus());

        db.update(SqlUtil.BD_MESA_NOME_TABLE ,valores,"id=?", params);

    }
}
