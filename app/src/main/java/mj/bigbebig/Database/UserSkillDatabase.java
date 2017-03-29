package mj.bigbebig.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mj.bigbebig.Class.user;

/**
 * Created by Jino on 2017-03-28.
 * 스킬 레벨을 저장하는 데이터 베이스 스킬의 정보도 포함예정
 */

public class UserSkillDatabase extends SQLiteOpenHelper {

    String skillInfo[][]=new String[4][5];

    public UserSkillDatabase(Context context){
        super(context,"dbskill",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase userSkillData) {

        //유저의 스킬 레벨 저장하는 테이블
        userSkillData.execSQL("CREATE TABLE USERSKILL1 (id STRING PRIMARY KEY NOT NULL," +
                "skill1 INTEGER NOT NULL," +
                "skill2 INTEGER NOT NULL," +
                "skill3 INTEGER NOT NULL," +
                "skill4 INTEGER NOT NULL," +
                "skill5 INTEGER NOT NULL);");

        userSkillData.execSQL("CREATE TABLE USERSKILL2 (id STRING PRIMARY KEY NOT NULL," +
                "skill1 INTEGER NOT NULL," +
                "skill2 INTEGER NOT NULL," +
                "skill3 INTEGER NOT NULL," +
                "skill4 INTEGER NOT NULL," +
                "skill5 INTEGER NOT NULL);");

        userSkillData.execSQL("CREATE TABLE USERSKILL3 (id STRING PRIMARY KEY NOT NULL," +
                "skill1 INTEGER NOT NULL," +
                "skill2 INTEGER NOT NULL," +
                "skill3 INTEGER NOT NULL," +
                "skill4 INTEGER NOT NULL," +
                "skill5 INTEGER NOT NULL);");

        userSkillData.execSQL("CREATE TABLE USERSKILL4 (id STRING PRIMARY KEY NOT NULL," +
                "skill1 INTEGER NOT NULL," +
                "skill2 INTEGER NOT NULL," +
                "skill3 INTEGER NOT NULL," +
                "skill4 INTEGER NOT NULL," +
                "skill5 INTEGER NOT NULL);");

        // 스킬의 정보가 입력되는 테이블
        // 스킬이름 스킬정보    스킬쿨타임   지속시간     공격속도     능력수치
        //skillName skillINFO CoolTime RunningTime  Speed        Ability
        userSkillData.execSQL("CREATE TABLE SKILL1INFO (skillName TEXT PRIMARY KEY, skillINFO TEXT," +
                "CoolTime INTEGER, RunningTime INTEGER, Speed INTEGER, Ability INTEGER);");
        userSkillData.execSQL("CREATE TABLE SKILL2INFO (skillName TEXT PRIMARY KEY, skillINFO TEXT," +
                "CoolTime INTEGER, RunningTime INTEGER, Speed INTEGER, Ability INTEGER);");
        userSkillData.execSQL("CREATE TABLE SKILL3INFO (skillName TEXT PRIMARY KEY, skillINFO TEXT," +
                "CoolTime INTEGER, RunningTime INTEGER, Speed INTEGER, Ability INTEGER);");
        userSkillData.execSQL("CREATE TABLE SKILL4INFO (skillName TEXT, skillINFO TEXT," +
                "CoolTime INTEGER, RunningTime INTEGER, Speed INTEGER, Ability INTEGER);");


        /*delTable("SKILL4INFO");*/
        Cursor cs = userSkillData.rawQuery("SELECT * FROM SKILL1INFO ", null);
        if (cs.moveToNext()) {
            delTable("SKILL1INFO");delTable("SKILL2INFO");delTable("SKILL3INFO");delTable("SKILL4INFO");
        }
         cs.close();
        Skill1(userSkillData);Skill2(userSkillData);Skill3(userSkillData);Skill4(userSkillData);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void delTable(String tablename){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE TABLE " + tablename );
        onCreate(db);
    }

    // 스킬번호 스킬정보 스킬쿨타임 지속시간 공격속도 능력수치
    public void Skill1(SQLiteDatabase db){
        db.execSQL("INSERT INTO SKILL1INFO VALUES ('재구성1','재구성의 첫번째 스킬이다',   1 , 0 , 3 ,4);");
        db.execSQL("INSERT INTO SKILL1INFO VALUES ('재구성2','재구성의 두번째 스킬이다',   2 , 1 , 23 ,4);");
        db.execSQL("INSERT INTO SKILL1INFO VALUES ('재구성3','재구성의 세번째 스킬이다',   3 , 0 , 43 ,4);");
        db.execSQL("INSERT INTO SKILL1INFO VALUES ('재구성4','재구성의 네번째 스킬이다',   4 , 2 , 63 ,4);");
        db.execSQL("INSERT INTO SKILL1INFO VALUES ('재구성5','재구성의 다섯번째 스킬이다', 5 , 0 , 83 ,4);");
    }

    public void Skill2(SQLiteDatabase db){
        db.execSQL("INSERT INTO SKILL2INFO VALUES ('분해1','분해의 첫번째 스킬이다',   1 , 0 , 3 ,4);");
        db.execSQL("INSERT INTO SKILL2INFO VALUES ('분해2','분해의 두번째 스킬이다',   2 , 1 , 23 ,4);");
        db.execSQL("INSERT INTO SKILL2INFO VALUES ('분해3','분해의 세번째 스킬이다',   3 , 0 , 43 ,4);");
        db.execSQL("INSERT INTO SKILL2INFO VALUES ('분해4','분해의 네번째 스킬이다',   4 , 2 , 63 ,4);");
        db.execSQL("INSERT INTO SKILL2INFO VALUES ('분해5','분해의 다섯번째 스킬이다', 5 , 0 , 83 ,4);");
    }

    public void Skill3(SQLiteDatabase db){
        db.execSQL("INSERT INTO SKILL3INFO VALUES ('회복1','회복의 첫번째 스킬이다',   1 , 0 , 3 ,4);");
        db.execSQL("INSERT INTO SKILL3INFO VALUES ('회복2','회복의 두번째 스킬이다',   2 , 1 , 23 ,4);");
        db.execSQL("INSERT INTO SKILL3INFO VALUES ('회복3','회복의 세번째 스킬이다',   3 , 0 , 43 ,4);");
        db.execSQL("INSERT INTO SKILL3INFO VALUES ('회복4','회복의 네번째 스킬이다',   4 , 2 , 63 ,4);");
        db.execSQL("INSERT INTO SKILL3INFO VALUES ('회복5','회복의 다섯번째 스킬이다', 5 , 0 , 83 ,4);");
    }

    public void Skill4(SQLiteDatabase db){
        db.execSQL("INSERT INTO SKILL4INFO VALUES ('중독1', '중독의 첫번째 스킬이다' ,1, 0 , 3 ,4);");
        db.execSQL("INSERT INTO SKILL4INFO VALUES ('중독2', '중독의 두번째 스킬이다' ,2, 1 , 23 ,4);");
        db.execSQL("INSERT INTO SKILL4INFO VALUES ('중독3', '중독의 세번째 스킬이다' ,3, 0 , 43 ,4);");
        db.execSQL("INSERT INTO SKILL4INFO VALUES ('중독4', '중독의 네번째 스킬이다' ,4, 2 , 63 ,4);");
        db.execSQL("INSERT INTO SKILL4INFO VALUES ('중독5', '중독의 다섯번째 스킬이다',5, 0 , 83 ,4);");
    }

    public String gettest() {
        SQLiteDatabase db = getReadableDatabase();
        String test = null;
        Cursor cs = db.rawQuery("Select*from SKILL4INFO", null);
        while (cs.moveToNext()) {
            test = " " + cs.getString(1);
        }
        if(test!=null)
            test="스킬 데이터 로드";
        db.close();
        cs.close();
        return test;
    }

    //회원가입시 아이디 유저스킬데이터베이스에 등록
    public void makeID_Skill(String id){
        SQLiteDatabase db = getWritableDatabase();
        String[] userskill={"USERSKILL1","USERSKILL2","USERSKILL3","USERSKILL4"};
        for(String USERSKILL:userskill){
            db.execSQL("INSERT INTO "+USERSKILL+" VALUES ('"+id+"',0,0,0,0,0)");
        }
        db.close();
    }

    public int[][] getSkillLevel(String id){
        SQLiteDatabase db = getWritableDatabase();
        String[] skillname= {"USERSKILL1","USERSKILL2","USERSKILL3","USERSKILL4"};
        int[][] skillLev=new int[4][5];
        int i=0;
        Cursor cursor;
        for(String NAME:skillname) {
            cursor = db.rawQuery("Select * FROM "+NAME+
                    " Where id='" + id + "'", null);
            while (cursor.moveToNext()){
                for(int j=0;j<5;j++)
                    skillLev[i][j]=cursor.getInt(j+1);
            }
            i++;
            if(i==4) cursor.close();
        }
        db.close();
        return skillLev;
    }

    public String[][] getSkillinfo(){
        SQLiteDatabase db = getWritableDatabase();
        String[] skillname= {"SKILL1INFO","SKILL2INFO","SKILL3INFO","SKILL4INFO"};
        int i=0;
        Cursor cursor;
        for(String name:skillname) {
            cursor = db.rawQuery("SELECT * FROM "+name, null);
            int j=0;
            while (cursor.moveToNext()){
                    skillInfo[i][j]=cursor.getString(1);
                    j++;
            }
            i++;
            if(i==4) cursor.close();
        }
        db.close();
        return skillInfo;
    }

    //skillName skillINFO CoolTime RunningTime  Speed        Ability
    public String getskilldata(String data,int i,int j){
        String resultdata=" ";
        SQLiteDatabase db=getReadableDatabase();
        String[] skillname= {"SKILL1INFO","SKILL2INFO","SKILL3INFO","SKILL4INFO"};
        Cursor cs=db.rawQuery("SELECT "+data+" FROM "+skillname[i-1],null);
        while(cs.moveToFirst())
            resultdata=cs.getString(0);
        cs.close();
        db.close();
        return resultdata;
    }
    //int 형 데이터들
    public int getskilldata2(String data,int i,int j){
        int resultdata=0;
        SQLiteDatabase db=getReadableDatabase();
        String[] skillname= {"SKILL1INFO","SKILL2INFO","SKILL3INFO","SKILL4INFO"};
        Cursor cs=db.rawQuery("SELECT "+data+" FROM "+skillname[i-1],null);
        int num=0;
        while(cs.moveToNext()) {
            num++;
            if(num==j) resultdata = cs.getInt(0);
        }
        cs.close();
        db.close();
        return resultdata;
    }

    //저장하기
    public void savaSkillData(user ud){
        int[][] skillLev=new int[4][5];
        skillLev = ud.skill.skillLevel;
        SQLiteDatabase db=getWritableDatabase();
        String[] userskill={"USERSKILL1","USERSKILL2","USERSKILL3","USERSKILL4"};
        int i=0;
        for(String USERSKILL:userskill){
            db.execSQL("UPDATE "+USERSKILL+" SET skill1="+skillLev[i][0]+ "," +
                                            " skill2="+skillLev[i][1]+"," +
                                            " skill3="+skillLev[i][2]+"," +
                                            " skill4="+skillLev[i][3]+"," +
                                            " skill5="+skillLev[i][4]+" WHERE id='" + ud.getId() + "'");
        }
        db.close();
    }

}
