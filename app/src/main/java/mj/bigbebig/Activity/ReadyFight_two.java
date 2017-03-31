package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

public class ReadyFight_two extends Activity {

        ImageView readyMonImg;                  //준비중인 몬스터이미지
        TextView[] readyMonInfo=new TextView[7];//0= 공격력 ,1= 방어력, 3=체력 4,5,6= 속성치
        Button[] chooseReadyMon=new Button[20]; //준비할 몬스터 선택 버튼
        ImageView[] chooseReadyMon1=new ImageView[20];
        Button chooseMonsterBtn,chooseSkillBtn;
        LinearLayout chooseMonserLayout,chooseSkillLayout;
        int j,readyMonsterNum;                    //준비된 몬스터 넘버 -> intent 해줄번호 초기 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readyfight);

        chooseMonsterBtn=(Button)findViewById(R.id.chooseMonster_Btn);
        chooseMonserLayout=(LinearLayout)findViewById(R.id.chooseMonster_layout);

        //준비중인 몬스터 정보
        readyMonImg=(ImageView)findViewById(R.id.readyMonsterImg);
        for(int i=0;i<7;i++){
            readyMonInfo[i]=(TextView)findViewById(R.id.readyMonInfo_0+i);
        }
        updateReadyMonster();

        //몬스터 선택
        for(j=0;j<20;j++){
            chooseReadyMon[j]=(Button)findViewById(R.id.readyMonster1+j);
            if(j<user_zero.monList.size()){
                final int m=j;
                chooseReadyMon[j].setVisibility(View.VISIBLE);
                chooseReadyMon[j].setBackgroundResource(monster.getData(user_zero.getMon_name(j), 13));
                chooseReadyMon[j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(int i=0;i<user_zero.monList.size();i++){
                            if(i==m) readyMonsterNum=m;
                        }
                        chooseMonserLayout.setVisibility(LinearLayout.INVISIBLE);

                        updateReadyMonster();
                    }
                });
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.chooseMonster_Btn:
                chooseMonserLayout.setVisibility(LinearLayout.VISIBLE);
                break;

            case R.id.chooseSkill_Btn:
                break;

            case R.id.btn_dofight:

                Intent it = new Intent(getApplicationContext(),Fight_Act.class);
                it.putExtra("MonNum",readyMonsterNum);
                startActivityForResult(it, 0);
                onDestroy();
                finish();
                break;

            case R.id.btn_back:
                Intent rtit = getIntent();
                setResult(RESULT_OK, rtit);
                onDestroy();
                finish();
                break;

        }
    }

    public void updateReadyMonster(){
        /*readyMon=new Fighting_Monster(user_zero.monList.get(readyMonsterNum).fightMonster());*/
        readyMonImg.setImageResource(monster.getData(user_zero.getMon_name(readyMonsterNum), 13));
        readyMonInfo[0].setText("크 기  : "+user_zero.getMon_size(readyMonsterNum));
        readyMonInfo[1].setText("공격력 : "+user_zero.getMon_attack(readyMonsterNum));
        readyMonInfo[2].setText("방어력 : "+user_zero.getMon_armor(readyMonsterNum));
        readyMonInfo[3].setText(" 체 력 : "+user_zero.getMon_armor(readyMonsterNum));
        readyMonInfo[4].setText("나 무 : "+user_zero.getMon_wood(readyMonsterNum));
        readyMonInfo[5].setText("암 석 : "+user_zero.getMon_stone(readyMonsterNum));
        readyMonInfo[6].setText("금 속 : "+user_zero.getMon_metal(readyMonsterNum));
    }
}
