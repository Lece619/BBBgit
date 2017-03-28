package mj.bigbebig.Class;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import mj.bigbebig.R;

/**
 * Created by mk92 on 2017-03-28.
 */

public class SPcanvas {
    private int showtext = 0;
    private Paint text_info;
    private Bitmap map_monster;
    private Canvas canvas;
    private Bitmap earth;
    private Bitmap alchemist;
    private Bitmap map;
    private Bitmap map_character;
    private Bitmap[][] map_go = new Bitmap[5][4];

    public SPcanvas(Resources res, SPinformation spinfo){
        earth = BitmapFactory.decodeResource(res, R.drawable.earth);
        alchemist = BitmapFactory.decodeResource(res, R.drawable.alchemist3);
        map = BitmapFactory.decodeResource(res, R.drawable.map_info);
        map_character = BitmapFactory.decodeResource(res, R.drawable.map_alchemist);
    }
}
