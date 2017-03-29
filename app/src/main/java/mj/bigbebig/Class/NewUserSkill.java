package mj.bigbebig.Class;

import static mj.bigbebig.Activity.MonsterLoad.userskilldata;

/**
 * Created by User on 2017-03-29.
 */

public class NewUserSkill {
    public int skillLevel[][]=new int[4][5];
    public String skillInfo[][]= new String[4][5];


    public NewUserSkill(String id){
       skillLevel= userskilldata.getSkillLevel(id);
       skillInfo=  userskilldata.getSkillinfo();
    }

    public void setSkillLevel(int[][] skillLevel) {
        this.skillLevel = skillLevel;
    }

    //스킬 정보보기
    public String getskillInfo(int i,int j){
        return skillInfo[i][j];
    }
}
