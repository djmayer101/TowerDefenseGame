package com.game.main;
import android.graphics.Point;


public class GridNode {

		Point point;
		private GridNode parent;
		public int distanceFromStart;
		public int distanceToGoal = Integer.MAX_VALUE;

		GridNode(Point me) {
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

		public int getDistanceFromStart () {
			return distanceFromStart;
		}

		public void setDistanceFromStart (int newDistance) {
			distanceFromStart = newDistance;
		}


		public void setdistanceToGoal(int d) {
			this.distanceToGoal = d;
		}

}
