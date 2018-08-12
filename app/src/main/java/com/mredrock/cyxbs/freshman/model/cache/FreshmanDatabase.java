package com.mredrock.cyxbs.freshman.model.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FreshmanDatabase extends SQLiteOpenHelper {

    private Context mContext;

    public static final String NECESSARY_ADD_CONTENT = "create table necessary("
            +"id integer primary key autoincrement,"
            +"name text ,"
            +"number integer,"
            +"oldPosition integer,"
            +"property text,"
            +"content text  )";
    public FreshmanDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NECESSARY_ADD_CONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists necessary");
        onCreate(sqLiteDatabase);
    }

}
