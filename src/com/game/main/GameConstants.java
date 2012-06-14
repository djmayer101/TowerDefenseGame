package com.game.main;

public class GameConstants {

	public static final double SOUTH = 270;
	public static final double NORTH = 90;
	public static final double EAST = 0;
	public static final double WEST = 180;
	
	public static final double BASIC_CANNON_SPEED = 10;
	public static final double BASIC_ENEMY_SPEED = 1;
	public static final double BASIC_TOWER_SPEED = 0;
	
	public static final int BASIC_TOWER_RANGE = 100;
	public static final int BASIC_TOWER_COOLDOWN = 30;
	
	public static enum State{LAUNCH,TRAVEL,EXPLODE,DONE, INITIAL};
	
}
