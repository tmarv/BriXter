package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */
import android.graphics.Rect;


public class BouncerCollision {
    private boolean isCollision=false;

    public BouncerCollision(Rect bounceR,Rect bRect){
        if(bounceR.top<=bRect.bottom) {
            if (bRect.centerX() >= bounceR.left) {
                if (bRect.centerX() <= bounceR.right) {
                    isCollision = true;
                }
            }
        }
    }

    public boolean getCol(){
        return isCollision;
    }
}
