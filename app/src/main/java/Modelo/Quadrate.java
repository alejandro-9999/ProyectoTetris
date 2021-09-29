package Modelo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

public class Quadrate extends View {
    protected String BackgroundColor;
    protected String BorderColor;
    protected int QuadrateWidth;
    protected int QuadrateHeight;
    protected GradientDrawable figura;
    protected int pos_x;
    protected int pos_y;

    boolean fill;

    public Quadrate(Context context, String backgroundColor, String borderColor, int quadrateWidth, int quadrateHeight, int pos_x, int pos_y) {
        super(context);
        BackgroundColor = backgroundColor;
        BorderColor = borderColor;
        QuadrateWidth = quadrateWidth;
        QuadrateHeight = quadrateHeight;
        setLayoutParams(new FrameLayout.LayoutParams(this.QuadrateWidth,this.QuadrateHeight));
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.fill = false;
    }

    public Quadrate(Context context, String backgroundColor, String borderColor, int quadrateWidth, int quadrateHeight) {
        super(context);
        BackgroundColor = backgroundColor;
        BorderColor = borderColor;
        QuadrateWidth = quadrateWidth;
        QuadrateHeight = quadrateHeight;
        setLayoutParams(new FrameLayout.LayoutParams(this.QuadrateWidth,this.QuadrateHeight));
        fill = true;

    }

    public Quadrate(String backgroundColor, String borderColor,int pos_x, int pos_y, Context context) {
        super(context);
        BackgroundColor = backgroundColor;
        BorderColor = borderColor;
        this.pos_x = pos_x;
        this.pos_y = pos_y;

    }
    public Quadrate(Context context) {
        super(context);
    };

    public Quadrate(String backgroundColor, String borderColor,Context context) {
        super(context);
        BackgroundColor = backgroundColor;
        BorderColor = borderColor;


    }




    public void  setFill(String backgroundColor, String borderColor){
        this.figura = new GradientDrawable();
        this.figura.setStroke(15,Color.parseColor(borderColor));
        this.figura.setColor(Color.parseColor(backgroundColor));
        setBackground(figura);
        setElevation(15);
        fill = true;
    }
    public void  setEmpty(String backgroundColor, String borderColor){
        this.figura = new GradientDrawable();
        this.figura.setStroke(2,Color.parseColor(this.BorderColor));
        this.figura.setColor(Color.parseColor(this.BackgroundColor));
        setBackground(figura);
        setElevation(0);
        fill = false;
    }

    public void  setFill(String backgroundColor, String borderColor,int pos_y,int pos_x){
        this.figura = new GradientDrawable();
        this.figura.setStroke(15,Color.parseColor(borderColor));
        this.figura.setColor(Color.parseColor(backgroundColor));
        setBackground(figura);
        setElevation(15);
        fill = true;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    public void  setEmpty(String backgroundColor, String borderColor,int pos_y,int pos_x){
        this.figura = new GradientDrawable();
        this.figura.setStroke(2,Color.parseColor(this.BorderColor));
        this.figura.setColor(Color.parseColor(this.BackgroundColor));
        setBackground(figura);
        setElevation(0);
        fill = false;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public void clonate(Quadrate clon){
        this.BackgroundColor = clon.BackgroundColor;
        this.BorderColor = clon.BorderColor;
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return BorderColor;
    }

    public void setBorderColor(String borderColor) {
        BorderColor = borderColor;
    }

    public int getQuadrateWidth() {
        return QuadrateWidth;
    }

    public void setQuadrateWidth(int quadrateWidth) {
        QuadrateWidth = quadrateWidth;
    }

    public int getQuadrateHeight() {
        return QuadrateHeight;
    }

    public void setQuadrateHeight(int quadrateHeight) {
        QuadrateHeight = quadrateHeight;
    }

    public GradientDrawable getFigura() {
        return figura;
    }

    public void setFigura(GradientDrawable figura) {
        this.figura = figura;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
