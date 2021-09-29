package Modelo;

import android.content.Context;

public class Figure_S extends  Figure{

    public Figure_S(Context context) {
        super(context,"#38b000","#70e000",3,3);

        this.DataFrame = new int[][]{
                {0,0,0},
                {0,1,1},
                {1,1,0}
        };
        GenerateFigure();
    }
}
