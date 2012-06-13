package com.game.main;

import android.graphics.Point;

import com.game.main.BasicEnemy.Directions;

public class BasicEnemy {
	public enum Directions{NORTH,SOUTH, EAST,WEST};
	int x;
	int y;
	private Point location;
	private int health;
	private Directions direction;
	
	public BasicEnemy(int x, int y){
		this.x = x;
		this.y = y;
		this.health = 100;
		this.direction = Directions.SOUTH;
		this.location = new Point(x,y);
		
	}
	public void update() {
		if(direction == Directions.SOUTH){
			this.x =x + 1;
			this.y = y;
		}
		this.location = new Point(x,y);
		
	}
	public Point getLocation(){
		return location;
	}
}

