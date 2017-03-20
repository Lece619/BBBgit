package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import mj.bigbebig.R;

import static mj.bigbebig.R.id.btn1;
import static mj.bigbebig.R.id.btn2;
import static mj.bigbebig.R.id.btn3;
import static mj.bigbebig.R.id.btn4;

//행성 선택
public class ChoosePlanet extends Activity {
	ImageView[] planets=new ImageView[3];
	Button btn;
	HorizontalScrollView hs;
    Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseplanet);
		hs=(HorizontalScrollView)findViewById(R.id.ScrollView);
		btn=(Button)findViewById(R.id.btn4);
		planets[0]=(ImageView)findViewById(R.id.btn1); //
		planets[1]=(ImageView)findViewById(btn2);	   // 둘다 가능?
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
				finish();
				break;
			case btn2:
				startActivity(new Intent(getApplicationContext(), SecondPlanet.class));
				finish();
				break;
			case btn3:
				startActivity(new Intent(getApplicationContext(), ThirdPlanet.class));
				finish();
				break;
			case btn4:
				finish();
				break;
		}
	}
}