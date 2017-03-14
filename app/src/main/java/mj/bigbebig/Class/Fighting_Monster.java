package mj.bigbebig.Class;

/**
 * Created by JIno on 2017-02-22.
 * Update  by JIno on 2017-02-23.
 * Updated by Jino on 2017-02-28.
 */

public class Fighting_Monster {
    //이름 , 사이즈 , 공 , 방 , 나무 , 돌 , 철
    private String name;
    private int size;
    private int attack;
    private int defence;
    private int wood;
    private int stone;
    private int metal;
    private int HP;
    private int img;

    //생성자
    public Fighting_Monster(Object[] monster1){
        name =  (String)monster1[0];
        size =  (Integer)monster1[1];
        HP    = (Integer)monster1[2];
        attack= (Integer)monster1[3];
        defence=(Integer)monster1[4];
        wood  = (Integer)monster1[5];
        stone = (Integer)monster1[6];
        metal = (Integer)monster1[7];
        img   = (Integer)monster1[8];
        /*img=monster.getData(name,13);*/
    }
    /*          monster[0]=name;
                monster[1]=size;
                monster[2]=hp;
                monster[3]=attack;
                monster[4]=armor;
                monster[5]=wood;
                monster[6]=stone;
                monster[7]=metal;
                monster[8]=image;*/
    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getMetal() {
        return metal;
    }
    public int getHp(){
        return HP;
    }
    public void setHP(int a){
        HP=HP-a;
        if(HP<0){
            HP=0;
        }
    }
    public int getImg(){return img; }
}
