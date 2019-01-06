package brix.geektimch.brixter;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by gkt on 15.03.16.
 */

//colors for the bricks
    /*
    * center p red
    * way1p rgb 255 69 0
    * way2p rgb 255 196 0
    * way3p yellow
    * way4p rgb 58 255 0
    *
    * */
public class Game {

    private SurfaceHolder holder;

    private Ball ball;

    private Bouncer bouncer;
    private Paint bouncerPaint= new Paint();
    private Paint ballPaint= new Paint();

    private BrickTable brickTable;
    private BrickLine[] filledTable;
    private Rect[] rectForLines= new Rect[16];

    private int tableSize;


    private int sWidth;
    private int sHeigth;
    private int bSize;
    private int bUseful;
    private int[] collsion=new int[7];

    private float line;
    private Paint lineP=new Paint(Color.RED);

    private int level=1;
    private int score=0;
    private int lives=1;
    private double divisionFac=1;

    private long totalTime=0;
    private long localTime=0;
    private long stopTime=0;

    private String scoreText="score: ";
    private String levelTxt="level: ";

    private float speedAdapter=1.0f;

    private enum GameState{start,play,restart,end}
    private GameState gameState;

    public Game(int width, int heihgt,SurfaceHolder holder, Resources resources){

        gameState=GameState.start;
        this.sWidth=width;
        this.sHeigth=heihgt;
        this.holder=holder;
        divisionFac=sHeigth/42;
        ball=new Ball(sWidth,sHeigth);
        line=(.4f)*sHeigth+12*3;
        bouncer = new Bouncer(sWidth,sHeigth);
        bouncerPaint.setColor(Color.RED);
        ballPaint.setColor(Color.WHITE);
        bSize=(bouncer.getRectBouncer().right-bouncer.getRectBouncer().left);
        bUseful=bSize/7;
        for(int i=1;i<8;i++) {
            collsion[i-1]=bUseful*i;
            //System.out.println("THIS IS busefull "+ collsion[i-1]);
        }
        // System.out.println("this are the screen dimensions "+ sWidth +" "+sHeigth);
        ball.update(50);

    }//end of constructor
    //480 *762  device id 52936a03
    public void init(){
        //level= how many lines are visible
        brickTable = new BrickTable(sWidth,sHeigth,1);
        level=1;
        double toAdd=level/(((1+level/8.001))*divisionFac);
        speedAdapter=(float)(speedAdapter+toAdd);
        filledTable=brickTable.getTable();
        tableSize=filledTable.length;
        for(int i=0;i<16;i++){
            rectForLines[i]=filledTable[i].getLineRect();
        }
        ballPaint.setTextSize(sWidth/10);

    }

    //position of ball us update before being repainted!
    public void update(long elapsed) {

        if (gameState == GameState.play) {
            localTime+=elapsed;
            totalTime+=elapsed;
            //the higher the level, the faster the regeneration goes
            stopTime=localTime+28*level;
            if(stopTime>10000){
                localTime=0;
                stopTime=0;
                nextLevel();
            }
            Rect ballR = ball.getBRect();

            //check for collisions at the bottom
            BouncerCollision daddyB=new BouncerCollision(bouncer.getRectBouncer(),ballR);
            if (daddyB.getCol()) {
                ball.setDirectionY(-1);
                //set the bounce angle
                int compare = ballR.centerX() - bouncer.getRectBouncer().left;
                if (compare < collsion[0]) {
                    ball.setSpeedX(.22f*speedAdapter);
                    ball.setSpeedY(.17f*speedAdapter);
                    ball.setDirectionX(-1);
                    bouncerPaint.setColor(Color.CYAN);
                } else if (compare < collsion[1]) {
                    ball.setDirectionX(-1);
                    ball.setSpeedX(.16f * speedAdapter);
                    ball.setSpeedY(.17f * speedAdapter);
                    bouncerPaint.setColor(Color.LTGRAY);
                } else if (compare < collsion[2]) {
                    ball.setDirectionX(-1);
                    ball.setSpeedX(.07f * speedAdapter);
                    ball.setSpeedY(.17f * speedAdapter);
                    bouncerPaint.setColor(Color.BLUE);
                } else if (compare < collsion[3]) {
                    ball.setSpeedY(.17f*speedAdapter);
                    ball.setSpeedX(0.f);
                    bouncerPaint.setColor(Color.WHITE);
                } else if (compare < collsion[4]) {
                    ball.setDirectionX(1);
                    ball.setSpeedY(.17f * speedAdapter);
                    ball.setSpeedX(.07f*speedAdapter);
                    bouncerPaint.setColor(Color.BLUE);
                } else if (compare < collsion[5]) {
                    ball.setDirectionX(1);
                    ball.setSpeedY(.17f * speedAdapter);
                    ball.setSpeedX(.16f*speedAdapter);
                    bouncerPaint.setColor(Color.LTGRAY);
                } else if (compare < collsion[6]) {
                    ball.setDirectionX(1);
                    ball.setSpeedY(.17f*speedAdapter);
                    ball.setSpeedX(.22f*speedAdapter);
                    bouncerPaint.setColor(Color.CYAN);
                }
            } //game over or lost a life
            else if (ballR.bottom >= sHeigth) {
                ball.setSpeedX(0.f);
                ball.setSpeedY(0.f);
                gameState=gameState.end;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }

            //collision against bricks
            if (ball.getY() < (sHeigth / 2)) {
                for (int j = 15; j >= 0; j--) {
                    CollisionDetector daddy = new CollisionDetector(filledTable[j].getLineRect(), ballR);
                    if (filledTable[j].getVis() && daddy.getCol()) {
                        Brick[] localLine = filledTable[j].getLocalLine();
                        boolean hasHit = false;
                        boolean lineSurvives=false;
                        CollisionDetector collision;
                        for (int k = 0; k < 10; k++) {
                            collision = new CollisionDetector(localLine[k].getBrickRect(), ballR);
                            if (localLine[k].getVis() && collision.getCol()) {
                                localLine[k].setVis(false);
                                score=score+level*5;
                                hasHit = true;
                            }
                            else if(localLine[k].getVis()){
                                lineSurvives=true;
                            }
                        }
                        if (hasHit) {
                            ball.setDirectionY(1);
                            break;
                        }
                        //checks if all the bricks of one line are destroyed
                        if(!lineSurvives){
                            filledTable[j].setVisible(false);
                            //System.out.println("line has disappeared");
                            if(level>1){
                                level--;
                                double toAdd=level/((1+level/8.01)*divisionFac);
                                speedAdapter=(float)(speedAdapter-toAdd);
                            }
                        }
                    }
                }
            }
            ball.update(elapsed);
        }
    }

