package com.game.main;


import android.graphics.Point;

public class CannonBall {

	public enum State{LAUNCH,TRAVEL,EXPLODE,DONE};
	private Point startLocation;
	private Point endLocation;
	public Point location;
	public State state;
	private int cannonSpeed = 10;
	private double theta;
	private double Vx;
	private double Vy;

	public CannonBall(Point towerLocation, Point nearestEnemyLocation) {
		this.startLocation = towerLocation;
		this.location = new Point(towerLocation.x,towerLocation.y);
		this.endLocation = nearestEnemyLocation;
		this.state = State.LAUNCH;
		this.theta = calculateTheta(startLocation, endLocation);
		this.Vx = cannonSpeed * Math.cos(theta);
		this.Vy = cannonSpeed * Math.sin(theta);
	}

	public void update(){

		if (this.state == State.EXPLODE){
			this.state = State.DONE;
		}
		else{
			double distanceSquaredToTarget = calculateDistanceSquared(location,endLocation);
			if (distanceSquaredToTarget < Math.pow(cannonSpeed,2)){
				this.state = State.EXPLODE;
			}
			else{
				this.location.x = (int) (this.location.x + Vx);
				this.location.y = (int) (this.location.y + Vy);
				this.state = State.TRAVEL;
			}
		}
	}

	private double calculateDistanceSquared(Point startLocation,Point endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}
	
	private double calculateTheta(Point startLocation, Point endLocation){
		int x0 = startLocation.x;
		int y0 = startLocation.y;
		int x1 = endLocation.x;
		int y1 = endLocation.y;
		double theta = Math.atan2(y1-y0, x1-x0);
		if (theta == Double.NaN){
			return 0;
		}
		return theta;
	}


}
