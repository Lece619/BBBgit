package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mj.bigbebig.Class.usermonster;
import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by jino on 2017-02-24.
 * Updated by jino on 2017-02-26.
 * Updated by Jino on 2017-02-28
 * Updated by Jino on 2017-03-03.
 */

public class CombineTool_Act extends Activity {
    int j,k;
    Button[] btn=new Button[20];
    Button btn_protect;
    ImageView num1_img,num2_img,combine_mark;
    TextView num1_name,num2_name;
    Animation combineAni;
    usermonster combineResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        Intent get=getIntent();
        j=get.getIntExtra("combine1",0);


        num1_img=(ImageView)findViewById(R.id.img_1);
        num2_img=(ImageView)findViewById(R.id.img_2);
        num1_name=(TextView)findViewById(R.id.num1_name);
        num2_name=(TextView)findViewById(R.id.num2_name);
        combine_mark=(ImageView)findViewById(R.id.combine_cen);
        combine_mark.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            combine_mark.setClipToOutline(true);
        }
        combineAni= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.combine_mark);

        //버튼 설정이 문제가 생길수도 있다.
        for(int i=0;i<20;i++) {
            btn[i] = (Button) findViewById(R.id.mbtn1 + i);
        }
        btn_protect = (Button) findViewById(R.id.btn_protect);
        //이미 지정됨
        btn[j].setBackgroundColor(Color.GRAY);
        btn[j].setClickable(false); //
        num1_name.setText(user_zero.getMon_name(j));
        num1_img.setImageResource(monster.getData(user_zero.getMon_name(j), 13));


        k=-1;
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<20;i++) {
                    if (view.getId() == btn[i].getId()&&i!=j){
                        if(k!=-1) btn[k].setBackgroundColor(Color.BLACK);
                        btn[i].setBackgroundColor(Color.GRAY);
                        num2_name.setText(user_zero.getMon_name(i));
                        k = i;
                        num2_img.setImageResource(monster.getData(user_zero.getMon_name(k), 13));
                        btn[j].setBackgroundColor(Color.GRAY);

                        if(user_zero.getMon_Protect(i)==0) btn_protect.setBackgroundColor(Color.GRAY);
                        else{
                            Toast.makeText(getApplicationContext(),
                                    "잠긴 몬스터는 조합이 불가능 합니다 ", Toast.LENGTH_SHORT).show();
                            btn_protect.setBackgroundColor(Color.BLACK);
                        }
                    }
                }
            }
        };
        for(int i=0;i<user_zero.monList.size(); i++){ btn[i].setOnClickListener(listener); }

        //버튼에 몬스터 이름 부여, 클릭 가능
        for(int i = 0; i < user_zero.monList.size(); i++){
            btn[i].setText(user_zero.getMon_name(i));
            btn[i].setClickable(true);
        }
        //빈 도구함 이름 삭제, 클릭 불가능
        for(int i = user_zero.monList.size(); i < 20; i++){
            btn[i].setText(null);
            btn[i].setClickable(false);
        }
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_doCombin:
                //애니메이션 스타트
                if(k!=-1) {
                    combine_mark.startAnimation(combineAni);
                    combineResult=monster.combine(user_zero.monList.get(j), user_zero.monList.get(k));
                    user_zero.monList.set(j, combineResult);
                    user_zero.monList.remove(k);
                    update();
                }else
                    Toast.makeText(this, "조합할 몬스터를 선택해 주세요", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_protect:
                if(user_zero.getMon_Protect(k)==0) {
                    user_zero.setMon_Protect(k, 1);
                    btn_protect.setBackgroundColor(Color.BLACK);
                }
                else {
                    user_zero.setMon_Protect(k, 0);
                    btn_protect.setBackgroundColor(Color.GRAY);
                }

                break;

            case R.id.btn_back:
                startActivity(new Intent(getApplicationContext(),ToolList.class));
                finish();
                break;
        }
    }

    public void update(){
        for(int i = 0; i < user_zero.monList.size(); i++){
            btn[i].setText(user_zero.getMon_name(i));
            btn[i].setClickable(true);
        }
        //빈 도구함 이름 삭제, 클릭 불가능
        for(int i = user_zero.monList.size(); i < 20; i++){
            btn[i].setText(null);
            btn[i].setClickable(false);
        }
        if(j>k) {
            btn[j].setBackgroundColor(Color.BLACK);
            j -= 1;
        }
        num1_name.setText(user_zero.getMon_name(j));
        num1_img.setImageResource(monster.getData(user_zero.getMon_name(j), 13));
        num2_name.setText(" ");
        num2_img.setImageResource(0);
        btn[k].setBackgroundColor(Color.BLACK);
    }

    /*
        애니메이션 적인 부분 -> 두개도 같이돌면서 작아짐
        조합적 method 조합시 두개의 몬스터 정보를 보내어 합성
        합성시 티어업
        ======================티어에 대한 내용======================
        1. 1티어가 2티어가 될수있는지 ( 형상유지)
        2. 1조합은 무조껀 2티어인지
        ==========================================================
        우선 조합식 생성후 티어확인
     */
}
