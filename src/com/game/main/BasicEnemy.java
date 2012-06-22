package com.game.main;

import java.util.concurrent.CopyOnWriteArrayList;

import com.game.main.Constants.EnemyType;
import com.game.main.Constants.State;
import android.graphics.Point;

public class BasicEnemy extends BasicGameObject {

	protected int health;
	private Point endLocation;
	private Point localGoal;
	private EnemyType type;
	private int value;
	private CopyOnWriteArrayList<Point> path;

	public BasicEnemy(Point startLocation, Point endLocation, int health, EnemyType type, int value){
		super(new Point(startLocation.x*Constants.GRID_SQUARE_SIZE, startLocation.y*Constants.GRID_SQUARE_SIZE), 0);
		this.endLocation = endLocation;
		this.theta = Constants.EAST;
		this.health = health;
		this.localGoal = new Point(startLocation.x*Constants.GRID_SQUARE_SIZE, startLocation.y*Constants.GRID_SQUARE_SIZE);
		this.type = type;
		this.value = value;
		setSpeed();
	}

	@Override
	void updateState() {
		if (health <= 0){
			this.state = State.DONE;
		}
	}

	public void reduceHeath(int cannonBallDamage) {
		health -= cannonBallDamage;
	}


	public Point getEndLocation(){
		return endLocation;
	}

	@Override
	public void updateLocation(){
		if((this.location.x == TerrainMap.scaleGridPointToPixel(endLocation).x) &&
				(this.location.y == TerrainMap.scaleGridPointToPixel(endLocation).y)){
			this.speed = 0;
			state = State.MADE_IT_TO_GOAL_LOCATION;
		}
		else if (TerrainMap.calculateDistanceSquared(location, localGoal) < this.speed * this.speed){
			this.location = new Point(localGoal);
		}
		else{
			int x = (int) (this.location.x + getVx());
			int y = (int) (this.location.y + getVy());
			this.location = new Point(x,y);
		}

	}

	public void updateLocalGoal(){
		Point gridPoint = TerrainMap.scalePixelToGridPoint(this.location);
		if (this.path.contains(gridPoint)){
			int newLocalGoalIndex = this.path.indexOf(TerrainMap.scalePixelToGridPoint(this.location)) + 1;
			if(path.size() > newLocalGoalIndex){
				this.localGoal = TerrainMap.scaleGridPointToPixel(this.path.get(newLocalGoalIndex));
				this.updateTheta(localGoal);
			}
			else{
				this.localGoal = TerrainMap.scaleGridPointToPixel(this.endLocation);

			}
		}
	}

	public void updatePath(CopyOnWriteArrayList<Point> path) {
		this.path = path;
	}

	public int value() {
		return value;
	}
	
	public EnemyType getType(){
		return type;
	}
	
	private void setSpeed(){
		switch(type){
		case	BASIC:	speed = Constants.BASIC_ENEMY_SPEED;
		break;
		case	ICE:	speed = Constants.ICE_ENEMY_SPEED;
		break;
		}
	}



}

