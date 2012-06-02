package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GameMain extends Activity {
    /** Called when the activity is first created. */
	Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myButton = (Button) findViewById(R.id.startbtn);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
    }
    
    public void startGame(View view){
    	myButton.setText("button pushed");
    }
    
}