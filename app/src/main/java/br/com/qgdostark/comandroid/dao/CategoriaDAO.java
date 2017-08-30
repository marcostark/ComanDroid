package br.com.qgdostark.comandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.model.Categoria;
import br.com.qgdostark.comandroid.util.SqlUtil;

/**
 * Created by stark on 03/07/17.
 */

public class CategoriaDAO {

    public SQLiteDatabase db;
    private Context context;

    public CategoriaDAO(Context context) {
        db = SQLiteFactory.getInstanceDatabase(context);
        this.context = context;
    }



    /*Buscando todos os itens da tabela*/
    public List<Categoria> getItensAll(){
        try {
            Cursor cursor = db.query(SqlUtil.BD_CATEGORIA_NOME_TABLE,null,null,null,null,null,null);
            return populaLista(cursor);
        }finally {
            // SQLiteFactory.closeDatabase();
        }
    }

    /*Populando a lista com os itens buscados*/
    public List<Categoria> populaLista(Cursor cursor){
        List<Categoria> categorias = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Categoria categoria;
                categoria = new Categoria();

                categoria.setId(cursor.getInt(cursor.getColumnIndex(SqlUtil.COLUNA_CATEGORIA_ID)));
                categoria.setNome(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_CATEGORIA_NOME)));
                categoria.setDescricao(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_CATEGORIA_DESCRICAO)));
                categoria.setThumb(cursor.getString(cursor.getColumnIndex(SqlUtil.COLUNA_CATEGORIA_THUMB)));

                Log.d("Id", String.valueOf(categoria.getId()));
                Log.d("Nome", categoria.getNome().toString());
                Log.d("Descricão", categoria.getDescricao().toString());
                Log.d("Thumb", String.valueOf(categoria.getThumb()));

                categorias.add(categoria);

            }while(cursor.moveToNext());
        }
        return categorias;
    }


}
