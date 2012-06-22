package com.game.main;

import java.util.HashMap;

public class GameRound {
	private HashMap<Integer, BasicEnemy> roundEnemies;
	private int counter;
	private int roundNumber;
	private int maxCount;
	private int numEnemies;
	private boolean roundDeployed = false;
	private int roundDelay;


	public GameRound(int roundNumber){
		this.roundNumber = roundNumber;
		roundDelay = (int)(Constants.BASIC_ENEMY_DELAY*(double)(1.0-roundNumber*0.05));
		initialize();
		populateHashMap();
	}

	private void initialize(){
		counter = 0;
		roundEnemies = new HashMap<Integer, BasicEnemy>();
	}

	private void populateHashMap(){
		switch (roundNumber){
		case 1:		fillRound1(); 	break;
		case 2:		fillRound2(); 	break;
		case 3:		fillRound3(); 	break;
		case 4:		fillRound4(); 	break;
		case 5:		fillRound5(); 	break;
		case 6:		fillRound6(); 	break;
		case 7:		fillRound1(); 	break;
		}
	}

	public BasicEnemy update(){
		BasicEnemy myEnemy = roundEnemies.get(counter);
		if(counter>maxCount){
			roundDeployed = true;
		}
		counter++;
		return myEnemy;
	}

	private void fillRound1() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, Constants.BASIC_ENEMY_HEALTH, Constants.EnemyType.BASIC, Constants.BASIC_ENEMY_VALUE));
		}
	}

	private void fillRound2() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, (int) (1.1*(double)(Constants.ICE_ENEMY_HEALTH)), Constants.EnemyType.ICE, Constants.ICE_ENEMY_VALUE));
		}
	}

	private void fillRound3() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, (int) (1.2*(double)(Constants.BASIC_ENEMY_HEALTH)), Constants.EnemyType.BASIC, Constants.BASIC_ENEMY_VALUE));
		}
	}

	private void fillRound4() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, (int) (1.3*(double)(Constants.ICE_ENEMY_HEALTH)), Constants.EnemyType.ICE, Constants.ICE_ENEMY_VALUE));
		}
	}

	private void fillRound5() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, (int) (1.4*(double)(Constants.BASIC_ENEMY_HEALTH)), Constants.EnemyType.BASIC, Constants.BASIC_ENEMY_VALUE));
		}
	}

	private void fillRound6() {
		numEnemies = 10;
		maxCount = (numEnemies-1)*Constants.BASIC_ENEMY_DELAY + 1;
		for (int i=0;i<numEnemies;i++){
			roundEnemies.put(i*roundDelay, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT, (int) (1.5*(double)(Constants.ICE_ENEMY_HEALTH)), Constants.EnemyType.ICE, Constants.ICE_ENEMY_VALUE));
		}
	}

	public boolean isRoundDeployed(){
		return roundDeployed;
	}



}
