package com.example.finalgame;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.Random;

public class LightsOut extends AppCompatActivity {

    private Button mButton_Solucion;


    public int contador=500;
    TextView[][] lucesArray = new TextView[5][5];
    TextView[][] solucion = new TextView[5][5];
    int[][] arrayRandom = new int[5][5];
    int[][] arrayContador = new int[5][5];
    private Button mButtonNewGame;
    String [][] arrayRespaldo = new String[5][5];
    private Button mButtonBack;

    String nom;

    //private ActivityLightGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intento = getIntent();
        nom = intento.getStringExtra("nom");
        //binding = ActivityLightGameBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_lightsout);

        lucesArray[0][0] = findViewById(R.id.P00);
        lucesArray[0][1] = findViewById(R.id.P01);
        lucesArray[0][2] = findViewById(R.id.P02);
        lucesArray[0][3] = findViewById(R.id.P03);
        lucesArray[0][4] = findViewById(R.id.P04);
        lucesArray[1][0] = findViewById(R.id.P10);
        lucesArray[1][1] = findViewById(R.id.P11);
        lucesArray[1][2] = findViewById(R.id.P12);
        lucesArray[1][3] = findViewById(R.id.P13);
        lucesArray[1][4] = findViewById(R.id.P14);
        lucesArray[2][0] = findViewById(R.id.P20);
        lucesArray[2][1] = findViewById(R.id.P21);
        lucesArray[2][2] = findViewById(R.id.P22);
        lucesArray[2][3] = findViewById(R.id.P23);
        lucesArray[2][4] = findViewById(R.id.P24);
        lucesArray[3][0] = findViewById(R.id.P30);
        lucesArray[3][1] = findViewById(R.id.P31);
        lucesArray[3][2] = findViewById(R.id.P32);
        lucesArray[3][3] = findViewById(R.id.P33);
        lucesArray[3][4] = findViewById(R.id.P34);
        lucesArray[4][0] = findViewById(R.id.P40);
        lucesArray[4][1] = findViewById(R.id.P41);
        lucesArray[4][2] = findViewById(R.id.P42);
        lucesArray[4][3] = findViewById(R.id.P43);
        lucesArray[4][4] = findViewById(R.id.P44);

