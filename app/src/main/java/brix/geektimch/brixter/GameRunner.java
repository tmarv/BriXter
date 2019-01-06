package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */

/**
 * Created by gkt on 15.03.16.
 */
public class GameRunner extends Thread {

    private volatile boolean running=true;
    private Game game;

    public GameRunner(Game game){
        this.game=game;
    }

    //our game loop called from GameView
    @Override
    public void run(){

        long lastTime= System.currentTimeMillis();
        //init before game loop
        game.init();
        while(running){
            //draw stuff

            long now= System.currentTimeMillis();
            long elapsed=now-lastTime;

            if(elapsed<110) {
                // Log.d("TIME"," elapsed is smaller than 100");
                game.update(elapsed);
                game.draw();
            }
            lastTime = now;
        }
    }

    public void shutdown()
    {
        running=false;
    }
}

