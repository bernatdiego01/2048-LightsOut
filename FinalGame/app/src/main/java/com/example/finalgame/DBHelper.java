package com.example.finalgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "listaJugadors";
    private static final int DATABASE_VERSION = 1;

    public static final String Table = "CLASSIFICACIO";
    public static final String Id = "_id";
    public static final String jugador = "Nombre";
    public static final String puntos = "Puntuacion";

    private static final String CREATE_TABLE ="CREATE TABLE " + Table + " (" +
            jugador + " TEXT, " +
            puntos + " TEXT)";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(db);
    }
    public boolean insertdatas(String nom, String punts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new  ContentValues();
        contentValues.put("Nombre", nom);
        contentValues.put("Puntuacion", punts);
        long result =db.insert("CLASSIFICACIO", null, contentValues );
        if (result==-1){
            return false;

        }else{
            return true;

        }




    }
    public Cursor getdata(){
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CLASSIFICACIO", null);
        return cursor;
    }
}
