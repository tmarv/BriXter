package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */

import android.graphics.Rect;

public class Bouncer extends Sprite {

    private static int bWidth;
    private static int bDepth;
    private static int thinckness;
    private static int bx;

    private Rect rect;

    public Bouncer(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        bWidth= Math.round(screenWidth/5);
        double tool=screenHeight;
        bDepth=(int)(tool*.85);
        bx=screenWidth/3;
        thinckness=(int)(screenHeight/30);
        rect=new Rect(bx,bDepth,bx+bWidth,bDepth+thinckness);
    }

    public Rect getRectBouncer(){
        return rect;
    }

    public void setBx(float newBx) {
        bx=(int)newBx+thinckness/2;
        rect=new Rect(bx,bDepth,bx+bWidth,bDepth+thinckness);
        //Log.d("touching", " has touched");
    }
}
