package mj.bigbebig.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mj.bigbebig.Class.user;
import mj.bigbebig.Database.UserDatabase;
import mj.bigbebig.R;


/**
 *  updated by mk on 2017-02-22
 *  Updated by mk on 2017-02-27 - user 재료들이 배열로 바뀌면서 생성자도 배열로 바뀜. 고로 user_zero를 생성할 때 생성자에 배열이 들어가게 바꿈.
 *  Updated by jk on 2017-03-05 - onCreate 자동 로그인 추가
 *  Updated by jk on 2017-03-15 - github 연결
 *  Test
 *
 */

public class MainActivity extends Activity {

		static final int LOGIN_OU_PAGE=0;
		static UserDatabase udb; //유저 데이터베이스
		public static user user_zero;
		Button login_out,login;
		Handler handler=new Handler();

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			//배경 이미지 설정
			LinearLayout mainLayout=(LinearLayout)findViewById(R.id.layout_main);
			mainLayout.setBackgroundResource(R.drawable._back1);

			login_out=(Button)findViewById(R.id.btn_Login_out);
			//유저 데이터베이스와 데이터 생성
			udb = new UserDatabase(getApplicationContext(), "users4.db", null, 1);
			int data[] = {0, 0, 0};
			int skill1[] = {0,0,0,0,0};
			int skill2[] = {0,0,0,0,0};
			int skill3[] = {0,0,0,0,0};
			int skill4[] = {0,0,0,0,0};
			int skill5[][] = {skill1, skill2, skill3, skill4};
			user_zero = new user(null,0, 0, 0, 0, 0, data, skill5);
			startActivity(new Intent(getApplicationContext(), MonsterLoad.class)); //몬스터 데이터베이스를 불러오는 액티비티로 넘어감