    private void nextLevel(){
        //look for the level of the current game
        level=level+1;
        double toAdd=level/((1+(level/8.01))*divisionFac);
        System.out.println("to add "+toAdd);
        speedAdapter=(float) (speedAdapter+toAdd);
        //System.out.println("this is level "+level);
        //System.out.println("update now!");
        if(level>=16){
            gameState=gameState.end;
        }
        else{
            for(int i=level;i>0;i--){
                BrickLine localBl=filledTable[i-1];

                BrickLine newBl;
                //if it's visible otherwise we don't care
                //create a new line at the desired depth and set visibilities
                if(localBl.getVis()){
                    newBl=new BrickLine(sWidth,sHeigth,true,i);
                    Brick [] bl=localBl.getLocalLine();
                    Brick [] nBl=newBl.getLocalLine();
                    for(int k=0;k<10;k++){
                        nBl[k].setVis(bl[k].getVis());
                    }
                    brickTable.setLine(newBl,i);
                }


            }

        }
        //first line needs to be updated!
        BrickLine localBrickLine= new BrickLine(sWidth,sHeigth,true,0);
        brickTable.setLine(localBrickLine, 0);

        filledTable=brickTable.getTable();
    }

    //calls individual sprites in the if loop
    public void draw(){

        if(gameState==GameState.play) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.DKGRAY);
                canvas.drawLine(0, line, sWidth, line, ballPaint);
                canvas.drawText(levelTxt + level, (int) (sWidth / 3.5), (int) (sHeigth / 1.6), ballPaint);
                canvas.drawText(scoreText+score,(int)(sWidth/3.5),(int) (sHeigth/1.4),ballPaint);
                ball.drawCircle(canvas, ballPaint);
                bouncer.drawRect(canvas, bouncerPaint, bouncer.getRectBouncer());

                for (int i = 0; i < tableSize; i++) {
                    //check if the whole line hasn't been shutdown
                    if (filledTable[i].getVis()) {
                        Brick[] localLine = filledTable[i].getLocalLine();
                        for (int j = 0; j < 10; j++) {
                            localLine[j].paintBrick(canvas);
                        }
                    }
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }

        else if(gameState==GameState.start){
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.DKGRAY);
                canvas.drawText("Start", (int) (sWidth / (2.5)), (int)(sHeigth / 2.3), ballPaint);
                holder.unlockCanvasAndPost(canvas);
            }

        }

        else if(gameState==GameState.end){
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.DKGRAY);
                canvas.drawText("GAME OVER", (int) (sWidth / (4.5)), sHeigth / 3, ballPaint);
                canvas.drawText("Score: "+score, (int) (sWidth / (4.5)), sHeigth / 2,ballPaint);
                holder.unlockCanvasAndPost(canvas);
            }

        }

    }//end of draw

    public void onTouchEvent(MotionEvent event) {

        if(gameState==GameState.start){
            gameState=GameState.play;
        }
        else if(gameState==GameState.play) {
            bouncer.setBx(20 + event.getX() - bSize / 2);
        }
        else if(gameState==GameState.end){
            //replay vs quit
        }
        else if(gameState==GameState.restart){

        }
    }

}//end of class