package mj.bigbebig.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
	public static HorizontalScrollView planetScroll;
	private FrameLayout fl;
	private ChoosePlanet_surface cs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseplanet);
		planetScroll=(HorizontalScrollView)findViewById(R.id.ScrollView);
		btn=(Button)findViewById(R.id.btn4);
		planets[0]=(ImageView)findViewById(R.id.btn1); //
		planets[1]=(ImageView)findViewById(btn2);	   // 둘다 가능?
		planets[2]=(ImageView)findViewById(btn3);
		fl=(FrameLayout)findViewById(R.id.choosePlanet_layout);
		cs=new ChoosePlanet_surface(this);/*
		cs.setLayoutParams(new ViewGroup.LayoutParams(fl.getWidth(),fl.getHeight()));*/
		//fl.addView(cs);  //보류

		/*for(ImageView planet : planets){
			planet.setAdjustViewBounds(true);
			planet.setMaxHeight(400);planet.setMaxWidth(400);
		}*/
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case btn1:
				cs.onOFF=false;
				cs.surfaceDestroyed(cs.getHolder());
				startActivity(new Intent(getApplicationContext(), FirstPlanet.class));

				finish();

				break;
			case btn2:
				cs.onOFF=false;
				cs.surfaceDestroyed(cs.getHolder());
				startActivity(new Intent(getApplicationContext(), SecondPlanet.class));
				finish();
				break;
			case btn3:
				cs.onOFF=false;
				cs.surfaceDestroyed(cs.getHolder());
				startActivity(new Intent(getApplicationContext(), ThirdPlanet.class));
				finish();
				break;
			case btn4:
				cs.onOFF=false;
				cs.surfaceDestroyed(cs.getHolder());
				finish();
				break;
		}
	}
	@Override
	public void onBackPressed(){
		cs.onOFF=false;
		cs.surfaceDestroyed(cs.getHolder());
		super.onBackPressed();
	}

	/*public boolean onTouchEvent(MotionEvent e){
		switch (e.getAction()){
			case MotionEvent.ACTION_DOWN:
				Toast.makeText(ChoosePlanet.this, ""+hs.getScrollY(), Toast.LENGTH_SHORT).show();
				break;
			case MotionEvent.ACTION_UP:
				Toast.makeText(getApplicationContext(), ""+hs.getScrollY(), Toast.LENGTH_SHORT).show();
				break;
		}

		return true;
	}*/
}