package Modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tetris.R;

public class Quadrate extends View {

    private String BackgroundColor;
    private  String BorderColor;
    private int Width;
    private  int Height;
    GradientDrawable figura;
    public Quadrate(Context context,String BackgroundColor,String BorderColor,int Width, int Height) {
        super(context);
        this.BackgroundColor = BackgroundColor;
        this.BorderColor = BorderColor;
        this.Height = Height;
        this.Width = Width;

        setLayoutParams(new FrameLayout.LayoutParams(this.Width,this.Height));
        figura = new GradientDrawable();
        figura.setStroke(2,Color.BLACK);
        figura.setColor(Color.parseColor(this.BackgroundColor));
        setBackground(figura);

    }





}
