package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class SetTower extends Activity {
    /** Called when the activity is first created. */
	Button myButton;
	GameView mGameView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_tower);
        //mGameView = (GameView) findViewById(R.id.tv);
        //mGameView.setTextView((TextView) findViewById(R.id.text));
    }
     
}