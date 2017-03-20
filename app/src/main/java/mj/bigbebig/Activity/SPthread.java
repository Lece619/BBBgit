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
 * Updated by mk on 2017-03-17 - run 부분에 지도가 켜졌을 경우 실행할 canvas를 추가, th_run을 int형으로 바꿔 run의 종류를 결정 할 수 있도록 변경.
 */

public class SPthread extends Thread {

    private SPsurface sps;
    private SurfaceHolder holder;
    protected int th_run = 0;
    protected Resources res;
    protected int showtext = 0;
    protected Paint text_info;
    protected Bitmap map_monster;
    protected Canvas canvas;
    protected Bitmap earth;
    protected Bitmap alchemist;
    protected Bitmap btn_map;
    protected Bitmap map;
    protected Bitmap map_character;
    protected Bitmap[][] map_go = new Bitmap[5][4];

    public SPthread(SPsurface sps, SurfaceHolder holder, Resources res){
        this.sps = sps;
        this.holder = holder;
        this.res = res;
    }

    public void setRunning(int run){
        th_run = run;
    }
    public void run() {

        //화면 표시에 필요한 그림과 텍스트들을 설정
        earth = BitmapFactory.decodeResource(res, R.drawable.earth);
        alchemist = BitmapFactory.decodeResource(res, R.drawable.alchemist3);
        btn_map = BitmapFactory.decodeResource(res, R.drawable.map_info);
        map = BitmapFactory.decodeResource(res, R.drawable.map_info);
        map_character = BitmapFactory.decodeResource(res, R.drawable.map_alchemist);


        alchemist = Bitmap.createScaledBitmap(alchemist, 700, 700, true);
        btn_map = Bitmap.createScaledBitmap(btn_map, 150, 150, true);
        map_character = Bitmap.createScaledBitmap(map_character, 100, 100, true);

        Paint axis = new Paint();
        axis.setTextSize(80);
        axis.setColor(Color.BLACK);

        //일반적으로 행성에 들어갔을 경우 실행되는 화면
        while(th_run==1){
            canvas = null;
            try {
                canvas = holder.lockCanvas(null);
                synchronized (holder) {
                    earth = Bitmap.createScaledBitmap(earth, canvas.getWidth(), canvas.getHeight(), true);
                    canvas.drawBitmap(earth, 0, 0, null);
                    canvas.drawBitmap(alchemist, (canvas.getWidth() - alchemist.getWidth()) / 2, canvas.getHeight() - alchemist.getHeight(), null);

                    canvas.drawText("X좌표: " + sps.char_location[0] + " Y좌표: " + sps.char_location[1] +
                            " 지역존재: " + sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3] +" 몬스터 번호: " + sps.mapInfo[sps.char_location[0]][sps.char_location[1]][4], 0, 80, axis);
                    if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3]==0){

                    }
                    else if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3]==1){
                        map_monster = BitmapFactory.decodeResource(res, monster.getData(user_zero.getMon_name(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][4]), 13));
                        map_monster = Bitmap.createScaledBitmap(map_monster, 600, 600, true);
                        canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2,  (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                    }
                    else if(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3]==2){
                        map_monster = BitmapFactory.decodeResource(res, R.drawable.steel);
                        map_monster = Bitmap.createScaledBitmap(map_monster, 300, 300, true);
                        canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2,  (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                    }

                    if(showtext ==1){
                        text_info = new Paint();
                        text_info.setTextSize(80);
                        text_info.setColor(Color.BLACK);
                        canvas.drawText("Can't move", canvas.getWidth()/2, canvas.getHeight()/ 2, text_info);
                        showtext = 0;
                    }
                    else if(showtext == 2){
                        text_info = new Paint();
                        text_info.setTextSize(80);
                        text_info.setColor(Color.BLACK);
                        canvas.drawText(sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3] + "재료가" + sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3] + "개 나왔습니다."
                                , canvas.getWidth()/2, canvas.getHeight()/ 2, text_info);
                        sps.mapInfo[sps.char_location[0]][sps.char_location[1]][3] = 0;
                        sps.mapInfo[sps.char_location[0]][sps.char_location[1]][4] = 0;
                        showtext = 0;
                    }

                    try{
                        sleep(250);
                    } catch(InterruptedException e){ }
                }
            } finally {
                holder.unlockCanvasAndPost(canvas);
            }

            //지도가 실행되었을 때 나오는 화면
            while(th_run==2) {
                canvas = null;
                try {
                    canvas = holder.lockCanvas(null);
                    synchronized (holder) {


                        map = Bitmap.createScaledBitmap(map, canvas.getWidth(), 1300, true);
                        canvas.drawBitmap(map, 0, (canvas.getHeight() - map.getHeight())/2, null);
                        canvas.drawBitmap(map_character, map.getWidth()/10*(2*sps.char_location[0]+1) - map_character.getWidth()/2, (canvas.getHeight()-map.getHeight())/2 + map.getHeight()/8*(2*sps.char_location[1]+1), null);

                        for(int i=0; i<5;i++){
                            for(int j=0;j<4;j++){
                                if(sps.mapInfo[i][j][0] == 0){
                                    map_go[i][j] = null;
                                }
                                else{
                                    if(sps.mapInfo[i][j][2] == 0){
                                        map_go[i][j] = BitmapFactory.decodeResource(res, R.drawable.map_cloud);
                                        map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], map.getWidth()/5, map.getHeight()/4, true);
                                        canvas.drawBitmap(map_go[i][j],map.getWidth()/5*(i), (canvas.getHeight()-map.getHeight())/2 + map.getHeight()/4*(j), null);
                                    }
                                    else{
                                        if(sps.mapInfo[i][j][3] == 1){
                                            map_go[i][j] = BitmapFactory.decodeResource(res, monster.getData(user_zero.getMon_name(sps.mapInfo[i][j][4]), 13));
                                            map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], 150, 150, true);
                                            canvas.drawBitmap(map_go[i][j], map.getWidth()/10*(2*i+1) - map_go[i][j].getWidth()/2, (canvas.getHeight()-map.getHeight())/2 + map.getHeight()/8*(2*j), null);
                                        }
                                        else if(sps.mapInfo[i][j][3] == 2){
                                            map_go[i][j] = BitmapFactory.decodeResource(res, R.drawable.steel);
                                            map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], 150, 150, true);
                                            canvas.drawBitmap(map_go[i][j], map.getWidth()/10*(2*i+1) - map_go[i][j].getWidth()/2, (canvas.getHeight()-map.getHeight())/2 + map.getHeight()/8*(2*j), null);
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                        }
                    }
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
