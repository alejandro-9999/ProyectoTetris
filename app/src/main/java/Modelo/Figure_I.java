package Modelo;

import android.content.Context;

public class Figure_I extends  Figure {
    public Figure_I(Context context) {

        super(context,"#48cae4","#90e0ef",4,4);

        this.DataFrame = new int[][]{
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0}
        };
        GenerateFigure();
    }
}
