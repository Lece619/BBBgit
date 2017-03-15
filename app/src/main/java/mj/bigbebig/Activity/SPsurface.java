package mj.bigbebig.Activity;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import static mj.bigbebig.Activity.MonsterLoad.mapdata;

/**
 * Created by mk on 2017-03-13.
 * Updated by mk on 2017-03-14 - 움직이는 방향에 따라 좌표 이동하도록 수정, 이동 못할 경우 이동불가 창이 나오도록 변경
 */

public class SPsurface extends SurfaceView implements SurfaceHolder.Callback {

    public int prix = 0, priy = 0;
    public int aftx = 0, afty = 0;
    private SPthread spthread;
    public int[][][] mapInfo = new int[5][4][4];
	public int[] char_location = new int[2];

    public SPsurface(Context context){
        super(context);
        getHolder().addCallback(this);
        spthread = new SPthread(this, getHolder(), getResources());
    }

    public void surfaceCreated(SurfaceHolder holder){

        mapdata.getFirstplanet(mapInfo);
		Random random = new Random();
		int x, y;
		do{
			x = random.nextInt(5);
			y = random.nextInt(4);
			if(mapInfo[x][y][0]==1){
				char_location[0] = x;
				char_location[1] = y;
			}
		}while(mapInfo[x][y][0]==0);

        spthread.setRunning(true);
        spthread.start();

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        spthread.setRunning(false);
        while(retry){
            try{
                spthread.join();
                retry = false;
            } catch(InterruptedException e){}
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            prix = (int)event.getX();
            priy = (int)event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            aftx = (int)event.getX();
            afty = (int)event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            direction(prix, priy, aftx, afty);
            Log.d("hello","hello" + prix + "  " + priy + "  " + aftx + "  " + afty);
        }

        return true;
    }
    public void direction(int px, int py, int ax, int ay){
        int dirX, dirY;
        dirX = px - ax;
        dirY = py - ay;
        if(dirX*dirX + dirY*dirY > 5000){
            if(dirY*dirY>dirX*dirX){
                if(dirY<0) {
                    Log.d("move","up");
                    move(0);
                }
                else{
                    Log.d("move","down");
                    move(1);
                }
            }
            else{
                if(dirX>0){
                    Log.d("move","right");
                    move(2);
                }
                else{
                    Log.d("move","left");
                    move(3);
                }
            }
        }
    }
    private void move(int dir){
        if(dir==0){
            if((char_location[1]!=0) && (mapInfo[char_location[0]][char_location[1]-1][0]!=0)){
                char_location[1]--;
            }
            else{
                Log.d("move","can't move");
                spthread.moving = true;
            }
        }
        if(dir==1){
            if((char_location[1]!=3) && (mapInfo[char_location[0]][char_location[1]+1][0]!=0)){
                char_location[1]++;
            }
            else{
                Log.d("move","can't move");
                spthread.moving = true;
            }
        }
        if(dir==2){
            if((char_location[0]!=4) && (mapInfo[char_location[0]+1][char_location[1]][0]!=0)){
                char_location[0]++;
            }
            else{
                Log.d("move","can't move");
                spthread.moving = true;
            }
        }
        if(dir==3){
            if((char_location[0]!=0) && (mapInfo[char_location[0]-1][char_location[1]][0]!=0)){
                char_location[0]--;
            }
            else{
                Log.d("move","can't move");
                spthread.moving = true;
            }
        }
    }
}