package com.game.main;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TouchableGrid extends TableLayout{



	public TouchableGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setStretchAllColumns(false);
		
        for (int i = 0; i < 13; i++){
        	TableRow tr = new TableRow(context);
        	for (int j = 0; j < 9; j++){
        		 ImageView imageView = new ImageView(context);
        	        imageView.setImageResource(R.drawable.clear);
        	        
        			tr.addView(imageView);
        			imageView.setOnClickListener(new OnClickListener(){
        				
        				public void onClick(View arg0) {
        					arg0.setBackgroundResource(R.drawable.clearyellow);
        				}});
        	}
        
        
        	this.addView(tr);
   
        }
		

	}
	
	@Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
    }

}
