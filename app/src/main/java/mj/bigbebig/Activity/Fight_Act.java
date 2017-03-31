package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import mj.bigbebig.Class.Fight_Infomation;
import mj.bigbebig.Class.Fighting_Monster;
import mj.bigbebig.R;

import static mj.bigbebig.Activity.MonsterLoad.monster;
import static mj.bigbebig.R.id.enemy_HP;
import static mj.bigbebig.R.id.mine_HP;

/**
 * Created by Jino on 2017-02-21.
 * Updated by Jino on 2017-02-22.
 * Updated by Jino on 2017-02-23.
 * Updated by Jino on 2017-02-24
 * Updated by Jino on 2017-02-26
 * Updated by Jino on 2017-02-28
 * Updated by Jino on 2017-03-01
 * Updated by Jino on 2017-03-03
 */

public class Fight_Act extends Activity {
    //적의 정보
    Fighting_Monster enemy;
    Fighting_Monster mine;

    Fight_Infomation fI;
    int mineMonNum,minesize;
    int enemyMonNum,enemysize;

    Handler handler=new Handler();

    TextView enemy_name,enemy_HP_num,mine_HP_num,mine_name;
    ProgressBar enemy_HPbar,mine_HPbar;
    Button skill1,skill2,run;
    ImageView enemy_image, mine_image,userSkill1,userSkill2,userSkill3;
    Animation attackAnime, skill2Anime;

    int mineMaxHP, enemyMaxHP;

    //진동
    Vibrator vibe;
    boolean Vic_or_Def;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        mineMonNum=getIntent().getIntExtra("MonNum",0);
        enemyMonNum=0;

        fI=new Fight_Infomation(mineMonNum,enemyMonNum);
        enemy=fI.enemy;
        mine =fI.mine;
        /*enemy=new Fighting_Monster(monster.fightMonster(3));
        mine =new Fighting_Monster(user_zero.monList.get(mineMonNum).fightMonster());*/

        //이름설정
        enemy_name=(TextView)findViewById(R.id.enemy_name);
        enemy_name.setText(enemy.getName());
        mine_name =(TextView)findViewById(R.id.mine_name);
        mine_name.setText(mine.getName());

