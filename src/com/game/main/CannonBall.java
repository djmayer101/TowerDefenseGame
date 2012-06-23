package com.game.main;

import android.graphics.Point;
import com.game.main.Constants.State;

public class CannonBall extends BasicGameObject{

	private Point endLocation;
	private int damage;

	public CannonBall(Point location, Point endLocation, int damage) {
		super(location, Constants.BASIC_CANNON_SPEED);
		this.damage = damage;
		this.endLocation = endLocation;
		this.state = State.LAUNCH;

		updateTheta(endLocation);
	}

	@Override
	void updateState() {
		double distanceSquaredToTarget = TerrainMap.calculateDistanceSquared(location,endLocation);
		if (this.state == State.EXPLODE || this.state == State.DONE){
			this.state = State.DONE;
		}
		else if(distanceSquaredToTarget < Constants.BASIC_CANNON_SPEED*Constants.BASIC_CANNON_SPEED){
			this.state = State.EXPLODE;
		}
		else{
			this.state = State.TRAVEL;
		}
	}

	public int getDamage() {
		return damage;
	}
}
