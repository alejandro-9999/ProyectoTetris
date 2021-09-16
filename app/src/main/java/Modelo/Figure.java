package Modelo;

import android.content.Context;

public class Figure {

    protected  int[][] DataFrame;
    protected  Quadrate[][] Frames;
    String BorderColor;
    String BackgroundColor;
    int FigureWidth;
    int FigureHeight;
    Context context;

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


    public  void  Rotar90(){
        DataFrame =  rotateMatrix(DataFrame);
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
}
