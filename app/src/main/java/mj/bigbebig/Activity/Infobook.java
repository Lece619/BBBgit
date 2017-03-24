package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import mj.bigbebig.R;

/**
 * Created by Seo on 2017-03-14.
 */
public class Infobook extends Activity{
    Handler handler = new Handler();
    Button btn_skill,btn_collect,btn_char, btn_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_infobook);

        ImageView bookanim = (ImageView)findViewById(R.id.infoanim);
        btn_skill   = (Button)findViewById((R.id.btn_skill));
        btn_collect = (Button)findViewById((R.id.btn_collect));
        btn_char = (Button)findViewById(R.id.btn_character);
        btn_back = (Button)findViewById(R.id.btn_back);
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
                        btn_collect.setVisibility(View.VISIBLE);
                        btn_skill.setVisibility(View.VISIBLE);
                        btn_back.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        thread.start();
    }
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_character:
                startActivity(new Intent(getApplicationContext(), CharacterInfo.class));
                break;
            case R.id.btn_tools:
                startActivity(new Intent(getApplicationContext(), ToolList.class));
                break;
            case R.id.btn_skill:
                startActivity(new Intent(getApplicationContext(), SkillList.class));
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}

