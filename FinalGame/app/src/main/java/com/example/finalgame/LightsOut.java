package com.example.finalgame;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

    int SIZE;
    private int[][] solucio;
    private int[][] segona;
    private int[][] matriu;

    private int filaM=0;
    private int columnaM=0;
    int count;
    TextView contador;


    private ImageButton[][] buttons = new ImageButton[SIZE][SIZE];

    private ImageButton[][] gridM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightsout);
        contador=findViewById(R.id.contador);
        Button cuatro=findViewById(R.id.cuatroporcuatro);
        Button seis=findViewById(R.id.sispersis);
        Button cinc=findViewById(R.id.cincpercinc);

        cinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIZE=5;
                create();
                cinc.setEnabled(false);
                seis.setEnabled(false);
                cuatro.setEnabled(false);
            }
        });

        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIZE=6;
                create();
                cinc.setEnabled(false);
                seis.setEnabled(false);
                cuatro.setEnabled(false);
            }
        });
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIZE=4;
                create();
                cinc.setEnabled(false);
                cuatro.setEnabled(false);
                seis.setEnabled(false);

            }
        });


    }

    public void create(){
        count=0;
        columnaM=0;
        filaM=0;
        segona= new int[SIZE][SIZE];
        matriu= new int[SIZE][SIZE];
        solucio=new int[SIZE][SIZE];
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                ImageButton imgB = new ImageButton(this);
                imgB.setBackground(activarBombilles());

                imgB.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = (int) getResources().getDimension(R.dimen.image_width);
                params.height = (int) getResources().getDimension(R.dimen.image_height);
                params.rowSpec = GridLayout.spec(row, 1f);
                params.columnSpec = GridLayout.spec(column, 1f);
                imgB.setLayoutParams(params);

                int finalColumn = column;
                int finalRow = row;
                imgB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        instrucciones(imgB, finalRow, finalColumn);
                    }
                });


                System.out.println("   "+row+"  "+column+" añadido");
                columnaM++;
                gridLayout.addView(imgB);
                buttons[row][column] = imgB;

            }

        }

    }



    public void instrucciones(ImageButton imgB, int row, int column){

        // Cambia las imágenes de los botones vecinos
        cambiarBombilla(row, column);
        if (row > 0) {
            ImageButton aboveButton = buttons[row - 1][column];
            if (aboveButton != null) {
                cambiarBombilla(row-1, column);
            }
        }
        if (row < SIZE) {
            // Cambia la imagen del botón debajo
            ImageButton belowButton = buttons[row + 1][column];
            if (belowButton != null) {
                cambiarBombilla(row+1, column);;
            }
        }
        if (column > 0) {
            // Cambia la imagen del botón a la izquierda
            ImageButton leftButton = buttons[row][column - 1];
            if (leftButton != null) {
                cambiarBombilla(row, column-1);;
            }
        }
        if (column < SIZE) {
            // Cambia la imagen del botón a la derecha
            ImageButton rightButton = buttons[row][column + 1];
            if (rightButton != null) {
                cambiarBombilla(row, column+1);;
            }
        }
        mirarmatriz();


    }
    public void mirarmatriz(){
        int contadorLuz=0;
        for(int i=0; i<matriu.length;i++){
            for(int j=0; j< matriu[i].length;j++){
                if(matriu[i][j]==1){
                    contadorLuz++;
                }
            }
        }
        if(contadorLuz==0){
            popup();
            create();
            count++;
            contador.setText(String.valueOf(count));
        }
    }

    public void popup(){
        Toast.makeText(this, "Enhorabuena, has ganado \n  puntuacion +1", Toast.LENGTH_SHORT).show();
        Resources res = getResources();
        int x = Integer.parseInt(res.getString(R.string.nivell));
        x++;
        res.getString((int)R.string.nivell, x);
        create();
    }

    public void cambiarBombilla(int row, int column) {
        if (matriu[row][column]==0) {
            matriu[row][column]=1;
            this.buttons[row][column].setBackground(this.getDrawable(R.drawable.bombillaencendida));
        } else {
            matriu[row][column]=0;
            this.buttons[row][column].setBackground(getDrawable(R.drawable.bombillaapagada));
        }
    }


    private Drawable activarBombilles () {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int randomInt = (int) (randomDouble * 2);

        if(randomInt==0){
            //Luz apagada
            llenarMatriz(randomInt);
            return getDrawable(R.drawable.bombillaapagada);

        }
        else{
            //Luz encendida
            llenarMatriz(randomInt);
            return getDrawable(R.drawable.bombillaencendida);


        }

    }

    private void llenarMatriz(int serOnoser){
        if(columnaM<SIZE){
            matriu[filaM][columnaM]=serOnoser;
            System.out.print(matriu[filaM][columnaM]);


        }else{
            columnaM=0;
            filaM++;
            matriu[filaM][columnaM]=serOnoser;
            System.out.println(matriu[filaM][columnaM]);


        }


    }

}
