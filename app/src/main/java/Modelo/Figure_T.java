package Modelo;

import android.content.Context;

public class Figure_T extends Figure {
    public Figure_T(Context context) {
        super(context,"#7b2cbf","#9d4edd",3,3);

        this.DataFrame = new int[][]{
                {0,1,0},
                {1,1,1},
                {0,0,0}
        };
        GenerateFigure();
    }
}
