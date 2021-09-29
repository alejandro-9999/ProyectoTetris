package Modelo;

import android.content.Context;

public class Figure_Z extends  Figure{
    public Figure_Z(Context context) {
        super(context,"#a4161a","#ff0000",3,3);

        this.DataFrame = new int[][]{
                {0,0,0},
                {1,1,0},
                {0,1,1}
        };
        GenerateFigure();
    }
}
