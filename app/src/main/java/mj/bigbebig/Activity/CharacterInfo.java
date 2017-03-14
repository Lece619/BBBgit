package mj.bigbebig.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mj.bigbebig.R;

import static mj.bigbebig.Activity.MainActivity.user_zero;

public class CharacterInfo extends Activity {

    TextView tv[] = new TextView[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);
        tv[0] = (TextView) findViewById(R.id.characterid);
        tv[1] = (TextView) findViewById(R.id.expmax);
        tv[2] = (TextView) findViewById(R.id.exp);
        tv[3] = (TextView) findViewById(R.id.wood);
        tv[4] = (TextView) findViewById(R.id.stone);
        tv[5] = (TextView) findViewById(R.id.metal);

        tv[0].setText(user_zero.getId());
        tv[1].setText(user_zero.getExpmax() + " ");
        tv[2].setText(user_zero.getExp() + " ");
        tv[3].setText(user_zero.getElement(0) + " ");
        tv[4].setText(user_zero.getElement(1) + " ");
        tv[5].setText(user_zero.getElement(2) + " ");
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_back:
                finish();
        }
    }
}
