package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;



public class SetTower extends Activity {
	/** Called when the activity is first created. */
	Button myButton;

	GridView mGameView;
	int gameViewNumColumns = 10;
	private int columnWidth;
	private int gameViewHeight;
	private int gameViewWidth = 300;
	private int numSquares = 160;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.set_tower);
        mGameView = (GridView) findViewById(R.id.tv);

        gameViewHeight = mGameView.getHeight();
        //gameViewWidth = mGameView.getWidth();
        mGameView.setNumColumns(this.gameViewNumColumns);
        columnWidth =(int) (this.gameViewWidth*1.0/this.gameViewNumColumns);
        mGameView.setColumnWidth(columnWidth);
        mGameView.setAdapter(new MapUnitImageAdapter(this,this.columnWidth,this.numSquares));
        
        mGameView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		
        		Toast.makeText(SetTower.this, "test" + position, Toast.LENGTH_SHORT).show();
        	}
        });
       
        
        Button button = (Button)findViewById(R.id.submit_score);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Get a reference to the score_name_entry object in score.xml
				LinearLayout submitScoreLayout = (LinearLayout)findViewById(R.id.score_name_entry);
				submitScoreLayout.removeAllViews();

				// Create new LayoutInflater - this has to be done this way, as you can't directly inflate an XML without creating an inflater object first
				LayoutInflater inflater = getLayoutInflater();
				submitScoreLayout.addView(inflater.inflate(R.layout.done_set_tower, null));
			}
		});
        

    }
     

}