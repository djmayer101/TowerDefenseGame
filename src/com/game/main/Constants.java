package com.game.main;

import android.graphics.Point;

public class Constants {

	public static final double SOUTH = 270;
	public static final double NORTH = 90;
	public static final double EAST = 0;
	public static final double WEST = 180;

	public static final int NUM_COLUMNS = 16;
	public static final int NUM_ROWS = 8;
	public static final int GRID_SQUARE_SIZE = 75;

	public static final int WORLD_WIDTH = NUM_COLUMNS*GRID_SQUARE_SIZE;
	public static final int WORLD_HEIGHT = NUM_ROWS*GRID_SQUARE_SIZE;

	public static final double BASIC_CANNON_SPEED = 30;
	public static final double BASIC_ENEMY_SPEED = 6;
	public static final double BASIC_TOWER_SPEED = 0;

	public static final int BASIC_TOWER_RANGE = 100000;
	public static final int BASIC_TOWER_COOLDOWN = 30;


	public static final String GAME_NAME = "TowerDefense";
	public static final int OBJECT_CELL_SIDE_LENGTH = (int) (GRID_SQUARE_SIZE*.9);
	public static final int IMAGE_OFFSET = 3;

	public static final int UPDATE_CYCLE_TIME = 50;

	public static final int CANNONBALL_EXPLOSION_RADIUS_SQUARED = 1625;
	public static final int CANNONBALL_DAMAGE = 20;

	public static enum State{LAUNCH,TRAVEL,EXPLODE,DONE, INITIAL, MADE_IT_TO_GOAL_LOCATION};

	public static enum DrawObject{CURSOR,TOWER,CANNON_BALL,CANNON_BALL_EXPLOSION, BASIC_ENEMY,GRASSTILE_1,GRASSTILE_2,BORDERTILE, STARTTILE, ENDTILE, ICE_ENEMY}

	public static enum EnemyType{BASIC,ICE,FIRE,BOSS1,BOSS2,BOSS3};


	public static final Point SPAWN_POINT = new Point(1,1);
	public static final Point END_POINT = new Point(NUM_COLUMNS-2,NUM_ROWS-2);
	public static final int TOWER_COST = 20;
	public static final int BASIC_ENEMY_HEALTH = 100;
	public static final int BASIC_ENEMY_DELAY = 50;

	public static final int BASIC_ENEMY_VALUE = 10;
}
