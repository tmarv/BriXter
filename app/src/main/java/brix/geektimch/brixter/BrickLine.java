package brix.geektimch.brixter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created on 30.03.16.
 */
public class BrickLine {

    private Brick[] bLine = new Brick[10];
    private Boolean visible = false;
    private Rect LineRect;
    private int depth;

    private static int brickW;
    private static int brickH;

    public BrickLine(int sWidth, int sHeight, boolean visible, int deepth) {
        this.depth=deepth;
        this.visible = visible;
        brickH=sHeight/40;
        brickW=(sWidth-27)/10;
        Brick localBrick = null;

        LineRect = new Rect(0, ((depth) * (3+(sHeight / 40))), sWidth, ((depth+1) * (3+(sHeight / 40))));
        for (int i = 0; i < 10; i++) {

            if (i == 0 || i == 9) {
                Paint localPaint = new Paint();
                localPaint.setColor(Color.rgb(58, 255, 0));
                localBrick = new Brick(sWidth, sHeight, visible, localPaint, i, depth);
            } else if (i == 1 || i == 8) {
                Paint localPaint = new Paint();
                localPaint.setColor(Color.YELLOW);
                localBrick = new Brick(sWidth, sHeight, visible, localPaint, i, depth);

            } else if (i == 2 || i == 7) {
                Paint localPaint = new Paint();
                localPaint.setColor(Color.rgb(255, 196, 0));
                localBrick = new Brick(sWidth, sHeight, visible, localPaint, i, depth);

            } else if (i == 3 || i == 6) {
                Paint localPaint = new Paint();
                localPaint.setColor(Color.rgb(255, 69, 0));
                localBrick = new Brick(sWidth, sHeight, visible, localPaint, i, depth);

            } else if (i == 4 || i == 5) {
                Paint localPaint = new Paint();
                localPaint.setColor(Color.RED);
                localBrick = new Brick(sWidth, sHeight, visible, localPaint, i, depth);
            }
            bLine[i] = localBrick;
        }
    }//end of constructor

    public boolean getVis() {
        return visible;
    }

    public void setVisible(boolean vis){
        visible=vis;
    }

    public Brick[] getLocalLine() {
        return bLine;
    }


    public Rect getLineRect() {
        return LineRect;
    }

    public void setDeepth(int z){
        this.depth=z;
    }
}
