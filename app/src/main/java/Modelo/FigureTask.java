package Modelo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class FigureTask extends AsyncTask<Boolean,Boolean,Boolean> {
    Figure figure;
    Quadrate [][] Grid;
    GridLayout ActualFigure;
    Button Derecha;
    Button Izquierda;
    Button Rotar;
    Context context;
    private float x2,x1,y2,y1;

    public FigureTask(Context context,Figure figure, Quadrate[][] grid, GridLayout actualFigure, Button derecha, Button izquierda, Button rotar) {
        this.figure = figure;
        Grid = grid;
        ActualFigure = actualFigure;
        Derecha = derecha;
        Izquierda = izquierda;
        Rotar = rotar;
        this.context = context;
    }
    private void soltarPantalla(float x, float y) {
        this.x2= x;
        this.y2 = y;
    }

    private void tocarPantalla(float x, float y) {
        this.x1= x;
        this.y1= y;

    }


    private void movimiento() {
        float  dx = Math.abs(this.x2- this.x1);
        float  dy = Math.abs(this.y2- this.y1);
        String dir;


        if(dx >dy){
            //horizontal
            if(this.x2 > this.x1){
                //derecha
                //this.juego.derecha();
                dir="Derecha";
                figure.Derecha(Grid,figure);

            }else{
                dir="Izquierda";
                // i.Izquierda( tablero_back.getTablero() );
                //izquierda
                figure.Izquierda(Grid,figure);
            }

        }else{
            //vertical
            if (this.y2 >this.y1){
                //hacia abajo
                dir="HASTA ABAJO SOY YO";

            }else{
                dir="Arriba";
                figure.Rotar90(Grid,figure);
            }

        }


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

        View pantalla = ((Activity)context).getWindow().getDecorView();

        pantalla.setOnTouchListener( new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tocarPantalla( event.getX(), event.getY() );
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    soltarPantalla( event.getX(), event.getY() );
                    movimiento();
                }
                return false;
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
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @Override
    protected void onProgressUpdate(Boolean... values) {

        validate_points(Grid);
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


    public  void validate_points(Quadrate[][] grid ){
        System.out.println("Sistema entra");

        for (int i = 0; i <grid.length ; i++) {
            if(linea_llena(i,grid)){
                Quadrate[][] auxiliar= new Quadrate[i][grid[0].length];
                for (int k = 0; k < i; k++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        auxiliar[k][j] = new Quadrate(context);
                        auxiliar[k][j]= grid[k][j];
                     System.out.println(grid[k][j].getBackgroundColor());
                    }
                }
                for (int z = i-1; z > 0; z--) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if(auxiliar[z][j].isFill()){
                            grid[z+1][j].setFill("#adb5bd","#6c757d",auxiliar[z][j].getPos_y(),auxiliar[z][j].getPos_x());
                        }else{
                            grid[z+1][j].setEmpty("#c7f9cc","#57cc99",auxiliar[z][j].getPos_y(),auxiliar[z][j].getPos_x());
                        }

                    }
                }

            }
        }
    }

    public  boolean linea_llena(int i,Quadrate[][] grid){
        for (int j = 0; j < grid[0].length; j++) {
            if(!grid[i][j].isFill()) return  false;
        }
        return true;
    }
}
