package Modelo;

import android.content.Context;

public class Figure_O extends  Figure{
    public Figure_O(Context context) {
        super(context,"#ffd000","#ffea00",2,2);

        this.DataFrame = new int[][]{
                {1,1},
                {1,1}
        };
        GenerateFigure();


    }
}
