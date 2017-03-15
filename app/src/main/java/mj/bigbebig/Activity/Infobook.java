package mj.bigbebig.Activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import mj.bigbebig.R;

/**
 * Created by Seo on 2017-03-14.
 */
public class Infobook extends Activity{
    ImageView img;
    AnimationDrawable ani;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobook);
        img = (ImageView)findViewById(R.id.infoanim);
        ani = (AnimationDrawable)img.getDrawable();
        ani.setOneShot(true);
    }

}
