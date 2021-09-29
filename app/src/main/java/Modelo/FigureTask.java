package Modelo;

import android.os.AsyncTask;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class FigureTask extends AsyncTask<Boolean,Boolean,Boolean> {
    Figure figure;
    Quadrate [][] Grid;
    GridLayout ActualFigure;
    Button Derecha;
    Button Izquierda;
    Button Rotar;

    public FigureTask(Figure figure, Quadrate[][] grid, GridLayout actualFigure, Button derecha, Button izquierda,Button rotar) {
        this.figure = figure;
        Grid = grid;
        ActualFigure = actualFigure;
        Derecha = derecha;
        Izquierda = izquierda;
        Rotar = rotar;
    }

    @Override
    protected void onPreExecute() {
        figure = null;
        figure = RandomFigure();
        LoadActualFigure(ActualFigure,figure);
    }

    @Override
    protected Boolean doInBackground(Boolean... booleans) {

        Derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Derecha(Grid,figure);

            }
        });
        Izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Izquierda(Grid,figure);
            }
        });
        Rotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Rotar90(Grid,figure);
                System.out.println("ESTA ROTANDO");
            }
        });

        while(true){
            if(figure.getStoped_time() < 2 ) {
                figure.Bajar(Grid, figure);
            }else{
                if(figure.ChocaAbajo(figure,Grid)){
                    publishProgress(true);
                }else{
                    figure.setStoped_time(0);
                }

            }
            try {
                Thread.sleep(350);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {

        figure = null;
        figure = RandomFigure();
        LoadActualFigure(ActualFigure,figure);
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
                    ActualGrid[i][j].setFill(figure.getBackgroundColor(),figure.getBorderColor());
                }
            }
        }

        return  ActualGrid;
    }


    public Figure RandomFigure(){
        Random r = new Random();
        int x = r.nextInt(7)+1;
        switch (x){
            case 1: return new Figure_I(ActualFigure.getContext());
            case 2: return new Figure_J(ActualFigure.getContext());
            case 3: return new Figure_L(ActualFigure.getContext());
            case 4: return new Figure_O(ActualFigure.getContext());
            case 5: return new Figure_S(ActualFigure.getContext());
            case 6: return new Figure_T(ActualFigure.getContext());
            case 7: return new Figure_Z(ActualFigure.getContext());
        }
        return new Figure_I(ActualFigure.getContext());
    }
}