        mButton_Solucion = findViewById(R.id.solution);
        mButtonNewGame = findViewById(R.id.button_newGame);
        mButtonBack = findViewById(R.id.button_back);



        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                solucion[i][j] = lucesArray[i][j];
                solucion[i][j].setText("0");
                Random rand = new Random();
                int num = rand.nextInt(2);
                arrayRandom[i][j] = num;

            }
        }


        crearTableroJuego(arrayRandom, arrayContador,lucesArray, solucion);


        for (int i = 0;i<5;i++){
            for(int j = 0;j<5;j++){
                colorInicial(lucesArray[i][j]);
            }
        }


        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        mButton_Solucion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    //Evento cuando el boton se mantiene pulsado
                    case MotionEvent.ACTION_DOWN:
                        mostrarSolucion();
                        return true;

                    case MotionEvent.ACTION_UP:
                        //Evento cuando el boton se deja de pulsar
                        volverJuego();
                        return  true;
                    default:
                        return false;
                }
            }
        });

        mButtonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LightsOut.class);
                startActivity(intent);
            }
        });


    }


    public void crearTableroJuego(int[][]arrayRandom, int [][]arrayContador, TextView[][] lucesArray, TextView[][] solucion){
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if (arrayRandom[i][j] ==1){
                    //Se suma en el mismo  que esta "pulsado"
                    arrayContador[i][j] +=1;
                    //Se suma 1 al de la arriba
                    if (i-1>=0){
                        arrayContador[i - 1][j] +=1;
                    }
                    //Se suma 1 al de la abajo
                    if (i+1<5){
                        arrayContador[i + 1][j] +=1;
                    }
                    //Se suma 1 al de la arriba
                    if (j-1>=0){
                        arrayContador[i][j - 1] +=1;
                    }
                    //Se suma 1 al de la abajo
                    if (j+1<5){
                        arrayContador[i][j + 1] +=1;
                    }
                }
            }
        }

        for (int i = 0;i<5;i++){
            for (int j=0;j<5;j++){
                int res = arrayContador[i][j];
                if ((res%2) ==0){
                    solucion[i][j].setText("0");
                }else{
                    solucion[i][j].setText("1");
                }

                if (solucion[i][j].getText().equals("1")){
                    lucesArray[i][j].setText("0");
                }else {
                    lucesArray[i][j].setText("1");
                }

            }
        }


    }

    public void colorInicial(View view) {
        TextView vista = (TextView) view;

        if (vista.getText().equals("1")) {
            vista.setBackgroundResource(R.drawable.bombillaapagada);
        } else {
            vista.setBackgroundResource(R.drawable.bombillaencendida);
        }
    }
    public void colorSolucion(View view) {
        TextView vista = (TextView) view;

        if (vista.getText().equals("1")) {
            vista.setBackgroundResource(R.drawable.bombillasolucio);
        }
    }




    public void canviarColor(View view) {
        TextView vista = (TextView) view;

        if (vista.getText().equals("0")) {
            vista.setText("1");
            vista.setBackgroundResource(R.drawable.bombillaapagada);
        } else {
            vista.setText("0");
            vista.setBackgroundResource(R.drawable.bombillaencendida);
        }
    }



    public void clicks(View view) {
        TextView vista = (TextView) view;
        canviarColor(vista);
        //Para llamar al metodo actualizar solucion
        actualizarSolucion(vista);

        int id = vista.getId();
        String nombreBoton = vista.getResources().getResourceName(id);
        String stringCords = nombreBoton.replaceAll("[^0-9]", "");

        int id_i = Integer.parseInt(String.valueOf(stringCords.charAt(0)));
        int id_j = Integer.parseInt(String.valueOf(stringCords.charAt(1)));

        //Mira hacia arriba
        if (id_i - 1 >= 0) {
            int newId_i = id_i - 1;
            int id_arriba = this.getResources().getIdentifier("P" + newId_i + id_j, "id", this.getPackageName());
            TextView id_boton_arriba = findViewById(id_arriba);
            canviarColor(id_boton_arriba);
        }

        //Mira hacia abajo
        if (id_i + 1 < 5) {
            int newId_i = id_i + 1;
            int id_abajo = this.getResources().getIdentifier("P" + newId_i + id_j, "id", this.getPackageName());
            TextView id_boton_abajo = findViewById(id_abajo);
            canviarColor(id_boton_abajo);
        }

        //Mira hacia el lado derecho
        if (id_j + 1 < 5) {
            int newId_j = id_j + 1;
            int id_derecho = this.getResources().getIdentifier("P" + id_i + newId_j, "id", this.getPackageName());
            TextView id_boton_derecho = findViewById(id_derecho);
            canviarColor(id_boton_derecho);
        }

        //Mira hacia el lado izquierdo
        if (id_j - 1 >= 0) {
            int newId_j = id_j - 1;
            int id_izq = this.getResources().getIdentifier("P" + id_i + newId_j, "id", this.getPackageName());
            TextView id_boton_izq = findViewById(id_izq);
            canviarColor(id_boton_izq);
        }
        int acabado=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (lucesArray[i][j].getText()=="1"){{
                    System.out.println(acabado+" ");
                    acabado++;
                    if (acabado==25){
                        victoria();

                    }

                }

                }
            }
        }

    }

    public void mostrarSolucion() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arrayRespaldo[i][j] = lucesArray[i][j].getText().toString();
                lucesArray[i][j].setText(String.valueOf(arrayRandom[i][j]));
                colorSolucion(lucesArray[i][j]);
            }
        }
        contador=contador-10;
    }


    public void volverJuego(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lucesArray[i][j].setText(arrayRespaldo[i][j]);
                colorInicial(lucesArray[i][j]);
            }
        }
    }


    public void actualizarSolucion(View view){
        TextView vista = (TextView) view;
        int id = vista.getId();

        String nombreBoton = vista.getResources().getResourceName(id);
        String stringCords = nombreBoton.replaceAll("[^0-9]", "");

        int id_i = Integer.parseInt(String.valueOf(stringCords.charAt(0)));
        int id_j = Integer.parseInt(String.valueOf(stringCords.charAt(1)));

        if (arrayRandom[id_i][id_j] == 1){
            arrayRandom[id_i][id_j] =0;
        }else {
            arrayRandom[id_i][id_j] =1;
        }
    }
    public void victoria(){
        Intent intent = new Intent(this, GameOver2048.class);
        intent.putExtra("puntuacio", String.valueOf(contador));
        intent.putExtra("nom", nom);
        intent.putExtra("joc", "Lightsout");

        startActivity(intent);
    }
}