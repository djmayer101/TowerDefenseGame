package com.game.main;

import java.util.concurrent.CopyOnWriteArrayList;

import com.game.main.Constants.TowerType;

import android.graphics.Point;


public class Tower extends BasicGameObject {

	private int range;
	private int coolDown;
	private int coolDownCounter;
	private TowerType towerType;
	private int damage;

	public Tower(Point location, Constants.TowerType type){
		super(location,Constants.BASIC_TOWER_SPEED);
		this.coolDownCounter = 0;
		this.towerType = type;
		setStats();
	}

	private void setStats() {
		switch(towerType){
		case	BASIC:	
			coolDown = Constants.BASIC_TOWER_COOLDOWN;
			range = Constants.BASIC_TOWER_RANGE;
			damage = Constants.BASIC_TOWER_DAMAGE;
		break;
		case	FAST:	
			coolDown = Constants.FAST_TOWER_COOLDOWN;
			range = Constants.FAST_TOWER_RANGE;
			damage = Constants.FAST_TOWER_DAMAGE;
		break;
		case	HEAVY:	
			coolDown = Constants.HEAVY_TOWER_COOLDOWN;
			range = Constants.HEAVY_TOWER_RANGE;
			damage = Constants.HEAVY_TOWER_DAMAGE;
		break;
		case	ICE:	
			coolDown = Constants.ICE_TOWER_COOLDOWN;
			range = Constants.ICE_TOWER_RANGE;
			damage = Constants.ICE_TOWER_DAMAGE;
		break;
		case	FIRE:	
			coolDown = Constants.FIRE_TOWER_COOLDOWN;
			range = Constants.FIRE_TOWER_RANGE;
			damage = Constants.FIRE_TOWER_DAMAGE;
		break;
		}
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
				return new CannonBall(location,nearestEnemy.getLocation(), this.damage);
			}
			return null;

		}else{
			coolDownCounter +=1;
			return null;
		}


	}
	public TowerType getTowerType(){
		return towerType;
	}

	@Override
	void updateState() {}
	
	public static int getTowerCost(Constants.TowerType towerType){
		int cost = 0;
		switch(towerType){
		case	BASIC:		cost = Constants.BASIC_TOWER_COST;
		break;
		case	FAST:		cost = Constants.FAST_TOWER_COST;
		break;
		case	HEAVY:		cost = Constants.HEAVY_TOWER_COST;
		break;
		case	ICE:		cost = Constants.ICE_TOWER_COST;
		break;
		case	FIRE:		cost = Constants.FIRE_TOWER_COST;
		break;
		}
		return cost;
	}
}
