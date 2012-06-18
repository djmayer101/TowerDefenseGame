package com.game.main;

import android.graphics.Point;


public class TerrainMap {

	private int world_width;
	private int world_height;

	private int numColumns;
	private int numRows;
	
	int squareSize;

	private Point focus;

	public Constants.DrawObject[][] worldTerrainGrid;
	private ObstacleManager myObstacleManager;

	public TerrainMap(int width, int height, ObstacleManager myObstacleManager){
		this.myObstacleManager = myObstacleManager;
		this.world_width = Constants.WORLD_WIDTH;;
		this.world_height =  Constants.WORLD_HEIGHT;

		this.numColumns = Constants.NUM_COLUMNS;
		this.numRows = Constants.NUM_ROWS;
		
		this.squareSize = Constants.GRID_SQUARE_SIZE;
		worldTerrainGrid = new Constants.DrawObject[numColumns][numRows];

		for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				if (i %2 == 0 && j %2 == 0){
					worldTerrainGrid[i][j] = Constants.DrawObject.GRASSTILE_1;
				}else{
					worldTerrainGrid[i][j] = Constants.DrawObject.GRASSTILE_2;
				}
				if (i == 0 || j == 0 || i ==Constants.NUM_COLUMNS-1 || j == Constants.NUM_ROWS-1){
					worldTerrainGrid[i][j] = Constants.DrawObject.BORDERTILE;
					myObstacleManager.obstaclesHash.put(new Point(i,j),true);
				}
				if(i == 1 && j == 1){
					worldTerrainGrid[i][j] = Constants.DrawObject.STARTTILE;
				}
				if(i == Constants.NUM_COLUMNS-2 && j == Constants.NUM_ROWS-2){
					worldTerrainGrid[i][j] = Constants.DrawObject.ENDTILE;
				}

			}
		}
	}

	public void setFocus(Point p) {
		this.focus = p;
	}


	public boolean LocationOutOfBounds(Point location) {
		if (location.x < 0 || location.x > this.world_width || location.y < 0 || location.y > this.world_height){
			return true;
		}
		return false;
	}

	public Point getFocus() {
		return this.focus;
	}
	
	protected static double calculateDistanceSquared(Point startLocation,Point endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}
	
	public static Point scaleGridPointToPixel(Point point){
		return new Point(point.x*Constants.GRID_SQUARE_SIZE, point.y*Constants.GRID_SQUARE_SIZE);
	}
	
	public static Point scalePixelToGridPoint(Point point){
		return new Point((int) Math.floor(point.x/Constants.GRID_SQUARE_SIZE), (int) Math.floor(point.y/Constants.GRID_SQUARE_SIZE));
	}

}
