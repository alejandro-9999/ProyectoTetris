package com.example.tetris;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.widget.Button;
import android.widget.TextView;

import Modelo.Figure;
import Modelo.GamePanel;

import  Modelo.FigureTask;


public class MainActivity extends AppCompatActivity {

    private GridLayout GamePanelView ;
    private GridLayout ActualFigure ;
    private Button Bajar;
    private Button Derecha;
    private Button Izquierda;
    private Button Reset;
    private TextView puntaje;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanelView =  (GridLayout) findViewById(R.id.GamePanel);
        ActualFigure =  (GridLayout) findViewById(R.id.ActualFigure);
        Bajar = (Button) findViewById(R.id.rotar);
        Derecha = (Button) findViewById(R.id.bdercha);
        Izquierda = (Button) findViewById(R.id.bizquierda);
        Reset =(Button) findViewById(R.id.reset);
        puntaje = (TextView) findViewById(R.id.puntaje);
        GamePanel panelGame = new GamePanel(GamePanelView,ActualFigure,Bajar,Derecha,Izquierda,Reset,puntaje,total);
        FigureTask figureTask = new FigureTask(this,GamePanelView,new Figure(ActualFigure.getContext()),panelGame.getGrid(),ActualFigure,Derecha,Izquierda,Bajar,Reset,puntaje,total);

        figureTask.execute(true);


    }
}
