package mj.bigbebig.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import mj.bigbebig.Database.MapDatabase;
import mj.bigbebig.Database.Monster;
import mj.bigbebig.R;

/**
 * Created by mk on 2017-02-03
 * Updated by jh on 2017-02-22
 */

public class MonsterLoad extends Activity {

    public static Monster monster;
    public static MapDatabase mapdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsterload);

        monster = new Monster(getApplicationContext());
        mapdata = new MapDatabase(getApplicationContext());
        monster.delTable(); //몬스터 데이터베이스 초기화
        mapdata.delTable(); //맵 데이터베이스 초기화
        if(!monster.chkData(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
            if(mapdata.chkData(getApplicationContext())){
                Toast.makeText(getApplicationContext(), "지도 정보가 있습니다.", Toast.LENGTH_SHORT).show();
            }
            //이름 사이즈 체력 공 방 나무 암석 금속 사이즈성장 hp성장 공격력성장 아머성장 이미지 티어
            // 1    2    3    4  5  6   7    8    9         10     11        12    13    14
            //0티어
            monster.insert("protobox", 10, 10, 10, 10, 0, 0, 0, 1, 1, 1, 1, R.drawable.protobox,0);

            //1티어
            monster.insert("woodboard", 50, 50, 50, 60,/**/ 100, 0, 0,/**/ 1, 1, 1, 2, R.drawable.woodboard,1);
            monster.insert("stone100", 50, 50, 50, 60,/**/ 0, 100, 0,/**/ 1, 1, 1, 1, R.drawable.bonobono,1);
            monster.insert("metal100", 50, 50, 50, 60,/**/ 0, 0, 100,/**/ 1, 1, 1, 1, R.drawable.bonobono,1);
            monster.insert("arrow", 50, 50, 70, 40,/**/ 70, 0, 30,/**/ 1, 1, 2, 1, R.drawable.arrow,2);
            monster.insert("pencil", 50, 30, 30, 20,/**/ 70, 30, 0,/**/ 1, 1, 1, 1, R.drawable.pencil,1);
            monster.insert("st70mt30", 50, 50, 70, 40,/**/ 0, 70, 30,/**/ 1, 1, 2, 1, R.drawable.bonobono,1);
            monster.insert("st70wd30", 50, 50, 70, 40,/**/ 30, 70, 0,/**/ 1, 1, 2, 1, R.drawable.bonobono,1);
            monster.insert("mt70wd30", 50, 50, 70, 40,/**/ 30, 0, 70,/**/ 1, 1, 2, 1, R.drawable.bonobono,1);
            monster.insert("mt70st30", 40, 50, 70, 40,/**/ 0, 30, 70,/**/ 1, 1, 2, 1, R.drawable.bonobono,1);

            /*//2티어
            monster.insert("teir2_1st",100,100,100,100,70,30,0,1,1,1,2,R.drawable.bonobono_2,2);
            monster.insert("teir2_2st",100,100,100,100,0,30,70,1,1,1,2,R.drawable.bonobono_2,2);

            //특수
            monster.insert("americano", 80, 40, 30, 60, 40, 40, 20, 1, 1, 1, 1, R.drawable.americano,1);*/
            onDestroy();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "데이터가 있습니다.", Toast.LENGTH_SHORT).show();
            onDestroy();
            finish();
        }
    }
}
