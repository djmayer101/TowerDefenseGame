package com.game.main;

import java.util.ArrayList;
import java.util.Collections;
import android.graphics.Point;
import android.util.Log;


public class TerrainMap {


	public int width;
	public int height;
	public int numColumns;
	public int numRows;
	int squareSize;
	private Point focus;
	private PathBuilder myPath;

	public Tower[][] worldTowerGrid;



	public TerrainMap(int width, int height){
		this.width = width;
		this.height =  height;
		Log.e("board dimensions", "height: " + height + " width " + width);
		this.numColumns = 9;
		this.numRows = 11;
		this.squareSize = Constants.GRID_SQUARE_SIZE;
		worldTowerGrid = new Tower[numRows][numColumns];

		
		myPath = new PathBuilder(this);
	}





	public Tower getTowerAt(Point p){
		int y = (int) Math.floor(p.y / squareSize);
		int x = (int) Math.floor(p.x / squareSize);
		Log.e("getTowerAt", "x=" + x + " y=" + y);
		return worldTowerGrid[(int) Math.floor(p.y / squareSize)][(int) Math.floor(p.x / squareSize)];
	}

	public Point computeNearestTowerLocation(Point p) {
		int GridX = ((int) Math.floor(p.x / squareSize));
		int GridY = ((int) Math.floor(p.y / squareSize));
		if(GridX>=numColumns){
			GridX = numColumns-1;
		}
		if(GridY>=numRows){
			GridY = numRows-1;
		}
		int nearestTowerLocationX = squareSize*GridX;
		int nearestTowerLocationY = squareSize*GridY;
		
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




	



	public boolean LocationOutOfBounds(Point location) {
		if (location.x < 0 || location.x > this.width || location.y < 0 || location.y > this.height){
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

		Collections.reverse(path);
		return path;
	}

	


}
