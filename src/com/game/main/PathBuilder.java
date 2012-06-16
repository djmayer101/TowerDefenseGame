package com.game.main;

import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Point;

public class PathBuilder {
	private TerrainMap terrainMap;
	private CopyOnWriteArrayList<GridNode> vertexes;
	private Hashtable<Point, Boolean> mapping;
	private Hashtable<Point, GridNode> pointToGridNode;
	private GridNode startPoint;
	private GridNode endPoint;
	private TowerManager towerManager;


	PathBuilder (TerrainMap terrainMap, TowerManager towerManager) {
		this.terrainMap = terrainMap;
		this.towerManager = towerManager;
	}
	
	public CopyOnWriteArrayList<Point> getPath(Point start, Point end){
		CopyOnWriteArrayList<Point> path = run(start, end);
		if (path.size()==0){
			//no possible route
		}
		//Collections.reverse(path);
		return path;
	}

	public CopyOnWriteArrayList<Point> run(Point start, Point end) {
		startPoint = new GridNode(start);
		startPoint.setDistance(0);
		startPoint.f_score = (int) TerrainMap.calculateDistanceSquared(start, end);
		endPoint = new GridNode(end);
		CopyOnWriteArrayList<Point> path = new CopyOnWriteArrayList<Point>();
		initialize();

		while (!vertexes.isEmpty()) {
			GridNode v = getLowestDistance();
			vertexes.remove(v);
			if(v.distanceFromStart == Integer.MAX_VALUE) {
				break; 
			}
			CopyOnWriteArrayList<GridNode> myNeighbors = getNeighbors(v);
			if(!myNeighbors.isEmpty()) {
				for(GridNode neighbor: myNeighbors) {
					int alt = v.distanceFromStart +1;
					if(alt < neighbor.distanceFromStart){
						neighbor.setDistance(alt);
						neighbor.setParent(v);
						neighbor.setF_score((int) (alt + TerrainMap.calculateDistanceSquared(neighbor.me, end)));
					}
				}
			}
		}
		if(endPoint.getDistance() < Integer.MAX_VALUE) {
			path.add(endPoint.me);
			GridNode pointer = endPoint;
			while(pointer != startPoint) {
				pointer = pointer.getParent();
				path.add(pointer.me);
			}
		}
		return path;
	}
	
	private GridNode getLowestDistance() {
		if (vertexes.isEmpty()) {
			return null;
		}
		GridNode lowest = vertexes.get(0);
		for (int i=1; i<vertexes.size(); i++) {
			if(lowest.f_score > vertexes.get(i).f_score){
				lowest = vertexes.get(i);
			}
		}
		return lowest;
	}


	private CopyOnWriteArrayList<GridNode> getNeighbors(GridNode v) {
		CopyOnWriteArrayList<GridNode> neighbors = new CopyOnWriteArrayList<GridNode>();

		Point n1 = new Point(v.me.x, v.me.y+1);
		Point n2 = new Point(v.me.x+1, v.me.y);
		Point n3 = new Point(v.me.x, v.me.y-1);
		Point n4 = new Point(v.me.x-1, v.me.y);

		if(mapping.get(n1)!=null && mapping.get(n1)) {
			neighbors.add(pointToGridNode.get(n1));
		}
		if(mapping.get(n2)!=null && mapping.get(n2)) {
			neighbors.add(pointToGridNode.get(n2));
		}
		if(mapping.get(n3)!=null && mapping.get(n3)) {
			neighbors.add(pointToGridNode.get(n3));
		}
		if(mapping.get(n4)!=null && mapping.get(n4)) {
			neighbors.add(pointToGridNode.get(n4));
		}
		return neighbors;
	}

	private void initialize() {
		mapping = new Hashtable<Point, Boolean>();
		pointToGridNode = new Hashtable<Point, GridNode>();
		vertexes = new CopyOnWriteArrayList<GridNode>();
		for(int i=0; i<terrainMap.worldTerrainGrid.length; i++) {
			for(int j=0; j<terrainMap.worldTerrainGrid[0].length; j++) {
				if(startPoint.me.equals(new Point(i,j))){
					mapping.put(startPoint.me, true);
					pointToGridNode.put(startPoint.me, startPoint);
					vertexes.add(startPoint);
				}
				else if(endPoint.me.equals(new Point(i,j))) {
					mapping.put(endPoint.me, true);
					pointToGridNode.put(endPoint.me, endPoint);
					vertexes.add(endPoint);
				}
				else if(towerManager.isTowerAt(TerrainMap.scaleGridPointToPixel(new Point(i,j))) == false){
					GridNode newNode = new GridNode(new Point(i, j));
					mapping.put(newNode.me, true);
					pointToGridNode.put(newNode.me, newNode);
					vertexes.add(newNode);
				}
				else {
					GridNode newNode = new GridNode(new Point(i, j));
					mapping.put(newNode.me, false);
				}
			}
		}
		startPoint.distanceFromStart = 0;
	}
	



}
