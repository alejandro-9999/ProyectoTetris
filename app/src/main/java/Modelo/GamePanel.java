package Modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayout;
import android.view.View;

public class GamePanel  extends  GridLayout{
    GradientDrawable figura;
    public GamePanel(Context context){
        super(context);
        figura = new GradientDrawable();
        figura.setStroke(6,Color.RED);
        setBackground(figura);
    }


}
