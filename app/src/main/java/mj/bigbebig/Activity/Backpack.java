package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import mj.bigbebig.R;

/**
 * Created by Seo on 2017-03-24.
 */

public class Backpack extends Activity{
    Handler handler = new Handler();
    Button btn_tool, btn_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);
        /*ImageView bookanim = (ImageView)findViewById(R.id.backpackanim);
        final AnimationDrawable drawable =
                (AnimationDrawable) bookanim.getBackground();
        drawable.start();

        btn_tool = (Button)findViewById((R.id.btn_tools));
        btn_back = (Button)findViewById(R.id.btn_back);
        */

        ImageView iv = (ImageView)findViewById(R.id.backpackanim);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(iv);
        GlideDrawableImageViewTarget into = Glide.with(getApplicationContext()).load(R.raw.kyo3).into(imageViewTarget);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{Thread.sleep(3000);}catch(Exception e){}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getApplicationContext()).onStop();
                    }
                });
            }
        });
        thread.start();

    }
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_tools:
                startActivity(new Intent(getApplicationContext(), ToolList.class));
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}