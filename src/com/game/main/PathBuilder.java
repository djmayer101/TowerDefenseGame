package com.game.main;

import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;
import android.graphics.Point;

public class PathBuilder {
	private TerrainMap terrainMap;
	private TowerManager towerManager;
	
	private GridNode startNode;
	private GridNode endNode;
	private CopyOnWriteArrayList<Point> path;
	
	private CopyOnWriteArrayList<GridNode> gridNodes;
	private Hashtable<Point, Boolean> pointInWorldHash;
	private Hashtable<Point, GridNode> pointToGridNode;
	

	public PathBuilder (TerrainMap terrainMap, TowerManager towerManager) {
		this.terrainMap = terrainMap;
		this.towerManager = towerManager;
	}

	public CopyOnWriteArrayList<Point> getPath(Point start, Point end) {
		if (start.x == end.x && start.y == end.y){
			path = new CopyOnWriteArrayList<Point>();
			return path;
		}

		initializeFields(start,end);
		initializeGrid();

		runAStarSearch();

		backTrackToCreatePath();

		return path;
	}

	private void backTrackToCreatePath() {
		if(endNode.getDistanceFromStart() < Integer.MAX_VALUE) {
			path.add(endNode.point);
			GridNode pointer = endNode;
			while(pointer != startNode) {
				pointer = pointer.getParent();
				if (pointer == null){
					break;
				}
				path.add(pointer.point);
			}
		}
	}

	private void runAStarSearch() {
		while (!gridNodes.isEmpty()) {
			GridNode currentNode = getLowestDistance();

			if(currentNode.distanceFromStart == Integer.MAX_VALUE) {
				break; 
			}
			gridNodes.remove(currentNode);
			CopyOnWriteArrayList<GridNode> myNeighbors = getNeighbors(currentNode);

			for(GridNode neighbor: myNeighbors) {
				relaxNeighbors(currentNode, neighbor);
			}
		}
	}

	private void relaxNeighbors(GridNode currentNode, GridNode neighbor) {
		int alt = currentNode.distanceFromStart +1;
		if(alt < neighbor.distanceFromStart){
			neighbor.setDistanceFromStart(alt);
			neighbor.setParent(currentNode);
			neighbor.setdistanceToGoal((int) (alt + TerrainMap.calculateDistanceSquared(neighbor.point, this.endNode.point)));
		}
	}

	private void initializeGrid() {
		for(int i=0; i<terrainMap.worldTerrainGrid.length; i++) {
			for(int j=0; j<terrainMap.worldTerrainGrid[0].length; j++) {
				if(startNode.point.equals(new Point(i,j))){
					pointInWorldHash.put(startNode.point, true);
					pointToGridNode.put(startNode.point, startNode);
					gridNodes.add(startNode);
				}
				else if(endNode.point.equals(new Point(i,j))) {
					pointInWorldHash.put(endNode.point, true);
					pointToGridNode.put(endNode.point, endNode);
					gridNodes.add(endNode);
				}
				else if(towerManager.isTowerAt(TerrainMap.scaleGridPointToPixel(new Point(i,j))) == false){
					GridNode newNode = new GridNode(new Point(i, j));
					pointInWorldHash.put(newNode.point, true);
					pointToGridNode.put(newNode.point, newNode);
					gridNodes.add(newNode);
				}
				else {
					GridNode newNode = new GridNode(new Point(i, j));
					pointInWorldHash.put(newNode.point, false);
				}
			}
		}

	}

	private GridNode getLowestDistance() {		
		GridNode lowest = gridNodes.get(0);
		for (GridNode node :gridNodes){
			if(lowest.distanceToGoal > node.distanceToGoal){
				lowest = node;
			}
		}
		return lowest;
	}


	private CopyOnWriteArrayList<GridNode> getNeighbors(GridNode v) {
		CopyOnWriteArrayList<GridNode> neighbors = new CopyOnWriteArrayList<GridNode>();

		Point n1 = new Point(v.point.x, v.point.y+1);
		Point n2 = new Point(v.point.x+1, v.point.y);
		Point n3 = new Point(v.point.x, v.point.y-1);
		Point n4 = new Point(v.point.x-1, v.point.y);

		if(pointInWorldHash.get(n1)!=null && pointInWorldHash.get(n1)) {
			neighbors.add(pointToGridNode.get(n1));
		}
		if(pointInWorldHash.get(n2)!=null && pointInWorldHash.get(n2)) {
			neighbors.add(pointToGridNode.get(n2));
		}
		if(pointInWorldHash.get(n3)!=null && pointInWorldHash.get(n3)) {
			neighbors.add(pointToGridNode.get(n3));
		}
		if(pointInWorldHash.get(n4)!=null && pointInWorldHash.get(n4)) {
			neighbors.add(pointToGridNode.get(n4));
		}
		return neighbors;
	}

	private void initializeFields(Point start, Point end) {
		startNode = new GridNode(start);
		startNode.setDistanceFromStart(0);
		startNode.distanceToGoal = (int) TerrainMap.calculateDistanceSquared(start, end);
		
		endNode = new GridNode(end);

		path = new CopyOnWriteArrayList<Point>();
		pointInWorldHash = new Hashtable<Point, Boolean>();
		pointToGridNode = new Hashtable<Point, GridNode>();
		gridNodes = new CopyOnWriteArrayList<GridNode>();


	}

}
