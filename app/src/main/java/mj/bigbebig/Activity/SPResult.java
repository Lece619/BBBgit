package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;

/**
 * Created by mk on 2017-03-28 - 탐험 결과 창
 */

public class SPResult extends Activity {

    private int[] getElement = new int[4];
    int current_lev;
    private boolean end_run;

    TextView tv_wood;
    TextView tv_stone;
    TextView tv_metal;
    TextView tv_percent;
    TextView tv_exp;
    TextView tv_gold;
    TextView expNum;
    Button btn_level;
    ProgressBar pgb_expBar;
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
        tv_percent = (TextView) findViewById(R.id.percent);
        tv_exp = (TextView) findViewById(R.id.exp);
        tv_gold = (TextView) findViewById(R.id.gold);

        expNum = (TextView) findViewById(R.id.exp_num);
        btn_level = (Button) findViewById(R.id.set_level);
        pgb_expBar = (ProgressBar) findViewById(R.id.expBar);
        pgb_expBar.setMax(user_zero.getExpmax());
        pgb_expBar.setProgress(user_zero.getExp());
        expNum.setText(user_zero.getExp() + " / " + user_zero.getExpmax());

        current_lev = user_zero.getLevel();
        btn_level.setText(user_zero.getLevel() + "");

        tv_wood.setText(getElement[0] + " 개");
        tv_stone.setText(getElement[1] + " 개");
        tv_metal.setText(getElement[2] + " 개");
        tv_percent.setText(getElement[4] + "%");
        tv_exp.setText(getElement[3] + "exp");
        tv_gold.setText("200G");
        end_run = false;

        Thread thread = new Thread(new Runnable() {
            int i;
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                for (i = 0; i < getElement[3]; i++) {
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
                                btn_level.setText("" + user_zero.getLevel());
                            }
                            pgb_expBar.setMax(user_zero.getExpmax());
                            pgb_expBar.setProgress(user_zero.getExp());
                            expNum.setText(user_zero.getExp() + " / " + user_zero.getExpmax());
                            tv_exp.setText((getElement[3] - i) + "exp");

                        }
                    });
                }
            }
        });
        thread.start();
        end_run = true;
    }//터치 이벤트 함수
    public boolean onTouchEvent(MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_UP){
                if(end_run){
                    onDestroy();
                    finish();
                }
            }
        return true;
    }
}
