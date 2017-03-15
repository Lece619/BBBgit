package mj.bigbebig.Activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
    private void setAnimation(Animation animation)
    {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Button a = (Button)findViewById(R.id.btn_character);
                a.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

