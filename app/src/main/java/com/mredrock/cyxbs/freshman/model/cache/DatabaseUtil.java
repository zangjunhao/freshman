package com.mredrock.cyxbs.freshman.model.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtil {
    public  int version;
    private FreshmanDatabase freshmanDatabase;
    private SQLiteDatabase db;
    private DatabaseUtil(){

    }

   public static class DatabaseUtilHelper{
        private final static DatabaseUtil DATABASE_UTIL = new DatabaseUtil();
        public static DatabaseUtil getInstance(){
            return DATABASE_UTIL;
        }
    }

    public void initDatabasse(Context context,String name,int version){
        if (this.version!=version) {
            freshmanDatabase = new FreshmanDatabase(context, name, null, version);
            db = freshmanDatabase.getWritableDatabase();
            this.version = version;
        }
    }

    public void add(String table, ContentValues values){
        if (db==null) {
            return;
          }
          db.insert(table,null,values);
         values.clear();
        }

        public Cursor query(String table,String selection,String[] selectionArgs ){
        if (db==null){
            return null;
        }
            Cursor cursor = db.query(table,null,selection,selectionArgs,null,null,null);
        return cursor;
    }
}
