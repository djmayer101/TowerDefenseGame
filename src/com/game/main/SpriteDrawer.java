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
	private Bitmap basicEnemyImage;
	private Bitmap iceEnemyImage;
	private Bitmap cannonBallImage;
	private Bitmap cannonBallExplosionImage;
	private Bitmap cursorImage;

	private Paint mBitmapPaint = new Paint();
	private Context context;
	private Bitmap borderTerrainTile;
	private Bitmap GRASS_1TerrainTile;
	private Bitmap GRASS_2TerrainTile;
	private Bitmap startTerrainTile;
	private Bitmap endTerrainTile;

	public SpriteDrawer(Context context){
		this.context = context;
		towerImage = getImage(R.drawable.awesome_castle);
		towerImage = Bitmap.createScaledBitmap( towerImage, Constants.OBJECT_CELL_SIDE_LENGTH, Constants.OBJECT_CELL_SIDE_LENGTH, false);

		basicEnemyImage = getImage(R.drawable.crazy_enemy);
		basicEnemyImage = Bitmap.createScaledBitmap(basicEnemyImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);

		iceEnemyImage = getImage(R.drawable.ice_monster);
		iceEnemyImage = Bitmap.createScaledBitmap(iceEnemyImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		cannonBallImage = getImage(R.drawable.fire_projectile);
		cannonBallImage = Bitmap.createScaledBitmap(cannonBallImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);

		cannonBallExplosionImage = getImage(R.drawable.cannonball_explosion);
		cannonBallExplosionImage = Bitmap.createScaledBitmap(cannonBallExplosionImage, Constants.OBJECT_CELL_SIDE_LENGTH,Constants.OBJECT_CELL_SIDE_LENGTH, false);

		cursorImage = getImage(R.drawable.clearyellow);
		cursorImage = Bitmap.createScaledBitmap(cursorImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);

		
		GRASS_1TerrainTile = getImage(R.drawable.fire_ground_tile);
		GRASS_1TerrainTile = Bitmap.createScaledBitmap( GRASS_1TerrainTile, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		
		GRASS_2TerrainTile = getImage(R.drawable.fire_ground_tile);
		GRASS_2TerrainTile = Bitmap.createScaledBitmap( GRASS_2TerrainTile, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);

		borderTerrainTile = getImage(R.drawable.border_square);
		borderTerrainTile = Bitmap.createScaledBitmap( borderTerrainTile, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);

		startTerrainTile = getImage(R.drawable.start_square);
		startTerrainTile = Bitmap.createScaledBitmap( startTerrainTile, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);

		endTerrainTile = getImage(R.drawable.end_square);
		endTerrainTile = Bitmap.createScaledBitmap( endTerrainTile, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
	}

	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	public void drawGameObject(Canvas canvas, Point location, DrawObject drawObject){
		Bitmap image;
		switch (drawObject) {
		case CURSOR:				image = cursorImage;
		break;
		case BASIC_TOWER:			image = towerImage;
		break;
		case CANNON_BALL:			image = cannonBallImage;
		break;
		case CANNON_BALL_EXPLOSION:	image = cannonBallExplosionImage;
		break;
		case BASIC_ENEMY:			image = basicEnemyImage;
		break;
		case ICE_ENEMY:				image = iceEnemyImage;
		break;
		case GRASSTILE_1:			image = GRASS_1TerrainTile;
		break;
		case GRASSTILE_2:			image = GRASS_2TerrainTile;
		break;
		case BORDERTILE:			image = borderTerrainTile;
		break;
		case STARTTILE:				image = startTerrainTile;
		break;
		case ENDTILE:				image = endTerrainTile;
		break;
		default:					image = null;
		}
		if(image==null){
			//error
		}
		canvas.drawBitmap(image, location.x, location.y, mBitmapPaint);
	}
}
