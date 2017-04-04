package mj.bigbebig.Activity;
//2017-02-03

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import mj.bigbebig.Class.SPinformation;
import mj.bigbebig.R;

public class SecondPlanet extends Activity {


	private FrameLayout fl;
	private SPsurface spsurface;
	private SPinformation spinfo;
	private ImageButton mapbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondplanet);
		fl = (FrameLayout) findViewById(R.id.fl_sp);
		mapbtn = (ImageButton) findViewById(R.id.btn_map);
		spinfo = new SPinformation();
		spsurface = new SPsurface(this);
        spsurface.getactivity(this);
		spsurface.getspinfo(spinfo);
		fl.addView(spsurface);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		String str = data.getStringExtra("hello");
		Log.d("fight","requestCode : " + requestCode);
		Log.d("fight","requestCode : " + requestCode);
		Log.d("fight","requestCode : " + requestCode);
		Log.d("fight","resultCode : " + resultCode);
		Log.d("fight","resultCode : " + resultCode);
		Log.d("fight","resultCode : " + resultCode);



		if(resultCode==RESULT_CANCELED){
			Log.d("fight","Not Clear");
			Log.d("fight","Not Clear");
			Log.d("fight","Not Clear");
			Log.d("fight","Not Clear");
			Log.d("fight","Not Clear");
		}
		else if(resultCode==RESULT_OK){
			Log.d("fight","Clear");
			Log.d("fight","Clear");
			Log.d("fight","Clear");
			Log.d("fight","Clear");
			Log.d("fight","Clear");
			spinfo.setCatch_monster();
		}
        spsurface = new SPsurface(this);
        spsurface.getactivity(this);
		spsurface.getspinfo(spinfo);
		fl.addView(spsurface);
		spsurface.run = true;
	}

	public void onClick(View v){
		switch(v.getId()){
			case R.id.btn_back:
				Intent it = new Intent(getApplicationContext(), SPResult.class);
				it.putExtra("complete", spsurface.spinfo.complete());
				spsurface.surfaceDestroyed(spsurface.getHolder());
				onDestroy();
				startActivity(it);
				finish();
				break;
			case R.id.btn_map:
				spsurface.th_run = 2;
				break;
		}
	}
}
