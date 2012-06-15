package com.game.main;

import java.util.ArrayList;

import com.game.main.Constants.State;

import android.graphics.Point;

public class BasicEnemy extends BasicGameObject {
	
	protected int health;
	private Point endLocation;
	private Point localGoal;
	private ArrayList<Point> path;

	public BasicEnemy(Point startLocation, Point endLocation){
		super(startLocation, Constants.BASIC_ENEMY_SPEED);
		this.endLocation = endLocation;
		this.theta = Constants.EAST;
		this.health = 100;
		this.localGoal = startLocation;
		
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
		if (calculateDistanceSquared(location, localGoal) < this.speed * this.speed){
			this.location = new Point(localGoal);
		}
		else{
			int x = (int) (this.location.x + getVx());
			int y = (int) (this.location.y + getVy());
			this.location = new Point(x,y);
		}

	}
	
	public void updateLocalGoal(){
		if (this.path.contains(this.location)){
			int newLocalGoalIndex = this.path.indexOf(this.location) + 1;
			this.localGoal = this.path.get(newLocalGoalIndex);
			this.updateTheta(localGoal);
		}
	}

	public void updatePath(ArrayList<Point> path) {
		this.path = path;
	}
	
	
	
}

