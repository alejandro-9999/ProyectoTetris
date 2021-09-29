package Modelo;

import android.content.Context;
import android.support.v7.widget.GridLayout;

public class Figure {

    protected  int[][] DataFrame;
    protected  Quadrate[][] Frames;
    String BorderColor;
    String BackgroundColor;
    int FigureWidth;
    int FigureHeight;
    Context context;
    int stoped_time = 0;


    public Quadrate[][] getFrames() {
        return Frames;
    }

    public void setFrames(Quadrate[][] frames) {
        Frames = frames;
    }

    public String getBorderColor() {
        return BorderColor;
    }

    public void setBorderColor(String borderColor) {
        BorderColor = borderColor;
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public Figure(String borderColor, String backgroundColor) {
        stoped_time = 0;
        BorderColor = borderColor;
        BackgroundColor = backgroundColor;

    }



    public Figure(Context context,String borderColor, String backgroundColor, int figureWidth, int figureHeight) {
        this.context = context;
        BorderColor = borderColor;
        BackgroundColor = backgroundColor;
        FigureWidth = figureWidth;
        FigureHeight = figureHeight;
    }

    public Figure(Context context) {
        this.context = context;
    }

    public int getFigureWidth() {
        return FigureWidth;
    }

    public void setFigureWidth(int figureWidth) {
        FigureWidth = figureWidth;
    }

    public int getFigureHeight() {
        return FigureHeight;
    }

    public void setFigureHeight(int figureHeight) {
        FigureHeight = figureHeight;
    }

    public int[][] getDataFrame() {
        return DataFrame;
    }

    public void setDataFrame(int[][] dataFrame) {
        DataFrame = dataFrame;
    }


    public  void  Rotar90(Quadrate[][] Grid,Figure figure){
        int[][] aux = DataFrame.clone();
        clearPastMovement(Grid,figure);
        DataFrame =  rotateMatrix(DataFrame);
        ReloadFigure();
        int condicion = Choca(figure,Grid);
        System.out.println(condicion);

        switch (condicion){
            case 1:
                int x = figure.getDataFrame().length>=4?2:1;
                if (!ChocaIzquierda(figure, Grid,1)) {
                    if (!ChocaIzquierda(figure, Grid, x)) {
                        Xmovemen(figure, -x);
                        if (Choca(figure, Grid) != 0) {
                            DataFrame = aux.clone();
                            ReloadFigure();
                        }
                    }
                }
                break;
            case 2:
                x = figure.getDataFrame().length>=4?2:1;
                if (!ChocaDerecha(figure, Grid,1)){
                    if (!ChocaDerecha(figure, Grid,x)){
                        Xmovemen(figure,x);
                        if (Choca(figure,Grid) != 0){
                            DataFrame =  aux.clone();
                            ReloadFigure();
                        }
                    }
                }
                break;
            case 3:
                DataFrame =  aux.clone();
                ReloadFigure();

                break;
            case 4:
                DataFrame =  aux.clone();
                ReloadFigure();

                break;
        }

        MakeMovement(Grid,figure);
    }




    public int[][] rotateMatrix(int[][] matrix)
    {
        int tamanho = matrix.length;
        int[][] novaMatrix = new int[tamanho][tamanho];

        for(int i = 0, j = tamanho - 1; i < tamanho && j >= 0; i++, j--)
        {
            for(int k = 0; k < tamanho; k++)
            {
                novaMatrix[k][j] = matrix[i][k];
            }
        }
        return novaMatrix;
    }




    public  void GenerateFigure(){

        int initial_y = -DataFrame.length;


        Frames = new Quadrate[DataFrame.length][DataFrame[0].length];
        for (int i = 0; i < DataFrame.length; i++) {
            int initial_x = 2;
            for (int j = 0; j < DataFrame[0].length ; j++) {
                if(DataFrame[i][j] ==1){
                    Frames[i][j] = new Quadrate(BorderColor,BackgroundColor,initial_x,initial_y,context);
                    Frames[i][j].setFill(BorderColor,BackgroundColor);
                }else{
                    Frames[i][j] = new Quadrate("#c7f9cc","#57cc99",initial_x,initial_y,context);
                    Frames[i][j].setEmpty("#c7f9cc","#57cc99");
                }
                initial_x ++;
            }

            initial_y++;
        }
    }


    public  void ReloadFigure(){

        for (int i = 0; i < DataFrame.length; i++) {

            for (int j = 0; j < DataFrame[0].length ; j++) {
                if(DataFrame[i][j] ==1){;
                    Frames[i][j].setFill(BorderColor,BackgroundColor);
                }else{
                    Frames[i][j].setEmpty("#c7f9cc","#57cc99");
                }
            }
        }
    }

    public int getStoped_time() {
        return stoped_time;
    }

    public void setStoped_time(int stoped_time) {
        this.stoped_time = stoped_time;
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

    public void Bajar(Quadrate[][] Grid,Figure figure){
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
        }
    }


    public void Xmovemen(Figure figure,int x){
        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                figure.getFrames()[i][j].setPos_x(figure.getFrames()[i][j].getPos_x() + x);
            }
        }
    }

    public void Ymovemen(Figure figure,int x){
        for (int i = 0; i < figure.getFrames().length; i++) {
            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                figure.getFrames()[i][j].setPos_x(figure.getFrames()[i][j].getPos_x() + x);
            }
        }
    }

    public void Derecha(Quadrate[][] Grid, Figure figure){
        if (!ChocaDerecha(figure, Grid,1)){
            clearPastMovement(Grid,figure);
            Xmovemen(figure,1);
            MakeMovement(Grid,figure);
        }
    }

    public void Izquierda(Quadrate[][] Grid, Figure figure){
        if(!ChocaIzquierda(figure, Grid,1)) {
            clearPastMovement(Grid,figure);
            Xmovemen(figure,-1);
            MakeMovement(Grid,figure);
        }
    }


    public boolean ChocaAbajo(Figure figure,Quadrate[][] GamePanel){


            for (int j = 0; j < figure.getDataFrame()[0].length; j++) {
                int pos = PosicionMasBaja(figure,j);
                if(figure.getFrames()[pos][j].isFill()) {
                    int max_y = figure.getFrames()[pos][j].getPos_y();
                    int max_x = figure.getFrames()[pos][j].getPos_x();
                    if (max_y + 1 >= GamePanel.length) return true;
                    if(max_y>=0) {
                        if (GamePanel[max_y + 1][max_x].isFill()  ) return true;
                    }
                }
            }

        return false;
    }

    public boolean ChocaDerecha(Figure figure,Quadrate[][] GamePanel,int x){

        for (int i = 0; i < figure.getDataFrame().length; i++) {
            int Pos = PosicionMasDerecha(figure,i);
            if(figure.getFrames()[i][Pos].isFill()) {
                int max_y = figure.getFrames()[i][Pos].getPos_y();
                int max_x = figure.getFrames()[i][Pos].getPos_x();
                if ( max_x + x >= GamePanel[0].length) return true;
                if ( max_x + x <0) return true;
                if( max_y<0) return true;

                if (GamePanel[max_y][max_x+x].isFill()) return true;

            }
        }
        return false;
    }

    public boolean ChocaIzquierda(Figure figure,Quadrate[][] GamePanel,int x){

        for (int i = 0; i < figure.getDataFrame().length; i++) {
            int Pos = PosicionMasIzquierda(figure,i);
            if(figure.getFrames()[i][Pos].isFill()) {
                int max_y = figure.getFrames()[i][Pos].getPos_y();
                int max_x = figure.getFrames()[i][Pos].getPos_x();
                if ( max_x - 1 >= GamePanel[0].length) return true;
                if ( max_x - 1 <0) return true;
                if( max_y<0) return true;

                if (GamePanel[max_y][max_x-1].isFill()) return true;

            }
        }
        return false;
    }


    public int PosicionMasBaja(Figure figure,int j){
        for (int i = figure.getFrames().length-1 ; i >= 0 ; i--) {
            if(figure.getFrames()[i][j].isFill()) return i;
        }
        return  figure.getFrames().length-1;
    }

    public int PosicionMasDerecha(Figure figure,int i){
        for (int j = figure.getFrames()[0].length-1 ; j >= 0 ; j--) {
            if(figure.getFrames()[i][j].isFill()) return j;
        }
        return  figure.getFrames()[0].length-1;
    }

    public int PosicionMasIzquierda(Figure figure,int i){
        for (int j = 0  ; j < figure.getFrames()[0].length ; j++) {
           if(figure.getFrames()[i][j].isFill()) return j;
        }
        return  0;
    }

    public int  Choca(Figure figure,Quadrate[][] Grid)
    {

        for (int i = 0; i < figure.getFrames().length; i++) {

            for (int j = 0; j < figure.getFrames()[0].length; j++) {
                if(figure.getFrames()[i][j].isFill()){

                    if(figure.getFrames()[i][j].getPos_x()>=Grid[0].length) return 1;
                    if(figure.getFrames()[i][j].getPos_x() <0 ) return  2;
                    if(figure.getFrames()[i][j].getPos_y() >= Grid.length) return  3;

                    if(figure.getFrames()[i][j].getPos_y() >= 0 && figure.getFrames()[i][j].getPos_y() < Grid.length && figure.getFrames()[i][j].getPos_x() >= 0 && figure.getFrames()[i][j].getPos_x() < Grid[0].length)
                    if(Grid[figure.getFrames()[i][j].getPos_y()][figure.getFrames()[i][j].getPos_x()].isFill()) return 4;

                }
            }
        }
        return  0;
    }
}






