package com.example.tetris;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.widget.Button;

import Modelo.FigureRunnable;
import Modelo.Figure_J;
import Modelo.GamePanel;
import Modelo.HiloFigura;

import  Modelo.FigureTask;


public class MainActivity extends AppCompatActivity {

    private GridLayout GamePanelView ;
    private GridLayout ActualFigure ;
    private Button Bajar;
    private Button Derecha;
    private Button Izquierda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanelView =  (GridLayout) findViewById(R.id.GamePanel);
        ActualFigure =  (GridLayout) findViewById(R.id.ActualFigure);
        Bajar = (Button) findViewById(R.id.bajar);
        Derecha = (Button) findViewById(R.id.bdercha);
        Izquierda = (Button) findViewById(R.id.bizquierda);
        GamePanel panelGame = new GamePanel(GamePanelView,ActualFigure,Bajar,Derecha,Izquierda);
        Figure_J J = new Figure_J(ActualFigure.getContext(),"#003f88","#00509d");

//        FigureRunnable figureRunnable = new FigureRunnable(panelGame.getGrid(),J,ActualFigure,Derecha,Izquierda);
        FigureTask figureTask = new FigureTask(J,panelGame.getGrid(),ActualFigure,Derecha,Izquierda);

        figureTask.execute(true);
//        new Thread(figureRunnable).start();
//        runOnUiThread(figureRunnable);

//        hiloFigura.start();


    }
}
