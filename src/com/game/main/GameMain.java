package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class GameMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button myButton = (Button) findViewById(R.id.startbtn);
        
    }
}