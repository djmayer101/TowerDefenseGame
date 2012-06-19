package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class TowerDefenseActivity extends Activity{
	private View view;
	private TowerDefenseGame myGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater factory = LayoutInflater.from(this);

		// Set game layout
		view = factory.inflate(R.layout.main, null);
		setContentView(view);

		myGame = (TowerDefenseGame) this.findViewById(R.id.ll_absolute);

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


}
