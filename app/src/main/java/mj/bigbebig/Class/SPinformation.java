package mj.bigbebig.Class;

import android.util.Log;

import java.util.Random;

import static mj.bigbebig.Activity.MonsterLoad.mapdata;

/**
 * Created by mk92 on 2017-03-28 - 맵에 관련된 정보 및 함수
 */

public class SPinformation {
    private int[][][] mapInfo = new int[5][4][6]; //0은 이동가능여부, 1은 환경, 2는 탐험 여부, 3은 지역 존재, 4는 몬스터 번호, 5는 쌘 정도
    private int[] char_location = new int[2];
    private double percent;
    private int exp;
    private int[] element = {0, 0, 0};

    public SPinformation(){
        percent = 0;
        exp = 0;

        //맵 몬스터 및 재료 배치
        mapdata.getFirstplanet(mapInfo);

        //캐릭터 랜덤 배치
        Random random = new Random();
        int x, y;
        do{
            x = random.nextInt(5);
            y = random.nextInt(4);
            if(mapInfo[x][y][0]==1){
                char_location[0] = x;
                char_location[1] = y;
            }
        }while(mapInfo[x][y][0]==0);
        mapInfo[char_location[0]][char_location[1]][2] = 1;
        setPercent();
    }

    //맵 정보 출력 함수
    public int getMap_Info(int i){ return mapInfo[char_location[0]][char_location[1]][i]; }
    public int getMap_Info2(int i, int j, int k){ return mapInfo[i][j][k]; }
    public int[] getChar_location(){ return char_location; }
    public int getElement(int i){ return element[i]; }

    //맵 탐험률과 관련된 함수
    public double getPercent() { return Math.round(percent*100); }
    private void setPercent() { percent += 1./13.;}

    //재료 습득 함수
    public void increaseElement(){
        exp += getMap_Info(5) * 1;
        element[getMap_Info(4)-1] += getMap_Info(5);
        mapInfo[char_location[0]][char_location[1]][3] = 0;
        mapInfo[char_location[0]][char_location[1]][4] = 0;
        mapInfo[char_location[0]][char_location[1]][5] = 0;
    }

    //몬스터 사냥 추가 함수
    public void setCatch_monster(){
        exp += getMap_Info(5) * 5;
        element[0] += 1;
        mapInfo[char_location[0]][char_location[1]][3] = 0;
        mapInfo[char_location[0]][char_location[1]][4] = 0;
        mapInfo[char_location[0]][char_location[1]][5] = 0;
    }

    //방향 결정 함수에서 움직임이 결정되었을때 움직일 수 있는지 판단 후 실제로 이동하게 하는 함수
    public boolean move(int dir){
        if(dir==0){
            if((char_location[1]!=0) && (mapInfo[char_location[0]][char_location[1]-1][0]!=0)){
                char_location[1]--;
                if(mapInfo[char_location[0]][char_location[1]][2] == 0) setPercent();
                mapInfo[char_location[0]][char_location[1]][2] = 1;
                Log.d("mon_num",getMap_Info(0) + " " + getMap_Info(1) + " " + getMap_Info(2) + " " + getMap_Info(3) + " " + getMap_Info(4) + " " + getMap_Info(5));
                return true;
            }
            else{
                Log.d("move","can't move");
                return false;
            }
        }
        if(dir==1){
            if((char_location[1]!=3) && (mapInfo[char_location[0]][char_location[1]+1][0]!=0)){
                char_location[1]++;
                if(mapInfo[char_location[0]][char_location[1]][2] == 0) setPercent();
                mapInfo[char_location[0]][char_location[1]][2] = 1;
                Log.d("mon_num",getMap_Info(0) + " " + getMap_Info(1) + " " + getMap_Info(2) + " " + getMap_Info(3) + " " + getMap_Info(4) + " " + getMap_Info(5));
                return true;
            }
            else{
                Log.d("move","can't move");
                return false;
            }
        }
        if(dir==2){
            if((char_location[0]!=4) && (mapInfo[char_location[0]+1][char_location[1]][0]!=0)){
                char_location[0]++;
                if(mapInfo[char_location[0]][char_location[1]][2] == 0) setPercent();
                mapInfo[char_location[0]][char_location[1]][2] = 1;
                Log.d("mon_num",getMap_Info(0) + " " + getMap_Info(1) + " " + getMap_Info(2) + " " + getMap_Info(3) + " " + getMap_Info(4) + " " + getMap_Info(5));
                return true;
            }
            else{
                Log.d("move","can't move");
                return false;
            }
        }
        if(dir==3){
            if((char_location[0]!=0) && (mapInfo[char_location[0]-1][char_location[1]][0]!=0)){
                char_location[0]--;
                if(mapInfo[char_location[0]][char_location[1]][2] == 0) setPercent();
                mapInfo[char_location[0]][char_location[1]][2] = 1;
                Log.d("mon_num",getMap_Info(0) + " " + getMap_Info(1) + " " + getMap_Info(2) + " " + getMap_Info(3) + " " + getMap_Info(4) + " " + getMap_Info(5));
                return true;
            }
            else{
                Log.d("move","can't move");
                return false;
            }
        }
        return false;
    }

    //탐험 종료시 전달할 목록들
    public int[] complete(){
        int[] data = new int[5];
        data[0] = element[0];
        data[1] = element[1];
        data[2] = element[2];
        data[3] = exp;
        data[4] = (int)Math.round(percent*100);
        Log.d("complete", data[0] + "  " + data[1] + "  " + data[2] + "  " + data[3] + "  " + data[4]);
        return data;
    }
}
