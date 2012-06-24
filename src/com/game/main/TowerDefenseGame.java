package com.game.main;


import android.util.Log;
import android.widget.Toast;

import com.game.main.Constants.TowerType;



public class TowerDefenseGame{

	private boolean ingame = false;
	private boolean inround = false;
	
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
		terrainMap.setFocus(new PixelPoint(Constants.GRID_SQUARE_SIZE,Constants.GRID_SQUARE_SIZE));
		pathBuilder = new PathBuilder(terrainMap,obstacleManager);
		gameEngine = new GameEngine(terrainMap,spriteDrawer,pathBuilder,obstacleManager,gameStatistics,this);
		updateTaskManager = new UpdateTaskManager(towerDefenseView, this);

	}



	void pauseOrStartRoundClicked() {
		if (ingame){
			updateTaskManager.pause();
			towerDefenseView.togglePausePlayButtons();
		}
		else if(inround){
			updateTaskManager.go();
			towerDefenseView.togglePausePlayButtons();
		}
		else if (!inround){
			gameStatistics.incrementRound();
			gameStatistics.startRound();
			towerDefenseView.togglePausePlayButtons();
			ingame = true;
			inround = true;
		}
		else{
			throw new RuntimeException("shouldnt be here");
		}
		

	}
	public boolean isfocusOnTower(){
		return obstacleManager.towersHash.containsKey(terrainMap.getFocus());
	}
	
	public void focusChanged(PixelPoint unprocessedFocus){
		if(obstacleManager.isObstacleAt(unprocessedFocus.scaleToGridPoint()) == false){
			PixelPoint newFocus = gameEngine.computeNearestTowerLocation(unprocessedFocus);
			terrainMap.setFocus(newFocus);
			if (obstacleManager.towersHash.containsKey(newFocus)){
				towerDefenseView.showUpgradeView();
			}
			else{
				towerDefenseView.showBuildView();
			}
		}
	}

	public void gameOver() {
		ingame = false;
		towerDefenseView.showGameOver();
	}


	void buildTowerClicked(TowerType towerType) {
		boolean built = gameEngine.buildTowerClicked(towerType);
		if(built){
			towerDefenseView.showUpgradeView();
		}

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

	public void showRoundOver(){
		towerDefenseView.showRoundOver();
		this.inround = false;
		if(towerDefenseView.getButtonsWrapper().isShowingPaused()){
			towerDefenseView.togglePausePlayButtons();
		}
	}

	public boolean getInGame() {
		return ingame;
	}

	public void killedEnemy(BasicEnemy enemy) {
		gameStatistics.incrementMoney(enemy.value());
	}

	public void showTowerUpgradeOptions() {
		towerDefenseView.showTowerUpgradeOptions();
	}

	public void showMenu() {
		towerDefenseView.showMenu();
	}

	public void toggleBuildAndUpgradeButtonClicked() {
		towerDefenseView.toggleBuildAndUpgradeButtonClicked();
	}

	public void showSellTowerDialog() {
		towerDefenseView.showSellTowerDialog();
		
	}

	public void sellTower() {
		Tower tower = obstacleManager.towersHash.get(terrainMap.getFocus());
		gameEngine.sellTowerClicked(tower);
		towerDefenseView.showBuildView();

		
	}

}
