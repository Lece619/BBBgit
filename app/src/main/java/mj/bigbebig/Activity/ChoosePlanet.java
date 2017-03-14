package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mj.bigbebig.R;

//행성 선택
public class ChoosePlanet extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseplanet);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn1:
				startActivity(new Intent(getApplicationContext(), FirstPlanet.class));
				break;
			case R.id.btn2:
				startActivity(new Intent(getApplicationContext(), SecondPlanet.class));
				break;
			case R.id.btn3:
				startActivity(new Intent(getApplicationContext(), ThirdPlanet.class));
				break;
			case R.id.btn4:
				finish();
				break;
		}
	}
}