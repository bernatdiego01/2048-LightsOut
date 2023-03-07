package com.example.finalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dosmil= findViewById(R.id.start2048);
        TextView lights=findViewById(R.id.Lights_Out_Start);
        TextView clasi=findViewById(R.id.Clasificacio);
        EditText usuari=findViewById(R.id.usuario);

        dosmil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set2048(usuari);
            }
        });
        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLights(usuari);
            }
        });

        clasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               setClasi();
            }
        });


    }

    private void set2048(EditText usu) {
        String nom= String.valueOf(usu.getText());
        Intent intent=new Intent(this, g2048.class);
        intent.putExtra("nom", nom);
        startActivity(intent);
    }
    private void setLights(EditText usu) {
        String nom= String.valueOf(usu.getText());
        Intent intent=new Intent(this, LightsOut.class);
        intent.putExtra("nom", nom);
        startActivity(intent);
    }
    private  void setClasi(){
        Intent intent=new Intent(this, Classificaciones.class);
        startActivity(intent);
    }
}