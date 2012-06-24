package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Sprites {
	protected Bitmap basicTowerImage;
	protected Bitmap basicEnemyImage;
	protected Bitmap iceEnemyImage;
	protected Bitmap cannonBallImage;
	protected Bitmap cannonBallExplosionImage;
	protected Bitmap cursorImage;

	protected Paint mBitmapPaint = new Paint();
	protected Context context;
	protected Bitmap borderTerrainTile;
	protected Bitmap GRASS_1TerrainTile;
	protected Bitmap GRASS_2TerrainTile;
	protected Bitmap startTerrainTile;
	protected Bitmap endTerrainTile;
	protected Bitmap heavyTowerImage;
	protected Bitmap fastTowerImage;

	public Sprites(Context context){
		this.context = context;

		basicTowerImage = getImage(R.drawable.awesome_castle);
		basicTowerImage = Bitmap.createScaledBitmap( basicTowerImage, Constants.OBJECT_CELL_SIDE_LENGTH, Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		heavyTowerImage = getImage(R.drawable.heavy_tower);
		heavyTowerImage = Bitmap.createScaledBitmap( heavyTowerImage, Constants.OBJECT_CELL_SIDE_LENGTH, Constants.OBJECT_CELL_SIDE_LENGTH, false);
		
		fastTowerImage = getImage(R.drawable.fast_tower);
		fastTowerImage = Bitmap.createScaledBitmap( fastTowerImage, Constants.OBJECT_CELL_SIDE_LENGTH, Constants.OBJECT_CELL_SIDE_LENGTH, false);

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

	private Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}
}
