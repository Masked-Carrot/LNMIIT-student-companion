package com.example.app100;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.app100.ui.home.HomeFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String table_name = "SUBJECT_LIST";
    private static final String COL0 = "ID";
    private static final String COL1 = "NAME";
    private static final String COL2 = "CREDITS";
    private static final String COL3 = "CLASS_ATTENDED";
    private static final String COL4 = "CLASS_MISSED";


    public DatabaseHelper(@Nullable Context context) {
        super(context, table_name, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + table_name + "("+
                COL0+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " INTEGER, "+
                COL3 + " INTEGER, "+
                COL4 + " INTEGER);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    boolean addData(String name, int credit , int good , int bad){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1 , name);
        contentValues.put(COL2 , credit);
        contentValues.put(COL3 , good);
        contentValues.put(COL4 , bad);
        long result = db.insert(table_name , null , contentValues);
        return result != -1;
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM SUBJECT_LIST;";
        return db.rawQuery(query , null);
    }



    void append_data(String str, String name , int goodbad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, goodbad);
        int count = db.update(table_name, contentValues, "NAME = ?", new String[]{name});
        Log.d(TAG, "blue button is pressed");
        System.out.println(count);

    }
}
