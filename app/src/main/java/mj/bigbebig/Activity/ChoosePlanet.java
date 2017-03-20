package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import mj.bigbebig.R;

import static mj.bigbebig.R.id.btn1;
import static mj.bigbebig.R.id.btn2;
import static mj.bigbebig.R.id.btn3;

//행성 선택
public class ChoosePlanet extends Activity {
	ImageView[] planets=new ImageView[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseplanet);
		planets[0]=(ImageView)findViewById(btn1);
		planets[1]=(ImageView)findViewById(btn2);
		planets[2]=(ImageView)findViewById(btn3);
		for(ImageView planet : planets){
			planet.setAdjustViewBounds(true);
			planet.setMaxHeight(400);planet.setMaxWidth(400);
		}
			}

	public void onClick(View v) {
		switch (v.getId()) {
			case btn1:
				startActivity(new Intent(getApplicationContext(), FirstPlanet.class));
				break;
			case btn2:
				startActivity(new Intent(getApplicationContext(), SecondPlanet.class));
				break;
			case btn3:
				startActivity(new Intent(getApplicationContext(), ThirdPlanet.class));
				break;
			case R.id.btn4:
				finish();
				break;
		}
	}
}