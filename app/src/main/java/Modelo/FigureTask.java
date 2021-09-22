package Modelo;

import android.os.AsyncTask;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

public class FigureTask extends AsyncTask<Boolean,Boolean,Boolean> {
    Figure figure;
    Quadrate [][] Grid;
    GridLayout ActualFigure;
    Button Derecha;
    Button Izquierda;

    public FigureTask(Figure figure, Quadrate[][] grid, GridLayout actualFigure, Button derecha, Button izquierda) {
        this.figure = figure;
        Grid = grid;
        ActualFigure = actualFigure;
        Derecha = derecha;
        Izquierda = izquierda;
    }

    @Override
    protected void onPreExecute() {
        LoadActualFigure(ActualFigure,figure);
    }

    @Override
    protected Boolean doInBackground(Boolean... booleans) {

        Derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Derecha(Grid,figure,ActualFigure);

            }
        });
        Izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Izquierda(Grid,figure,ActualFigure);

            }
        });

        while(true){
            if(figure.getStoped_time() < 2 ) {
                figure.Bajar(Grid, figure, ActualFigure);
            }else{

                publishProgress(true);
            }
            try {
                Thread.sleep(550);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {


        figure = null;


        figure = new Figure_L(ActualFigure.getContext(),"#f48c06","#ff9e00");
        LoadActualFigure(ActualFigure,new Figure_L(ActualFigure.getContext(),"#f48c06","#ff9e00"));
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
}
