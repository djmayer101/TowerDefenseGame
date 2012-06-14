package com.game.main;

import android.graphics.Point;

public class BasicEnemy extends BasicGameObject {
	
	protected int health;

	public BasicEnemy(Point location){
		super(location, GameConstants.BASIC_ENEMY_SPEED);
		this.theta = GameConstants.EAST;
		this.health = 100;
		
	}
	
	@Override
	void updateState() {}
}

