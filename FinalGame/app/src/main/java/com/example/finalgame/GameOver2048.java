package com.example.finalgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver2048 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2048);
        Button boton = findViewById(R.id.clas);
        Intent intent = getIntent();
        String puntos = intent.getStringExtra("puntuacio");
        String nom= intent.getStringExtra("nom");
        TextView punts= findViewById(R.id.Punts);
        TextView text= findViewById(R.id.text1);
        punts.setText(puntos);
        text.setText("Enhorabuena, "+nom+", por conseguir estos puntos");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClassi(puntos, nom);
            }
        });

        DisplayMetrics mesuresf = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mesuresf);

        int width= mesuresf.widthPixels;
        int height= mesuresf.heightPixels;

        getWindow().setLayout((int)(width * 0.85),(int) (height * 0.5));

    }
    public void setClassi(String puntos, String nom){
        Intent intent2 = new Intent(this, Classificaciones.class);
        intent2.putExtra("puntuacio", puntos);
        intent2.putExtra("nombre", nom);
    }
}