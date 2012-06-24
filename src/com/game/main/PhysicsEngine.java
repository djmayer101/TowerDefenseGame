package com.game.main;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;



public class PhysicsEngine {

	private TerrainMap terrainMap;
	private PathBuilder pathBuilder;
	private ObstacleManager obstacleManager;
	private GameStatistics gameStatistics;

	private static Semaphore mutex;


	private boolean isNewTowerBuilt = false;
	GridPoint enemyStartPoint = Constants.SPAWN_POINT;
	GridPoint enemyEndPoint = Constants.END_POINT;

	private CopyOnWriteArrayList<GridPoint> path;
	private TowerDefenseGame towerDefenseGame;


	public PhysicsEngine(TerrainMap terrainMap, PathBuilder myPathBuilder, ObstacleManager myObstacleManager, GameStatistics gameStatistics, TowerDefenseGame towerDefenseGame){
		this.terrainMap = terrainMap;
		this.pathBuilder = myPathBuilder;
		this.obstacleManager = myObstacleManager;
		this.gameStatistics = gameStatistics;
		path = pathBuilder.getPath(enemyStartPoint,enemyEndPoint);
		this.towerDefenseGame = towerDefenseGame;
		mutex = new Semaphore(1, true);
	}

	public void updatePhysics() {
		BasicEnemy myEnemy = gameStatistics.currentGameRound.update();
		if(myEnemy != null){
			addEnemy(myEnemy);
		}

		try {
			mutex.acquire();
			if(isNewTowerBuilt){
				for (BasicEnemy enemy: obstacleManager.basicEnemies){
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
		if (obstacleManager.basicEnemies.size() == 0 && gameStatistics.currentGameRound.isRoundDeployed() && towerDefenseGame.getInGame()){
			towerDefenseGame.showRoundOver();
			towerDefenseGame.setIngame(false);
			obstacleManager.cannonBalls.clear();
		}
		for (BasicEnemy enemy: obstacleManager.basicEnemies){
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
			CannonBall cannonBall = tower.update(obstacleManager.basicEnemies);
			if(cannonBall != null){
				obstacleManager.cannonBalls.add(cannonBall);
			}
		}
		obstacleManager.finishedCannonBalls = new CopyOnWriteArrayList<CannonBall>();
		for (CannonBall cannonBall: obstacleManager.cannonBalls){
			cannonBall.updateLocation();
			cannonBall.updateState();
			if (cannonBall.getState() == Constants.State.DONE){
				explodeCannonBall(cannonBall);
				obstacleManager.finishedCannonBalls.add(cannonBall);
			}
		}


		for (BasicEnemy enemy: finishedEnemies){
			obstacleManager.basicEnemies.remove(enemy);
		}
		for (CannonBall cannonBall : obstacleManager.finishedCannonBalls){
			obstacleManager.cannonBalls.remove(cannonBall);

		}
	}

	private void explodeCannonBall(CannonBall cannonBall){
		for (BasicEnemy enemy: obstacleManager.basicEnemies){
			double distanceSquared = TerrainMap.calculateDistanceSquared(enemy.getLocation(), cannonBall.getLocation());
			if (distanceSquared < Constants.CANNONBALL_EXPLOSION_RADIUS_SQUARED){
				enemy.reduceHeath(cannonBall.getDamage());
			}
		}
	}

	private void addEnemy(BasicEnemy myEnemy){
		path = pathBuilder.getPath(enemyStartPoint,enemyEndPoint);
		myEnemy.updatePath(path);
		obstacleManager.basicEnemies.add(myEnemy);
	}

	public PixelPoint computeNearestTowerLocation(PixelPoint p) {
		PixelPoint towerLocation = p.scaleToGridPoint().scaleToPixelPoint();
		return towerLocation;
	}

	public boolean buildTowerClicked(Constants.TowerType towerType){
		boolean impossibru = true;
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
		for (BasicEnemy enemy: obstacleManager.basicEnemies){
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


	public void sellTowerClicked(Tower tower) {
		gameStatistics.incrementMoney(Tower.getTowerCost(tower.getTowerType()));
		obstacleManager.remove(tower);
	}

}
