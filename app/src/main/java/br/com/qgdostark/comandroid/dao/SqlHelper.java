package br.com.qgdostark.comandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.qgdostark.comandroid.util.SqlUtil;

/**
 * Created by stark on 08/07/17.
 */


public class SqlHelper extends SQLiteOpenHelper {

    public static final String BASE_DE_DADOS= "comandroid.sqlite";
    public static final int VERSAO = 2;
    public static final String TAG = "BD";


    private Context context;
    public SqlHelper(Context context) {
        super(context, BASE_DE_DADOS, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlUtil.SQL_CREATE_TABLE_PRODUTO);
        db.execSQL(SqlUtil.SQL_CREATE_TABLE_MESA);
        db.execSQL(SqlUtil.SQL_CREATE_TABLE_PEDIDO);
        db.execSQL(SqlUtil.SQL_CREATE_TABLE_PRODUTO_PEDIDO);
        db.execSQL(SqlUtil.SQL_CREATE_TABLE_CATEGORIA);

        db.execSQL(SqlUtil.SQL_INSERT_TABLE_PRODUTO);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_MESA);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_PIZZA);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_LANCHE);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_PETISCO);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_BEBIDA);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_SOBREMESA);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_HAMBURGUERES);
        db.execSQL(SqlUtil.SQL_INSERT_TABLE_CATEGORIA_TODAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "DROP TABLE IF EXISTS";
//        db.execSQL(sql);
//        onCreate(db);
    }
}

