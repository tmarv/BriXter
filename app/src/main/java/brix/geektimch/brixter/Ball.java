package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Ball extends Sprite {

    private  float speedX=0.2f;
    private  float speedY=0.22f;

    private Rect screenRect;

    private float ballW=10;

    private float x=(int)Math.random()*200;
    private float y=300;

    private int directionX=1;
    private int directionY=1;

    private Paint squarePaint;

    public Ball(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        ballW=screenWidth/35;


    }

    public void update(long elapsed){
        //System.out.println("this is elapsed "+elapsed);
        //float x=getX();
        //float y=getY();

        x+=directionX*speedX*elapsed;
        y+=directionY*speedY*elapsed;

        //System.out.println("this is x ball "+x);
        setX(x);
        setY(y);
        screenRect=new Rect((int)(x-ballW),(int)(y-ballW),(int)(x+ballW),(int)(y+ballW));
        squarePaint=new Paint(Color.GREEN);

        if(screenRect.left<=0){
            directionX=1;
        }
        else if(screenRect.right>=getScreenW()){
            directionX=-1;
        }

        if(screenRect.top<=0){
            directionY=1;
        }
        else if(screenRect.bottom>=getScreenH()){
            directionY=-1;
        }
        else if(x<0){
            directionX=1;
        }
        else if(x>getScreenW()){
            directionX=-1;
        }
    }

    public void drawCircle( Canvas canvas , Paint ballPaint){
        //canvas.drawCircle(x, y, ballW, ballPaint);
        canvas.drawRect(screenRect, ballPaint);
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }
    public Rect getBRect(){
        return screenRect;
    }

    public void setSpeedX(float nSpeedX){
        speedX=nSpeedX;
    }

    public void setSpeedY(float nSpeedY){
        speedY=nSpeedY;
    }
}