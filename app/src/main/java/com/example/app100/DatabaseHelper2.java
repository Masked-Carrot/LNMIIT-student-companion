package com.example.app100;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {



    private   String table_name ;
    private static final String COL0 = "ID";
    private static final String COL1 = "YOUR_MARKS";
    private static final String COL2 = "TOTAL_MARKS";

    public DatabaseHelper2(@Nullable Context context, @Nullable String name) {
        super(context, name, null , 1);
        table_name = name;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + table_name + "("+
                COL0+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " INTEGER, "+
                COL2 + " INTEGER);";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);

    }

    boolean addData(int total , int marks){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1 , marks);
        contentValues.put(COL2 , total);
        long result = db.insert(table_name , null , contentValues);
        return result != -1;
    }

    public Cursor getmarksdata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+name +" ;";
        return db.rawQuery(query , null);
    }


    public Cursor gettotaldata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+name+";";
        return db.rawQuery(query , null);
    }

}
