package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class TowerDefenseActivity extends Activity{
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater factory = LayoutInflater.from(this);

		// Set game layout
		view = factory.inflate(R.layout.main, null);
		setContentView(view);

		// Enable view key events
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);

	}


	@Override
	protected void onStop() {
		super.onStop();
		((ArcadeGame)view).halt();
	}

	@Override
	protected void onPause() {
		super.onPause();
		onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		((ArcadeGame)view).resume();
	}

}
