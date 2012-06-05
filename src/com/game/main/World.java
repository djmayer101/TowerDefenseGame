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
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	
	private static final int GreenSquare = 0;
	private static final int TowerSquare = 1;
	
	public int[][] worldTerrainTileGrid;
	public static Hashtable<Integer, Point> worldTileID = new Hashtable<Integer, Point>();
	
	public World(int width, int height){
		this.width = width;
		this.height = height;
		this.numColumns = WorldView.numColumns;
		this.numRows = WorldView.numRows;
		worldTerrainTileGrid = new int[numRows][numColumns];
		initializeWorld();
		
	}

	private void initializeWorld() {
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				Log.e("i j =", "i=" + i + "  j=" + j) ;
				worldTerrainTileGrid[i][j] = GreenSquare;
			}
		}
		
	}
	
	public void setTower(int id){
		Point indeces = worldTileID.get(id);
		int i = indeces.x;
		int j = indeces.y;

		worldTerrainTileGrid[i][j] = TowerSquare;

		
		
		
	}
	public int getTerrainTileAt(int x, int y){
		return worldTerrainTileGrid[x][y];
	}
	
	
	
}
