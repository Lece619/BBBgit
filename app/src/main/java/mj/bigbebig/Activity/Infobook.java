package mj.bigbebig.Activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import mj.bigbebig.R;

/**
 * Created by Seo on 2017-03-14.
 */
public class Infobook extends Activity{
    Handler handler = new Handler();
    Button btn_skill,btn_tool,btn_char;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobook);
        ImageView bookanim = (ImageView)findViewById(R.id.infoanim);
        btn_skill   = (Button)findViewById((R.id.btn_skill));
        btn_tool = (Button)findViewById((R.id.btn_tools));
        btn_char = (Button)findViewById(R.id.btn_character);

        final AnimationDrawable drawable =
                (AnimationDrawable) bookanim.getBackground();
        drawable.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{Thread.sleep(3000);}catch(Exception e){}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        drawable.stop();
                        btn_char.setVisibility(View.VISIBLE);
                        btn_tool.setVisibility(View.VISIBLE);
                        btn_skill.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        thread.start();
    }

}

