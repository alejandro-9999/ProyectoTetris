package com.example.tetris;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.widget.Button;

import Modelo.GamePanel;


public class MainActivity extends AppCompatActivity {

    private GridLayout GamePanelView ;
    private GridLayout ActualFigure ;
    private Button Bajar;
    private Button Derecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanelView =  (GridLayout) findViewById(R.id.GamePanel);
        ActualFigure =  (GridLayout) findViewById(R.id.ActualFigure);
        Bajar = (Button) findViewById(R.id.bajar);
        Derecha = (Button) findViewById(R.id.bdercha);
        GamePanel panelGame = new GamePanel(GamePanelView,ActualFigure,Bajar,Derecha);

    }
}
