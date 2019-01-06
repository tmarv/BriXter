package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 this is the view where we draw rhe game wiew in the xml layout
 */
public class GameView extends SurfaceView implements Callback {

    private GameRunner runner;
    private Game game;

    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);

        SurfaceHolder holder=getHolder();
        holder.addCallback(this);
    }//end of constructor

    @Override
    public boolean onTouchEvent(MotionEvent event){
        game.onTouchEvent(event);
        return true;
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        game=new Game(getWidth(),getHeight(),holder, getResources());
        runner= new GameRunner(game);
        runner.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(runner!=null) {
            // Log.d("CREATED ", "surface destroyed");
            runner.shutdown();

            while (runner != null) {
                try {
                    runner.join();
                    runner=null;
                } catch (InterruptedException e){
                }
            }
        }
    }
}//end of class