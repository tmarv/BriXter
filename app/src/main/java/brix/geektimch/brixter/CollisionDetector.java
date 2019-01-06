package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */

import android.graphics.Rect;

/**
 * Created by gkt on 04.04.16.
 */
public class CollisionDetector {
    private boolean collision = false;

    public CollisionDetector(Rect localBrick, Rect localBall) {
        if ((localBall.left <= localBrick.right) && (localBall.right>localBrick.left)) {
            if (localBall.top <= localBrick.bottom) {
                collision = true;
            }
        }
    }

    public boolean getCol() {
        return collision;
    }
}
