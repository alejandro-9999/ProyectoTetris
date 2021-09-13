package Modelo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

public class Empty extends View {

    private String BackgroundColor;
    private  String BorderColor;
    private int Width;
    private  int Height;
    GradientDrawable figura;
    public Empty(Context context, String BorderColor, int Width, int Height) {
        super(context);
        this.BackgroundColor = BackgroundColor;
        this.BorderColor = BorderColor;
        this.Height = Height;
        this.Width = Width;

        setLayoutParams(new FrameLayout.LayoutParams(this.Width,this.Height));
        figura = new GradientDrawable();
        figura.setStroke(2,Color.parseColor(this.BorderColor));
        setBackground(figura);

    }





}
