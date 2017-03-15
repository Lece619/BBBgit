package mj.bigbebig.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk on 2017-02-24
 * Updated by mk on 2017-02-27 - user에서 자체적으로 monList를 불러오는 함수를 만듦을써 그에 따른 명령어들을  간편한 방법으로 변경함.
 *                              또한 user의 재료들이 배열로 바뀌면서 재료를 보여줄때 텍스트세팅을 반복문으로 바꿈.
 * Updated by mk on 2017-03-01 - button을 for문을 사용해서 등록함.
 * Updated by mk on 2017-03-02 - 도구 강화 액티비티에서 돌아올때 몬스터 정보를 갱신하도록 수정, 생성된 도구 클릭 바로 가능하게 수정
 * Updated by mk on 2017-03-03 - Monster_Info를 setting하는 함수를 만듦(setMon_Info(int i);), 버튼 아이디를 element로 대체해서 반복문 사용가능하도록 수정.
 */

public class ToolList extends Activity {
    int j = 0;
    final Button[] btn = new Button[20];
    TextView[] tv = new TextView[3];
    Button btn_protect;
    ImageView mon_img;
    TextView tv_mon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_list);

        //개발자모드 ////////////////
        user_zero.setElement(0,300);
        user_zero.setElement(1,300);
        user_zero.setElement(2,300);
        ////////////////////////////

        //버튼 등록
        for(int i = 0; i < 20; i++) btn[i] = (Button) findViewById(R.id.mbtn1 + i);
        btn_protect = (Button) findViewById(R.id.btn_protect);
        mon_img = (ImageView) findViewById(R.id.monster_img);
        tv_mon = (TextView) findViewById(R.id.monster_info);

        updateMonList(); //화면 갱신

        //버튼마다 이미지, 텍스트 변경
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = 0 ; i < user_zero.monList.size(); i++) {
                    if (v.getId() == btn[i].getId()) {
                        setMon_Info(i);
                        btn[j].setBackgroundColor(Color.BLACK);
                        btn[i].setBackgroundColor(Color.GRAY);


                        if(user_zero.getMon_Protect(i)==0) btn_protect.setBackgroundColor(Color.GRAY);
                        else btn_protect.setBackgroundColor(Color.BLACK);
                        j = i;
                    }
                }
            }
        };
        for(int i = 0; i < user_zero.monList.size(); i++){ btn[i].setOnClickListener(listener); }

    }

    //나머지 버튼 작동
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_mktool:
                if(user_zero.monList.size() < 20) {
                    user_zero.makeMonster();
                    Toast.makeText(getApplicationContext(), "도구 갯수는 " + user_zero.monList.size() + "개 입니다.", Toast.LENGTH_SHORT).show();
                    Intent it = getIntent();
                    finish();
                    startActivity(it);
                }
                else Toast.makeText(getApplicationContext(), "도구 보관함 용량을 초과했습니다.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_strength:

                Intent it = new Intent(getApplicationContext(), MakeStrong.class);
                it.putExtra("mon_num",j);
                startActivity(it);
                finish();
                break;

            case R.id.btn_combine:
                if(user_zero.getMon_Protect(j)==1)
                    Toast.makeText(this, "잠긴몬스터는 조합이 불가능 합니다", Toast.LENGTH_SHORT).show();
                else {
                    Intent it2 = new Intent(getApplicationContext(), CombineTool_Act.class);
                    it2.putExtra("combine1", j);
                    startActivity(it2);
                    finish();
                }
                break;

            //도구 삭제하기
            case R.id.btn_remove:
                final String[] confirmRemove = new String[]{"삭  제",
                        "취  소"};
                final AlertDialog.Builder cfRemove=new AlertDialog.Builder(ToolList.this);
                cfRemove.setTitle("도구 삭제");
                cfRemove.setItems(confirmRemove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            if(user_zero.getMon_Protect(j)==0){
                                user_zero.monList.remove(j);
                                updateMonList();
                                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(getApplicationContext(), "삭제가 불가능한 몬스터입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cfRemove.setPositiveButton("닫기",null);
                cfRemove.show();
                break;

            case R.id.btn_protect:
                if(user_zero.getMon_Protect(j)==0) {
                    user_zero.setMon_Protect(j, 1);
                    btn_protect.setBackgroundColor(Color.BLACK);
                }
                else {
                    user_zero.setMon_Protect(j, 0);
                    btn_protect.setBackgroundColor(Color.GRAY);
                }
                break;
        }
    }

    //액티비티를 갱신하는 함수
    public void updateMonList(){
        for(int i=0;i<3;i++) {
            tv[i] = (TextView) findViewById(R.id.element1 + i);
            tv[i].setText(user_zero.getElement(i) + "개");
        }

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
        setMon_Info(0);
        btn[j].setBackgroundColor(Color.BLACK);
        j=0;
        if(user_zero.getMon_Protect(j)==0) btn_protect.setBackgroundColor(Color.GRAY);
        else btn_protect.setBackgroundColor(Color.BLACK);
        btn[j].setBackgroundColor(Color.GRAY);
    }

    //몬스터 정보를 화면에 띄우는 함수
    public void setMon_Info(int i) {
        mon_img.setImageResource(monster.getData(user_zero.getMon_name(i), 13));
        tv_mon.setText("이름 : " + user_zero.getMon_name(i)
                + "\n크기 : " + user_zero.getMon_size(i)
                + "\n크기 : " + user_zero.getMon_hp(i)
                + "\n공격 : " + user_zero.getMon_attack(i)
                + "\n방어 : " + user_zero.getMon_armor(i)
                + "\n나무 : " + user_zero.getMon_wood(i)
                + "\n암석 : " + user_zero.getMon_stone(i)
                + "\n금속 : " + user_zero.getMon_metal(i));
    }
}
