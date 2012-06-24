package com.game.main;

public class GameStatistics {
	private int round = 0;
	private int lives = Constants.STARTING_LIVES;
	private int money = Constants.STARTING_MONEY;

	public GameRound currentGameRound;

	public GameStatistics(){
	}

	public void decrementLives(){
		lives -= 1;
	}

	public void decrementMoney(int amountSpent){
		money -= amountSpent;
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

	public void startRound() {
		this.currentGameRound = new GameRound(this.round);
	}

	public void incrementMoney(int amountSpent){
		money += amountSpent;
	}

}
