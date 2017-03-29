package mj.bigbebig.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import mj.bigbebig.Class.SPinformation;
import mj.bigbebig.R;

import static java.lang.Thread.sleep;
import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk on 2017-03-13.
 * Updated by mk on 2017-03-14 - 움직이는 방향에 따라 좌표 이동하도록 수정, 이동 못할 경우 이동불가 창이 나오도록 변경
 * Updated bu mk on 2017-03-17 - 터치 이벤트에 지도와 관련된 함수 추가
 */

public class SPsurface extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private Context context;
    private SurfaceHolder holder;
    protected Thread runrun;
    protected boolean run;
    protected int th_run = 0;

    //정보 관련 변수들
    private int prix = 0, priy = 0;
    private int aftx = 0, afty = 0;
    /*private int[][][] mapInfo = new int[5][4][5];
    private int[] char_location = new int[2];*/
    protected SPinformation spinfo;


    //캔버스 관련 변수들
    private int showtext = 0;
    private Paint text_info;
    private Bitmap map_monster;
    private Canvas canvas;
    private Bitmap earth;
    private Bitmap alchemist;
    private Bitmap map;
    private Bitmap map_character;
    private Bitmap[][] map_go = new Bitmap[5][4];


    //생성자
    public SPsurface(Context context){
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        runrun  = new Thread(this);
        holder = getHolder();
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
    //surfaceView 관련 함수들
    public void surfaceCreated(SurfaceHolder holder){

        spinfo = new SPinformation();

        /*mapdata.getFirstplanet(mapInfo);
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
        mapInfo[char_location[0]][char_location[1]][2] = 1;*/

        run = true;
        th_run = 1;
        runrun.start();
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        run = false;
        while(retry){
            try{
                runrun.join();
                retry = false;
            } catch(InterruptedException e){}
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
    //Runnable 함수
    public void run(){
        //화면 표시에 필요한 그림과 텍스트들을 설정
        earth = BitmapFactory.decodeResource(getResources(), R.drawable.earth);
        alchemist = BitmapFactory.decodeResource(getResources(), R.drawable.alchemist3);
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map_info);
        map_character = BitmapFactory.decodeResource(getResources(), R.drawable.map_alchemist);

        Paint axis = new Paint();
        axis.setTextSize(80);
        axis.setColor(Color.BLACK);

        while(run){
            canvas = null;
            try {
                canvas = holder.lockCanvas(null);
                synchronized (holder) {
                    //일반적으로 행성에 들어갔을 경우 실행되는 화면
                    if (th_run == 1) {
                        alchemist = Bitmap.createScaledBitmap(alchemist, canvas.getWidth() / 2, canvas.getHeight() / 3, true);
                        earth = Bitmap.createScaledBitmap(earth, canvas.getWidth(), canvas.getHeight(), true);
                        canvas.drawBitmap(earth, 0, 0, null);
                        canvas.drawBitmap(alchemist, (canvas.getWidth() - alchemist.getWidth()) / 2, canvas.getHeight() - alchemist.getHeight(), null);

                        canvas.drawText("X좌표: " + spinfo.getChar_location()[0] + " Y좌표: " + spinfo.getChar_location()[1] +
                                " 지역존재: " + spinfo.getMap_Info(3) + " 몬스터 번호: " + spinfo.getMap_Info(4), 0, 80, axis);
                        if (spinfo.getMap_Info(3) == 0) {

                        } else if (spinfo.getMap_Info(3) == 1) {
                            map_monster = BitmapFactory.decodeResource(getResources(), monster.getData(user_zero.getMon_name(spinfo.getMap_Info(4)), 13));
                            map_monster = Bitmap.createScaledBitmap(map_monster, 600, 600, true);
                            canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2, (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                        } else if (spinfo.getMap_Info(3) == 2) {
                            map_monster = BitmapFactory.decodeResource(getResources(), R.drawable.steel);
                            map_monster = Bitmap.createScaledBitmap(map_monster, 300, 300, true);
                            canvas.drawBitmap(map_monster, (canvas.getWidth() - map_monster.getWidth()) / 2, (canvas.getHeight() - map_monster.getHeight()) / 2, null);
                        }

                        if (showtext == 1) {
                            text_info = new Paint();
                            text_info.setTextSize(80);
                            text_info.setColor(Color.BLACK);
                            canvas.drawText("Can't move", canvas.getWidth() / 2, canvas.getHeight() / 2, text_info);
                            showtext = 0;
                        } else if (showtext == 2) {
                            text_info = new Paint();
                            text_info.setTextSize(80);
                            text_info.setColor(Color.BLACK);
                            canvas.drawText(spinfo.getMap_Info(4) + "재료가" + spinfo.getMap_Info(5) + "개 나왔습니다."
                                    , canvas.getWidth() / 2, canvas.getHeight() / 2, text_info);
                            spinfo.increaseElement();
                            showtext = 0;
                        }

                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                        }
                    }
                    //지도가 실행되었을 때 나오는 화면
                    else if (th_run == 2) {
                        map = Bitmap.createScaledBitmap(map, canvas.getWidth(), canvas.getHeight() / 2, true);
                        map_character = Bitmap.createScaledBitmap(map_character, map.getWidth() / 12, map.getHeight() / 10, true);
                        canvas.drawBitmap(map, 0, (canvas.getHeight() - map.getHeight()) / 2, null);
                        canvas.drawBitmap(map_character, map.getWidth() / 10 * (2 * spinfo.getChar_location()[0] + 1) - map_character.getWidth() / 2, (canvas.getHeight() - map.getHeight()) / 2 + map.getHeight() / 8 * (2 * spinfo.getChar_location()[1] + 1), null);
                        text_info = new Paint();
                        text_info.setTextSize(80);
                        text_info.setColor(Color.BLACK);
                        canvas.drawText("Adventure Percent: " + spinfo.getPercent(), 0, (canvas.getHeight()-map.getHeight()) / 2 - 160, text_info);
                        canvas.drawText("Wood: " + spinfo.getElement(0) + " Stone: " + spinfo.getElement(1) + " Metal: " + spinfo.getElement(2), 0, (canvas.getHeight()-map.getHeight()) / 2 - 80, text_info);

                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (spinfo.getMap_Info2(i, j, 0) == 0) {
                                    map_go[i][j] = null;
                                } else {
                                    if (spinfo.getMap_Info2(i, j, 2) == 0) {
                                        map_go[i][j] = BitmapFactory.decodeResource(getResources(), R.drawable.map_cloud);
                                        map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], map.getWidth() / 5, map.getHeight() / 4, true);
                                        canvas.drawBitmap(map_go[i][j], map.getWidth() / 5 * (i), (canvas.getHeight() - map.getHeight()) / 2 + map.getHeight() / 4 * (j), null);
                                    } else {
                                        if (spinfo.getMap_Info2(i, j, 3) == 1) {
                                            map_go[i][j] = BitmapFactory.decodeResource(getResources(), monster.getData(user_zero.getMon_name(spinfo.getMap_Info2(i, j, 4)), 13));
                                            map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], map.getWidth() / 12, map.getHeight() / 10, true);
                                            canvas.drawBitmap(map_go[i][j], map.getWidth() / 10 * (2 * i + 1) - map_go[i][j].getWidth() / 2, (canvas.getHeight() - map.getHeight()) / 2 + map.getHeight() / 8 * (2 * j), null);
                                        } else if (spinfo.getMap_Info2(i, j, 3) == 2) {
                                            map_go[i][j] = BitmapFactory.decodeResource(getResources(), R.drawable.steel);
                                            map_go[i][j] = Bitmap.createScaledBitmap(map_go[i][j], map.getWidth() / 12, map.getHeight() / 10, true);
                                            canvas.drawBitmap(map_go[i][j], map.getWidth() / 10 * (2 * i + 1) - map_go[i][j].getWidth() / 2, (canvas.getHeight() - map.getHeight()) / 2 + map.getHeight() / 8 * (2 * j), null);
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
                    //이동 애니메이션
                    else if (th_run == 3) {
                        alchemist = Bitmap.createScaledBitmap(alchemist, canvas.getWidth() / 2, canvas.getHeight() / 3, true);
                        earth = Bitmap.createScaledBitmap(earth, canvas.getWidth(), canvas.getHeight(), true);
                        canvas.drawBitmap(earth, 0, 0, null);
                        canvas.drawBitmap(alchemist, (canvas.getWidth() - alchemist.getWidth()) / 2, canvas.getHeight() - alchemist.getHeight(), null);

                        for (int i = 0; i < 5; i++) {
                            earth = BitmapFactory.decodeResource(getResources(), R.drawable.moveup1 + i);
                            earth = Bitmap.createScaledBitmap(earth, canvas.getWidth(), canvas.getHeight(), true);
                            canvas.drawBitmap(earth, 0, 0, null);
                            try {
                                sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            sleep(250);
                            th_run = 1;
                        } catch (InterruptedException e) { }
                    }
                }
            }finally{ holder.unlockCanvasAndPost(canvas); }
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
    //터치 이벤트 함수
    public boolean onTouchEvent(MotionEvent event){
        //맵 이동 이벤트
        if(th_run==1){
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
        else if(th_run==2){
            if(event.getAction() == MotionEvent.ACTION_UP){
                th_run = 1;

            }
        }

        return true;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
    //좌표을 계산해서 어느 방향으로 이동할지 아니면 전투를 할지 선택하는 함수
    public void direction(int px, int py, int ax, int ay){
        int dirX, dirY;
        dirX = px - ax;
        dirY = py - ay;
        if(dirX*dirX + dirY*dirY > 80000){
            if(dirY*dirY>dirX*dirX){
                if(dirY<0) {
                    Log.d("move","up");
                    if(!spinfo.move(0)) showtext = 1;
                }
                else{
                    Log.d("move","down");
                    if(!spinfo.move(1)) showtext = 1;
                }
            }
            else{
                if(dirX>0){
                    Log.d("move","right");
                    if(!spinfo.move(2)) showtext = 1;
                }
                else{
                    Log.d("move","left");
                    if(!spinfo.move(3)) showtext = 1;
                }
            }
        }
        else{
            Log.d("fight", "readytofight");
            if (aftx < ((canvas.getWidth() + map_monster.getWidth()) / 2.) && aftx > ((canvas.getWidth() - map_monster.getWidth()) / 2.)) {
                if (afty < ((canvas.getHeight() + map_monster.getHeight()) / 2.) && afty > ((canvas.getHeight() - map_monster.getHeight()) / 2.)) {
                    if(spinfo.getMap_Info(3) == 1) {
                        Log.d("monster", "monsterfight");
                        spinfo.setCatch_monster();
                        //run = false;
                        //context.startActivity(new Intent(context, ReadyFight_two.class));


                    }
                    else if(spinfo.getMap_Info(3) == 2) {
                        showtext = 2;
                    }
                }


            }
        }
    }
}