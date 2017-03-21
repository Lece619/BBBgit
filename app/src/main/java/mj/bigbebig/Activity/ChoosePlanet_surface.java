package mj.bigbebig.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.ChoosePlanet.planetScroll;

/**
 * Created by User on 2017-03-20.
 */

public class ChoosePlanet_surface extends SurfaceView implements SurfaceHolder.Callback {

    private Canvas canvas;
    private SurfaceHolder holder;
    private Resources res;
    private Bitmap planet1,planet2,planet3,back;
    private Boolean i;
    private int count=1;
    private PlanetThread plThread;
    private HorizontalScrollView hs;
    public Boolean onOFF;

    public ChoosePlanet_surface(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        onOFF=true;
    }
/*
    public ChoosePlanet_surface(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder=getHolder();
        holder.addCallback(this);
        onOFF=true;

    }*/

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
       /* setZOrderOnTop(true);*/
        Toast.makeText(getContext(), ""+getWidth(), Toast.LENGTH_SHORT).show();
        res=getResources();
        plThread=new PlanetThread(holder);
        plThread.start();

        hs=(HorizontalScrollView)findViewById(R.id.ScrollView);
        /*holder.setFormat(PixelFormat.TRANSLUCEiNT);*/

        i=true;

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try{
            PlanetThread.interrupted();
        } catch(Exception e){}
    }



    /*public boolean onTouchEvent(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                Toast.makeText(getContext(), ""+getScrollX(), Toast.LENGTH_SHORT).show();
                break;
        }
       *//*if(e.getAction()==MotionEvent.ACTION_DOWN)
           Toast.makeText(getContext(), ""+hs.getScrollX(), Toast.LENGTH_SHORT).show();
*//*
        return true;
    }*/

    class PlanetThread extends Thread{
        SurfaceHolder pholder;
        PlanetThread(SurfaceHolder holder){
            pholder=holder;
        }

        @Override
        public void run() {
            back = BitmapFactory.decodeResource(res, R.drawable._back3);
            back = Bitmap.createScaledBitmap(back, getWidth(), getHeight(), true);
            int reset=0;
            Paint paint=new Paint();
            paint.setTextSize(30);
            paint.setColor(Color.WHITE);

            while(onOFF) {
                canvas = null;
                try {
                    canvas = holder.lockCanvas(null);
                    synchronized (holder) {
                        canvas.drawBitmap(back, 0, 0, null);
                        if(planetScroll.getScrollX()<320) {
                            if(reset==0) {
                                planet1 = BitmapFactory.decodeResource(res, R.drawable.planet_1);
                                reset=1;
                            }
                            planet1 = Bitmap.createScaledBitmap(planet1, 400 - planetScroll.getScrollX(), 400 - planetScroll.getScrollX(), true);
                        }
                        else if(planetScroll.getScrollX()>800&&planetScroll.getScrollX()<1500) {
                            if(reset==0) {
                                planet2 = BitmapFactory.decodeResource(res, R.drawable.planet_2);
                            }
                            planet2=Bitmap.createScaledBitmap(planet2,600-planetScroll.getScrollX()+800,
                                                            600-planetScroll.getScrollX()+800,true);

                        }
                        else if(planetScroll.getScrollX()>2600&&planetScroll.getScrollX()<4000) {
                            if(reset==0) {
                                planet3 = BitmapFactory.decodeResource(res, R.drawable.planet_3);
                            }
                            planet3=Bitmap.createScaledBitmap(planet3,800-planetScroll.getScrollX()+2600,
                                    800-planetScroll.getScrollX()+2600,true);

                        }
                        else reset=0;
                        canvas.drawBitmap(planet1, 200, canvas.getHeight()/2-planet1.getHeight()/2, null);
                        canvas.drawBitmap(planet2, 1200,canvas.getHeight()/2-planet2.getHeight()/2, null);
                        canvas.drawBitmap(planet3, 3000,canvas.getHeight()/2-planet3.getHeight()/2, null);
                        canvas.drawText("df "+planetScroll.getScrollX()+" "+count,600,300,paint);


                    }
                } catch (Exception E) {
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }
                try{sleep(1);}catch (Exception e){}
                count++;

            }
        }
    }
}