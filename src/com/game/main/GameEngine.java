package com.game.main;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import com.game.main.Constants.DrawObject;
import com.game.main.Constants.TowerType;

import android.graphics.Canvas;


public class GameEngine {

	private TerrainMap terrainMap;
	private SpriteDrawer spriteDrawer;
	private PathBuilder pathBuilder;
	private ObstacleManager obstacleManager;
	private GameStatistics gameStatistics;

	private static Semaphore mutex;


	private boolean isNewTowerBuilt = false;
	GridPoint enemyStartPoint = Constants.SPAWN_POINT;
	GridPoint enemyEndPoint = Constants.END_POINT;


	public CopyOnWriteArrayList<BasicEnemy> basicEnemies = new CopyOnWriteArrayList<BasicEnemy>();

	public CopyOnWriteArrayList <CannonBall> cannonBalls = new CopyOnWriteArrayList <CannonBall>();
	CopyOnWriteArrayList<CannonBall> finishedCannonBalls = new CopyOnWriteArrayList<CannonBall>();

	private CopyOnWriteArrayList<GridPoint> path;
	private TowerDefenseGame towerDefenseGame;


	public GameEngine(TerrainMap terrainMap, SpriteDrawer mySpriteDrawer, PathBuilder myPathBuilder, ObstacleManager myObstacleManager, GameStatistics gameStatistics, TowerDefenseGame towerDefenseGame){
		this.terrainMap = terrainMap;
		this.spriteDrawer = mySpriteDrawer;
		this.pathBuilder = myPathBuilder;
		this.obstacleManager = myObstacleManager;
		this.gameStatistics = gameStatistics;
		path = pathBuilder.getPath(enemyStartPoint,enemyEndPoint);
		this.towerDefenseGame = towerDefenseGame;
		mutex = new Semaphore(1, true);
	}




