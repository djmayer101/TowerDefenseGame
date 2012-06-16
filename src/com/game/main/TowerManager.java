package com.game.main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Point;

public class TowerManager {
	public ConcurrentHashMap<Point,Tower> towersHash = new ConcurrentHashMap<Point,Tower>();
	public CopyOnWriteArrayList<Tower> towers = new CopyOnWriteArrayList<Tower>();
	
	public TowerManager(){
		
	}
	
	
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
}
