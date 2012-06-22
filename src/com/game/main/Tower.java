package com.game.main;

import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Point;


public class Tower extends BasicGameObject {

	int range= Constants.BASIC_TOWER_RANGE;
	int coolDown = Constants.BASIC_TOWER_COOLDOWN;
	int coolDownCounter;

	public Tower(Point location){
		super(location,Constants.BASIC_TOWER_SPEED);
		this.coolDownCounter = 0;
	}

	public BasicEnemy findNearestEnemy(CopyOnWriteArrayList<BasicEnemy> basicEnemies){
		double currentClosestDistance = Double.MAX_VALUE;
		BasicEnemy closestEnemy = null;
		for(BasicEnemy enemy : basicEnemies){
			double distanceSquared = TerrainMap.calculateDistanceSquared(location,enemy.getLocation());
			if (distanceSquared < currentClosestDistance){
				currentClosestDistance = distanceSquared;
				closestEnemy = enemy;
			}
		}
		if(currentClosestDistance<=range){
			return closestEnemy;
		}
		else{
			return null;
		}
	}

	public CannonBall update(CopyOnWriteArrayList<BasicEnemy> basicEnemies) {
		if (coolDownCounter == coolDown){
			BasicEnemy nearestEnemy = findNearestEnemy(basicEnemies);
			coolDownCounter =0;
			if (nearestEnemy != null){
				return new CannonBall(location,nearestEnemy.getLocation());
			}
			return null;

		}else{
			coolDownCounter +=1;
			return null;
		}


	}

	@Override
	void updateState() {}
}
