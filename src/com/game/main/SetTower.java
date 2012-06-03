package com.game.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;



public class SetTower extends Activity {
	/** Called when the activity is first created. */
	Button myButton;


	GameView mGameView;
	int gameViewNumColumns = 10;
	private int columnWidth;
	private int gameViewHeight;
	private int gameViewWidth = 400;
	private int numSquares = 100;


	private ImageView redStarView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.set_tower);
        mGameView = (GameView) findViewById(R.id.tv);


        gameViewHeight = mGameView.getHeight();
        mGameView.setNumColumns(this.gameViewNumColumns);
        columnWidth =(int) (this.gameViewWidth*1.0/this.gameViewNumColumns);
        mGameView.setColumnWidth(columnWidth);
        mGameView.mTileSize = columnWidth;
        mGameView.setAdapter(new MapUnitImageAdapter(this,this.columnWidth,this.numSquares));
        
        mGameView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		ImageView myImageView = (ImageView) v;
        		myImageView.setImageResource(R.drawable.yellowsquare);
                int[] location = new int[2]; 
                v.getLocationOnScreen(location);
                Log.v("testing", "my location is: " + location[0] +" " + location[1]);
        		Toast.makeText(SetTower.this, "test " + location[0] +" " +  location[1], Toast.LENGTH_SHORT).show();
        	}
        });
        
        TableLayout mGameTableLayout = (TableLayout) findViewById(R.id.gametablelayout);

        TableRow tr = new TableRow(this);
        ImageView imageView = new ImageView(mGameTableLayout.getContext());
        //imageView.getLayoutParams().height = 50;
        //imageView.setLayoutParams(new TableLayout.LayoutParams(400, 400));
        imageView.setImageResource(R.drawable.clearyellow);
		tr.addView(imageView);
		imageView.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				int id = arg0.getId();
				Toast.makeText(SetTower.this,"pressd"+ id, Toast.LENGTH_SHORT).show();
			}});
		
 
      
       mGameTableLayout.addView(tr);
     

 
        
      
 
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
	private Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}
     

}