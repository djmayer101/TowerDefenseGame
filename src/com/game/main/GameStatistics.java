package com.game.main;

public class GameStatistics {
	private int round = 1;
	private int lives = 10;
	private int money = 100;
	private TowerDefenseGame game;
	
	public GameStatistics(TowerDefenseGame game){
		this.game = game;
	}
	
	public void decrementLives(){
		lives -= 1;
		if (lives == 0){
			game.gameOver();
		}
		refreshMenu();
	}
	
	public void decrementMoney(int amountSpent){
		money -= amountSpent;
		refreshMenu();
	}
	
	public void incrementRound(){
		round += 1;
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
	
	private void refreshMenu(){
		game.refreshButtons();
	}

}
