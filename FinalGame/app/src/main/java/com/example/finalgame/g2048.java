package com.example.finalgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class g2048 extends AppCompatActivity {

        private int SIZE;
        private TextView[][] tiles;
        private int[][] values;
        private int[][] anterior;
        SwipeListener swipeListener;
        int puntuacio;
        TextView punts;
        int aaaa=0;
        Intent intento = getIntent();
        String nom = intento.getStringExtra("nom");



        private boolean gameOver;

        public g2048() {
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_2048);
            puntuacio=0;
            punts= (TextView) findViewById(R.id.contador);
            SwipeListener e = new SwipeListener(findViewById(R.id.activity_main));

            Button cuatro=findViewById(R.id.cuatroporcuatro);
            Button seis=findViewById(R.id.sispersis);
            Button ocho=findViewById(R.id.ochoporocho);
            Button enrera=findViewById(R.id.enrera);



            ocho.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SIZE=8;
                    crear();
                    cuatro.setEnabled(false);
                    ocho.setEnabled(false);
                    seis.setEnabled(false);
                    enrera.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            possarAnteriorUI();
                        }
                    });
                }
            });

            seis.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SIZE=6;
                    crear();
                    cuatro.setEnabled(false);
                    ocho.setEnabled(false);
                    seis.setEnabled(false);
                    enrera.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            possarAnteriorUI();

                        }
                    });
                }
            });
            cuatro.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SIZE=4;
                    crear();
                    cuatro.setEnabled(false);
                    ocho.setEnabled(false);
                    seis.setEnabled(false);
                    enrera.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            possarAnteriorUI();

                        }
                    });
                }
            });

        };

        public void crear(){
            tiles= new TextView[SIZE][SIZE];
            values = new int[SIZE][SIZE];
            anterior= new int[SIZE][SIZE];


            GridLayout gridLayout = findViewById(R.id.grid_layout);
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    TextView tile = new TextView(this);
                    values[row][col] = 0;
                    anterior[row][col]=0;
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    tile.setBackgroundColor(getColor(R.color.tile_0));
                    tile.setTextColor(Color.parseColor("#776E65"));
                    tile.setTextSize(30);
                    tile.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    tile.setGravity(View.TEXT_ALIGNMENT_CENTER);

                    params.rowSpec = GridLayout.spec(row, 1f);

                    params.columnSpec = GridLayout.spec(col, 1f);
                    params.height=(int) getResources().getDimension(R.dimen.heighBlock);
                    params.width=(int) getResources().getDimension(R.dimen.widthBlock);
                    tile.setLayoutParams(params);
                    tiles[row][col] = tile;

                    gridLayout.addView(tile);
                }
            }

            addRandomTile();
            addRandomTile();
            updateUI();

        }

        private class SwipeListener implements OnTouchListener{
            GestureDetector gestureDetector;

            SwipeListener(View view) {
                int threshold = 100;
                int velocity_thereshold = 100;

                GestureDetector.SimpleOnGestureListener listener =
                        new GestureDetector.SimpleOnGestureListener() {
                            @Override
                            public boolean onDown(MotionEvent e) {
                                return true;
                            }

                            @Override
                            public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                                float xDiff = e2.getX() - e1.getX();
                                float yDiff = e2.getY() - e1.getY();
                                try {
                                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_thereshold) {
                                            if (xDiff > 0) {
                                                moverDerecha();
                                            } else {
                                                moverIzquierda();
                                            }
                                            return true;
                                        }
                                    } else {
                                        if(Math.abs(yDiff)>threshold && Math.abs(velocityY)>velocity_thereshold){
                                            if(yDiff>0){
                                                moverAbajo();
                                            }else {
                                                moverArriba();
                                            }
                                            return true;
                                        }

                                    }
                                } catch (Exception e) {
                                }
                                return false;
                            }
                        };
                gestureDetector =new GestureDetector(listener);

                view.setOnTouchListener(this);
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }



        }


        public void possarAnteriorUI(){

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    TextView tile = tiles[row][col];
                    tile.setBackgroundColor(elegirColor(getTileIndex(anterior[row][col])));
                    if (anterior[row][col] != 0) {
                        tile.setText(String.valueOf(anterior[row][col]));
                    } else {
                        tile.setText("");
                    }
                }
            }
            setnewvalues();
        }
        public void updateUI() {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    TextView tile = tiles[row][col];
                    tile.setBackgroundColor(elegirColor(getTileIndex(values[row][col])));
                    if (values[row][col] != 0) {
                        tile.setText(String.valueOf(values[row][col]));
                    } else {
                        tile.setText("");
                    }
                }
            }

        }
        public int elegirColor(int value){
            int color;
            switch (value){
                case 0:
                    color=getColor(R.color.tile_0);
                    break;
                case 2:
                    color= getColor(R.color.tile_2);
                    break;
                case 4:
                    color= getColor(R.color.tile_4);
                    break;
                case 8:
                    color= getColor(R.color.tile_8);
                    break;
                case 16:
                    color= getColor(R.color.tile_16);
                    break;
                case 32:
                    color= getColor(R.color.tile_32);
                    break;
                case 64:
                    color= getColor(R.color.tile_64);
                    break;
                case 128:
                    color= getColor(R.color.tile_128);
                    break;
                case 256:
                    color=getColor(R.color.tile_256);
                    break;
                case 512:
                    color= getColor(R.color.tile_512);
                    break;
                case 1024:
                    color= getColor(R.color.tile_1024);
                    break;
                case 2048:
                    color= getColor(R.color.tile_2048);
                    break;
                default: color=getColor(R.color.tile_max);


            }
            return color;
        }

        private int getTileIndex(int value) {
            if (value == 0) {
                return 0;
            }
            int index = 1;
            while (value > 1) {
                value /= 2;
                index*=2;
            }
            return index;
        }

        public boolean canMove() {
            boolean potM = false;
            if (!isFull()) {
                return true;
            } else {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (i > 0 && values[i][j] == values[i-1][j]) {
                            potM = true;
                        }
                        if (j < SIZE-1 && values[i][j] == values[i][j+1]) {
                             potM= true;
                        }
                    }
                }
                if (!potM) {
                    GameOver();
                }
                return potM;
            }

        }


        public void moverArriba () {
            canMove();
            updateAnterior(values);
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    for (int k = j + 1; k < SIZE; k++) {
                        if(values[k][i]!=0){
                            if(values[j][i]==0) {
                                values[j][i] = values[k][i];
                                values[k][i] = 0;
                                j--;
                            }else if (values[j][i]==values[k][i]){
                                this.puntuacio+=(values[j][i]*2);
                                setpuntuacio();
                                values[j][i]*=2;
                                values[k][i]=0;



                            }
                            break;
                        }

                    }

                }
            }
            addRandomTile();
            updateUI();

        }

    public void moverAbajo() {
            if(aaaa==4){
                GameOver();
            }
            canMove();
        updateAnterior(values);
        for (int i = 0; i < values.length; i++) {
            for (int j = SIZE-1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (values[k][i] != 0) {
                        if (values[j][i] == 0) {
                            values[j][i] = values[k][i];
                            values[k][i] = 0;
                            j++;
                        } else if (values[j][i] == values[k][i]) {
                            this.puntuacio+=(values[j][i]*2);
                            setpuntuacio();
                            values[j][i] *= 2;
                            values[k][i] = 0;
                        }
                        break;
                    }
                }
            }
        }
        addRandomTile();
        updateUI();
        aaaa++;


    }


    public void moverIzquierda() {
            canMove();
        updateAnterior(values);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = j + 1; k < SIZE; k++) {
                    if (values[i][k] != 0) {
                        if (values[i][j] == 0) {
                            values[i][j] = values[i][k];
                            values[i][k] = 0;
                            j--;
                        } else if (values[i][j] == values[i][k]) {
                            this.puntuacio+=(values[j][i]*2);
                            setpuntuacio();
                            values[i][j] *= 2;
                            values[i][k] = 0;
                        }
                        break;
                    }
                }
            }
        }
        addRandomTile();
        updateUI();
    }
    public void moverDerecha() {
            canMove();
        updateAnterior(values);
        for (int i = 0; i < values.length; i++) {
            for (int j = SIZE-1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (values[i][k] != 0) {
                        if (values[i][j] == 0) {
                            values[i][j] = values[i][k];
                            values[i][k] = 0;
                            j++;
                        } else if (values[i][j] == values[i][k]) {
                            this.puntuacio+=(values[j][i]*2);
                            setpuntuacio();
                            values[i][j] *= 2;
                            values[i][k] = 0;
                        }
                        break;
                    }
                }
            }
        }
        addRandomTile();
        updateUI();

    }

    private void updateAnterior(int[][] valor) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                anterior[row][col] = values[row][col];
            }
        }

    }
    private void setnewvalues(){
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                values[row][col] = anterior[row][col];
            }
        }
    }



    public void addRandomTile() {
        List<int[]> emptyCells = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (values[row][col] == 0) {
                    emptyCells.add(new int[] { row, col });
                }
            }
        }

        if (emptyCells.size() > 0) {
            Random random= new Random();
            int[] randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
            values[randomCell[0]][randomCell[1]] =2;
        }

    }


        private boolean isFull() {
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (values[i][j] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void GameOver() {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    TextView tile = tiles[row][col];
                    values[row][col]=0;
                    tile.setBackgroundColor(elegirColor(0));
                    tile.setText("");

                }
            }
            updateUI();
            Intent intent = new Intent(this, GameOver2048.class);
            intent.putExtra("puntuacio", String.valueOf(puntuacio));
            intent.putExtra("nom", nom);

            startActivity(intent);


        }

    private void setpuntuacio() {

        punts.setText(String.valueOf(puntuacio));

    }
    }
