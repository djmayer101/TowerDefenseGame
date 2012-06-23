package com.game.main;

import java.util.concurrent.CopyOnWriteArrayList;
import com.game.main.Constants.EnemyType;
import com.game.main.Constants.State;

public class BasicEnemy extends BasicGameObject {

	protected int health;
	private GridPoint endLocation;
	private PixelPoint localGoal;
	private EnemyType type;
	private int value;
	private CopyOnWriteArrayList<GridPoint> path;

	public BasicEnemy(GridPoint startLocation, GridPoint endLocation, int health, EnemyType type, int value){
		super(new PixelPoint(startLocation.scaleToPixelPoint()), 0);
		this.endLocation = endLocation;
		this.theta = Constants.EAST;
		this.health = health;
		this.localGoal = new PixelPoint(startLocation.scaleToPixelPoint());
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


	public GridPoint getEndLocation(){
		return endLocation;
	}

	@Override
	public void updateLocation(){
		if(this.location.equals(endLocation)){
			this.speed = 0;
			state = State.MADE_IT_TO_GOAL_LOCATION;
		}
		else if (TerrainMap.calculateDistanceSquared(location, localGoal) < this.speed * this.speed){
			this.location = new PixelPoint(localGoal);
		}
		else{
			int x = (int) (this.location.x + getVx());
			int y = (int) (this.location.y + getVy());
			this.location = new PixelPoint(x,y);
		}

	}

	public void updateLocalGoal(){
		GridPoint gridPoint = this.location.scaleToGridPoint();
		if (this.path.contains(gridPoint)){
			int newLocalGoalIndex = this.path.indexOf(gridPoint) + 1;
			if(path.size() > newLocalGoalIndex){
				this.localGoal = this.path.get(newLocalGoalIndex).scaleToPixelPoint();
				this.updateTheta(localGoal);
			}
			else{
				this.localGoal = this.endLocation.scaleToPixelPoint();

			}
		}
	}

	public void updatePath(CopyOnWriteArrayList<GridPoint> path) {
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

