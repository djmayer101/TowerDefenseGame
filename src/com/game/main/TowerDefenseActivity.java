package com.game.main;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

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
		
		ImageButton button = (ImageButton)findViewById(R.id.good_tower);

		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Get a reference to the score_name_entry object in score.xml
				//LinearLayout submitScoreLayout = (LinearLayout)findViewById(R.id.placing_tower);
				//submitScoreLayout.removeAllViews();

				// Create new LayoutInflater - this has to be done this way, as you can't directly inflate an XML without creating an inflater object first
				//LayoutInflater inflater = getLayoutInflater();
				//submitScoreLayout.addView(inflater.inflate(R.layout.done_set_tower, null));

				//TODO Disable Gridview so user cannot click on grid
				//start round logic
				
				
				Point newTower = myGame.myWorld.focus;
				
				myGame.myWorld.setTower(new Tower(newTower.x, newTower.y));
				
			}
		});

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
