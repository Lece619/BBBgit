package mj.bigbebig.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;

/**
 * Created by User on 2017-03-07.
 */

public class FightResult extends Activity {

    ImageView fightMonImg;
    ImageView rewardItem[] = new ImageView[5];
    TextView getExp, getGold, expNum;
    ProgressBar expBar;
    int current_lev;
    Button level;
    Button bt_ok;
    //true 일시 승리
    boolean Vic_of_Def;
    int fightMonNum;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_result);

        fightMonNum = getIntent().getIntExtra("fightMonNum", 0);
        Vic_of_Def = getIntent().getBooleanExtra("VicOrDef", true);

        fightMonImg = (ImageView) findViewById(R.id.fightMon_img);
        fightMonImg.setImageResource(user_zero.getMon_Image(fightMonNum));

        getExp = (TextView) findViewById(R.id.exp_text);
        getGold = (TextView) findViewById(R.id.gold_text);
        expBar = (ProgressBar) findViewById(R.id.expBar);
        expNum = (TextView) findViewById(R.id.exp_num);

        expBar.setMax(user_zero.getExpmax());
        expBar.setProgress(user_zero.getExp());
        expNum.setText(user_zero.getExp() + " / " + user_zero.getExpmax());
        level = (Button) findViewById(R.id.set_level);
        current_lev = user_zero.getLevel();
        level.setText(user_zero.getLevel() + "");
        bt_ok = (Button) findViewById(R.id.bnt_ok);
//        for(int i=0;i<5;i++)
        rewardItem[0] = (ImageView) findViewById(R.id.reward_0);
        rewardItem[1] = (ImageView) findViewById(R.id.reward_1);
        rewardItem[2] = (ImageView) findViewById(R.id.reward_2);
        rewardItem[3] = (ImageView) findViewById(R.id.reward_3);
        rewardItem[4] = (ImageView) findViewById(R.id.reward_4);

        //승리시
        if (Vic_of_Def == true) {
            getExp.setText("Exp      80");
            getGold.setText("Gold     150");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                    for (int i = 0; i < 80; i++) {
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

        }// fin.승리

    }

    public void onClick(View view) {

        switch (view.getId()) {
            //선물상자 클릭
            case R.id.resultBox:
                ImageView imageView = (ImageView) findViewById(R.id.resultBox);
                imageView.setVisibility(View.INVISIBLE);
                imageView.setClickable(false);
                Random random = new Random();
                int num = random.nextInt(3);
                String[] source = {"나무", "돌", "철"};
                int num2 = random.nextInt(5) + 1;

                for (int i = 0; i < num2; i++) {
                    rewardItem[i].setVisibility(View.VISIBLE);
                    rewardItem[i].setImageResource(R.drawable.stone);
                }

                Toast.makeText(getApplicationContext(), source[num] + " 이(가) " + num2 + "개 나왔습니다.", Toast.LENGTH_SHORT).show();
                bt_ok.setVisibility(View.VISIBLE);
                user_zero.setElement(num, num2);
                break;
            case R.id.bnt_ok:
                finish();
                break;
        }
    }
}

