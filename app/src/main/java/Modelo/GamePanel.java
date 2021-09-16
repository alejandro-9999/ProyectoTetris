package Modelo;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

public class GamePanel{
    GradientDrawable figura;

    public GamePanel(GridLayout GamePanelView, GridLayout ActualFigure, Button bajar){

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

        Figure_J J = new Figure_J(ActualFigure.getContext(),"#003f88","#00509d");
        Figure_L L = new Figure_L(ActualFigure.getContext(),"#f48c06","#ff9e00");
        Figure_Quadrate C = new Figure_Quadrate(ActualFigure.getContext(),"#ffd500","#fdc500");
        Figure_T T = new Figure_T(ActualFigure.getContext(),"#7b2cbf","#9d4edd");
        Figure_I I = new Figure_I(ActualFigure.getContext(), "#48cae4","#90e0ef");
        Quadrate[][] Grid= new Quadrate[20][10];
        Quadrate[][] GridPrev = new Quadrate[20][10];

        LoadActualFigure(ActualFigure,T);


        I.Rotar90();
        LoadActualFigure(ActualFigure,L);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Grid[i][j] = new Quadrate(GamePanelView.getContext(),"#c7f9cc","#57cc99",CuadradoWidth,CuadradoHeight);
                GridPrev[i][j] = new Quadrate(GamePanelView.getContext(),"#c7f9cc","#57cc99",CuadradoWidth,CuadradoHeight);
                GamePanelView.addView(Grid[i][j]);
                Grid[i][j].setEmpty("#c7f9cc","#57cc99");
                GridPrev[i][j].setEmpty("#c7f9cc","#57cc99");
            }
        }


        bajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bajar(Grid,GridPrev,L);
            }
        });
    }


    public Quadrate[][] LoadActualFigure(GridLayout ActualFigure, Figure figure){
        ActualFigure.removeAllViews();
        ActualFigure.setColumnCount(3);
        ActualFigure.setRowCount(3);

        
        Quadrate[][] Frames = figure.getFrames();
        int tam = Math.max(figure.getFigureHeight(), figure.getFigureWidth());
        int ActualQuadreateWidth = (int)ActualFigure.getLayoutParams().width/tam;
        int ActualQuadreateHeight = (int)ActualFigure.getLayoutParams().height/tam;
        Quadrate[][] ActualGrid = new Quadrate[tam][tam];
        ActualFigure.setColumnCount(tam);
        ActualFigure.setRowCount(tam);
        ActualFigure.getLayoutParams().width = ActualQuadreateWidth * tam;
        ActualFigure.getLayoutParams().height = ActualQuadreateHeight * tam;


        for (int i = 0; i < tam ; i++) {
            for (int j = 0; j < tam ; j++) {
                ActualGrid[i][j] = new Quadrate(ActualFigure.getContext(),"#c7f9cc","#57cc99",ActualQuadreateWidth,ActualQuadreateHeight);
                ActualGrid[i][j].setEmpty("#c7f9cc","#57cc99");
                ActualFigure.addView(ActualGrid[i][j]);
            }
        }


        for (int i = 0; i < figure.getDataFrame().length; i++) {
            for (int j = 0; j < figure.getDataFrame()[0].length; j++) {
                if(Frames[i][j].fill){
                    System.out.print("["+Frames[i][j].pos_x+"/"+Frames[i][j].pos_y+"]");
                    ActualGrid[i][j].setFill(figure.getBackgroundColor(),figure.getBorderColor());
                }
                System.out.println();
            }
        }

        return  ActualGrid;
    }

    public void Bajar(Quadrate[][] Grid,Quadrate[][] GridPrev,Figure figure){

        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if(figure.getFrames()[i][j].getPos_y()>=0 && figure.getFrames()[i][j].getPos_y() <Grid.length  && figure.getFrames()[i][j].getPos_x() >= 0 && figure.getFrames()[i][j].getPos_x() <Grid[0].length ){
                    if(figure.getFrames()[i][j].isFill()){
                        Grid[figure.getFrames()[i][j].getPos_y()][figure.getFrames()[i][j].getPos_x()].setEmpty("#c7f9cc","#57cc99");
                    }
                }

            }
        }
        figure.Rotar90();

        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                figure.getFrames()[i][j].setPos_y(figure.getFrames()[i][j].getPos_y()+1);

            }
        }
        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if(figure.getFrames()[i][j].getPos_y()>=0 && figure.getFrames()[i][j].getPos_y() <Grid.length  && figure.getFrames()[i][j].getPos_x() >= 0 && figure.getFrames()[i][j].getPos_x() <Grid[0].length ){
                    if(figure.getFrames()[i][j].isFill()){
                        Grid[figure.getFrames()[i][j].getPos_y()][figure.getFrames()[i][j].getPos_x()].setFill(figure.getBackgroundColor(),figure.getBorderColor());
                    }
                }

            }
        }

        Quadrate[][] Frames = figure.getFrames();
        for (int i = 0; i < figure.getDataFrame().length; i++) {
            for (int j = 0; j < figure.getDataFrame()[0].length; j++) {
                if(Frames[i][j].fill){
                    System.out.print("["+Frames[i][j].pos_x+"/"+Frames[i][j].pos_y+"]");
                }
                System.out.println();
            }
        }

    }



}
