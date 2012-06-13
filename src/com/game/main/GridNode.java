package com.game.main;
import android.graphics.Point;


public class GridNode {

		Point me;
		private GridNode parent;
		int distanceToGoal;

		GridNode(Point me) {
			this.me = me;
			parent = null;
			distanceToGoal = Integer.MAX_VALUE;
		}

		public GridNode getParent () {
			return parent;
		}

		public void setParent (GridNode newParent) {
			parent = newParent;
		}

		public int getDistance () {
			return distanceToGoal;
		}

		public void setDistance (int newDistance) {
			distanceToGoal = newDistance;
		}

}
