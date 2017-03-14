package mj.bigbebig.Activity;
//2017-02-03

import android.app.Activity;
import android.os.Bundle;

public class SecondPlanet extends Activity {

	/*int[][][] mapInfo = new int[5][4][4];
	int[] char_location = new int[2];
	TextView tv_charloca;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SPsurface(getApplicationContext()));

		/*mapdata.getFirstplanet(mapInfo);
		tv_charloca = (TextView) findViewById(R.id.char_location);
		Random random = new Random();
		int x, y;
		do{
			x = random.nextInt(5);
			y = random.nextInt(4);
			if(mapInfo[x][y][0]==1){
				char_location[0] = x;
				char_location[1] = y;
				tv_charloca.setText("x: " + char_location[0] + " y: " + char_location[1]);
			}
		}while(mapInfo[x][y][0]==0);*/
	}
}
