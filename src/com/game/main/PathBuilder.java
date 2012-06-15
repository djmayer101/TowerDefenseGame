package com.game.main;

import java.util.ArrayList;
import java.util.Hashtable;

import android.graphics.Point;

public class PathBuilder {
	private TerrainMap myTerrainMap;
	private ArrayList<GridNode> vertexes;
	private Hashtable<Point, Boolean> mapping;
	private Hashtable<Point, GridNode> pointToGridNode;
	private GridNode startPoint;
	private GridNode endPoint;


	PathBuilder (TerrainMap terrainMap) {
		myTerrainMap = terrainMap;
	}

	public ArrayList<Point> run(Point start, Point end) {
		startPoint = new GridNode(start);
		startPoint.setDistance(0);
		endPoint = new GridNode(end);
		ArrayList<Point> path = new ArrayList<Point>();
		initialize();

		while (!vertexes.isEmpty()) {
			GridNode v = getLowestDistance();
			vertexes.remove(v);
			if(v.distanceToGoal == Integer.MAX_VALUE) {
				break; 
			}
			ArrayList<GridNode> myNeighbors = getNeighbors(v);
			if(!myNeighbors.isEmpty()) {
				for(GridNode neighbor: myNeighbors) {
					int alt = v.distanceToGoal + 1;
					if(alt < neighbor.distanceToGoal){
						neighbor.setDistance(alt);
						neighbor.setParent(v);
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
			if(lowest.getDistance() > vertexes.get(i).getDistance()){
				lowest = vertexes.get(i);
			}
		}
		return lowest;
	}


	private ArrayList<GridNode> getNeighbors(GridNode v) {
		ArrayList<GridNode> neighbors = new ArrayList<GridNode>();

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
		vertexes = new ArrayList<GridNode>();
		for(int i=0; i<myTerrainMap.worldTowerGrid.length; i++) {
			for(int j=0; j<myTerrainMap.worldTowerGrid[0].length; j++) {
				if(startPoint.me.equals(new Point(j,i))){
					mapping.put(startPoint.me, true);
					pointToGridNode.put(startPoint.me, startPoint);
					vertexes.add(startPoint);
				}
				else if(endPoint.me.equals(new Point(j,i))) {
					mapping.put(endPoint.me, true);
					pointToGridNode.put(endPoint.me, endPoint);
					vertexes.add(endPoint);
				}
				else if(myTerrainMap.worldTowerGrid[i][j]==null) {
					GridNode newNode = new GridNode(new Point(j, i));
					mapping.put(newNode.me, true);
					pointToGridNode.put(newNode.me, newNode);
					vertexes.add(newNode);
				}
				else {
					GridNode newNode = new GridNode(new Point(j, i));
					mapping.put(newNode.me, false);
				}
			}
		}
		startPoint.distanceToGoal = 0;
	}

}
