package com.game.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;


public class MapUnitView extends View {
	private int mTileSize;
	private Resources r;
	public Bitmap bitmap;
	private int mXOffset;
	private int mYOffset;
	private final Paint mPaint = new Paint();
	private int image_id;

	public MapUnitView(Context context,int mTileSize,int mXOffset,int mYOffset) {
		super(context);
		this.mTileSize = mTileSize;
		this.mXOffset = mXOffset;
		this.mYOffset = mXOffset;
		r = this.getContext().getResources();
		loadTile(r.getDrawable(R.drawable.redstar));
		this.image_id = R.drawable.redstar;
		this.setClickable(true);
		this.setEnabled(true);
		
		
		
	}

	public void loadTile(Drawable tile) {
		bitmap= Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		tile.setBounds(0, 0, mTileSize, mTileSize);
		tile.draw(canvas);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, mXOffset,mYOffset,mPaint);
	}
	

	public int image_id() {
		return image_id;
	}
	
	public void setOnClickListener (View.OnClickListener l){
		super.setOnClickListener(l);
	}
	
	

}


