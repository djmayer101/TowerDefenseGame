package com.game.main;

import android.graphics.Point;
import com.game.main.GameConstants.State;

public class CannonBall extends BasicGameObject{

	private Point endLocation;
	private double previousDistanceToTarget;

	public CannonBall(Point location, Point endLocation) {
		super(location, GameConstants.BASIC_CANNON_SPEED);

		this.endLocation = endLocation;
		this.state = State.LAUNCH;

		updateTheta(endLocation);

		this.previousDistanceToTarget = Double.MAX_VALUE;
	}

	@Override
	void updateState() {
		double distanceSquaredToTarget = calculateDistanceSquared(location,endLocation);
		if (this.state == State.EXPLODE || this.state == State.DONE){
			this.state = State.DONE;
		}
		else if(distanceSquaredToTarget > this.previousDistanceToTarget){
			this.state = State.EXPLODE;
		}
		else{
			this.state = State.TRAVEL;
		}
		this.previousDistanceToTarget = distanceSquaredToTarget;
	}
}
