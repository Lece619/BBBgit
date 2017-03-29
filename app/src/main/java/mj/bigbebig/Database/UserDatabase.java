package mj.bigbebig.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mj.bigbebig.Class.user;
import mj.bigbebig.Class.usermonster;

import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk92 on 2017-02-20.
 * updated by mk on 2017-02-22
 * updated by mk on 2017-02-22
 * updated by mk on 2017-02-27 - user에서 monList의 get함수를 사용하게 되면서 그에 맞게 데이터베이스 함수도 수정함
 * updated by jk on 2017-02-28 - 초기 아이디 생성시 몬스터 생성 추가
 * updated by jk on 2017-03-04
 */

public class UserDatabase extends SQLiteOpenHelper{
    public UserDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase userData) {
        userData.execSQL("CREATE TABLE USER (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " id STRING NOT NULL, " +
                " passward STRING NOT NULL, "+
                " level INTEGER, "+
                " expmax INTEGER, "+
                " exp INTEGER, "+
                " skillpoint INTEGER, "+
                " story INTEGER, "+
                "wood INTEGER, " +
                "stone INTEGER, " +
                "metal INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //아이디 생성
    public void makeID(String id, String passward){


        SQLiteDatabase userData = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("passward", passward);
        values.put("level", 1);
        values.put("expmax", 100);
        values.put("exp", 0);
        values.put("skillpoint", 10);
        values.put("story", 1);
        values.put("wood", 0);
        values.put("stone", 0);
        values.put("metal",0);
        userData.insert("USER",null, values);
        userData.execSQL("CREATE TABLE " + id + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name STRING, " +
                "size INTEGER, " +
                "hp INTEGER, " +
                "attack INTEGER, " +
                "armor INTEGER, " +
                "wood INTEGER, " +
                "stone INTEGER, " +
                "metal INTEGER, " +
                "protect INTEGER, " + //몬스터 잠금(삭제 불가용)
                "image INTEGER," +
                "tier INTEGER);");
        userData.close();
    }

    //최근 등록된 아이디 출력
    public String getResID(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext()){
            result = cursor.getString(1);
        }
        cursor.close();
        return result;
    }
    //최근 등록된 비밀번호 출력
    public String getResPw(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext()){
            result = cursor.getString(2);
        }
        cursor.close();
        return result;
    }

    //아이디 중복확인
    public boolean checkID(String checkid){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        boolean check=true;

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);

        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(checkid)==true){
                check=false;
            }
        }
        return check;
    }

    //로그인
    public int dologin(String id, String pwd){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM USER", null);
        int confirm = 0;
        while(cs.moveToNext()){
            if(cs.getString(1).equals(id)){
                confirm = 1;
                if(cs.getString(2).equals(pwd)){
                    confirm = 2;
                    return confirm;
                }
            }
        }
        return confirm;
    }

    //데이터 불러오기
    public int loadData(String id, int i){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM USER", null);
        while(cs.moveToNext()){
            if(id.equals(cs.getString(1))){
                db.close();
                return cs.getInt(i);
            }
        }
        db.close();
        return 0;
    }

    //유저 몬스터 정보 불러오기
    public void loadM(user ud){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + ud.getId(), null);
        while(cs.moveToNext()){
            ud.monList.add(new usermonster(cs.getString(1), cs.getInt(2), cs.getInt(3), cs.getInt(4),
                    cs.getInt(5), cs.getInt(6), cs.getInt(7), cs.getInt(8), cs.getInt(9), cs.getInt(10),cs.getInt(11)));
        }
        db.close();

        //처음 로그인시( 몬스터 없을시 ) 모두제공( 개발자 모드)
        if(ud.monList.size()==0){
            String newmon[] = monster.getallName();
                for(String name : newmon) {
                    ud.monList.add(new usermonster(name, monster.getData(name,"size"),monster.getData(name,"hp"),
                            monster.getData(name,"attack"), monster.getData(name,"armor"),
                            monster.getData(name,"wood"), monster.getData(name,"stone"), monster.getData(name,"metal"),
                            0, monster.getData(name,"image"),monster.getData(name,"tier")));
                }
        }
    }

    //데이터 저장하기
    public void saveData(user ud){
        SQLiteDatabase db = getWritableDatabase();

        //유저 정보 갱신
        db.execSQL("UPDATE USER SET level=" + ud.getLevel() +
                ", expmax=" + ud.getExpmax() + ", exp=" + ud.getExp() + ", skillpoint=" + ud.getSkillpoint() + ", story=" + ud.getStory() +
                ", wood=" + ud.getElement(0) + ", stone=" + ud.getElement(1) + ", metal=" + ud.getElement(2) + " WHERE id='" + ud.getId() + "'");

        //몬스터데이터베이스 초기화
        db.execSQL("DROP TABLE " + ud.getId());
        db.execSQL("CREATE TABLE " + ud.getId() + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name STRING, " +
                "size INTEGER, " +
                "hp INTEGER, " +
                "attack INTEGER, " +
                "armor INTEGER, " +
                "wood INTEGER, " +
                "stone INTEGER, " +
                "metal INTEGER, " +
                "protect INTEGER, " +
                "image INTEGER, " +
                "tier INTEGER);");
        //몬스터데이터 저장


        for(int i = 0; i<ud.monList.size(); i++){
            db.execSQL("INSERT INTO " + ud.getId() + " VALUES (" + i
                    + ", '"+ ud.getMon_name(i)
                    + "', "+ ud.getMon_size(i)
                    + ", " + ud.getMon_hp(i)
                    + ", " + ud.getMon_attack(i)
                    + ", " + ud.getMon_armor(i)
                    + ", " + ud.getMon_wood(i)
                    + ", " + ud.getMon_stone(i)
                    + ", " + ud.getMon_metal(i)
                    + ", " + ud.getMon_Protect(i)
                    + ", " + ud.getMon_Image(i)
                    + ", " + ud.getMon_tier(i) + ");");
        }
        db.close();
    }
}

