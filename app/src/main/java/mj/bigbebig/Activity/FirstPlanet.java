package mj.bigbebig.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Random;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;
/**
 * update by jino 2017-02-21
 * updated by mk on 2017-02-22
 * Updated by mk on 2017-03-03 - 던전 진입시 ReadyFight 액티비티로 넘어가록 수정
 * Updated by mk on 2017-03-06 - 던전 클리어시 경험치 획득 함수 추가
*/
public class FirstPlanet extends Activity {

	Button btn_dg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstplanet);
		btn_dg = (Button) findViewById(R.id.btn_dg);
		registerForContextMenu(btn_dg);
	}
	public void onClick(View v){
		ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
		switch(v.getId()){
			case R.id.btn_Prev:
				vf.showPrevious();
				break;
			case R.id.btn_Next:
				vf.showNext();
				break;
			case R.id.btn_dg:
				final String[] dungeonList=new String[]{"첫번째 던전",
						"두번째 던전",
						"세번째 던전"};
				final AlertDialog.Builder dgList=new AlertDialog.Builder(FirstPlanet.this);
				dgList.setTitle("던전선택");
				dgList.setItems(dungeonList, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						if(i==0){
							startActivity(new Intent(getApplicationContext(), ReadyFight.class));
						}
						else {
							clear();
							int pl = user_zero.getLevel();
							user_zero.setLevel(80);
							if(pl!=user_zero.getLevel()){
								Toast.makeText(getApplicationContext(), "!축! 레벨업 했습니다 !축!", Toast.LENGTH_SHORT).show();
							}
						}
					}
				});
				dgList.setPositiveButton("닫기",null);
				dgList.show();
				break;
			case R.id.btn_mktool:
				if(user_zero.monList.size() < 20)
					user_zero.makeMonster();
				else Toast.makeText(getApplicationContext(), "도구 보관함 용량을 초과했습니다.", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btn_itemls:
				break;
			case R.id.btn_back:
				finish();
				break;
		}
	}

	private int[] clear(){

		Random random = new Random();
		int num=random.nextInt(3)+1;
		int num2=random.nextInt(99)%4+1;
		int[] n = {num, num2};
		Toast.makeText(this, n[0] + "번째 재료가 " + n[1] + "개 나왔습니다.", Toast.LENGTH_SHORT).show();
		user_zero.setElement(n[0]-1, n[1]);
		return n;
	}
}

