package com.game.main;

public abstract class BasicGameObject {

	PixelPoint location;
	double theta = 0;
	Constants.State state = Constants.State.INITIAL;
	double speed;

	public BasicGameObject(PixelPoint location,double  speed){
		this.location = location;
		this.speed = speed;
	}

	abstract void updateState();

	public void updateTheta(PixelPoint focus){
		int x0 = location.x;
		int y0 = location.y;
		int x1 = focus.x;
		int y1 = focus.y;
		double theta = Math.atan2(y1-y0, x1-x0);
		if (theta == Double.NaN){
			theta = 0;
		}
		this.theta = theta;

	}

	public void updateLocation(){
		int x = (int) (this.location.x + getVx());
		int y = (int) (this.location.y + getVy());
		this.location = new PixelPoint(x,y);
	}

	public Constants.State getState(){
		return state;
	}

	public PixelPoint getLocation(){
		return location;
	}

	protected double getVy() {
		return this.speed * Math.sin(theta);
	}

	protected double getVx() {
		return this.speed * Math.cos(theta);
	}

}
