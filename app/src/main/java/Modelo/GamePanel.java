package Modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;

public class GamePanel{
    GradientDrawable figura;
    public GamePanel(GridLayout GamePanelView){

        figura = new GradientDrawable();
//        figura.setStroke(6,Color.RED);
        figura.setColor(Color.parseColor("#c7f9cc"));
        GamePanelView.setBackground(figura);
        GamePanelView.setColumnCount(10);
        GamePanelView.setRowCount(20);
        int CuadradoWidth = (int)GamePanelView.getLayoutParams().width/10;
        int CuadradoHeight = (int)GamePanelView.getLayoutParams().height/20;

        GamePanelView.getLayoutParams().width = CuadradoWidth * 10;
        GamePanelView.getLayoutParams().height = CuadradoWidth * 20;
        GamePanelView.setElevation(15f);

        FigureJ jota = new FigureJ(GamePanelView.getContext(),CuadradoWidth,CuadradoHeight);
        Quadrate[] Casillas= new  Quadrate[200];

        for (int i = 0; i < Casillas.length; i++) {
            Empty cuadrado = new Empty(GamePanelView.getContext(),"#57cc99",CuadradoWidth,CuadradoHeight);
            GamePanelView.addView(cuadrado);

        }
    }

}
