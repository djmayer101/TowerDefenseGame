package com.game.main;

import com.game.main.Constants.State;

import android.graphics.Point;

public class BasicEnemy extends BasicGameObject {
	
	protected int health;

	public BasicEnemy(Point location){
		super(location, Constants.BASIC_ENEMY_SPEED);
		this.theta = Constants.EAST;
		this.health = 100;
		
	}
	
	@Override
	void updateState() {
		if (health <= 0){
			this.state = State.DONE;
		}
	}

	public void reduceHeath(int cannonBallDamage) {
		health -= cannonBallDamage;
	}
}

