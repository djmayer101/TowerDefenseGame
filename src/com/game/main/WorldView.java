package com.game.main;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
		this.world = new World(225,400);
		Resources r = this.getContext().getResources();
		Drawable drawable = r.getDrawable(R.drawable.greensquare);
		Bitmap bitmap = Bitmap.createBitmap(cellWidth, cellHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, cellWidth, cellHeight);
		mDrawableArray[0] = bitmap;

		drawable = r.getDrawable(R.drawable.towersquare);
		bitmap = Bitmap.createBitmap(cellWidth, cellHeight, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, cellWidth, cellHeight);
		mDrawableArray[1] = bitmap;



		/*for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				bitmap = Bitmap.createBitmap(cellWidth, cellHeight, Bitmap.Config.ARGB_8888);
				canvas = new Canvas(bitmap);
				Drawable tile =r.getDrawable(R.drawable.greensquare_blue_border);
				tile.setBounds(0, 0, cellWidth, cellHeight);
				tile.draw(canvas);
				bitmaps[i][j] = bitmap;
			}
		}*/

		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				bitmap = mDrawableArray[world.worldTileGrid[i][j]];
				canvas = new Canvas(bitmap);
				Drawable tile =r.getDrawable(R.drawable.greensquare_blue_border);
				tile.setBounds(0, 0, cellWidth, cellHeight);
				tile.draw(canvas);
				//	bitmaps[i][j] = bitmap;
			}
		}

	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				int bitmapKey = world.worldTileGrid[i][j];
				canvas.drawBitmap(mDrawableArray[bitmapKey], j*cellWidth,i*cellHeight,new Paint());
				//canvas.drawBitmap(bitmaps[i][j], j*cellWidth,i*cellHeight,new Paint());
			}
		}

	}

}
