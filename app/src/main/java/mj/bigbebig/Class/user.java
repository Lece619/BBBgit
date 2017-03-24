package mj.bigbebig.Class;

import java.util.ArrayList;
import java.util.List;

import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk92 on 2017-02-21.
 * updated by mk on 2017-02-22
 * updated by mk on 2017-02-23
 * Updated by mk on 2017-02-27 - wood, stone, metal을 int element 배열하나로 합침. 그리고 monList의 데이터를 불러오는 함수를 만듦.
 * Updated by mk on 2017-03-06 - 경험치 획득 함수 생성.
 */

public class user {

    public user(String id, int level, int expmax, int exp, int skillpoint, int story, int element[], int skill_rank[][]){
        this.id = id; this.level = level; this.expmax = expmax; this.exp = exp; this.skillpoint = skillpoint; this.story = story;
        for(int i = 0 ; i < ele_num; i++){
            this.element[i] = element[i];
        }
        skill = new UserSkill(skill_rank);
    }

    private int ele_num = 3;
    private int element[] = new int[ele_num];
    private String id;
    private int level;
    private int expmax;
    private int exp;
    private int skillpoint;
    private int story;
    private UserSkill skill;
    public List<usermonster> monList = new ArrayList<usermonster>();

    //사용자 정보 get함수
    public String getId() {
        return id;
    }
    public int getLevel() {
        return level;
    }
    public int getExp() { return exp; }
    public int getExpmax() { return expmax; }
    public int getSkillpoint() { return skillpoint; }
    public int getStory() { return story; }
    public int getElement(int i ) {
        return element[i];
    }

    //사용자 정보 set함수
    public void setElement(int i, int ele ) {
        element[i]+=ele;
    }
    public void setSkillpoint(){
        skillpoint--;
    }

    //protobox 생성 함수
    public boolean makeMonster(){
        String name = "protobox";
        if(monList.size() < 20){
            monList.add(new usermonster(name, monster.getData(name,"size"),monster.getData(name,"hp"),
                    monster.getData(name,"attack"), monster.getData(name,"armor"),
                    monster.getData(name,"wood"), monster.getData(name,"stone"), monster.getData(name,"metal"),
                    0, monster.getData(name,"image"),monster.getData(name,"tier")));
            return true;
        }
        else return false;
    }

    //경험치 획득 함수
    public int setLevel(int getexp){
        if(this.level >= 40){
            return 0;
        }
        exp += getexp;
        if(exp>expmax) {
            exp -= expmax;
            level++;
            expmax = (int)(expmax*(2.5-(double)level*0.03));
            skillpoint++;
            return exp;
        }
        return exp;
    }

    //몬스터 정보 get함수
    public String getMon_name(int i) { return monList.get(i).name; }
    public int getMon_size(int i){
        return monList.get(i).size;
    }
    public int getMon_hp(int i) { return monList.get(i).hp; }
    public int getMon_attack(int i){ return monList.get(i).attack; }
    public int getMon_armor(int i){
        return monList.get(i).armor;
    }
    public int getMon_wood(int i){
        return monList.get(i).wood;
    }
    public int getMon_stone(int i){
        return monList.get(i).stone;
    }
    public int getMon_metal(int i){
        return monList.get(i).metal;
    }
    public int getMon_Protect(int i) { return monList.get(i).protect; }
    public int getMon_Image(int i) { return monList.get(i).image; }
    public int getMon_tier(int i){return monList.get(i).tier;}

    //몬스터 정보 set함수
    public void setMon_Protect(int i, int j) { monList.get(i).protect = j; }

    //스킬 정보 get함수
    public String getSkill_Name(int i, int j) { return skill.skill[i].getSkill_Name(j); }
    public String getSkill_Info(int i, int j) { return skill.skill[i].getSkill_Info(j); }
    public int getSkill_Rank(int i, int j) { return skill.skill[i].skillrank[j]; }

    //스킬 정보 set함수
    public void setSkill_Rank(int i, int j){ skill.skill[i].skillrank[j]++; }
}