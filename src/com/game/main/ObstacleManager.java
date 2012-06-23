package com.game.main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObstacleManager {
	public ConcurrentHashMap<PixelPoint,Tower> towersHash = new ConcurrentHashMap<PixelPoint,Tower>();
	public ConcurrentHashMap<GridPoint,Object> obstaclesHash = new ConcurrentHashMap<GridPoint,Object>();
	public CopyOnWriteArrayList<Tower> towers = new CopyOnWriteArrayList<Tower>();

	public ObstacleManager(){}

	public void addTower(Tower tower){
		towers.add(tower);
		towersHash.put(tower.location, tower);
	}

	public boolean isTowerAt(PixelPoint p) {
		if (towersHash.get(p) == null){
			return false;
		}
		return true;
	}

	public boolean isObstacleAt(GridPoint p) {
		if (obstaclesHash.get(p) == null){
			return false;
		}
		return true;
	}

	public void remove(Tower tower) {
		towers.remove(tower);
		towersHash.remove(tower.location, tower);
		
	}
}