        //이미지 설정, 애니매이션
        enemy_image=(ImageView)findViewById(R.id.enemy_img);
        mine_image=(ImageView)findViewById(R.id.mine_img);
        attackAnime= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.attack);
        skill2Anime= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.skill);
        minesize=300;
        enemysize=(enemy.getSize()*300)/mine.getSize();
        Toast.makeText(this, ""+enemysize+minesize, Toast.LENGTH_SHORT).show();

        enemy_image.setAdjustViewBounds(true);
        mine_image.setAdjustViewBounds(true);
        enemy_image.setMaxHeight(enemysize);enemy_image.setMaxWidth(enemysize);
        mine_image.setMaxWidth(minesize);mine_image.setMaxHeight(minesize);
        enemy_image.setImageResource(enemy.getImg());
        mine_image.setImageResource(monster.getData(mine.getName(),13));


        //HP바 색상 변경, 맥스 HP 설정
        enemy_HPbar=(ProgressBar)findViewById(enemy_HP);
        enemy_HPbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        mine_HPbar=(ProgressBar)findViewById(mine_HP);
        mine_HPbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        enemy_HPbar.setMax(enemy.getHp());
        mine_HPbar.setMax(mine.getHp());
        enemy_HPbar.setProgress(enemy.getHp());
        mine_HPbar.setProgress(mine.getHp());

        //HP 숫자
        enemyMaxHP=enemy.getHp();
        mineMaxHP=mine.getHp();
        enemy_HP_num=(TextView)findViewById(R.id.enemy_HP_num);
        enemy_HP_num.setText(enemy.getHp()+"/"+enemyMaxHP);
        mine_HP_num=(TextView)findViewById(R.id.mine_HP_num);
        mine_HP_num.setText(mine.getHp()+"/"+mineMaxHP);

        //버튼들
        skill1=(Button)findViewById(R.id.mine_skill1);
        skill2=(Button)findViewById(R.id.mine_skill2);
        run=(Button)findViewById(R.id.btn_run);

        //진도
        vibe=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //유저스킬
        userSkill1=(ImageView)findViewById(R.id.btn_userSkill1);
        userSkill2=(ImageView)findViewById(R.id.btn_userSkill2);
        userSkill3=(ImageView)findViewById(R.id.btn_userSkill3);


    }

    public void onClick(View view) {
        /*finish();*/
        Handler enemy_handler=new Handler();
        switch (view.getId()){
            /*공격순서
            * 클릭 -> 버튼 OFF -> 내공격 (움직) 적 HP 진행
            * 적공격 -> 내 HP 진행 -> 내or 상대 HP 0 일때 finish or 진행
            *
            * 진행시-> 버튼ON
            * finish->버튼 도망(확인으로 변경) ,Victiory 출력 ,Clear 출력*/
            //스킬 1 사용 // 공격력 5 라고 생각하고 진행
            case R.id.mine_skill1:

                fI.MonSkill1();

                Thread thread =new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ButtonOnOff(false);
                        try{Thread.sleep(100);} catch (Exception e){}

                        //모든 화면 작업은 핸들러로 동작해야 한다.
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"스킬1",Toast.LENGTH_SHORT).show();
                                mine_image.startAnimation(attackAnime);
                            }
                        });

                        try{Thread.sleep(350);} catch (Exception e){}

                        for(int i=0;i<15;i++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    enemy.setHP(1);
                                    enemy_HP_num.setText(enemy.getHp()+"/"+enemyMaxHP);
                                    enemy_HPbar.setProgress(enemy.getHp());
                                }
                            });
                            try{Thread.sleep(100);} catch (Exception e){}
                        }

                        Random random=new Random();
                        int num=random.nextInt(2);
                        //50퍼센트 확률로 적공격
                        if(num==0&&enemy.getHp()!=0){

                            try{Thread.sleep(350);} catch (Exception e){}
                            vibe.vibrate(100);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"!! 적공격",Toast.LENGTH_SHORT).show();
                                    enemy_image.startAnimation(attackAnime);
                                }
                            });
                            try{Thread.sleep(300);} catch (Exception e){}
                            for(int i=0;i<15;i+=3){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mine.setHP(3);
                                        mine_HP_num.setText(mine.getHp()+"/"+mineMaxHP);
                                        mine_HPbar.setProgress(mine.getHp());
                                    }
                                });
                                try{Thread.sleep(100);} catch (Exception e){}
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                finishTurn();
                            }
                        });

                    }
                });
                thread.start();

                break;

            //스킬 2 사용 // 공격력 10 이라고 생각하고 진행
            case R.id.mine_skill2:

                Thread thread2 =new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ButtonOnOff(false);
                        try{Thread.sleep(100);} catch (Exception e){}

                        //모든 화면 작업은 핸들러로 동작해야 한다.
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"스킬2",Toast.LENGTH_SHORT).show();
                                mine_image.startAnimation(skill2Anime);
                            }
                        });

                        try{Thread.sleep(350);} catch (Exception e){}

                        for(int i=0;i<10;i++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    enemy.setHP(1);
                                    enemy_HP_num.setText(enemy.getHp()+"/"+enemyMaxHP);
                                    enemy_HPbar.setProgress(enemy.getHp());
                                }
                            });
                            try{Thread.sleep(100);} catch (Exception e){}
                        }

                        Random random=new Random();
                        int num=random.nextInt(2);
                        //50퍼센트 확률로 적공격
                        if(num==0&&enemy.getHp()!=0){
                            try{Thread.sleep(550);} catch (Exception e){}
                            vibe.vibrate(100);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"!! 적공격",Toast.LENGTH_SHORT).show();
                                    enemy_image.startAnimation(attackAnime);
                                }
                            });
                            try{Thread.sleep(300);} catch (Exception e){}
                            for(int i=0;i<15;i+=3){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mine.setHP(3);
                                        mine_HP_num.setText(mine.getHp()+"/"+mineMaxHP);
                                        mine_HPbar.setProgress(mine.getHp());
                                    }
                                });
                                try{Thread.sleep(100);} catch (Exception e){}
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                finishTurn();
                            }
                        });

                    }
                });
                thread2.start();

                break;

            //도망
            case R.id.btn_run:
                Intent rtit = getIntent();
                setResult(RESULT_OK, rtit);
                onDestroy();
                finish();
                break;

            case R.id.btn_userSkill1:
                finish();
                break;
        }
    }

    //버튼 On(true) Off(false)
    public void ButtonOnOff(boolean a){


        if(a==true){
            skill1.setClickable(true);
            skill2.setClickable(true);
            run.setClickable(true);
        }
        else{
            skill1.setClickable(false);
            skill2.setClickable(false);
            run.setClickable(false);
        }
    }

    //승리시
    protected void finishTurn(){
        TextView result = (TextView) findViewById(R.id.resultmessage);
        LinearLayout result_Layout=(LinearLayout)findViewById(R.id.result_layout);
        Vic_or_Def=true;

        //터치시 이동
        result_Layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent=new Intent(getApplicationContext(),FightResult.class);
                        intent.putExtra("fightMonNum",mineMonNum);
                        intent.putExtra("VicOrDef",Vic_or_Def);
                        startActivity(intent);
                        finish();
                }
                return true;
            }
        });
        if(enemy.getHp()==0) {
            Vic_or_Def=true;
            vibe.vibrate(200);
            ButtonOnOff(false);
            result_Layout.setVisibility(LinearLayout.VISIBLE);
            result.setVisibility(View.VISIBLE);
            run.setText("확인");
            run.setClickable(true);





            /*Random random = new Random();
            int num = random.nextInt(3);
            String[] source={"나무","돌","철"};
            int num2 = random.nextInt(99) % 4 + 1;

            Toast.makeText(getApplicationContext(), source[num] + " 이(가) " + num2 + "개 나왔습니다.", Toast.LENGTH_SHORT).show();
            user_zero.setElement(num, num2);*/

        }
        else if(mine.getHp()==0){
            Vic_or_Def=false;
            vibe.vibrate(200);
            ButtonOnOff(false);
            result_Layout.setVisibility(View.VISIBLE);
            result.setText("DEFEAT");
            result.setVisibility(View.VISIBLE);
            run.setText("확인");
            run.setClickable(true);
        }
        else{
            ButtonOnOff(true);
        }
        //승리시 필요한 펑션을 넣어주면될듯
        //+ 데이터 베이스 insert 포함
    }

}

