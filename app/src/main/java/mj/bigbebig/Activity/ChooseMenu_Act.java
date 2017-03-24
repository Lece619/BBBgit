package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import mj.bigbebig.R;

import static java.net.InetAddress.getLocalHost;

/**
 * Created by Jino on 2017-02-21.
 * Updated by mk on 2017-02-22
 * Updated by mk on 2017-03-01 - skillList를 추가함
 */

public class ChooseMenu_Act extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);
        //배경 이미지 설정
        LinearLayout mainLayout=(LinearLayout)findViewById(R.id.layout_main);
        mainLayout.setBackgroundResource(R.drawable._back1);

        Thread network = new Thread(){
            public void run(){
                try{
                    InetAddress ip = getLocalHost();
                    String serverIP = "192.168.43.17";//ip.getHostAddress();

                    Socket socket = new Socket(serverIP, 5000);

                    InputStream in = socket.getInputStream();
                    DataInputStream dis = new DataInputStream(in);

                    Log.d("Server", "Message: " + dis.readUTF());
                    Log.d("Server", "연결을 종료합니다.");

                    dis.close();
                    socket.close();

                } catch(IOException ie){
                    ie.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        network.start();
    }

    public void onClick(View view) {
        switch (view.getId()){
            //메뉴 페이지 이동
            case R.id.btn_infobook:
                startActivity(new Intent(getApplicationContext(), Infobook.class));
                break;
            //배낭 페이지 이동
            case R.id.btn_backpack:
                startActivity(new Intent(getApplicationContext(), Backpack.class));
                break;
            //행성 페이지 이동
            case R.id.btn_chooseplanet:
                startActivity(new Intent(getApplicationContext(),ChoosePlanet.class));
                break;

            //캐릭터 정보보기
            case R.id.btn_character:
                startActivity(new Intent(getApplicationContext(), CharacterInfo.class));
                break;

            //도구 메뉴 선택하기
            case R.id.btn_tools:
                startActivity(new Intent(getApplicationContext(), ToolList.class));
                break;

            //스킬 메뉴 선택하기
            case R.id.btn_skill:
                startActivity(new Intent(getApplicationContext(), SecondPlanet.class));
                break;

            //메인으로 돌아가기
            case R.id.btn_goMain:
                finish();
                break;
        }
    }
}