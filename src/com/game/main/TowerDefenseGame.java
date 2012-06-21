package com.game.main;

import android.graphics.Point;

public class TowerDefenseGame{

	
	private boolean ingame = false;
	private TowerDefenseView towerDefenseView;
	private SpriteDrawer spriteDrawer;
	private ObstacleManager obstacleManager;
	private TerrainMap terrainMap;
	private PathBuilder pathBuilder;
	private GameStatistics gameStatistics;
	private GameEngine gameEngine;
	private UpdateTaskManager updateTaskManager;

	

	public TowerDefenseGame(TowerDefenseView towerDefenseView, GameStatistics gameStatistics) {
		this.towerDefenseView = towerDefenseView;
		this.gameStatistics = gameStatistics;
		initializeClasses();
	}

	private void initializeClasses() {
		spriteDrawer = new SpriteDrawer(towerDefenseView.getContext());
		obstacleManager = new ObstacleManager();
		terrainMap = new TerrainMap(towerDefenseView.getWidth(), towerDefenseView.getHeight(), obstacleManager);
		terrainMap.setFocus(new Point(Constants.GRID_SQUARE_SIZE,Constants.GRID_SQUARE_SIZE));
		pathBuilder = new PathBuilder(terrainMap,obstacleManager);
		gameEngine = new GameEngine(terrainMap,spriteDrawer,pathBuilder,obstacleManager,gameStatistics,this);
		updateTaskManager = new UpdateTaskManager(towerDefenseView, this);
		
	}

	
	protected void startRound() {
		if(!ingame){
			gameStatistics.incrementRound();
			gameStatistics.startRound();
			ingame = true;
		}
	}

	void pauseClicked() {
		if (ingame){
			updateTaskManager.pause();
		}
		else{
			updateTaskManager.go();
		}
	}
	
	public void gameOver() {
		ingame = false;
		towerDefenseView.showGameOver();
	}


	void buildTowerClicked() {
		gameEngine.buildTowerClicked();
	}

	protected void updatePhysics() {
		if (ingame){
			gameEngine.updatePhysics();
		}
	}

	protected boolean isGameOver() {
		return false;
	}

	public GameStatistics getGameStatistics() {
		return gameStatistics;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public void setIngame(boolean b) {
		ingame = b;
		
	}

	public UpdateTaskManager getUpdateTaskManager() {
		return updateTaskManager;
	}

	public void showTowerOptions() {
		towerDefenseView.showTowerOptions();
	}
	
	public void showRoundOver(){
		towerDefenseView.showRoundOver();
	}

	public boolean getInGame() {
		return ingame;
	}

	public void killedEnemy(BasicEnemy enemy) {
		gameStatistics.incrementMoney(enemy.value());
		
	}

}
