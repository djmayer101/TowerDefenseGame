package com.game.main;

import java.util.ArrayList;
import java.util.Hashtable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

public class World {

	public static int width;
	public static int height;
	public static int numColumns;
	public static int numRows;
	public int squareSize;
	public Point focus;

	public Tower[][] worldTowerGrid;
	public ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();


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
	}



	public void setTower(Tower tower) {
		worldTowerGrid[(int) Math.floor(tower.y / squareSize)][(int) Math.floor(tower.x / squareSize)] = tower;

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
		for (BasicEnemy enemy: basicEnemies){
			enemy.update();
			if (enemyOutOfBounds(enemy)){
				basicEnemies.remove(enemy);
			}
		}


	}



	private boolean enemyOutOfBounds(BasicEnemy enemy) {
		if (enemy.x < 0 || enemy.x > this.width || enemy.y < 0 || enemy.y > this.height){
			return true;
		}
		return false;
	}


}
