package mj.bigbebig.Activity;
//2017-02-03

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import mj.bigbebig.R;

public class SecondPlanet extends Activity {

	private FrameLayout fl;
	private SPsurface spsurface;
	private ImageButton mapbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondplanet);
		fl = (FrameLayout) findViewById(R.id.fl_sp);
		mapbtn = (ImageButton) findViewById(R.id.btn_map);

		spsurface = new SPsurface(this);
		fl.addView(spsurface);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == Activity.RESULT_OK){
			spsurface.th_run = 1;
		}
		if (resultCode == Activity.RESULT_CANCELED) {
			spsurface.th_run = 1;
		}
	}

	public void onClick(View v){
		switch(v.getId()){
			case R.id.btn_back:
				spsurface.surfaceDestroyed(spsurface.getHolder());
				onDestroy();
				finish();
				break;
			case R.id.btn_map:
				//mapbtn.setVisibility(View.GONE);
				spsurface.th_run = 2;
				break;
		}
	}
}
