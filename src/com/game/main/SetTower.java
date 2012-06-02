package com.game.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


public class SetTower extends Activity {
    /** Called when the activity is first created. */
	Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_tower);
        
        GridView menu_gridview = (GridView) findViewById(R.id.grid_menu);
        menu_gridview.setAdapter(new ImageAdapter(this));

        
        menu_gridview.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		Toast.makeText(SetTower.this, "test" + position, Toast.LENGTH_SHORT).show();
        	}
        });
        
    }
     
}