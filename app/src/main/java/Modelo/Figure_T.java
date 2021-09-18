package Modelo;

import android.content.Context;

public class Figure_T extends Figure {
    public Figure_T(Context context, String borderColor, String backgroundColor) {
        super(context,borderColor,backgroundColor,3,3);

        this.DataFrame = new int[][]{
                {0,1,0},
                {1,1,1},
                {0,0,0}
        };
        GenerateFigure();
    }
}
