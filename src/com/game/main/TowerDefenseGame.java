package com.game.main;

import com.game.main.Constants.TowerType;

public class TowerDefenseGame{

	private boolean ingame = false;
	private boolean inround = false;
	
	private TowerDefenseView towerDefenseView;
	private ObstacleManager obstacleManager;
	private TerrainMap terrainMap;
	private PathBuilder pathBuilder;
	private GameStatistics gameStatistics;
	private PhysicsEngine gameEngine;
	private UpdateTaskManager updateTaskManager;
	private SpriteEngine spriteEngine;

	public TowerDefenseGame(TowerDefenseView towerDefenseView, GameStatistics gameStatistics) {
		this.towerDefenseView = towerDefenseView;
		this.gameStatistics = gameStatistics;
		initializeClasses();
	}

	private void initializeClasses() {

		obstacleManager = new ObstacleManager();
		terrainMap = new TerrainMap(towerDefenseView.getWidth(), towerDefenseView.getHeight(), obstacleManager);
		terrainMap.setFocus(new PixelPoint(Constants.GRID_SQUARE_SIZE,Constants.GRID_SQUARE_SIZE));
		pathBuilder = new PathBuilder(terrainMap,obstacleManager);
		spriteEngine = new SpriteEngine(towerDefenseView.getContext(),obstacleManager,terrainMap);
		gameEngine = new PhysicsEngine(terrainMap,pathBuilder,obstacleManager,gameStatistics,this);
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
				towerDefenseView.showUpgradeView(obstacleManager.towersHash.get(newFocus));
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
		Tower tower = new Tower(terrainMap.getFocus(), towerType);
		boolean built = gameEngine.buildTowerClicked(tower);
		if(built){
			towerDefenseView.showUpgradeView(tower);
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

	public PhysicsEngine getGameEngine() {
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
		Tower tower = getCurrentTower();
		gameEngine.sellTowerClicked(tower);
		towerDefenseView.showBuildView();
	}

	public SpriteEngine getSpriteEngine() {
		return spriteEngine;
	}

	public void upgradeTowerRange() {
		Tower tower = getCurrentTower();
		if(gameStatistics.getMoney() >= Constants.RANGE_UPGRADE_COST){
			tower.incrementRange();
			gameStatistics.decrementMoney(Constants.RANGE_UPGRADE_COST);
		}
		else{
			//not enought money msg
		}
	}

	public Tower getCurrentTower() {
		return obstacleManager.towersHash.get(terrainMap.getFocus());
	}

	public void upgradeTowerCoolDown() {
		Tower tower = getCurrentTower();
		if(gameStatistics.getMoney() >= Constants.COOL_DOWN_UPGRADE_COST){
			if(tower.decrementCoolDown()){
				gameStatistics.decrementMoney(Constants.COOL_DOWN_UPGRADE_COST);
			}
			else{
				//cooldown maxed
			}
		}
		else{
			//not enought money msg
		}
		
	}
	

	

}
