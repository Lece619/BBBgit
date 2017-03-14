package mj.bigbebig.Class;

/**
 * Created by mk92 on 2017-02-21.
 * Updated by mk on 2017-02-22
 * Updated by mk on 2017-02-27 - 모든 인자를 private에서 protected로 바꿔서 user class에서 사용가능하도록 함. user에서 인자를 사용할 수 있게 됨에 따라 get과 set함수를 삭제함.
 * Will update by mk - user와 마찬가지로 wood, stone, metal 인자를 배열로 바꿈.
 * Updated by jh on   2017-02-28
 * Updated by jh on 2017-03-06
 */

public class usermonster {
        public usermonster(String name, int size, int hp, int attack, int armor,
                           int wood, int stone, int metal, int protect, int image,int tier){
                this.name = name; this.size = size; this.hp = hp; this.attack = attack; this.armor = armor;
                this.wood = wood; this.stone = stone; this.metal = metal; this.protect = protect; this.image = image;
                this.tier = tier;
        }
        String name;
        protected int size;
        protected int hp;
        protected int attack;
        protected int armor;
        protected int wood;
        protected int stone;
        protected int metal;
        protected int protect;
        protected int image;
        protected int tier;

        public void setElement(int ele[], int grow[]) { wood += ele[0]; stone += ele[1]; metal += ele[2];
                hp += grow[0]; size += grow[0]; attack += grow[1]; armor += grow[2];
        }
        public Object[] fightMonster() {
                Object[] monster = new Object[9];
                monster[0]=name;
                monster[1]=size;
                monster[2]=hp;
                monster[3]=attack;
                monster[4]=armor;
                monster[5]=wood;
                monster[6]=stone;
                monster[7]=metal;
                monster[8]=image;
                return monster;
        }
}
