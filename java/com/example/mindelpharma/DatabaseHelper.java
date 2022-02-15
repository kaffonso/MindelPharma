package com.example.mindelpharma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Farmacias.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME =  "my_farmacias";

    private final File DATABASE_FILE;

    private static final String COLUMN_ID = "_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        assert context != null;
        DATABASE_FILE = context.getDatabasePath("Farmacias.db");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

//    void addFarmacia(String name, String address){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_NAME, name);
//        cv.put(COLUMN_ADDRESS,address);
//
//        long result = db.insert(TABLE_NAME, null, cv);
//        if(result == -1){
//            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(context,"Added Sucessfully",Toast.LENGTH_LONG).show();
//        }
//    }
}
