package com.game.main;

import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class PathBuilder {
	private TerrainMap terrainMap;
	private ObstacleManager obstacleManager;

	private GridNode startNode;
	private GridNode endNode;
	private CopyOnWriteArrayList<GridPoint> path;

	private CopyOnWriteArrayList<GridNode> gridNodes;
	private Hashtable<GridPoint, Boolean> pointInWorldHash;
	private Hashtable<GridPoint, GridNode> pointToGridNode;

	private Semaphore pathMutex;


	public PathBuilder (TerrainMap terrainMap, ObstacleManager obstacleManager) {
		this.terrainMap = terrainMap;
		this.obstacleManager = obstacleManager;
		pathMutex = new Semaphore(1, true);
	}

	public CopyOnWriteArrayList<GridPoint> safeGetPath(GridPoint start, GridPoint end) {
		initializeFields(start,end);
		initializeGrid();

		runAStarSearch();

		backTrackToCreatePath();
		reversePath();

		return path;

	}

	public CopyOnWriteArrayList<GridPoint> getPath(GridPoint start, GridPoint end) {
		CopyOnWriteArrayList<GridPoint> myPath = null;
		if (start.equals(end)){
			myPath = new CopyOnWriteArrayList<GridPoint>();
			return myPath;
		}

		try {
			pathMutex.acquire();
			myPath = safeGetPath(start, end);
			pathMutex.release();
		} catch (InterruptedException e) {
			System.out.println("Exception " + e.toString());
		}
		if (myPath == null){
			//bad things happenned
		}
		return myPath;
	}

	private void reversePath() {
		CopyOnWriteArrayList<GridPoint> pathTemp = new CopyOnWriteArrayList<GridPoint>();
		for (GridPoint p : path){
			pathTemp.add(0,p);
		}
		path = pathTemp;

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

			if(currentNode.getDistanceFromStart() == Integer.MAX_VALUE) {
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
		int alt = currentNode.getDistanceFromStart() +1;
		if(alt < neighbor.getDistanceFromStart()){
			neighbor.setDistanceFromStart(alt);
			neighbor.setParent(currentNode);
			neighbor.setEstimatedTotalDistance((int) (alt + Math.sqrt(TerrainMap.calculateDistanceSquared(neighbor.point, this.endNode.point))));
		}
	}

	private void initializeGrid() {
		for(int i=0; i<terrainMap.worldTerrainGrid.length; i++) {
			for(int j=0; j<terrainMap.worldTerrainGrid[0].length; j++) {
				if(startNode.point.equals(new GridPoint(i,j))){
					pointInWorldHash.put(startNode.point, true);
					pointToGridNode.put(startNode.point, startNode);
					gridNodes.add(startNode);
				}
				else if(endNode.point.equals(new GridPoint(i,j))) {
					pointInWorldHash.put(endNode.point, true);
					pointToGridNode.put(endNode.point, endNode);
					gridNodes.add(endNode);
				}
				else if((obstacleManager.isTowerAt((new GridPoint(i,j)).scaleToPixelPoint()) == false) &&
						(obstacleManager.isObstacleAt((new GridPoint(i,j))) == false)){
					GridNode newNode = new GridNode(new GridPoint(i, j));
					pointInWorldHash.put(newNode.point, true);
					pointToGridNode.put(newNode.point, newNode);
					gridNodes.add(newNode);
				}
				else {
					GridNode newNode = new GridNode(new GridPoint(i, j));
					pointInWorldHash.put(newNode.point, false);
				}
			}
		}

	}

	private GridNode getLowestDistance() {		
		GridNode lowest = gridNodes.get(0);
		for (GridNode node :gridNodes){
			if(lowest.getEstimatedTotalDistance() > node.getEstimatedTotalDistance()){
				lowest = node;
			}
		}
		return lowest;
	}


	private CopyOnWriteArrayList<GridNode> getNeighbors(GridNode v) {
		CopyOnWriteArrayList<GridNode> neighbors = new CopyOnWriteArrayList<GridNode>();

		GridPoint n1 = new GridPoint(v.point.x, v.point.y+1);
		GridPoint n2 = new GridPoint(v.point.x+1, v.point.y);
		GridPoint n3 = new GridPoint(v.point.x, v.point.y-1);
		GridPoint n4 = new GridPoint(v.point.x-1, v.point.y);

		/*if(pointInWorldHash.get(n1)!=null && pointInWorldHash.get(n1)) {
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
		}*/
		if(pointInWorldHash.get(n1)!=null){
			if(pointInWorldHash.get(n1)) {
			neighbors.add(pointToGridNode.get(n1));
			}
		}
		if(pointInWorldHash.get(n2)!=null){
			if(pointInWorldHash.get(n2)) {
			neighbors.add(pointToGridNode.get(n2));
			}
		}
		if(pointInWorldHash.get(n3)!=null){
			if(pointInWorldHash.get(n3)) {
			neighbors.add(pointToGridNode.get(n3));
			}
		}
		if(pointInWorldHash.get(n4)!=null){
			if(pointInWorldHash.get(n4)) {
			neighbors.add(pointToGridNode.get(n4));
			}
		}
		return neighbors;
	}

	private void initializeFields(GridPoint start, GridPoint end) {
		startNode = new GridNode(start);
		startNode.setDistanceFromStart(0);
		startNode.setEstimatedTotalDistance((int) Math.sqrt(TerrainMap.calculateDistanceSquared(start, end)));

		endNode = new GridNode(end);

		path = new CopyOnWriteArrayList<GridPoint>();
		pointInWorldHash = new Hashtable<GridPoint, Boolean>();
		pointToGridNode = new Hashtable<GridPoint, GridNode>();
		gridNodes = new CopyOnWriteArrayList<GridNode>();
	}

}
