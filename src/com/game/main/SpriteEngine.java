package com.game.main;

import com.game.main.Constants.DrawObject;
import com.game.main.Constants.TowerType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteEngine {
	private Sprites sprites;
	private ObstacleManager obstacleManager;
	private TerrainMap terrainMap;
	
	public SpriteEngine(Context context, ObstacleManager obstacleManager, TerrainMap terrainMap){
		this.sprites = new Sprites(context);
		this.obstacleManager = obstacleManager;
		this.terrainMap = terrainMap;
	}
	
	public void drawAll(Canvas canvas) {
		/*for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				drawGameObject(canvas, new PixelPoint(i*Constants.GRID_SQUARE_SIZE + TowerDefenseView.X_offset, j*Constants.GRID_SQUARE_SIZE  + TowerDefenseView.Y_offset), terrainMap.worldTerrainGrid[i][j]);
			}
		}*/
		for (Tower tower:obstacleManager.towers){
			PixelPoint location = new PixelPoint(tower.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			if (tower.getTowerType() == TowerType.BASIC){
				drawGameObject(canvas,location, DrawObject.BASIC_TOWER);
			}
			else if(tower.getTowerType() == TowerType.HEAVY){
				drawGameObject(canvas,location, DrawObject.HEAVY_TOWER);
			}
			else if(tower.getTowerType() == TowerType.FAST){
				drawGameObject(canvas,location, DrawObject.FAST_TOWER);
			}

		}
		for (BasicEnemy enemy:obstacleManager.basicEnemies){
			PixelPoint location = new PixelPoint(enemy.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			Constants.DrawObject drawObject;
			switch(enemy.getType()){
			case	BASIC:	drawObject = DrawObject.BASIC_ENEMY;	
			break;
			case	ICE:	drawObject = DrawObject.ICE_ENEMY;	
			break;	
			default:	drawObject = DrawObject.BASIC_ENEMY;
			}
			drawGameObject(canvas,location, drawObject);

		}
		for (CannonBall cannonBall: obstacleManager.cannonBalls){
			PixelPoint location = new PixelPoint(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			drawGameObject(canvas,location, DrawObject.CANNON_BALL);

		}

		for (CannonBall cannonBall: obstacleManager.finishedCannonBalls){
			PixelPoint location = new PixelPoint(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			drawGameObject(canvas,location, DrawObject.CANNON_BALL_EXPLOSION);

		}
		PixelPoint cursor_location = new PixelPoint(terrainMap.getFocus().x+ TowerDefenseView.X_offset,terrainMap.getFocus().y+ TowerDefenseView.Y_offset);
		drawGameObject(canvas,cursor_location, DrawObject.CURSOR);
	}
	
	private void drawGameObject(Canvas canvas, PixelPoint location, DrawObject drawObject){
		Bitmap image;
		switch (drawObject) {
		case CURSOR:				image = sprites.cursorImage;
		break;
		case BASIC_TOWER:			image = sprites.basicTowerImage;
		break;
		case HEAVY_TOWER:			image = sprites.heavyTowerImage;
		break;
		case FAST_TOWER:			image = sprites.fastTowerImage;
		break;
		case CANNON_BALL:			image = sprites.cannonBallImage;
		break;
		case CANNON_BALL_EXPLOSION:	image = sprites.cannonBallExplosionImage;
		break;
		case BASIC_ENEMY:			image = sprites.basicEnemyImage;
		break;
		case ICE_ENEMY:				image = sprites.iceEnemyImage;
		break;
		case GRASSTILE_1:			image = sprites.GRASS_1TerrainTile;
		break;
		case GRASSTILE_2:			image = sprites.GRASS_2TerrainTile;
		break;
		case BORDERTILE:			image = sprites.borderTerrainTile;
		break;
		case STARTTILE:				image = sprites.startTerrainTile;
		break;
		case ENDTILE:				image = sprites.endTerrainTile;
		break;
		default:					image = null;
		}
		if(image==null){
			//error
		}
		canvas.drawBitmap(image, location.x, location.y, sprites.mBitmapPaint);
	}
}
