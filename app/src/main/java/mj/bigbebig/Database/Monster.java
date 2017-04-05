package mj.bigbebig.Database;
/**
 * Updated by JIno on 2017-02-22.
 * Updated by mk on 2017-02-24
 * Updated by mk on 2017-03-01 - 몬스터 데이터 베이스 사이즈를 리턴하는 함수 추가 getSize()
 * Updated by mk on 2017-03-02 - getSize()함수를 삭제하고 protobox가 최대 성장했을 경우 진화하는 함수 추가
 * Updated by jk on 2017-03-07 - getallname 함수 추가
 * Updated by mk on 2017-03-12 - 몬스터 갯수를 알려주는 size함수 추가.
 * Updated by jk on 2017-03-13 - Tier 추가  ,필요한 데이터 로드 -요소 검색 추가 getData(string,string) 사용
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mj.bigbebig.Class.usermonster;

import static mj.bigbebig.Activity.MonsterLoad.monster;

public class Monster extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MONSTER";
    private static final int DATABASE_VERSION = 1;
    public Monster(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + DATABASE_NAME + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, size INTEGER, hp INTEGER, attack INTEGER, armor INTEGER" +
                ", wood INTEGER, stone INTEGER, metal INTEGER, " +
                "sizegrow INTEGER, hpgrow INTEGER, attackgrow INTEGER, armorgrow INTEGER, image INTEGER,tier INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
    //이름 사이즈 체력 공 방 나무 암석 금속 사이즈성장 hp성장 공격력성장 아머성장 이미지 티어
    // 1    2    3    4  5  6   7    8    9         10     11        12    13    14
    public void insert(String name, Integer size, Integer hp, Integer attack, Integer armor,
                       Integer wood, Integer stone, Integer metal,
                       Integer sizegrow, Integer hpgrow, Integer attackgrow, Integer armorgrow, Integer image,Integer tier){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("size", size);
        values.put("hp", hp);
        values.put("attack", attack);
        values.put("armor", armor);
        values.put("wood", wood);
        values.put("stone", stone);
        values.put("metal", metal);
        values.put("sizegrow", sizegrow);
        values.put("hpgrow", hpgrow);
        values.put("attackgrow", attackgrow);
        values.put("armorgrow", armorgrow);
        values.put("image", image);
        values.put("tier", tier);
        db.insert(DATABASE_NAME, null, values);
        db.close();
    }

    public void delTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE " + DATABASE_NAME);
        onCreate(db);
    }

    public boolean chkData(Context context) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + DATABASE_NAME, null);
        if (cs.moveToFirst()) {
            cs.close();
            return true;
        } else {
            cs.close();
            return false;
        }
    }

    //필요한 데이터 로드
    public int getData(String name, int i){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + DATABASE_NAME, null);
        int result = 0;
        while(cs.moveToNext()){
            if(name.equals(cs.getString(1))){
                result = cs.getInt(i);
            }
        }
        db.close();
        return result;
    }

    public int getData(int i, int j){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + DATABASE_NAME, null);
        int result = 0;
        while(cs.moveToNext()){
            if(i == cs.getInt(0)){
                result = cs.getInt(j);
            }
        }
        db.close();
        return result;
    }

    //필요한 데이터 로드 -요소 검색
    public int getData(String name,String factor){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT "+factor+" FROM " + DATABASE_NAME+" WHERE name ='"+ name + "'", null);
        int result = 0;
        while(cs.moveToNext()){
                result = cs.getInt(0);

        }
        db.close();
        return result;
    }
    public String[] getallName(){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM "+ DATABASE_NAME,null);
        String[] result=new String[10];
        int i=0;
        while(cs.moveToNext()){
            result[i]=cs.getString(1);
            i++;
            if(i==10) break;
        }
        return result;
    }

    //protobox진화 함수
    public usermonster evolution(int wood, int stone, int metal){
        double range = 10000;
        double count = 0;
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + DATABASE_NAME, null);
        while(cs.moveToNext()){
            if(!cs.getString(1).equals("protobox")){
                count = Math.pow(cs.getInt(6) - (int)((double)wood*10./3), 2) +
                        Math.pow(cs.getInt(7) - (int)((double)stone*10./3), 2) +
                        Math.pow(cs.getInt(8) - (int)((double)metal*10./3), 2);
                if(count < range) {
                    range = count;
                    i = cs.getPosition();
                }
            }
        }
        cs.moveToPosition(i);

        usermonster userm = new usermonster(cs.getString(1), cs.getInt(2), cs.getInt(3), cs.getInt(4), cs.getInt(5),
                                            cs.getInt(6), cs.getInt(7), cs.getInt(8), 0, cs.getInt(13),1);
        db.close();
        return userm;
    }

    //Combine 함수
    public usermonster combine(usermonster A,usermonster B){
        String name ="teir2_1st";

        return new usermonster(name, monster.getData(name,"size"),monster.getData(name,"hp"),
                monster.getData(name,"attack"), monster.getData(name,"armor"),
                monster.getData(name,"wood"), monster.getData(name,"stone"), monster.getData(name,"metal"),
                0, monster.getData(name,"image"),monster.getData(name,"tier"));
    }


    //이름 사이즈 체력 공 방 나무 암석 금속 사이즈성장 hp성장 공격력성장 아머성장 이미지
    // 1    2      3   4  5   6    7    8       9        10      11         12      13
    public Object[] fightMonster(int monNum, int mon_size){
        Object[] monster=new Object[9];
        SQLiteDatabase db = getReadableDatabase();

        String findName="SELECT * FROM " + DATABASE_NAME +" WHERE _ID ='"+ monNum + "'";
        Cursor cs = db.rawQuery(findName,null);

        if(cs.moveToFirst()){
            monster[0]=cs.getString(1);
            monster[1]=cs.getInt(2) + cs.getInt(9)*mon_size;
            monster[2]=cs.getInt(3) + cs.getInt(10)*mon_size;
            monster[3]=cs.getInt(4) + cs.getInt(11)*mon_size;
            monster[4]=cs.getInt(5) + cs.getInt(12)*mon_size;
            monster[5]=cs.getInt(6);
            monster[6]=cs.getInt(7);
            monster[7]=cs.getInt(8);
            monster[8]=cs.getInt(13);
        }
        cs.close();
        db.close();
        return monster;
    }

    public int size(){
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;
        Cursor cs = db.rawQuery("SELECT * FROM " + DATABASE_NAME, null);
        while(cs.moveToNext()){
            count++;
        }
        db.close();
        return count;
    }
}
