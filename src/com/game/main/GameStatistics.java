package com.game.main;

public class GameStatistics {
	private int round = 0;
	private int lives = 10;
	private int money = 100;

	public GameRound currentGameRound;
	private TowerDefenseView towerDefenseView;
	
	public GameStatistics(TowerDefenseView towerDefenseView){
		this.towerDefenseView = towerDefenseView;
	}
	
	public void decrementLives(){
		lives -= 1;
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

	public void incrementMoney(int amountSpent){
		money += amountSpent;
		towerDefenseView.refreshButtons();
	}
}
