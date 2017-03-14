package mj.bigbebig.Class;

/**
 * Created by mk on 2017-02-27
 * Updated by mk on 2017-03-06 - 각 스킬을 구현하도록 수정중.
 */

public class UserSkill {

    protected Skill[] skill = new Skill[4];
    private int[] reSkill = new int[6];

    public UserSkill(int skill_rank[][]){
        for(int i=0;i<4;i++){
            String skill_name[][]={{"재구성1","재구성2","재구성3","재구성4","재구성5"}, {"분해1","분해2","분해3","분해4","분해5"},
                    {"회복1","회복2","회복3","회복4","회복5"}, {"중독1","중독2","중독3","중독4","중독5"} };
            String skill_info[][]={
                    {"재구성1 스킬 정보","재구성2 스킬 정보","재구성3 스킬 정보","재구성4 스킬 정보","재구성5 스킬 정보"},
                    {"분해1 스킬 정보","분해2 스킬 정보","분해3 스킬 정보","분해4 스킬 정보","분해5 스킬 정보"},
                    {"회복1 스킬 정보","회복2 스킬 정보","회복3 스킬 정보","회복4 스킬 정보","회복5 스킬 정보"},
                    {"중독1 스킬 정보","중독2 스킬 정보","중독3 스킬 정보","중독4 스킬 정보","중독5 스킬 정보"} };
            skill[i] = new Skill(skill_rank[i], skill_name[i], skill_info[i]);
        }
    }

    class Skill{
        protected int[] skillrank = new int[5];
        protected String[][] skill_name = new String[5][2];

        public Skill(int skillrank[], String skill_name[], String Skill_info[]) {
            for(int i = 0; i < 5; i++){
                this.skillrank[i] = skillrank[i];
                this.skill_name[i][0] = skill_name[i];
                this.skill_name[i][1] = Skill_info[i];
            }
        }
        public String getSkill_Name(int i){
            return skill_name[i][0];
        }
        public String getSkill_Info(int i) { return skill_name[i][1]; }
    }
    //강화 스킬 타입은 0번, 분해는 1번, 회복은 2번, 중독은 3번
    //어떤 스킬 타입인지, 누구한테 사용되는 스킬인지, 어떤 능력치에 사용되는 스킬인지,
    // 사용 효과는 얼마나 되는지, 스킬의 속도는 어덯게 되는지, 스킬 지속 턴은 얼마나 되는지 나타냄.
    public int[] skill(int skill_type, int skill_num){
        if(skill_type == 0) {
            switch(skill_num){
                case 0:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10 + 3*(skill[skill_type].skillrank[skill_num]-1); reSkill[4] = 5; reSkill[5] = 3;
                    return reSkill;
                case 1:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 2:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 3:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 4:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
            }
        }
        else if(skill_type == 1) {
            switch(skill_num){
                case 0:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 5; reSkill[5] = 3;
                    return reSkill;
                case 1:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 2:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 3:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 4:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
            }
        }
        else if(skill_type == 2) {
            switch(skill_num){
                case 0:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 5; reSkill[5] = 3;
                    return reSkill;
                case 1:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 2:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 3:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 4:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
            }
        }
        else if(skill_type == 3) {
            switch(skill_num){
                case 0:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 5; reSkill[5] = 3;
                    return reSkill;
                case 1:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 2:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 3:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
                case 4:
                    reSkill[0] = skill_type; reSkill[1] = 0; reSkill[2] = 0; reSkill[3] = 10; reSkill[4] = 3; reSkill[5] = 3;
                    return reSkill;
            }
        }
        return reSkill;
    }
}

