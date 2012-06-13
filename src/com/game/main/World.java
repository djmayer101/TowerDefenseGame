package com.game.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultDirectedGraph;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;


public class World {


	public int width;
	public int height;
	public int numColumns;
	public int numRows;
	private int squareSize;
	private Point focus;
	private PathBuilder myPath;

	public Tower[][] worldTowerGrid;
	public ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
	ArrayList<CannonBall> finishedCannonBalls = new ArrayList<CannonBall>();;


	public World(int width, int height){
		this.width = width;
		this.height = height;
		Log.e("board dimensions", "height: " + height + " width " + width);
		this.numColumns = 9;
		this.numRows = 11;
		this.squareSize = 50;
		worldTowerGrid = new Tower[numRows][numColumns];
		BasicEnemy enemy = new BasicEnemy(0,0);
		basicEnemies.add(enemy);
		
		
		myPath = new PathBuilder(this);
	}



	public void setTower(Tower tower) {
		worldTowerGrid[(int) Math.floor(tower.y / squareSize)][(int) Math.floor(tower.x / squareSize)] = tower;
		towers.add(tower);

	}

	public Tower getTowerAt(Point p){
		return worldTowerGrid[(int) Math.floor(p.y / squareSize)][(int) Math.floor(p.x / squareSize)];
	}

	public Point computeNearestTowerLocation(Point p) {
		int nearestTowerLocationX = squareSize*((int) Math.floor(p.x / squareSize));
		int nearestTowerLocationY = squareSize*((int) Math.floor(p.y / squareSize));
		return new Point(nearestTowerLocationX, nearestTowerLocationY);
	}

	public boolean isTowerAt(Point p) {
		Log.e("pointcheck", "x=" + p.x + "  y=" + p.y);
		if((p.x >= numColumns*squareSize) || (p.y >= numRows*squareSize)){
			return true;
		}
		if (worldTowerGrid[(int) Math.floor(p.y / squareSize)][(int) Math.floor(p.x / squareSize)]==null)
			return false;
		else 
			return true;
	}

	public void setFocus(Point p) {
		this.focus = p;
	}




	public void updatePhysics() {
		ArrayList<BasicEnemy> finishedEnemies = new ArrayList<BasicEnemy>();
		for (BasicEnemy enemy: basicEnemies){
			enemy.update();
			if (ObjectOutOfBounds(enemy)){
				finishedEnemies.add(enemy);
			}
		}
		for (Tower tower: towers){
			CannonBall cannonBall = tower.update(basicEnemies);
			if(cannonBall != null){
				cannonBalls.add(cannonBall);
			}
		}
		finishedCannonBalls = new ArrayList<CannonBall>();
		for (CannonBall cannonBall : cannonBalls){
			cannonBall.update();
			if (cannonBall.state == CannonBall.State.DONE){
				finishedCannonBalls.add(cannonBall);
			}
		}

		for (BasicEnemy enemy: finishedEnemies){
			basicEnemies.remove(enemy);
		}

		for (CannonBall cannonBall : finishedCannonBalls){
			cannonBalls.remove(cannonBall);
		}

	}



	private boolean ObjectOutOfBounds(BasicEnemy enemy) {
		if (enemy.x < 0 || enemy.x > this.width || enemy.y < 0 || enemy.y > this.height){
			return true;
		}
		return false;
	}



	public Point getFocus() {
		return this.focus;
	}

	public ArrayList<Point> getPath(Point start, Point end){
		ArrayList<Point> path = myPath.run(start, end);
		if (path.size()==0){
			//no possible route
		}

		return path;
	}


	private class PathBuilder {
		private World myWorld;
		private ArrayList<GridNode> vertexes;
		private Hashtable<Point, Boolean> mapping;
		private Hashtable<Point, GridNode> pointToGridNode;
		private GridNode startPoint;
		private GridNode endPoint;


		PathBuilder (World world) {
			myWorld = world;
		}

		public ArrayList<Point> run(Point start, Point end) {
			startPoint = new GridNode(start);
			startPoint.setDistance(0);
			endPoint = new GridNode(end);
			ArrayList<Point> path = new ArrayList<Point>();
			initialize();

			while (!vertexes.isEmpty()) {
				GridNode v = getLowestDistance();
				if(v.distanceToGoal == Integer.MAX_VALUE) {
					break; 
				}
				ArrayList<GridNode> myNeighbors = getNeighbors(v);
				if(!myNeighbors.isEmpty()) {
					for(GridNode neighbor: myNeighbors) {
						int alt = neighbor.distanceToGoal + 1;
						if(alt < v.distanceToGoal){
							v.setDistance(alt);
							v.setParent(neighbor);					
						}
					}
				}
			}
			if(endPoint.getDistance() < Integer.MAX_VALUE) {
				path = new ArrayList<Point>();
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

			if(mapping.get(n1)) {
				neighbors.add(pointToGridNode.get(n1));
			}
			if(mapping.get(n2)) {
				neighbors.add(pointToGridNode.get(n2));
			}
			if(mapping.get(n3)) {
				neighbors.add(pointToGridNode.get(n3));
			}
			if(mapping.get(n4)) {
				neighbors.add(pointToGridNode.get(n4));
			}
			return neighbors;
		}

		private void initialize() {
			mapping = new Hashtable<Point, Boolean>();
			pointToGridNode = new Hashtable<Point, GridNode>();
			vertexes = new ArrayList<GridNode>();
			for(int i=0; i<worldTowerGrid.length; i++) {
				for(int j=0; j<worldTowerGrid[0].length; j++) {
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
					else if(worldTowerGrid[i][j]==null) {
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

		private class GridNode implements Comparable {

			private Point me;
			private GridNode parent;
			private int distanceToGoal;

			GridNode(Point me) {
				this.me = me;
				parent = null;
				distanceToGoal = Integer.MAX_VALUE;
			}

			public GridNode getParent () {
				return parent;
			}

			public void setParent (GridNode newParent) {
				parent = newParent;
			}

			public int getDistance () {
				return distanceToGoal;
			}

			public void setDistance (int newDistance) {
				distanceToGoal = newDistance;
			}

			@Override
			public int compareTo(Object arg0) {
				if (this.distanceToGoal == ((GridNode) arg0).distanceToGoal) {
					return 0;
				}
				else if (this.distanceToGoal > ((GridNode) arg0).distanceToGoal) {
					return 1;			
				}
				else {
					return -1;
				}
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getOuterType().hashCode();
				result = prime * result + distanceToGoal;
				result = prime * result + ((me == null) ? 0 : me.hashCode());
				result = prime * result
						+ ((parent == null) ? 0 : parent.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				GridNode other = (GridNode) obj;
				if (!getOuterType().equals(other.getOuterType()))
					return false;
				if (distanceToGoal != other.distanceToGoal)
					return false;
				if (me == null) {
					if (other.me != null)
						return false;
				} else if (!me.equals(other.me))
					return false;
				if (parent == null) {
					if (other.parent != null)
						return false;
				} else if (!parent.equals(other.parent))
					return false;
				return true;
			}

			private PathBuilder getOuterType() {
				return PathBuilder.this;
			}
		}
	}
}
