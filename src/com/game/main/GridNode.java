package com.game.main;
import android.graphics.Point;


public class GridNode {

		Point me;
		private GridNode parent;
		public int distanceFromStart;
		public int f_score = Integer.MAX_VALUE;

		GridNode(Point me) {
			this.me = me;
			parent = null;
			distanceFromStart = Integer.MAX_VALUE;
			
		}

		public GridNode getParent () {
			return parent;
		}

		public void setParent (GridNode newParent) {
			parent = newParent;
		}

		public int getDistance () {
			return distanceFromStart;
		}

		public void setDistance (int newDistance) {
			distanceFromStart = newDistance;
		}


		public void setF_score(int d) {
			this.f_score = d;
		}

}
