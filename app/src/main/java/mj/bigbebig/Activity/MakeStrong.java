package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import mj.bigbebig.Class.usermonster;
import mj.bigbebig.R;

import static java.net.InetAddress.getLocalHost;
import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;
import static mj.bigbebig.R.id.metal;
import static mj.bigbebig.R.id.stone;
import static mj.bigbebig.R.id.wood;

/**
 * Created by mk on 2017-02-27
 * Updated by mk on 2017-03-01 - protobox에게 다른 함수 적용 및 protobox가 최대로 성장할때 거리추정에 의해 1단계 도구로 진화하도록 수정
 * Updated by mk on 2017-03-02 - 거리추정 오류 수정
 */

public class MakeStrong extends Activity {

    int mon_num;
    SeekBar[] sb = new SeekBar[3];
    TextView[] tv = new TextView[3];
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_strong);





        Intent it = getIntent();
        mon_num = it.getIntExtra("mon_num", 0);
        sb[0] = (SeekBar) findViewById(wood);
        sb[1] = (SeekBar) findViewById(stone);
        sb[2] = (SeekBar) findViewById(metal);
        tv[0] = (TextView) findViewById(R.id.wood2);
        tv[1] = (TextView) findViewById(R.id.stone2);
        tv[2] = (TextView) findViewById(R.id.metal2);
        iv = (ImageView) findViewById(R.id.monster_img);
        updateMonster();

        sb[0].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv[0].setText(progress + " ");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {} });

        sb[1].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { tv[1].setText(progress + " ");}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {} });

        sb[2].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { tv[2].setText(progress + " ");}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {} });
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_strength:
                monsterStrength();
                updateMonster();
                break;
            case R.id.btn_back:
                startActivityForResult(new Intent(getApplication(), ToolList.class), 0);
                finish();
                break;
        }
    }

    public void updateMonster(){
        TextView tv = (TextView) findViewById(R.id.monster_info);
        tv.setText("이름 : " + user_zero.getMon_name(mon_num)
                + "\n크기 : " + user_zero.getMon_size(mon_num)
                + "\n체력 : " + user_zero.getMon_hp(mon_num)
                + "\n공격 : " + user_zero.getMon_attack(mon_num)
                + "\n방어 : " + user_zero.getMon_armor(mon_num)
                + "\n나무 : " + user_zero.getMon_wood(mon_num)
                + "\n암석 : " + user_zero.getMon_stone(mon_num)
                + "\n금속 : " + user_zero.getMon_metal(mon_num));
        int num;

        if(user_zero.getMon_name(mon_num).equals("protobox")){
            for(int i = 0 ; i < 3; i++){
                num = 30 - (user_zero.getMon_wood(mon_num) + user_zero.getMon_stone(mon_num) + user_zero.getMon_metal(mon_num));
                if(num > user_zero.getElement(i)) sb[i].setMax(user_zero.getElement(i));
                else sb[i].setMax(num);
            }
        }
        else{
            num = monster.getData(user_zero.getMon_name(mon_num), 5) - user_zero.getMon_wood(mon_num);
            if(num < user_zero.getElement(0)) sb[0].setMax(num);
            else sb[0].setMax(user_zero.getElement(0));

            num = monster.getData(user_zero.getMon_name(mon_num), 6) - user_zero.getMon_stone(mon_num);
            if(num < user_zero.getElement(1)) sb[1].setMax(num);
            else sb[1].setMax(user_zero.getElement(1));

            num = monster.getData(user_zero.getMon_name(mon_num), 7) - user_zero.getMon_metal(mon_num);
            if(num < user_zero.getElement(2)) sb[2].setMax(num);
            else sb[2].setMax(user_zero.getElement(2));
            iv.setImageResource(user_zero.getMon_Image(mon_num));
        }

    }

    public void monsterStrength(){
        int ele[] = new int[3];
        int count = 0;
        int grow[] = new int[3];

        count = 0;
        if(user_zero.getMon_name(mon_num).equals("protobox")){
            for(int i=0;i<3;i++){
                count += sb[i].getProgress();
            }
            count += (user_zero.getMon_wood(mon_num) + user_zero.getMon_stone(mon_num) + user_zero.getMon_metal(mon_num));
            if(count > 30){
                Toast.makeText(getApplicationContext(),"최대치를 넘습니다.", Toast.LENGTH_SHORT).show();
            }
            else{
                count = 0;
                for(int i = 0; i < 3; i++){
                    ele[i] = sb[i].getProgress();
                    count += ele[i];
                }
                for(int i = 0; i < 3; i++)
                    grow[i] = count*monster.getData(user_zero.getMon_name(mon_num), i+8);

                user_zero.monList.get(mon_num).setElement(ele, grow);
                for(int i = 0; i < 3; i++){
                    user_zero.setElement(i, -ele[i]);
                }
                if(((user_zero.getMon_wood(mon_num) + user_zero.getMon_stone(mon_num) + user_zero.getMon_metal(mon_num)) == 30)) evolution();
            }
        }
        else{
            for(int i = 0; i < 3; i++){
                ele[i] = sb[i].getProgress();
                count += ele[i];
            }
            for(int i = 0; i < 3; i++)
                grow[i] = count*monster.getData(user_zero.getMon_name(mon_num), i+8);

            user_zero.monList.get(mon_num).setElement(ele, grow);
            for(int i = 0; i < 3; i++){
                user_zero.setElement(i, -ele[i]);
            }
        }
    }

    public void evolution(){
        usermonster userm;
        userm = monster.evolution(user_zero.getMon_wood(mon_num), user_zero.getMon_stone(mon_num), user_zero.getMon_metal(mon_num));
        Toast.makeText(getApplicationContext(), "진화했습니다.", Toast.LENGTH_SHORT).show();
        user_zero.monList.set(mon_num, userm);
    }
}
