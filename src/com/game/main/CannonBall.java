package com.game.main;


import android.graphics.Point;

public class CannonBall {

	public enum State{LAUNCH,TRAVEL,EXPLODE,DONE};
	private Point startLocation;
	private Point endLocation;
	public Point location;
	public State state;
	private int cannonSpeed = 10;

	public CannonBall(Point towerLocation, Point nearestEnemyLocation) {
		this.startLocation = towerLocation;
		this.location = new Point(towerLocation.x,towerLocation.y);
		this.endLocation = nearestEnemyLocation;
		this.state = State.LAUNCH;
	}

	public void update(){

		if (this.state == State.EXPLODE){
			this.state = State.DONE;
		}
		else{
			double distanceToTarget = calculateDistanceSquared(location,endLocation);
			if (distanceToTarget < 1000){
				this.state = State.EXPLODE;
			}
			else{
				if (endLocation.x > location.x){
					location.x += cannonSpeed;
				}
				else{
					location.x -= cannonSpeed;
				}

				if(endLocation.y > location.y){
					location.y += cannonSpeed;
				}
				else{
					location.y -= cannonSpeed;
				}
				this.state = State.TRAVEL;
			}
		}
	}

	private double calculateDistanceSquared(Point startLocation,Point endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}


}
