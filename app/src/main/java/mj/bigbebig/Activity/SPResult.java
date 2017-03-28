package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;

public class SPResult extends Activity {

    private int[] getElement = new int[4];
    int current_lev;

    TextView tv_wood;
    TextView tv_stone;
    TextView tv_metal;
    TextView expNum;
    Button level;
    ProgressBar expBar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spresult);
        Intent it = getIntent();
        getElement = it.getIntArrayExtra("complete");
        tv_wood = (TextView) findViewById(R.id.wood);
        tv_stone = (TextView) findViewById(R.id.stone);
        tv_metal = (TextView) findViewById(R.id.metal);
        expNum = (TextView) findViewById(R.id.exp_num);
        level = (Button) findViewById(R.id.set_level);
        expBar = (ProgressBar) findViewById(R.id.expBar);
        expBar.setMax(user_zero.getExpmax());
        expBar.setProgress(user_zero.getExp());
        expNum.setText(user_zero.getExp() + " / " + user_zero.getExpmax());

        current_lev = user_zero.getLevel();
        level.setText(user_zero.getLevel() + "");

        tv_wood.setText(getElement[0] + " 개");
        tv_stone.setText(getElement[1] + " 개");
        tv_metal.setText(getElement[2] + " 개");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                for (int i = 0; i < getElement[3]; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    user_zero.setLevel(1);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (current_lev != user_zero.getLevel()) {
                                Toast.makeText(getApplicationContext(), "!축! 레벨업 했습니다 !축!", Toast.LENGTH_SHORT).show();
                                current_lev = user_zero.getLevel();
                                level.setText("" + user_zero.getLevel());
                            }
                            expBar.setMax(user_zero.getExpmax());
                            expBar.setProgress(user_zero.getExp());
                            expNum.setText(user_zero.getExp() + " / " + user_zero.getExpmax());
                        }
                    });
                }
            }
        });
        thread.start();


    }
}
