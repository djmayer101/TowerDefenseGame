package com.game.main;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class World {

	public static int width;
	public static int height;
	public static int numColumns;
	public static int numRows;
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	
	private static final int GreenSquare = 0;
	private static final int TowerSquare = 1;
	
	public int[][] world;
	
	public World(int width, int height, int numColumns, int numRows){
		this.width = width;
		this.height = height;
		this.numColumns = numColumns;
		this.numRows = numRows;
		world = new int[numRows][numColumns];
		
		initializeWorld();
		
	}

	private void initializeWorld() {
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numColumns; j++){
				world[i][j] = GreenSquare;
			}
		}
		
	}
	
	
	
}
