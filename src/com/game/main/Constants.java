package com.game.main;


public class Constants {




	public static final double SOUTH = 270;
	public static final double NORTH = 90;
	public static final double EAST = 0;
	public static final double WEST = 180;

	public static final int NUM_COLUMNS = 16;
	public static final int NUM_ROWS = 10;

	public static final int GRID_SQUARE_SIZE = 80;//75;

	public static final int WORLD_WIDTH = NUM_COLUMNS*GRID_SQUARE_SIZE;
	public static final int WORLD_HEIGHT = NUM_ROWS*GRID_SQUARE_SIZE;

	public static final String GAME_NAME = "TowerDefense";
	public static final int OBJECT_CELL_SIDE_LENGTH = (int) (GRID_SQUARE_SIZE*.9);
	public static final int IMAGE_OFFSET = 3;

	public static final int UPDATE_CYCLE_TIME = 50;
	
	public static enum State{LAUNCH,TRAVEL,EXPLODE,DONE, INITIAL, MADE_IT_TO_GOAL_LOCATION};

	public static enum DrawObject{CURSOR,BASIC_TOWER,CANNON_BALL,CANNON_BALL_EXPLOSION, BASIC_ENEMY,
			GRASSTILE_1,GRASSTILE_2,BORDERTILE, STARTTILE, ENDTILE, ICE_ENEMY, HEAVY_TOWER, FAST_TOWER}


	/*						  Projectile Stuff									*/

	public static final double BASIC_CANNON_SPEED = 50;
	
	public static final int CANNONBALL_EXPLOSION_RADIUS_SQUARED = (int) Math.pow((GRID_SQUARE_SIZE/2), 2);


	/*						  Enemy Stuff										*/

	public static final GridPoint SPAWN_POINT = new GridPoint(1,1);
	public static final GridPoint END_POINT = new GridPoint(NUM_COLUMNS-2,NUM_ROWS-2);

	public static enum EnemyType{BASIC,ICE,FIRE,BOSS1,BOSS2,BOSS3};
	
	public static final int BASIC_ENEMY_HEALTH = 70;
	public static final int ICE_ENEMY_HEALTH = 110;

	public static final int ICE_ENEMY_VALUE = 5;
	public static final int BASIC_ENEMY_VALUE = 3;
	
	public static final double BASIC_ENEMY_SPEED = 8;
	public static final double ICE_ENEMY_SPEED = 5;
	
	public static final int BASIC_ENEMY_DELAY = 40;

	
	/*						  Tower Stuff										*/

	public enum TowerType {BASIC,FAST,SLOW,ICE,FIRE, HEAVY};
	
	public static final double BASIC_TOWER_SPEED = 0;
	
	public static final int BASIC_TOWER_COOLDOWN = 30;
	public static final int FAST_TOWER_COOLDOWN = 10;
	public static final int HEAVY_TOWER_COOLDOWN = 50;
	public static final int ICE_TOWER_COOLDOWN = 50;
	public static final int FIRE_TOWER_COOLDOWN = 10;
	
	public static final int FAST_TOWER_RANGE = (int) Math.pow((GRID_SQUARE_SIZE*3), 2);
	public static final int HEAVY_TOWER_RANGE = (int) Math.pow((GRID_SQUARE_SIZE*6), 2);
	public static final int ICE_TOWER_RANGE = (int) Math.pow((GRID_SQUARE_SIZE*4), 2);
	public static final int FIRE_TOWER_RANGE = (int) Math.pow((GRID_SQUARE_SIZE*3), 2);
	public static final int BASIC_TOWER_RANGE = (int) Math.pow((GRID_SQUARE_SIZE*4), 2);
	
	public static final int BASIC_TOWER_DAMAGE = 20;
	public static final int FAST_TOWER_DAMAGE = 10;
	public static final int HEAVY_TOWER_DAMAGE = 50;
	public static final int ICE_TOWER_DAMAGE = 20;
	public static final int FIRE_TOWER_DAMAGE = 15;
	
	public static final int BASIC_TOWER_COST = 20;
	public static final int FAST_TOWER_COST = 25;
	public static final int HEAVY_TOWER_COST = 50;
	public static final int ICE_TOWER_COST = 50;
	public static final int FIRE_TOWER_COST = 40;
	public static final int STARTING_LIVES = 10;
	public static final int STARTING_MONEY = 150;

}
