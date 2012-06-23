package com.game.main;


public class PixelPoint{
	int x;
	int y;
	
	public PixelPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public PixelPoint(PixelPoint p){
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
		PixelPoint other = (PixelPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public GridPoint scaleToGridPoint(){
		return new GridPoint((int) Math.floor(this.x/Constants.GRID_SQUARE_SIZE), (int) Math.floor(this.y/Constants.GRID_SQUARE_SIZE));
	}

	public void offset(int i, int j) {
		x += i;
		y += j;
	}

}
