package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {

    private float x;
    private float y;

    private int screenW;
    private int screenH;


    private Rect bounds;

    private Bitmap image;

    public Sprite(int screenWidth,int screenHeight){
        this.x=30;
        this.y=30;

        this.screenH=screenHeight;
        this.screenW=screenWidth;


    }

    public void init(Bitmap image){
        this.image=image;
        bounds=new Rect(0,0,image.getWidth(),image.getHeight());
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }

    public Rect getRect(){
        return(bounds);
    }

    public Rect getScreenRect(){
        return  new Rect((int)x,(int)y,(int)x+getRect().width(),(int)y+getRect().height());
    }

    public void drawRect(Canvas canvas, Paint paint, Rect rect){
        canvas.drawRect(rect,paint);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getScreenH() {
        return screenH;
    }

    public int getScreenW() {
        return screenW;
    }
}
