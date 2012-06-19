package com.game.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class TowerDefenseActivity extends Activity{
	private View view;
	private TowerDefenseGame myGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LayoutInflater factory = LayoutInflater.from(this);

		// Set game layout
		view = factory.inflate(R.layout.main, null);
		setContentView(view);

		myGame = (TowerDefenseGame) this.findViewById(R.id.ll_absolute);
		myGame.setTowerActivity(this);

		// Enable view key events
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);

	}


	@Override
	protected void onStop() {
		super.onStop();
		myGame.halt();
	}

	@Override
	protected void onPause() {
		super.onPause();
		myGame.pause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		myGame.resume();
	}
	
	protected void backToMenu(){
    	Intent i = new Intent(TowerDefenseActivity.this, GameMain.class);
    	startActivity(i);
    	finish();
	}
	
    public void startNewGame(){
    	Intent i = new Intent(TowerDefenseActivity.this, TowerDefenseActivity.class);
    	startActivity(i);
    	finish();
    }


}
