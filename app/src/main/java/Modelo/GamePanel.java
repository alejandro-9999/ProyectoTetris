package Modelo;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

public class GamePanel{
    GradientDrawable figura;
    Figure GameFigure;

    public GamePanel(GridLayout GamePanelView, GridLayout ActualFigure, Button bajar, Button derecha){

        figura = new GradientDrawable();
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

        GameFigure = T;

        LoadActualFigure(ActualFigure,T);




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
                GameFigure = Bajar(Grid,GridPrev,GameFigure,ActualFigure);
            }
        });
        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFigure = Derecha(Grid,GridPrev,GameFigure,ActualFigure);
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

    public  void clearPastMovement(Quadrate[][] Grid, Figure figure){
        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if (figure.getFrames()[i][j].getPos_y() >= 0 && figure.getFrames()[i][j].getPos_y() < Grid.length && figure.getFrames()[i][j].getPos_x() >= 0 && figure.getFrames()[i][j].getPos_x() < Grid[0].length) {
                    if (figure.getFrames()[i][j].isFill()) {
                        Grid[figure.getFrames()[i][j].getPos_y()][figure.getFrames()[i][j].getPos_x()].setEmpty("#c7f9cc", "#57cc99");
                    }
                }

            }
        }
    }

    public void  MakeMovement(Quadrate[][] Grid, Figure figure){
        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if (figure.getFrames()[i][j].getPos_y() >= 0 && figure.getFrames()[i][j].getPos_y() < Grid.length && figure.getFrames()[i][j].getPos_x() >= 0 && figure.getFrames()[i][j].getPos_x() < Grid[0].length) {
                    if (figure.getFrames()[i][j].isFill()) {
                        Grid[figure.getFrames()[i][j].getPos_y()][figure.getFrames()[i][j].getPos_x()].setFill(figure.getBackgroundColor(), figure.getBorderColor());
                    }
                }

            }
        }
    }

    public Figure Bajar(Quadrate[][] Grid,Quadrate[][] GridPrev,Figure figure,GridLayout ActualFigure){
        if(figure.getStoped_time() != 3) {

            if (!ChocaAbajo(figure, Grid)) {
                figure.setStoped_time(0);
                clearPastMovement(Grid,figure);

                for (int i = 0; i < figure.getFrames().length; i++) {
                    for (int j = 0; j < figure.getFrames()[0].length; j++) {
                        figure.getFrames()[i][j].setPos_y(figure.getFrames()[i][j].getPos_y() + 1);

                    }
                }
                MakeMovement(Grid,figure);


            } else {
                figure.setStoped_time(figure.getStoped_time() + 1);
            }
        }else{
            Figure newFigure = new Figure_I(ActualFigure.getContext(), "#48cae4","#90e0ef");
            LoadActualFigure(ActualFigure,newFigure);
            return newFigure;
        }
        return figure;
    }

    public Figure Derecha(Quadrate[][] Grid,Quadrate[][] GridPrev,Figure figure,GridLayout ActualFigure){
        if(figure.getStoped_time() != 3) {

            if (!ChocaDerecha(figure, Grid)) {
                figure.setStoped_time(0);
                clearPastMovement(Grid,figure);

                for (int i = 0; i < figure.getFrames().length; i++) {
                    for (int j = 0; j < figure.getFrames()[0].length; j++) {
                        figure.getFrames()[i][j].setPos_x(figure.getFrames()[i][j].getPos_x() + 1);

                    }
                }
                MakeMovement(Grid,figure);


            } else {
                figure.setStoped_time(figure.getStoped_time() + 1);
            }
        }else{
            Figure newFigure = new Figure_I(ActualFigure.getContext(), "#48cae4","#90e0ef");
            LoadActualFigure(ActualFigure,newFigure);
            return newFigure;
        }
        return figure;
    }


    public boolean ChocaAbajo(Figure figure,Quadrate[][] GamePanel){
        int Pos =PosicionMasBaja(figure);
        System.out.println(Pos);
        for (int i = 0; i < figure.getDataFrame()[0].length; i++) {

            if(figure.getFrames()[Pos][i].isFill()) {
                int max_y = figure.getFrames()[Pos][i].getPos_y();
                int max_x = figure.getFrames()[Pos][i].getPos_x();
                if (max_y + 1 >= GamePanel.length) return true;
                if(max_y>=0) {
                    if (GamePanel[max_y + 1][max_x].isFill()) return true;
                }
            }
        }
        return false;
    }
    public boolean ChocaDerecha(Figure figure,Quadrate[][] GamePanel){
        int Pos = PosicionMasDerecha(figure);
        for (int i = 0; i < figure.getDataFrame().length; i++) {
            if(figure.getFrames()[i][Pos].isFill()) {
                int max_y = figure.getFrames()[i][Pos].getPos_y();
                int max_x = figure.getFrames()[i][Pos].getPos_x();
                if (max_x + 1 >= GamePanel[0].length) return true;

                if (GamePanel[max_y][max_x+1].isFill()) return true;

            }
        }
        return false;
    }

    public int PosicionMasBaja(Figure figure){
        for (int i = figure.getFrames().length-1 ; i >= 0 ; i--) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if(figure.getFrames()[i][j].isFill()) return i;
            }
        }
        return  figure.getFrames().length-1;
    }

    public int PosicionMasDerecha(Figure figure){
        for (int j = figure.getFrames()[0].length-1 ; j >= 0 ; j--) {
            for (int i = 0; i < figure.getFrames().length; i++) {
                if(figure.getFrames()[i][j].isFill()) return j;
            }
        }
        return  figure.getFrames()[0].length-1;
    }
}
