package com.game.main;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


public class WorldView extends View{


	public static final int numColumns = 9;
	public static final int numRows = 13;
	public static final int cellWidth = 50;
	public static final int cellHeight = 50;
	
	private Bitmap[][] bitmaps = new Bitmap[numRows][numColumns];
	private Bitmap[] mDrawableArray = new Bitmap[2];
	World world;
	

    
	public WorldView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		Resources r = this.getContext().getResources();
		Drawable drawable = r.getDrawable(R.drawable.greensquare);
        Bitmap bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, 50, 50);
        mDrawableArray[0] = bitmap;
        
		drawable = r.getDrawable(R.drawable.towersquare);
        bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, 50, 50);
        mDrawableArray[1] = bitmap;
        


		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
				canvas = new Canvas(bitmap);
				Drawable tile =r.getDrawable(R.drawable.greensquare);
				tile.setBounds(0, 0, 50, 50);
				tile.draw(canvas);
				bitmaps[i][j] = bitmap;
			}
		}


		    


	}
	public void setWorld(World w){
		this.world = w;
	}
	



	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				canvas.drawBitmap(bitmaps[i][j], j*50,i*50,new Paint());
			}
		}
		
	}

}
