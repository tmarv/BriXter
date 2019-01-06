package brix.geektimch.brixter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by gkt on 30.03.16.
 */
public class Brick extends Sprite {


    private Paint brickPaint;
    private boolean visible=false;
    private int brickW;
    private int brickH;
    private Rect brickRect;
    private int depth;

    public Brick(int screenWidth, int screenHeight,boolean visible, Paint brickPaint,int location, int deepth) {
        super(screenWidth, screenHeight);
        this.visible=visible;
        depth=deepth;
        this.brickPaint=brickPaint;
        brickW=((screenWidth-27)/10);
        brickH=screenHeight/40;
        brickRect=new Rect(location*(brickW+3),depth*(3+brickH),location*(brickW+3)+brickW,depth*(3+brickH)+brickH);
    }
    public void paintBrick(Canvas canvas){
        if(visible) {
            canvas.drawRect(brickRect, brickPaint);
        }
    }

    public void SetPaint(Paint paint){
        brickPaint=paint;
    }
    public Rect getBrickRect(){
        return brickRect;
    }
    public boolean getVis(){
        return visible;
    }
    public void setVis (boolean newVis){
        visible=newVis;
    }
    public void setDepth(int z){
        depth=z;

    }
}
