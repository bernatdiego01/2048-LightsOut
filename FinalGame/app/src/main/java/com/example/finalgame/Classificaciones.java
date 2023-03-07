package com.example.finalgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Classificaciones extends AppCompatActivity {
    ArrayList<String> nom, punts;
    DBHelper dbHelper =new DBHelper(Classificaciones.this);
    TextAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        Intent intent = getIntent();
        String puntos = intent.getStringExtra("puntuacio");
        String nombre= intent.getStringExtra("nombre");
        nom=new ArrayList<>();
        punts= new ArrayList<>();
        adapter=new TextAdapter(nom, punts);
        Boolean checkinsertdata = dbHelper.insertdatas(nombre, puntos);
        if(checkinsertdata){
            System.out.print("si");
        }
        RecyclerView rcV= findViewById(R.id.recycler_view);
        rcV.setAdapter(adapter);
        rcV.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

    }

    private void displaydata() {

        Cursor cursor = dbHelper.getdata();
        while(cursor.moveToNext()){
            nom.add(cursor.getString(0));
            punts.add(cursor.getString(1));
        }

    }


}
