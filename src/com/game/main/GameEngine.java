package com.game.main;

import java.util.ArrayList;

import com.game.main.Constants.DrawObject;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

public class GameEngine {

	private TerrainMap terrainMap;
	
	Point enemyStartPoint = new Point(0,0);
	Point enemyEndPoint = new Point(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
	
	public ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
	ArrayList<CannonBall> finishedCannonBalls = new ArrayList<CannonBall>();
	
	private ArrayList<Point> path;

	private SpriteDrawer spriteDrawer;;

	GameEngine(TerrainMap terrainMap, SpriteDrawer mySpriteDrawer){
		this.terrainMap = terrainMap;
		this.spriteDrawer = mySpriteDrawer;
		BasicEnemy enemy = new BasicEnemy(enemyStartPoint,enemyEndPoint);
		basicEnemies.add(enemy);
		path = terrainMap.getPath(enemy.getLocation(), new Point(100,100));
	}
	
	public void setTower(Tower tower) {
		terrainMap.worldTowerGrid[(int) Math.floor(tower.getLocation().y / terrainMap.squareSize)][(int) Math.floor(tower.getLocation().x / terrainMap.squareSize)] = tower;
		towers.add(tower);

	}
	
	public void drawAll(Canvas canvas) {
		spriteDrawer.drawGameObject(canvas,terrainMap.getFocus(), DrawObject.CURSOR);
		for (int i=0; i<terrainMap.numRows; i++){
			for (int j=0; j<terrainMap.numColumns; j++){
				if(terrainMap.worldTowerGrid[i][j]!=null){
					Point location = new Point(terrainMap.worldTowerGrid[i][j].getLocation().x+Constants.IMAGE_OFFSET, terrainMap.worldTowerGrid[i][j].getLocation().y+Constants.IMAGE_OFFSET);
					spriteDrawer.drawGameObject(canvas,location, DrawObject.TOWER);
				}
			}
		}
		for (BasicEnemy enemy:basicEnemies){
			Point location = new Point(enemy.getLocation());
			location.offset(Constants.IMAGE_OFFSET, Constants.IMAGE_OFFSET);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.BASIC_ENEMY);
			
		}
		for (CannonBall cannonBall: cannonBalls){
			Point location = new Point(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET, Constants.IMAGE_OFFSET);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL);
			
		}
		
		for (CannonBall cannonBall: finishedCannonBalls){
			Point location = new Point(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET, Constants.IMAGE_OFFSET);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL_EXPLOSION);
			
		}
	}
	
	public void updatePhysics() {
		ArrayList<BasicEnemy> finishedEnemies = new ArrayList<BasicEnemy>();
		for (BasicEnemy enemy: basicEnemies){
			
			enemy.updatePath(path);
			enemy.updateLocalGoal();
			enemy.updateLocation();
			enemy.updateState();
			if (enemy.getState() == Constants.State.DONE ||terrainMap.LocationOutOfBounds(enemy.getLocation())){
				finishedEnemies.add(enemy);
			}
		}
		for (Tower tower: towers){
			CannonBall cannonBall = tower.update(basicEnemies);
			if(cannonBall != null){
				cannonBalls.add(cannonBall);
			}
		}
		finishedCannonBalls = new ArrayList<CannonBall>();
		for (CannonBall cannonBall : cannonBalls){
			cannonBall.updateLocation();
			cannonBall.updateState();
			if (cannonBall.getState() == Constants.State.DONE){
				finishedCannonBalls.add(cannonBall);
				explodeCannonBall(cannonBall);
			}
		}

		for (BasicEnemy enemy: finishedEnemies){
			basicEnemies.remove(enemy);
		}

		for (CannonBall cannonBall : finishedCannonBalls){
			cannonBalls.remove(cannonBall);
			
		}

	}
	
	private void explodeCannonBall(CannonBall cannonBall){
		for (BasicEnemy enemy: basicEnemies){
			double distanceSquared = BasicEnemy.calculateDistanceSquared(enemy.getLocation(), cannonBall.getLocation());
			if (distanceSquared < Constants.CANNONBALL_EXPLOSION_RADIUS_SQUARED){
				enemy.reduceHeath(Constants.CANNONBALL_DAMAGE);
			}
			Log.e("enemy health","health: " + enemy.health);
		}
	}
	
	private void addEnemy(){
		BasicEnemy enemy = new BasicEnemy(enemyStartPoint,enemyEndPoint);
		basicEnemies.add(enemy);
	}

}
