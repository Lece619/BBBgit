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
 * Updated bu mk on 2017-03-17 - 터치 이벤트에 지도와 관련된 함수 추가
 */

public class SPsurface extends SurfaceView implements SurfaceHolder.Callback {

    public int prix = 0, priy = 0;
    public int aftx = 0, afty = 0;
    public SPthread spthread;
    public int[][][] mapInfo = new int[5][4][5];
	public int[] char_location = new int[2];
    private Context context;

    public SPsurface(Context context){
        super(context);
        this.context = context;
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
        mapInfo[char_location[0]][char_location[1]][2] = 1;

        spthread.setRunning(1);
        spthread.start();

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        spthread.setRunning(0);
        while(retry){
            try{
                spthread.join();
                retry = false;
            } catch(InterruptedException e){}
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        //맵 이동 이벤트
        if(spthread.th_run==1){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                prix = (int)event.getX();
                priy = (int)event.getY();
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                aftx = (int)event.getX();
                afty = (int)event.getY();
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                direction(prix, priy, aftx, afty); //터치를 멈출때 어떻게 작동할지의 함수를 실행
                Log.d("hello","hello" + prix + "  " + priy + "  " + aftx + "  " + afty);
            }
        }
        else if(spthread.th_run==2){
            if(event.getAction() == MotionEvent.ACTION_UP){
                spthread.th_run = 1;

            }
        }

        return true;
    }

    //좌표을 계산해서 어느 방향으로 이동할지 아니면 전투를 할지 선택하는 함수
    public void direction(int px, int py, int ax, int ay){
        int dirX, dirY;
        dirX = px - ax;
        dirY = py - ay;
        if(dirX*dirX + dirY*dirY > 50000){
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
        else{
            Log.d("fight", "readytofight");
            if (aftx < ((spthread.canvas.getWidth() + spthread.map_monster.getWidth()) / 2.) && aftx > ((spthread.canvas.getWidth() - spthread.map_monster.getWidth()) / 2.)) {
                if (afty < ((spthread.canvas.getHeight() + spthread.map_monster.getHeight()) / 2.) && afty > ((spthread.canvas.getHeight() - spthread.map_monster.getHeight()) / 2.)) {
                    if(mapInfo[char_location[0]][char_location[1]][3] == 1) {
                        Log.d("monster", "monsterfight");
                        /*context.startActivity(new Intent(context, ReadyFight_two.class));
                        spthread.setRunning(0);*/
                        mapInfo[char_location[0]][char_location[1]][3] = 0;
                        mapInfo[char_location[0]][char_location[1]][4] = 0;
                    }
                    else if(mapInfo[char_location[0]][char_location[1]][3] == 2) {
                        spthread.showtext = 2;
                }
            }


            }
        }
    }

    //방향 결정 함수에서 움직임이 결정되었을때 움직일 수 있는지 판단 후 실제로 이동하게 하는 함수
    private void move(int dir){
        if(dir==0){
            if((char_location[1]!=0) && (mapInfo[char_location[0]][char_location[1]-1][0]!=0)){
                char_location[1]--;
                mapInfo[char_location[0]][char_location[1]][2] = 1;
            }
            else{
                Log.d("move","can't move");
                spthread.showtext = 1;
            }
        }
        if(dir==1){
            if((char_location[1]!=3) && (mapInfo[char_location[0]][char_location[1]+1][0]!=0)){
                char_location[1]++;
                mapInfo[char_location[0]][char_location[1]][2] = 1;
            }
            else{
                Log.d("move","can't move");
                spthread.showtext = 1;
            }
        }
        if(dir==2){
            if((char_location[0]!=4) && (mapInfo[char_location[0]+1][char_location[1]][0]!=0)){
                char_location[0]++;
                mapInfo[char_location[0]][char_location[1]][2] = 1;
            }
            else{
                Log.d("move","can't move");
                spthread.showtext = 1;
            }
        }
        if(dir==3){
            if((char_location[0]!=0) && (mapInfo[char_location[0]-1][char_location[1]][0]!=0)){
                char_location[0]--;
                mapInfo[char_location[0]][char_location[1]][2] = 1;
            }
            else{
                Log.d("move","can't move");
                spthread.showtext = 1;
            }
        }
    }
}