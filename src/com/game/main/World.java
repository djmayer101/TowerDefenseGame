package com.game.main;

import java.util.ArrayList;

public class World {

	public int width;
	public int height;
	public int numColumns;
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	
	public World(int width, int height, int numColumns){
		this.width = width;
		this.height = height;
		this.numColumns = numColumns;
		
	}
	
	
	
}
