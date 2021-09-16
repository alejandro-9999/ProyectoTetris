package Modelo;

import android.content.Context;

public class Figure_I extends  Figure {
    public Figure_I(Context context, String borderColor, String backgroundColor) {
        super(context,borderColor,backgroundColor,4,4);


        this.DataFrame = new int[][]{
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0}
        };
        GenerateFigure();
    }
}
