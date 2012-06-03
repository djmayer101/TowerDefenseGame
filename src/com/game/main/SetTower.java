package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
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
        
        ViewGroup layout = (ViewGroup) findViewById(R.layout.set_tower);
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
       
        
     
        GridView menu_gridview = (GridView) findViewById(R.id.grid_menu);
        menu_gridview.setAdapter(new ImageAdapter(this));
        menu_gridview.setStretchMode(0);

        

        
        menu_gridview.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		Toast.makeText(SetTower.this, "test" + position, Toast.LENGTH_SHORT).show();
        	}
        });
        

    }
     
}