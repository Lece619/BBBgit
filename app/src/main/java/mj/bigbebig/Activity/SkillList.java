package mj.bigbebig.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;

/**
 * Created by mk on 2017-03-01
 */

public class SkillList extends Activity {

    TextView tv[] = new TextView[2];
    TextView tv_skillinfo;
    ImageView btn[] = new ImageView[5];
    int skill_type = 0;
    int skill_num = 0;
    String[][] skillName;
    int[][] skillLev;
    int[][] skillimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_list);
        Toast.makeText(this, " " + user_zero.skill.getskillInfo(1, 1), Toast.LENGTH_SHORT).show();
        skillName = user_zero.skill.skillInfo;//나중에 겟으로 교체
        skillLev = user_zero.skill.skillLevel;
        skillimage=user_zero.skill.skillimage;


        tv[0] = (TextView) findViewById(R.id.skillpoint);
        tv[1] = (TextView) findViewById(R.id.skillrank);
        tv_skillinfo = (TextView) findViewById(R.id.skill_info);
        for (int i = 0; i < 5; i++) btn[i] = (ImageView) findViewById(R.id.btn_skill1 + i);
        updateAct(0);
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = 0 ; i < 5; i++) {
                    if (v.getId() == btn[i].getId()) {
                        tv_skillinfo.setText(""+user_zero.skill.getskillInfo(skill_type, i));
                        tv[1].setText(skillLev[skill_type][i] + " pt");
                        btn[i].setImageResource(skillimage[skill_type][i]);
                        /*btn[i].setBackgroundColor(Color.BLACK);
                        btn[skill_num].setBackgroundColor(Color.GRAY);*/
                        skill_num = i;
                    }
                }
            }
        };
        for(int i = 0; i < 5; i++){ btn[i].setOnClickListener(listener); }
    }    /*updateAct(0);

        //버튼마다 이미지, 텍스트 변경
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = 0 ; i < 5; i++) {
                    if (v.getId() == btn[i].getId()) {
                        tv_skillinfo.setText(user_zero.getSkill_Info(skill_type, i));
                        tv[1].setText(user_zero.getSkill_Rank(skill_type, i) + " pt");
                        *//*btn[i].setBackgroundColor(Color.BLACK);
                        btn[skill_num].setBackgroundColor(Color.GRAY);*//*
                        skill_num = i;
                    }
                }
            }
        };
        for(int i = 0; i < 5; i++){ btn[i].setOnClickListener(listener); }
    }*/
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_reconstruct:
                skill_type = 0;
                updateAct(skill_type);
                break;
            case R.id.btn_deconstruct:
                skill_type = 1;
                updateAct(skill_type);
                break;
            case R.id.btn_recovery:
                skill_type = 2;
                updateAct(skill_type);
                break;
            case R.id.btn_infection:
                skill_type = 3;
                updateAct(skill_type);
                break;
            case R.id.btn_skilllevelup:
                if(user_zero.getSkillpoint() > 0){
                    if(skill_num == 0){
                        skillLevelup();
                    }
                    else if((skill_num == 1 || skill_num == 2)&&(skillLev[skill_type][0] != 0)){
                        skillLevelup();
                    }
                    else if((skill_num == 3) && (skillLev[skill_type][1] != 0)){
                        skillLevelup();
                    }
                    else if((skill_num == 4) && (skillLev[skill_type][2] != 0)){
                        skillLevelup();
                    }
                    else Toast.makeText(getApplication(), "선행 스킬을 배워야합니다. ", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplication(), "스킬 포인트가 부족합니다. ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    /*public void updateAct(int skill_type){
        tv[0].setText(user_zero.getSkillpoint() + " pt");
        tv[1].setText(user_zero.getSkill_Rank(skill_type, 0) + " pt");
        for(int i=0;i<5;i++){
            btn[i].setText(user_zero.getSkill_Name(skill_type, i));
        }
    }*/
    public void updateAct(int skill_type){
        tv[0].setText(user_zero.getSkillpoint() + " pt");
        tv[1].setText(skillLev[skill_type][0] + " pt");
        for(int i=0;i<5;i++){
            /*btn[i].setText(skillName[skill_type][i]);*/
            btn[i].setImageResource(skillimage[skill_type][i]);
        }
    }
    /*
    public void skillLevelup(){
        user_zero.setSkillpoint();
        user_zero.setSkill_Rank(skill_type, skill_num);
        updateAct(skill_type);
    }*/
    public void skillLevelup() {
        user_zero.setSkillpoint();
        skillLev[skill_type][skill_num]++;
        user_zero.skill.setSkillLevel(skillLev);
        updateAct(skill_type);
    }
}
