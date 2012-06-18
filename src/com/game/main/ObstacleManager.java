package com.game.main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Point;

public class ObstacleManager {
	public ConcurrentHashMap<Point,Tower> towersHash = new ConcurrentHashMap<Point,Tower>();
	public ConcurrentHashMap<Point,Object> obstaclesHash = new ConcurrentHashMap<Point,Object>();
	public CopyOnWriteArrayList<Tower> towers = new CopyOnWriteArrayList<Tower>();
	
	public ObstacleManager(){}
	
	public void addTower(Tower tower){
		towers.add(tower);
		towersHash.put(tower.location, tower);
	}
	
	public boolean isTowerAt(Point p) {
		if (towersHash.get(p) == null){
			return false;
		}
		return true;
	}

	public boolean isObstacleAt(Point p) {
		if (obstaclesHash.get(p) == null){
			return false;
		}
		return true;
	}
}
