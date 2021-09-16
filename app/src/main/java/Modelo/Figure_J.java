package Modelo;

import android.content.Context;

import java.lang.reflect.Array;

public class Figure_J extends Figure {

    public Figure_J(Context context, String borderColor, String backgroundColor) {
        super(context,borderColor,backgroundColor,3,3);
        this.DataFrame = new int[][]{
                {0,1,0},
                {0,1,0},
                {1,1,0}
        };
        GenerateFigure();
    }
}
