package Modelo;

import android.content.Context;

public class Figure_L extends  Figure {

    public Figure_L(Context context) {
        super(context,"#ff9100","#ff9e00",3,3);

        this.DataFrame = new int[][]{
                {0,1,0},
                {0,1,0},
                {0,1,1}
        };
        GenerateFigure();
    }
}
