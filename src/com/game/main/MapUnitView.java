package com.game.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;




public class MapUnitView extends View {
	private int mTileSize;
	private Resources r;
	private Bitmap bitmap;
	private int mXOffset;
	private int mYOffset;
	private final Paint mPaint = new Paint();

	public MapUnitView(Context context,AttributeSet attrs,int mTileSize,int mXOffset,int mYOffset) {
		super(context,attrs);
		this.mTileSize = mTileSize;
		this.mXOffset = mXOffset;
		this.mYOffset = mXOffset;
		r = this.getContext().getResources();
		loadTile(r.getDrawable(R.drawable.redstar));

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

}


