package mj.bigbebig.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk on 2017-03-12 - 맵 정보 데이터 베이스 생성.
 */

public class MapDatabase extends SQLiteOpenHelper {
    private static final String Database_Name = "MAP";
    private static final int Database_Version = 1;

    public MapDatabase(Context context){ super(context, Database_Name, null, Database_Version); }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE FP (_ID INTEGER PRIMARY KEY AUTOINCREMENT, x INTEGER, y INTEGER, move INTEGER, env INTEGER)");
        db.execSQL("CREATE TABLE SP (_ID INTEGER PRIMARY KEY AUTOINCREMENT, x INTEGER, y INTEGER, move INTEGER, enviroment INTEGER)");
        db.execSQL("CREATE TABLE TP (_ID INTEGER PRIMARY KEY AUTOINCREMENT, x INTEGER, y INTEGER, move INTEGER, enviroment INTEGER)");
        firstPlanet(db);
        secondPlanet(db);
        thirdPlanet(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean chkData(Context context) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM FP", null);
        if (cs.moveToFirst()) {
            cs.close();
            return true;
        } else {
            cs.close();
            return false;
        }
    }

    public void delTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE FP");
        db.execSQL("DROP TABLE SP");
        db.execSQL("DROP TABLE TP");
        onCreate(db);
    }

    //행성의 기본적인 고정 데이터를 저장하는 함수
    public void firstPlanet(SQLiteDatabase db){
        db.execSQL("INSERT INTO FP VALUES (0, 0, 0, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (1, 0, 1, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (2, 0, 2, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (3, 0, 3, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (4, 1, 0, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (5, 1, 1, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (6, 1, 2, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (7, 1, 3, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (8, 2, 0, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (9, 2, 1, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (10, 2, 2, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (11, 2, 3, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (12, 3, 0, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (13, 3, 1, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (14, 3, 2, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (15, 3, 3, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (16, 4, 0, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (17, 4, 1, 1, 1);");
        db.execSQL("INSERT INTO FP VALUES (18, 4, 2, 0, 0);");//바다
        db.execSQL("INSERT INTO FP VALUES (19, 4, 3, 0, 0);");//바다
    }
    public void secondPlanet(SQLiteDatabase db){
    }
    public void thirdPlanet(SQLiteDatabase db){
    }

    //행성 정보를 무작위로 생성하는 함수
    public void getFirstplanet(int[][][] randomplanet){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM FP", null);

       while(cs.moveToNext()){
           randomplanet[cs.getInt(1)][cs.getInt(2)][0] = cs.getInt(3);//이동 가능 여부
           randomplanet[cs.getInt(1)][cs.getInt(2)][1] = cs.getInt(4);//환경 요소
           randomplanet[cs.getInt(1)][cs.getInt(2)][2] = 0;

           Random random = new Random();
           int num = random.nextInt(100);
           int num2;
           if(num < 10){
               randomplanet[cs.getInt(1)][cs.getInt(2)][3] = 0;
               randomplanet[cs.getInt(1)][cs.getInt(2)][4] = 0;
               randomplanet[cs.getInt(1)][cs.getInt(2)][5] = 0;
           }
           else if(num < 70){
               num2 = random.nextInt(monster.size());
               randomplanet[cs.getInt(1)][cs.getInt(2)][3] = 1;
               randomplanet[cs.getInt(1)][cs.getInt(2)][4] = num2;
               randomplanet[cs.getInt(1)][cs.getInt(2)][5] = 1;
           }
           else {
               randomplanet[cs.getInt(1)][cs.getInt(2)][3] = 2;
               randomplanet[cs.getInt(1)][cs.getInt(2)][4] = random.nextInt(3) + 1;
               randomplanet[cs.getInt(1)][cs.getInt(2)][5] = random.nextInt(3) + 1;
           }
       }
       db.close();

    }
}
