package com.game.main;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.game.main.Constants.DrawObject;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

public class GameEngine {

	private TerrainMap terrainMap;
	private SpriteDrawer spriteDrawer;
	private PathBuilder pathBuilder;
	
	Point enemyStartPoint = Constants.SPAWN_POINT;
	Point enemyEndPoint = Constants.END_POINT;


	public CopyOnWriteArrayList<BasicEnemy> basicEnemies = new CopyOnWriteArrayList<BasicEnemy>();

	public CopyOnWriteArrayList <CannonBall> cannonBalls = new CopyOnWriteArrayList <CannonBall>();
	CopyOnWriteArrayList<CannonBall> finishedCannonBalls = new CopyOnWriteArrayList<CannonBall>();

	private ArrayList<Point> path;
	private TowerManager towerManager;



	GameEngine(TerrainMap terrainMap, SpriteDrawer mySpriteDrawer, PathBuilder myPathBuilder, TowerManager myTowerManager){
		this.terrainMap = terrainMap;
		this.spriteDrawer = mySpriteDrawer;
		this.pathBuilder = myPathBuilder;
		this.towerManager = myTowerManager;
		path = pathBuilder.getPath(enemyStartPoint,enemyEndPoint);

	}


	public void drawAll(Canvas canvas) {
		
		for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				spriteDrawer.drawGameObject(canvas, new Point(i*Constants.GRID_SQUARE_SIZE + TowerDefenseGame.X_offset, j *Constants.GRID_SQUARE_SIZE + TowerDefenseGame.Y_offset), terrainMap.worldTerrainGrid[i][j]);
			}
		}
		for (Tower tower:towerManager.towers){
			Point location = new Point(tower.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseGame.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseGame.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.TOWER);
		}
		for (BasicEnemy enemy:basicEnemies){
			Point location = new Point(enemy.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseGame.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseGame.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.BASIC_ENEMY);

		}
		for (CannonBall cannonBall: cannonBalls){
			Point location = new Point(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseGame.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseGame.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL);

		}

		for (CannonBall cannonBall: finishedCannonBalls){
			Point location = new Point(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseGame.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseGame.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL_EXPLOSION);

		}
		Point cursor_location = new Point(terrainMap.getFocus().x+ TowerDefenseGame.X_offset,terrainMap.getFocus().y+ TowerDefenseGame.Y_offset);
		spriteDrawer.drawGameObject(canvas,cursor_location, DrawObject.CURSOR);
	}

	private int counter = 0;

	public void updatePhysics() {
		if(counter == 300){
			addEnemy();
			counter = 0;
			//path = terrainMap.getPath(enemyStartPoint, enemyEndPoint);
		}
		else if(counter!=295){
			
			counter++;
		

		ArrayList<BasicEnemy> finishedEnemies = new ArrayList<BasicEnemy>();
		for (BasicEnemy enemy: basicEnemies){
			//path = terrainMap.getPath(enemyStartPoint, enemyEndPoint);
			enemy.updatePath(path);
			enemy.updateLocalGoal();
			enemy.updateLocation();
			enemy.updateState();
			if (enemy.getState() == Constants.State.DONE ||terrainMap.LocationOutOfBounds(enemy.getLocation())){
				finishedEnemies.add(enemy);
			}
		}
		for (Tower tower: towerManager.towers){
			CannonBall cannonBall = tower.update(basicEnemies);
			if(cannonBall != null){
				cannonBalls.add(cannonBall);
			}
		}
		finishedCannonBalls = new CopyOnWriteArrayList<CannonBall>();
		for (CannonBall cannonBall: cannonBalls){
			cannonBall.updateLocation();
			cannonBall.updateState();
			if (cannonBall.getState() == Constants.State.DONE){
				explodeCannonBall(cannonBall);
				finishedCannonBalls.add(cannonBall);
			}
		}
		

		for (BasicEnemy enemy: finishedEnemies){
			basicEnemies.remove(enemy);
		}
		for (CannonBall cannonBall : finishedCannonBalls){
			cannonBalls.remove(cannonBall);

		}
		}
		else{
			path = pathBuilder.getPath(enemyStartPoint, enemyEndPoint);
			counter++;
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
	
	public Point computeNearestTowerLocation(Point p) {
		int nearestTowerLocationX = Constants.GRID_SQUARE_SIZE*((int) Math.floor(p.x / Constants.GRID_SQUARE_SIZE));
		int nearestTowerLocationY = Constants.GRID_SQUARE_SIZE*((int) Math.floor(p.y / Constants.GRID_SQUARE_SIZE));
		return new Point(nearestTowerLocationX, nearestTowerLocationY);
	}

	public void tileClicked(Point location) {
		terrainMap.setFocus(computeNearestTowerLocation(location));
	}
	
	public void buildTowerClicked(){
		if (towerManager.isTowerAt(terrainMap.getFocus()) == false){
			Tower tower = new Tower(terrainMap.getFocus());
			towerManager.addTower(tower);
		}
		
		BasicEnemy enemy = new BasicEnemy(enemyStartPoint,enemyEndPoint);
		basicEnemies.add(enemy);
	}
	


}
