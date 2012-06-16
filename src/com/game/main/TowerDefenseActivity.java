package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

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
		
		/*ImageButton towerButton = (ImageButton)findViewById(R.id.good_tower);

		towerButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Point newTowerLocation = myGame.myTerrainMap.getFocus();
				int y = newTowerLocation.y;
				int x = newTowerLocation.x;
				Log.e("button", "x=" + x + " y=" + y);
				myGame.myGameEngine.setTower(new Tower(newTowerLocation));
			}
		});*/
		
		/*ImageButton startRoundButton = (ImageButton)findViewById(R.id.start_round);

		startRoundButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Get a reference to the score_name_entry object in score.xml
				//LinearLayout submitScoreLayout = (LinearLayout)findViewById(R.id.placing_tower);
				//submitScoreLayout.removeAllViews();

				// Create new LayoutInflater - this has to be done this way, as you can't directly inflate an XML without creating an inflater object first
				//LayoutInflater inflater = getLayoutInflater();
				//submitScoreLayout.addView(inflater.inflate(R.layout.done_set_tower, null));

				//TODO Disable Gridview so user cannot click on grid
				//start round logic		

			}
		});*/
	

	}


	@Override
	protected void onStop() {
		super.onStop();
	//	((ArcadeGame)view).halt();
	}

	@Override
	protected void onPause() {
		super.onPause();
		onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		//((ArcadeGame)view).resume();
	}

}
