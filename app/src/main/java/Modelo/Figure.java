package Modelo;

import android.content.Context;

public class Figure {
    protected  Quadrate[][] matriz;
    protected  String[] DataFrame;
    public Figure(){
    }
    public  void GenerateFigure(Context context,int width,int height){
        int y = DataFrame[0].split(",").length;
        matriz = new Quadrate[y][DataFrame.length];
        for (int i = 0; i < DataFrame.length; i++) {
            System.out.println(DataFrame[i]);
            String[] frames = DataFrame[i].split(",");
            for (int j = 0; j < frames.length ; j++) {
                if(frames[i].equals("1")){
                    matriz[i][j] = new Quadrate(context,"#00509d","#00296b",width,height);
                }else{
                    matriz[i][j] = null;
                }
            }
        }
    }

    public Quadrate[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Quadrate[][] matriz) {
        this.matriz = matriz;
    }
}
