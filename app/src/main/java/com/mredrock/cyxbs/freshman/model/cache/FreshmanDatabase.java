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

    public static final String NAME_COLLEGE = "create table collegeName("
            +"id integer primary key autoincrement,"
            +"name text)";

    public static final String CAFETERIA = "create table cafeteria("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String BEDROOM = "create table bedroom("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String FOOD = "create table food("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String VIEWS = "create table views("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String CAMPUS = "create table campus("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String BANK = "create table bank("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";

    public static final String BUS = "create table bus("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";


    public static final String DELIVERY = "create table delivery("
            +"id integer primary key,"
            +"name text,"
            +"content text,"
            +"picture text) ";
    public FreshmanDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NECESSARY_ADD_CONTENT);
        sqLiteDatabase.execSQL(NAME_COLLEGE);
        sqLiteDatabase.execSQL(CAFETERIA);
        sqLiteDatabase.execSQL(BEDROOM);
        sqLiteDatabase.execSQL(FOOD);
        sqLiteDatabase.execSQL(VIEWS);
        sqLiteDatabase.execSQL(BUS);
        sqLiteDatabase.execSQL(DELIVERY);
        sqLiteDatabase.execSQL(BANK);
        sqLiteDatabase.execSQL(CAMPUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists necessary");
        sqLiteDatabase.execSQL("drop table if exists collegeName");
        sqLiteDatabase.execSQL("drop table if exists strategy");
        sqLiteDatabase.execSQL("drop table if exists bedroom");
        sqLiteDatabase.execSQL("drop table if exists cafeteria");
        sqLiteDatabase.execSQL("drop table if exists food");
        sqLiteDatabase.execSQL("drop table if exists views");
        sqLiteDatabase.execSQL("drop table if exists bus");
        sqLiteDatabase.execSQL("drop table if exists delivery");
        sqLiteDatabase.execSQL("drop table if exists bank");
        sqLiteDatabase.execSQL("drop table if exists campus");
        onCreate(sqLiteDatabase);
    }

}
