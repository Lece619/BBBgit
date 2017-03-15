package mj.bigbebig.Activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import mj.bigbebig.R;

/**
 * Created by Seo on 2017-03-14.
 */
public class Infobook extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobook);
        ImageView iv = (ImageView)findViewById(R.id.infoanim);

        final AnimationDrawable drawable =
                (AnimationDrawable) iv.getBackground();
        drawable.start();
        drawable.setOneShot(true);

    }
}

