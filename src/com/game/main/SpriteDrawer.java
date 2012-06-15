package com.game.main;

import com.game.main.Constants.DrawObject;

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

	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	public void drawGameObject(Canvas canvas, Point location, DrawObject drawObject){
		Bitmap image;
		switch (drawObject) {
		case CURSOR:				image = cursorImage;
		break;
		case TOWER:					image = towerImage;
		break;
		case CANNON_BALL:			image = cannonBallImage;
		break;
		case CANNON_BALL_EXPLOSION:	image = cannonBallExplosionImage;
		break;
		case BASIC_ENEMY:			image = enemyImage;
		break;
		default:					image = null;
		}
		if(image==null){
			//error
		}
		canvas.drawBitmap(image, location.x, location.y, mBitmapPaint);
	}
}
