package com.game.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GameMain extends Activity {
    /** Called when the activity is first created. */
	Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
     
        myButton = (Button) findViewById(R.id.start_new_game_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startNewGame(v);
            }
        });
        
    }
    
    public void startNewGame(View view){
    	Intent i = new Intent(GameMain.this, TowerDefenseActivity.class);
    	startActivity(i);
    }
    
}