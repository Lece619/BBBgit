package mj.bigbebig.Activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import mj.bigbebig.R;

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
                    canvas.drawText("X좌표: " + sps.char_location[0] + " Y좌표: " + sps.char_location[1], 0, 80, axis);
                    if(moving){
                        canvas.drawBitmap(move,  (canvas.getWidth() - move.getWidth()) / 2,  (canvas.getHeight() - move.getHeight()) / 2, null);
                        moving = false;
                    }

                    try{
                        sleep(500);
                    } catch(InterruptedException e){ }
                }
            } finally {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
