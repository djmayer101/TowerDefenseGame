package com.game.main;

public class Constants {

	public static final double SOUTH = 270;
	public static final double NORTH = 90;
	public static final double EAST = 0;
	public static final double WEST = 180;
	
	public static final double BASIC_CANNON_SPEED = 10;
	public static final double BASIC_ENEMY_SPEED = 1;
	public static final double BASIC_TOWER_SPEED = 0;
	
	public static final int BASIC_TOWER_RANGE = 100;
	public static final int BASIC_TOWER_COOLDOWN = 30;
	public static final int WORLD_WIDTH = 0;
	public static final int WORLD_HEIGHT = 0;
	
	public static final String GAME_NAME = "TowerDefense";
	public static final int OBJECT_CELL_SIDE_LENGTH = 44;
	public static final int CURSOR_CELL_SIDE_LENGTH = 50;
	public static final int IMAGE_OFFSET = 3;
	public static final long UPDATE_DELAY = 40;
	
	public static final int CANNONBALL_EXPLOSION_RADIUS_SQUARED = 1625;
	public static final int CANNONBALL_DAMAGE = 20;
	
	public static enum State{LAUNCH,TRAVEL,EXPLODE,DONE, INITIAL};
	
	public static enum DrawObject{CURSOR,TOWER,CANNON_BALL,CANNON_BALL_EXPLOSION, BASIC_ENEMY};
	
}
