package Modelo;

import android.content.Context;

public class Figure_Quadrate extends  Figure{
    public Figure_Quadrate(Context context, String borderColor, String backgroundColor) {
        super(context,borderColor,backgroundColor,2,2);

        this.DataFrame = new int[][]{
                {1,1},
                {1,1}
        };
        GenerateFigure();
    }
}
