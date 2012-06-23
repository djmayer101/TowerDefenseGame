package com.game.main;

public class TerrainMap {

	private int world_width;
	private int world_height;

	private int numColumns;
	private int numRows;

	int squareSize;

	private PixelPoint focus;

	public Constants.DrawObject[][] worldTerrainGrid;
	private ObstacleManager obstacleManager;

	public TerrainMap(int width, int height, ObstacleManager obstacleManager){
		this.obstacleManager = obstacleManager;
		this.world_width = Constants.WORLD_WIDTH;;
		this.world_height =  Constants.WORLD_HEIGHT;

		this.numColumns = Constants.NUM_COLUMNS;
		this.numRows = Constants.NUM_ROWS;

		this.squareSize = Constants.GRID_SQUARE_SIZE;
		worldTerrainGrid = new Constants.DrawObject[numColumns][numRows];
		initializeMap();
	}

	private void initializeMap(){
		for (int i=0; i<Constants.NUM_COLUMNS; i++){
			for (int j=0; j<Constants.NUM_ROWS; j++){
				if (i %2 == 0 && j %2 == 0){
					worldTerrainGrid[i][j] = Constants.DrawObject.GRASSTILE_1;
				}else{
					worldTerrainGrid[i][j] = Constants.DrawObject.GRASSTILE_2;
				}
				if (i == 0 || j == 0 || i ==Constants.NUM_COLUMNS-1 || j == Constants.NUM_ROWS-1){
					worldTerrainGrid[i][j] = Constants.DrawObject.BORDERTILE;
					obstacleManager.obstaclesHash.put(new GridPoint(i,j),true);
				}
				if(i == 1 && j == 1){
					worldTerrainGrid[i][j] = Constants.DrawObject.STARTTILE;
				}
				if(i == Constants.NUM_COLUMNS-2 && j == Constants.NUM_ROWS-2){
					worldTerrainGrid[i][j] = Constants.DrawObject.ENDTILE;
				}

			}
		}
	}

	public void setFocus(PixelPoint p) {
		this.focus = p;
	}

	public boolean LocationOutOfBounds(PixelPoint location) {
		if (location.x < 0 || location.x > this.world_width || location.y < 0 || location.y > this.world_height){
			return true;
		}
		return false;
	}

	public PixelPoint getFocus() {
		return this.focus;
	}

	protected static double calculateDistanceSquared(GridPoint startLocation,GridPoint endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}
	
	protected static double calculateDistanceSquared(PixelPoint startLocation,PixelPoint endLocation) {
		return Math.pow(startLocation.x-endLocation.x, 2) + Math.pow(startLocation.y-endLocation.y,2);
	}

}