	public void drawAll(Canvas canvas) {

		for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				spriteDrawer.drawGameObject(canvas, new PixelPoint(i*Constants.GRID_SQUARE_SIZE + TowerDefenseView.X_offset, j*Constants.GRID_SQUARE_SIZE  + TowerDefenseView.Y_offset), terrainMap.worldTerrainGrid[i][j]);
			}
		}
		for (Tower tower:obstacleManager.towers){
			PixelPoint location = new PixelPoint(tower.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			if (tower.getTowerType() == TowerType.BASIC){
				spriteDrawer.drawGameObject(canvas,location, DrawObject.BASIC_TOWER);
			}
			else if(tower.getTowerType() == TowerType.HEAVY){
				spriteDrawer.drawGameObject(canvas,location, DrawObject.HEAVY_TOWER);
			}
			else if(tower.getTowerType() == TowerType.FAST){
				spriteDrawer.drawGameObject(canvas,location, DrawObject.FAST_TOWER);
			}

		}
		for (BasicEnemy enemy:basicEnemies){
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
			spriteDrawer.drawGameObject(canvas,location, drawObject);

		}
		for (CannonBall cannonBall: cannonBalls){
			PixelPoint location = new PixelPoint(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL);

		}

		for (CannonBall cannonBall: finishedCannonBalls){
			PixelPoint location = new PixelPoint(cannonBall.getLocation());
			location.offset(Constants.IMAGE_OFFSET+ TowerDefenseView.X_offset, Constants.IMAGE_OFFSET+ TowerDefenseView.Y_offset);
			spriteDrawer.drawGameObject(canvas,location, DrawObject.CANNON_BALL_EXPLOSION);

		}
		PixelPoint cursor_location = new PixelPoint(terrainMap.getFocus().x+ TowerDefenseView.X_offset,terrainMap.getFocus().y+ TowerDefenseView.Y_offset);
		spriteDrawer.drawGameObject(canvas,cursor_location, DrawObject.CURSOR);
	}

	public void updatePhysics() {
		BasicEnemy myEnemy = gameStatistics.currentGameRound.update();
		if(myEnemy != null){
			addEnemy(myEnemy);
		}

		try {
			mutex.acquire();
			if(isNewTowerBuilt){
				for (BasicEnemy enemy: basicEnemies){
					path = pathBuilder.getPath(enemy.getLocation().scaleToGridPoint(), enemyEndPoint);
					enemy.updatePath(path);
				}
			}
			isNewTowerBuilt = false;
			mutex.release();
		} catch (InterruptedException e) {
			System.out.println("Exception " + e.toString());
		}



		ArrayList<BasicEnemy> finishedEnemies = new ArrayList<BasicEnemy>();
		if (basicEnemies.size() == 0 && gameStatistics.currentGameRound.isRoundDeployed() && towerDefenseGame.getInGame()){
			towerDefenseGame.showRoundOver();
			towerDefenseGame.setIngame(false);
			cannonBalls.clear();
		}
		for (BasicEnemy enemy: basicEnemies){
			enemy.updateLocalGoal();
			enemy.updateLocation();
			enemy.updateState();
			if (enemy.getState() == Constants.State.DONE ){
				finishedEnemies.add(enemy);
				towerDefenseGame.killedEnemy(enemy);
			}
			GridPoint scaledCurrentLocation = enemy.getLocation().scaleToGridPoint();
			if(scaledCurrentLocation.x == enemy.getEndLocation().x && scaledCurrentLocation.y == enemy.getEndLocation().y ||
					enemy.state == Constants.State.MADE_IT_TO_GOAL_LOCATION ||terrainMap.LocationOutOfBounds(enemy.getLocation())){
				finishedEnemies.add(enemy);
				gameStatistics.decrementLives();
				if (gameStatistics.getLives() == 0){
					towerDefenseGame.gameOver();
				}
			}

		}

		updateIsNewTowerBuilt(false);

		for (Tower tower: obstacleManager.towers){
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

	private void explodeCannonBall(CannonBall cannonBall){
		for (BasicEnemy enemy: basicEnemies){
			double distanceSquared = TerrainMap.calculateDistanceSquared(enemy.getLocation(), cannonBall.getLocation());
			if (distanceSquared < Constants.CANNONBALL_EXPLOSION_RADIUS_SQUARED){
				enemy.reduceHeath(cannonBall.getDamage());
			}
		}
	}

	private void addEnemy(BasicEnemy myEnemy){
		path = pathBuilder.getPath(enemyStartPoint,enemyEndPoint);
		myEnemy.updatePath(path);
		basicEnemies.add(myEnemy);
	}

	public PixelPoint computeNearestTowerLocation(PixelPoint p) {
		PixelPoint towerLocation = p.scaleToGridPoint().scaleToPixelPoint();
		return towerLocation;
	}

	public void tileClicked(PixelPoint location) {
		if(obstacleManager.isObstacleAt(location.scaleToGridPoint()) == false){
			terrainMap.setFocus(computeNearestTowerLocation(location));
		}
	}

	public boolean buildTowerClicked(Constants.TowerType towerType){
		boolean impossibru = false;
		if ((obstacleManager.isTowerAt(terrainMap.getFocus()) == false) ){
			if(gameStatistics.getMoney() >= Tower.getTowerCost(towerType)){
				Tower tower = new Tower(terrainMap.getFocus(), towerType);
				obstacleManager.addTower(tower);
				impossibru = checkPaths();
				if(!impossibru){
					updateIsNewTowerBuilt(true);
					gameStatistics.decrementMoney(Tower.getTowerCost(towerType));
				}
				else {
					obstacleManager.remove(tower);
				}
			}
		}
		return !impossibru;
	}

	private void updateIsNewTowerBuilt (boolean newVar){		
		try {
			mutex.acquire();
			isNewTowerBuilt = newVar;
			mutex.release();
		} catch (InterruptedException e) {
			System.out.println("Exception " + e.toString());
		}
	}

	public boolean checkPaths(){
		boolean impossibru= false;
		for (BasicEnemy enemy: basicEnemies){
			path = pathBuilder.getPath(enemy.getLocation().scaleToGridPoint(), enemyEndPoint);
			if(path.size() == 0){
				impossibru = true;
				break;
			}		
		}
		path = pathBuilder.getPath(enemyStartPoint, enemyEndPoint);
		if(path.size() == 0){
			impossibru = true;
		}
		return impossibru;
	}

}
