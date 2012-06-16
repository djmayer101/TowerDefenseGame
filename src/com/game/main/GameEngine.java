package com.game.main;

import java.util.ArrayList;

import com.game.main.Constants.DrawObject;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

public class GameEngine {

	private TerrainMap terrainMap;
	private SpriteDrawer spriteDrawer;
	private PathBuilder pathBuilder;
	
	Point enemyStartPoint = new Point(0,0);
	Point enemyEndPoint = new Point(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
	
	public ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
	ArrayList<CannonBall> finishedCannonBalls = new ArrayList<CannonBall>();
	
	private ArrayList<Point> path;



	GameEngine(TerrainMap terrainMap, SpriteDrawer mySpriteDrawer, PathBuilder myPathBuilder){
		this.terrainMap = terrainMap;
		this.spriteDrawer = mySpriteDrawer;
		this.pathBuilder = myPathBuilder;
		//BasicEnemy enemy = new BasicEnemy(enemyStartPoint,enemyEndPoint);
		//basicEnemies.add(enemy);
		//path = pathBuilder.run(new Point(0,0), new Point(5,5));
		path = new ArrayList<Point>();
		path.add(new Point(0,0));
		path.add(new Point(0,1));
		path.add(new Point(1,1));
		path.add(new Point(1,2));
		path.add(new Point(2,2));
	}
	
	public void setTower(Tower tower) {
		//terrainMap.worldTowerGrid[(int) Math.floor(tower.getLocation().y / terrainMap.squareSize)][(int) Math.floor(tower.getLocation().x / terrainMap.squareSize)] = tower;
		towers.add(tower);

	}
	
	public void drawAll(Canvas canvas) {
		
		for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				spriteDrawer.drawGameObject(canvas, new Point(i*Constants.GRID_SQUARE_SIZE + TowerDefenseGame.X_offset, j *Constants.GRID_SQUARE_SIZE + TowerDefenseGame.Y_offset), terrainMap.worldTerrainGrid[i][j]);
			}
		}
		for (Tower tower:towers){
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
	
	public Point computeNearestTowerLocation(Point p) {
		int nearestTowerLocationX = Constants.GRID_SQUARE_SIZE*((int) Math.floor(p.x / Constants.GRID_SQUARE_SIZE));
		int nearestTowerLocationY = Constants.GRID_SQUARE_SIZE*((int) Math.floor(p.y / Constants.GRID_SQUARE_SIZE));
		return new Point(nearestTowerLocationX, nearestTowerLocationY);
	}

	public void tileClicked(Point location) {
		terrainMap.setFocus(computeNearestTowerLocation(location));
		Tower tower = new Tower(terrainMap.getFocus());
		towers.add(tower);
		BasicEnemy enemy = new BasicEnemy(new Point(0,0),new Point(2,2));
		basicEnemies.add(enemy);
		
		
	}

}
