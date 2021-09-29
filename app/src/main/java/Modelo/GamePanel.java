package Modelo;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GamePanel{
    GradientDrawable figura;
    Figure GameFigure;
    HiloFigura hiloFigura;
    Quadrate[][] Grid;
    Button button_reset;
    TextView puntaje;
    int total;

    public GamePanel(GridLayout GamePanelView, GridLayout ActualFigure, Button bajar, Button derecha,Button izquierda,Button button_reset,TextView puntaje,int total){
        this.puntaje = puntaje;
        this.button_reset = button_reset;
        figura = new GradientDrawable();
        figura.setColor(Color.parseColor("#c7f9cc"));
        this.total = total;
        GamePanelView.setBackground(figura);
        GamePanelView.setColumnCount(10);
        GamePanelView.setRowCount(20);
        int CuadradoWidth = (int)GamePanelView.getLayoutParams().width/10;
        int CuadradoHeight = (int)GamePanelView.getLayoutParams().height/20;


        GamePanelView.getLayoutParams().width = CuadradoWidth * 10;
        GamePanelView.getLayoutParams().height = CuadradoWidth * 20;
        GamePanelView.setElevation(15f);


        Grid= new Quadrate[20][10];








        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Grid[i][j] = new Quadrate(GamePanelView.getContext(),"#c7f9cc","#57cc99",CuadradoWidth,CuadradoHeight);
                GamePanelView.addView(Grid[i][j]);
                Grid[i][j].setEmpty("#c7f9cc","#57cc99");
            }
        }

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GamePanelView.removeAllViews();
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 10; j++) {
                        Grid[i][j] = new Quadrate(GamePanelView.getContext(),"#c7f9cc","#57cc99",CuadradoWidth,CuadradoHeight);
                        GamePanelView.addView(Grid[i][j]);
                        Grid[i][j].setEmpty("#c7f9cc","#57cc99");
                    }
                }

                puntaje.setText(0);
            }
        });

    }


    public GradientDrawable getFigura() {
        return figura;
    }

    public void setFigura(GradientDrawable figura) {
        this.figura = figura;
    }

    public Figure getGameFigure() {
        return GameFigure;
    }

    public void setGameFigure(Figure gameFigure) {
        GameFigure = gameFigure;
    }

    public HiloFigura getHiloFigura() {
        return hiloFigura;
    }

    public void setHiloFigura(HiloFigura hiloFigura) {
        this.hiloFigura = hiloFigura;
    }

    public Quadrate[][] getGrid() {
        return Grid;
    }

    public void setGrid(Quadrate[][] grid) {
        Grid = grid;
    }
}
