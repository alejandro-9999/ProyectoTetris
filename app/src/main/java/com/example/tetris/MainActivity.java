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
        GamePanel panelGame = new GamePanel(GamePanelView);

    }
}
