package com.game.main;

import java.util.ArrayList;
import android.graphics.Point;


public class Tower extends BasicGameObject {

	int range;
	int coolDown;
	int coolDownCounter;

	public Tower(Point location){
		super(location,GameConstants.BASIC_TOWER_SPEED);
		this.range = GameConstants.BASIC_TOWER_RANGE;
		this.coolDown = GameConstants.BASIC_TOWER_COOLDOWN;
		this.coolDownCounter = 0;
	}
	
	public BasicEnemy findNearestEnemy(ArrayList<BasicEnemy> enemies){
		double currentClosestDistance = Double.MAX_VALUE;
		BasicEnemy closestEnemy = null;
		for(BasicEnemy enemy : enemies){
			double distanceSquared = calculateDistanceSquared(location,enemy.getLocation());
			if (distanceSquared < currentClosestDistance){
				currentClosestDistance = distanceSquared;
				closestEnemy = enemy;
			}
		}
		return closestEnemy;
	}

	public CannonBall update(ArrayList<BasicEnemy> basicEnemies) {
		if (this.coolDownCounter == this.coolDown){
			BasicEnemy nearestEnemy = findNearestEnemy(basicEnemies);
			this.coolDownCounter =0;
			if (nearestEnemy != null){
				return new CannonBall(location,nearestEnemy.getLocation());
			}
			return null;
			
		}else{
			this.coolDownCounter +=1;
			return null;
		}

		
	}

	@Override
	void updateState() {}
}
