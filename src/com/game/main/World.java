package com.game.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Set;

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
		BasicEnemy enemy = new BasicEnemy(0,300);
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



}
