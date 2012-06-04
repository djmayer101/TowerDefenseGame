package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;




public class SetTower extends Activity {
	/** Called when the activity is first created. */
	Button myButton;

	public static WorldView worldView;

	GameView mGameView;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		//World world = new World(225,400);
		setContentView(R.layout.set_tower);
		worldView = (WorldView)findViewById(R.id.worldview);

		//worldView.setWorld(world);
		
		ImageButton button = (ImageButton)findViewById(R.id.start_round);

		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Get a reference to the score_name_entry object in score.xml
				LinearLayout submitScoreLayout = (LinearLayout)findViewById(R.id.placing_tower);
				submitScoreLayout.removeAllViews();

				// Create new LayoutInflater - this has to be done this way, as you can't directly inflate an XML without creating an inflater object first
				LayoutInflater inflater = getLayoutInflater();
				submitScoreLayout.addView(inflater.inflate(R.layout.done_set_tower, null));

				//TODO Disable Gridview so user cannot click on grid
				//start round logic
			}
		});


	}



}