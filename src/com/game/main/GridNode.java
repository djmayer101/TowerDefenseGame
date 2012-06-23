package com.game.main;


public class GridNode {

	GridPoint point;
	private GridNode parent;
	private int distanceFromStart;
	private int estimatedTotalDistance = Integer.MAX_VALUE;

	GridNode(GridPoint me) {
		this.point = me;
		parent = null;
		distanceFromStart = Integer.MAX_VALUE;

	}

	public GridNode getParent () {
		return parent;
	}

	public void setParent (GridNode newParent) {
		parent = newParent;
	}

	public int getDistanceFromStart() {
		return distanceFromStart;
	}

	public void setDistanceFromStart (int newDistance) {
		distanceFromStart = newDistance;
	}


	public void setEstimatedTotalDistance(int d) {
		estimatedTotalDistance = d;
	}

	public int  getEstimatedTotalDistance() {
		return estimatedTotalDistance;
	}

}
