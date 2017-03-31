package mj.bigbebig.Class;

import static mj.bigbebig.Activity.MonsterLoad.userskilldata;

/**
 * Created by User on 2017-03-29.
 */

public class NewUserSkill {
    public int skillLevel[][]=new int[4][5];
    public String skillInfo[][]= new String[4][5];
    public int skillimage[][]=new int[4][5];
    public skill useSkill[]=new skill[3];


    public NewUserSkill(String id){
       skillLevel= userskilldata.getSkillLevel(id);
       skillInfo=  userskilldata.getSkillinfo();
       skillimage= userskilldata.getSkillImage();
       setUseskill(0,1,2);setUseskill(1,4,5);setUseskill(2,3,2);
    }

    public void setSkillLevel(int[][] skillLevel) {
        this.skillLevel = skillLevel;
    }

    //스킬 정보보기
    public String getskillInfo(int i,int j){
        return skillInfo[i][j];
    }

    public void setUseskill(int useskillnum,int i,int j){
        this.useSkill[useskillnum]=new skill(i,j);
    }


    // 스킬이름 스킬정보    스킬쿨타임   지속시간     공격속도     능력수치  이미지
    public class skill{
       public String name;
       public String info;
       public int lev;
       public int coolTime;
       public int runningTime;
       public int speed;
       public int ability;
       public int image;

        skill(int i,int j){
            this.name=userskilldata.getskilldata("skillName",i,j);
            this.info=skillInfo[i-1][j-1];
            this.lev=skillLevel[i-1][j-1];
            this.coolTime=userskilldata.getskilldata2("CoolTime",i,j);
            this.runningTime=userskilldata.getskilldata2("RunningTime",i,j);
            this.speed=userskilldata.getskilldata2("Speed",i,j);
            this.ability=userskilldata.getskilldata2("Ability",i,j);
            this.image=userskilldata.getskilldata2("Image",i,j);
        }
    }
}
