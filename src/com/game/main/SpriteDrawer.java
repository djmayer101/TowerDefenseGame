package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class SpriteDrawer {
	private Bitmap towerImage;
	private Bitmap enemyImage;
	private Bitmap cannonBallImage;
	private Bitmap cannonBallExplosionImage;
	private Bitmap cursorImage;
	
	private Paint mBitmapPaint = new Paint();
	private Context context;

	public SpriteDrawer(Context context){
		this.context = context;
		towerImage = getImage(R.drawable.awesome_castle);
		towerImage = towerImage.createScaledBitmap( towerImage, Constants.OBJECT_CELL_SIDE_LENGTH, Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		enemyImage = getImage(R.drawable.awesome_castle);
		enemyImage = enemyImage.createScaledBitmap(enemyImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		cannonBallImage = getImage(R.drawable.cannonball);
		cannonBallImage = cannonBallImage.createScaledBitmap(cannonBallImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		cannonBallExplosionImage = getImage(R.drawable.cannonball_explosion);
		cannonBallExplosionImage = cannonBallExplosionImage.createScaledBitmap(cannonBallExplosionImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		cursorImage = getImage(R.drawable.clearyellow);
		cursorImage = cursorImage.createScaledBitmap(cursorImage, Constants.CURSOR_CELL_SIDE_LENGTH, Constants.CURSOR_CELL_SIDE_LENGTH, false);
	}

	public void drawCursor(Canvas canvas, Point location){
		canvas.drawBitmap(cursorImage,location.x,location.y, mBitmapPaint);
	}
	
	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	public void drawTower(Canvas canvas, Point location) {
		canvas.drawBitmap(towerImage, location.x, location.y, mBitmapPaint);
	}

	public void drawEnemy(Canvas canvas, Point location) {
		canvas.drawBitmap(enemyImage, location.x,location.y, mBitmapPaint);
	}

	public void drawCannonBall(Canvas canvas, Point location) {
		canvas.drawBitmap(cannonBallImage, location.x,location.y, mBitmapPaint);
		
	}

	public void drawCannonBallExplosion(Canvas canvas, Point location) {
		canvas.drawBitmap(cannonBallExplosionImage, location.x,location.y, mBitmapPaint);
		
	}


}
