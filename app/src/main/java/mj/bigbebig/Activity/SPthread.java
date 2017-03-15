package mj.bigbebig.Activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk on 2017-03-13 - 가장 기본이 되는 쓰레드 생성(earth, alchemist)
 * Updated by mk on 2017-03-14 - 좌표를 표시해주는 페인트 생성, 움직일수 없을 경우 이동불가 창이 나오는 if문 추가
 */

public class SPthread extends Thread {

    private SPsurface sps;
    private SurfaceHolder holder;
    private boolean th_run = false;
    private Resources res;
    protected boolean moving = false;
    private Bitmap map_monster;


    public SPthread(SPsurface sps, SurfaceHolder holder, Resources res){
        this.sps = sps;
        this.holder = holder;
        this.res = res;
    }

    public void setRunning(boolean run){
        th_run = run;
    }
    public void run() {
        Canvas canvas;
        Bitmap earth = BitmapFactory.decodeResource(res, R.drawable.earth);
        Bitmap alchemist = BitmapFactory.decodeResource(res, R.drawable.alchemist3);
        Bitmap move = BitmapFactory.decodeResource(res, R.drawable.cant_move);

        alchemist = Bitmap.createScaledBitmap(alchemist, 700, 700, true);
        move = Bitmap.createScaledBitmap(move, 700, 300, true);
        Paint axis = new Paint();
        axis.setTextSize(80);
        axis.setColor(Color.BLACK);

        while(th_run){
            canvas = null;
            try {
                canvas = holder.lockCanvas(null);
                synchronized (holder) {
                    earth = Bitmap.createScaledBitmap(earth, canvas.getWidth(), canvas.getHeight(), true);
                    canvas.drawBitmap(earth, 0, 0, null);
                    canvas.drawBitmap(alchemist, (canvas.getWidth() - alchemist.getWidth()) / 2, canvas.getHeight() - alchemist.getHeight(), null);
                    canvas.drawText("X좌표: " + sps.char_location[0] + " Y좌표: " + sps.char_location[1] +
                            " 지역존재: " + sps.mapInfo[sps.char_location[0]][sps.char_location[1]][2] +" 몬스터 번호: " + sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3], 0, 80, axis);
                    if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][2]==0){

                    }
                    else if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][2]==1){
                        map_monster = BitmapFactory.decodeResource(res, monster.getData(user_zero.getMon_name(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3]), 13));
                        map_monster = Bitmap.createScaledBitmap(map_monster, 600, 600, true);
                        canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2,  (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                    }
                    else if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][2]==2){
                        map_monster = BitmapFactory.decodeResource(res, R.drawable.steel);
                        map_monster = Bitmap.createScaledBitmap(map_monster, 300, 300, true);
                        canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2,  (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                    }

                    if(moving){
                        canvas.drawBitmap(move,  (canvas.getWidth() - move.getWidth()) / 2,  (canvas.getHeight() - move.getHeight()) / 2, null);
                        moving = false;
                    }

                    try{
                        sleep(250);
                    } catch(InterruptedException e){ }
                }
            } finally {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
