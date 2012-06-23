package com.game.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class GameMainMenuActivity extends Activity {
	/** Called when the activity is first created. */
	Button myButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main_menu);

		myButton = (Button) findViewById(R.id.start_new_game_button);
		myButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startNewGame(v);
			}
		});

	}

	public void startNewGame(View view){
		Intent i = new Intent(GameMainMenuActivity.this, TowerDefenseActivity.class);
		startActivity(i);
		finish();
	}

}