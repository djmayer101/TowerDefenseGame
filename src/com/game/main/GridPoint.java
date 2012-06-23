package com.game.main;

public class GridPoint {
	int x;
	int y;
	
	public GridPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public GridPoint(GridPoint p){
		this.x = p.x;
		this.y = p.y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPoint other = (GridPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	public PixelPoint scaleToPixelPoint(){
		return new PixelPoint(this.x*Constants.GRID_SQUARE_SIZE, this.y*Constants.GRID_SQUARE_SIZE);
	}
}