			//자동 로그인
			Thread thread =new Thread(new Runnable() {
				@Override
				public void run() {
					try{Thread.sleep(1000);}catch (Exception e){}
					handler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "자동 로그인 실행", Toast.LENGTH_SHORT).show();
							login_out.performClick();
						}
					});
					try{Thread.sleep(1000);}catch (Exception e){}
					handler.post(new Runnable() {
						@Override
						public void run() {
							login.performClick();
						}
					});


				}
			});
			thread.start();

		}

	public void onClick(View v){
		switch(v.getId()){
			case R.id.btn1:
				if(login_out.getText().toString().equals("Login")==false)
					startActivity(new Intent(getApplicationContext(), ChooseMenu_Act.class));
				else Toast.makeText(getApplicationContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
				break;

			//저장 버튼
			case R.id.btn2:
				if(login_out.getText().toString().equals("Login")==false){
					udb.saveData(user_zero);
				Toast.makeText(getApplicationContext(), "저장 되었습니다", Toast.LENGTH_SHORT).show();}
				else Toast.makeText(getApplicationContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btn_back:

				finish();
				break;
		}
	}

	public void onClickLogin(View view) {

		switch(view.getId()){
			//로그인 버튼
			case R.id.btn_Login_out:

				//login 일때
				if(login_out.getText().toString().equals("Login")==true) {
			/*showDialog(LOGIN_OUT_PAGE);*/
					//인플레이터 생성
					final LayoutInflater inflater = getLayoutInflater();

					final View loginView = inflater.inflate(R.layout.longin_page, null);
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setView(loginView);

					Button makeID = (Button) loginView.findViewById(R.id.btn_resUser);
					login = (Button) loginView.findViewById(R.id.btn_login);
					Button closePage = (Button) loginView.findViewById(R.id.btn_loninPageClose);
					//버튼을 다이얼로그(로그인) 뷰에서  find해줘야 한다

					final AlertDialog dialog = builder.create();

					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  //타이틀 없애기
					dialog.setCanceledOnTouchOutside(false);               //외부터치 종료 x
					EditText loginId = (EditText) loginView.findViewById(R.id.edit_loginID);
					EditText loginPw = (EditText) loginView.findViewById(R.id.edit_loginPW);
					loginId.setText(udb.getResID());
					loginPw.setText(udb.getResPw());

					//로그인 버튼
					login.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							EditText loginId = (EditText) loginView.findViewById(R.id.edit_loginID);
							EditText loginPw = (EditText) loginView.findViewById(R.id.edit_loginPW);

							if(udb.dologin(loginId.getText().toString(), loginPw.getText().toString())){

								String id = loginId.getText().toString();
								//속성 로드
								int data[] = {udb.loadData(id, 8), udb.loadData(id, 9), udb.loadData(id, 10)};
								int skill1[] = {0,0,0,0,0};
								int skill2[] = {0,0,0,0,0};
								int skill3[] = {0,0,0,0,0};
								int skill4[] = {0,0,0,0,0};
								int skill5[][] = {skill1, skill2, skill3, skill4};
								user_zero = new user(id, udb.loadData(id, 3), udb.loadData(id, 4), udb.loadData(id, 5), udb.loadData(id, 6), udb.loadData(id, 7), data, skill5);
								udb.loadM(user_zero);
								dialog.dismiss();
								login_out.setText("Logout");
								//Toast.makeText(getApplicationContext(), loginId.getText().toString() + "로 로그인", Toast.LENGTH_SHORT).show();
								Toast.makeText(getApplicationContext(), user_zero.monList.size() + "입니다.", Toast.LENGTH_SHORT).show();
							}
							else Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
						}
					});


					//아이디 생성버튼
					makeID.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {


							final LayoutInflater inflater2 = getLayoutInflater();
							final View makeIDView = inflater2.inflate(R.layout.makeid_page, null);
							AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
									builder2.setView(makeIDView);
									final AlertDialog makeIDdialog = builder2.create();

									final EditText makeID = (EditText) makeIDView.findViewById(R.id.edit_makeID);
									final EditText makePW1 = (EditText) makeIDView.findViewById(R.id.edit_makePW1);
									final EditText makePW2 = (EditText) makeIDView.findViewById(R.id.edit_makePW2);


									final Button makeUser = (Button) makeIDView.findViewById(R.id.btn_makeUser);

									//중복 확인 버튼
									Button checkSameID = (Button) makeIDView.findViewById(R.id.check_id);
									checkSameID.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											String ID = makeID.getText().toString();
									if (udb.checkID(ID) == true) {
										makeUser.setEnabled(true);
										Toast.makeText(getApplicationContext(), ID + " 생성가능", Toast.LENGTH_SHORT).show();
									} else {
										makeUser.setEnabled(false);
										Toast.makeText(getApplicationContext(), ID + " 생성불가", Toast.LENGTH_SHORT).show();
									}
								}
							});

							//회원가입 버튼
							makeUser.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View view) {
									if (makeID.getText().toString().equals("") == false &&
											makePW1.getText().toString().equals("") == false &&
											makePW2.getText().toString().equals("") == false) {

										if (makePW1.getText().toString().equals(makePW2.getText().toString()) == true) {


											udb.makeID(makeID.getText().toString(), makePW1.getText().toString());
											Toast.makeText(getApplicationContext(), "아이디 생성완료", Toast.LENGTH_SHORT).show();
											makeIDdialog.dismiss();
										} else
											Toast.makeText(getApplicationContext(), "비밀번호 확인", Toast.LENGTH_SHORT).show();

									} else {
										Toast.makeText(getApplicationContext(), "확인바람\n" + udb.getResID(), Toast.LENGTH_SHORT).show();

									}

								}
							});

							//닫기 버튼
							Button closeMake = (Button) makeIDView.findViewById(R.id.btn_makePageClose);
							closeMake.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View view) {
									makeIDdialog.dismiss();
								}
							});
							makeIDdialog.show();


						}
					});

					//닫기 버튼
					closePage.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							dialog.dismiss();
						}
					});
					dialog.show();


					break;
				}
				//logout 일때
				else{
					login_out.setText("Login");
				}

		}

	}

}