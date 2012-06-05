package com.game.main;

import java.util.ArrayList;
import java.util.Hashtable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class World {

	public static int width;
	public static int height;
	public static int numColumns;
	public static int numRows;
	public int squareSize;
	
	public Tower[][] worldTowerGrid;
	//public static Hashtable<Integer, Point> worldTileID = new Hashtable<Integer, Point>();
	
	public World(int width, int height){
		this.width = width;
		this.height = height;
		this.numColumns = 10;
		this.numRows = 15;
		this.squareSize = Math.max((width / numColumns), (height / numRows));
		worldTowerGrid = new Tower[numRows][numColumns];
		initializeWorld();
		
	}

	private void initializeWorld() {
		/*for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				worldTowerGrid[i][j] = null;
			}
		}*/
		
	}
	
	public void setTower(Tower tower) {
		worldTowerGrid[(int) Math.floor(tower.x / squareSize)][(int) Math.floor(tower.y / squareSize)] = tower;

	}
	
	public Tower getTowerAt(Point p){
		return worldTowerGrid[(int) Math.floor(p.x / squareSize)][(int) Math.floor(p.y / squareSize)];
	}
	
	public Point computeNearestTowerLocation(Point p) {

		int nearestTowerLocationX = squareSize*((int) Math.floor(p.x / squareSize));
		int nearestTowerLocationY = squareSize*((int) Math.floor(p.y / squareSize));
		
		return new Point(nearestTowerLocationX, nearestTowerLocationY);
	}
	
	public boolean isTowerAt(Point p) {
		
		if (worldTowerGrid[(int) Math.floor(p.x / squareSize)][(int) Math.floor(p.y / squareSize)]==null)
			return false;
		else 
			return true;
	}
	
	
	
	
}
