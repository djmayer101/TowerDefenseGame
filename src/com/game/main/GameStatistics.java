package com.game.main;

public class GameStatistics {
	private int round = 0;
	private int lives = 10;
	private int money = 100;
	private TowerDefenseGame game;
	public GameRound currentGameRound;
	private TowerDefenseView towerDefenseView;
	
	public GameStatistics(TowerDefenseGame game, TowerDefenseView towerDefenseView){
		this.towerDefenseView = towerDefenseView;
		this.game = game;
	}
	
	public void decrementLives(){
		lives -= 1;
		if (lives == 0){
			game.gameOver();
		}
		towerDefenseView.refreshButtons();
	}
	
	public void decrementMoney(int amountSpent){
		money -= amountSpent;
		towerDefenseView.refreshButtons();
	}
	
	public void incrementRound(){
		round += 1;
		towerDefenseView.refreshButtons();
	}

	public int getRound() {
		return round;
	}

	public int getLives() {
		return lives;
	}

	public int getMoney() {
		return money;
	}
	
	public void startRound() {
		this.currentGameRound = new GameRound(this.round);
	}

}
