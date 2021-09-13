package Modelo;

import android.content.Context;

public class FigureJ extends Figure {

    public FigureJ(Context context,int width,int height) {
        super();
        this.DataFrame = new String[]{
                "0,1",
                "0,1",
                "1,1"
        };
        GenerateFigure(context,width,height);
    }
}
