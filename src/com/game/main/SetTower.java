package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;



public class SetTower extends Activity {
	/** Called when the activity is first created. */
	Button myButton;
	GameView2 mGameView;
	@Override

		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.set_tower);
	        mGameView = (GameView2) findViewById(R.id.tv);
	        //mGameView.setAdapter(new MapUnitAdapter(this,mGameView.mFullMapArray));

	       /* for (int i=0;i<10;i++){
	        	for (int j=0;j<10;j++){
	        		mGameView.mMapUnitViewArray[i][j].setOnClickListener(new View.OnClickListener() {
	    	        	public void onClick(View v) {
	    	        		
	    	        		Toast.makeText(SetTower.this, "test", Toast.LENGTH_SHORT).show();
	    	        	}
	    	        });
	        	}
	        }*/
	        


		Button button = (Button)findViewById(R.id.start_round);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Get a reference to the score_name_entry object in score.xml
				LinearLayout submitScoreLayout = (LinearLayout)findViewById(R.id.placing_tower);
				submitScoreLayout.removeAllViews();

				// Create new LayoutInflater - this has to be done this way, as you can't directly inflate an XML without creating an inflater object first
				LayoutInflater inflater = getLayoutInflater();
				submitScoreLayout.addView(inflater.inflate(R.layout.done_set_tower, null));
			}
		});



	}

}