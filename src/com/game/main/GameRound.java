package com.game.main;

import java.util.HashMap;

public class GameRound {
	private HashMap<Integer, BasicEnemy> roundEnemies;
	private int counter;
	private int roundNumber;
	
	
	public GameRound(int roundNumber){
		this.roundNumber = roundNumber;
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
		case 3:		fillRound1(); 	break;
		case 4:		fillRound1(); 	break;
		case 5:		fillRound1(); 	break;
		case 6:		fillRound1(); 	break;
		case 7:		fillRound1(); 	break;
		}
	}

	public BasicEnemy update(){
		BasicEnemy myEnemy = roundEnemies.get(counter);
		//if(myEnemy==null)
		counter++;
		return myEnemy;
	}
	
	private void fillRound1() {
		for (int i=0;i<10;i++){
			roundEnemies.put(i*50, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT));
		}
	}
	
	private void fillRound2() {
		for (int i=0;i<20;i++){
			roundEnemies.put(i*50, new BasicEnemy(Constants.SPAWN_POINT, Constants.END_POINT));
		}
	}

	
	
}