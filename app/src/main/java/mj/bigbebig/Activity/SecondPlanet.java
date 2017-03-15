package mj.bigbebig.Activity;
//2017-02-03

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import mj.bigbebig.R;

public class SecondPlanet extends Activity {

	private FrameLayout fl;
	private SPsurface spsurface;
	private ImageButton mapbtn;
	private ImageView mapimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondplanet);
		fl = (FrameLayout) findViewById(R.id.fl_sp);
		mapbtn = (ImageButton) findViewById(R.id.btn_map);
		mapimg = (ImageView) findViewById(R.id.map);

		spsurface = new SPsurface(this);
		fl.addView(spsurface);
	}
	public void onClick(View v){
		switch(v.getId()){
			case R.id.btn_back:
				spsurface.surfaceDestroyed(spsurface.getHolder());
				onDestroy();
				finish();
				break;
			case R.id.btn_map:
				mapimg.setVisibility(View.VISIBLE);
				mapbtn.setVisibility(View.GONE);
				break;
			case R.id.map:
				mapimg.setVisibility(View.GONE);
				mapbtn.setVisibility(View.VISIBLE);
				break;
		}
	}
}
