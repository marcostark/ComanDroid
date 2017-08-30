package br.com.qgdostark.comandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by stark on 08/07/17.
 */

public class SQLiteFactory {

    private static SQLiteDatabase db;
    private static SqlHelper dbHelper;

    private SQLiteFactory(){}


    public static SQLiteDatabase getInstanceDatabase(Context context) {

        if(db == null || !db.isOpen()){

            dbHelper = new SqlHelper(context);
            db = dbHelper.getWritableDatabase();
        }

        return db;
    }

    public static void closeDatabase(){

        if(db != null && db.isOpen()){

            db.close();

            if(dbHelper != null){

                dbHelper.close();

            }
        }
    }

    }
