package mj.bigbebig.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static mj.bigbebig.Activity.MainActivity.user_zero;
import static mj.bigbebig.Activity.MonsterLoad.monster;

/**
 * Created by mk on 2017-03-06 - 전투 정보를 나타낼 클래스 생성 ex)현재 어떤 스킬이 유효한지 현재 진행중인 스킬 목록 cur_skill을 만듦.
 * Updated by jk on 2017-03-06 - 유저 데이터 사용하기 위해 유저데이터 Public 부여
 */

public class Fight_Infomation {
    private List<Cur_Skill> cur_skill = new ArrayList<Cur_Skill>();
    private int cur_turn = 0;
    public Fighting_Monster mine,enemy;

    //생성자
    public Fight_Infomation(int mineMonNum,int enemyMonNum){
        Random random=new Random();
        enemyMonNum=random.nextInt(5)+1;
        enemy=new Fighting_Monster(monster.fightMonster(enemyMonNum));
        mine =new Fighting_Monster(user_zero.monList.get(mineMonNum).fightMonster());
    }


    public class Cur_Skill{
        //어떤 스킬 타입인지, 누구한테 사용되는 스킬인지, 어떤 능력치에 사용되는 스킬인지,
        // 사용 효과는 얼마나 되는지, 스킬의 속도는 얼마인지, 스킬 지속 턴은 얼마나 되는지 나타냄.
        private int skill_type, skill_to, skill_abilityto, skill_ability, skill_speed, skill_turn;

        public Cur_Skill(int skill_type, int skill_to, int skill_abilityto, int skill_ability, int skill_speed, int skill_turn) {
            this.skill_type = skill_type;
            this.skill_to = skill_to;
            this.skill_abilityto = skill_abilityto;
            this.skill_ability = skill_ability;
            this.skill_speed = skill_speed;
            this.skill_turn = skill_turn;
        }
    }

    //턴 진행 상황
    public void turn(){
        for(int i=0;i<cur_skill.size();i++){
            if(cur_skill.get(i).skill_turn > 1){

            }
            else {

            }
        }
        cur_turn++;
    }

    public void MonSkill1(){

    }


}
