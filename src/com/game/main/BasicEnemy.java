package com.game.main;

import com.game.main.BasicEnemy.Directions;

public class BasicEnemy {
	public enum Directions{NORTH,SOUTH, EAST,WEST};
	int x;
	int y;
	private int health;
	private Directions direction;
	//Bitmap bitmap;
	public BasicEnemy(int x, int y){
		this.x = x;
		this.y = y;
		this.health = 100;
		this.direction = Directions.SOUTH;
		
	}
	public void update() {
		if(direction == Directions.SOUTH){
			this.x =x + 1;
			this.y = y;
		}
		
	}
}

