package com.game.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class GameMain extends Activity {
    /** Called when the activity is first created. */
	Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
       myButton = (Button) findViewById(R.id.startbtn);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startGame(v);
            }
        });
        myButton = (Button) findViewById(R.id.startbasedoffbook);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBookGame(v);
            }

			private void startBookGame(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GameMain.this, TowerDefenseActivity.class);
		    	startActivity(i);
			}
        });
        
    }
    
    public void startGame(View view){
    	myButton.setText("button pushed");
    	
    	Intent i = new Intent(GameMain.this, SetTower.class);
    	startActivity(i);
    }
    
}