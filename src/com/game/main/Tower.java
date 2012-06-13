package com.game.main;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Point;


public class Tower {

	int x;
	int y;
	Point towerLocation;
	int range;
	int coolDown;
	int coolDownCounter;

	public Tower(int x, int y){
		this.x = x;
		this.y = y;
		this.range = 100;
		this.towerLocation = new Point(x,y);
		this.coolDown = 40;
		this.coolDownCounter = 0;
	}
	
	public BasicEnemy findNearestEnemy(ArrayList<BasicEnemy> enemies){
		double currentClosestDistance = Double.MAX_VALUE;
		BasicEnemy closestEnemy = null;
		for(BasicEnemy enemy : enemies){
			Point enemyLocation = new Point(enemy.x,enemy.y);
			double distanceSquared = calculateDistanceSquared(towerLocation,enemyLocation);
			if (distanceSquared < currentClosestDistance){
				currentClosestDistance = distanceSquared;
				closestEnemy = enemy;
			}
			
		}
		return closestEnemy;
	}

	private double calculateDistanceSquared(Point startLocation,Point endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}

	public CannonBall update(ArrayList<BasicEnemy> basicEnemies) {
		if (this.coolDownCounter == this.coolDown){
			BasicEnemy nearestEnemy = findNearestEnemy(basicEnemies);
			this.coolDownCounter =0;
			if (nearestEnemy != null){
				return new CannonBall(towerLocation,nearestEnemy.getLocation());
			}
			return null;
			
		}else{
			this.coolDownCounter +=1;
			return null;
		}

		
	}
}
