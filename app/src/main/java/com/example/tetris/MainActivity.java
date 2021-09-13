package com.example.tetris;


import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;

import Modelo.Game;
import Modelo.GamePanel;
import Modelo.Quadrate;


public class MainActivity extends AppCompatActivity {

    private GridLayout GamePanelView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanelView =  (GridLayout) findViewById(R.id.GamePanel);
        GamePanelView.setColumnCount(10);
        GamePanelView.setRowCount(20);

        float CuadradoWidth = GamePanelView.getLayoutParams().width/10;
        float CuadradoHeight = GamePanelView.getLayoutParams().height/20;
        System.out.println(GamePanelView.getLayoutParams().width+"---"+GamePanelView.getLayoutParams().height);

        String[] colores = { "#ff0a54","#ff7900","#7b2cbf","#b4f998","#4d194d","#e01e37" };
        Quadrate[] Casillas= new  Quadrate[200];

        for (int i = 0; i < Casillas.length; i++) {
            Random r = new Random();
            int num = r.nextInt(colores.length);
            int num2 = r.nextInt(colores.length);

            Quadrate cuadrado = new Quadrate(this,colores[num],colores[num2],(int)CuadradoWidth,(int)CuadradoHeight);
            Casillas[i] = cuadrado;
            GamePanelView.addView(Casillas[i]);
        }

    }
}
