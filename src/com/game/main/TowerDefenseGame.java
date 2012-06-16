package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class TowerDefenseGame extends ArcadeGame{


	private Bitmap towerImage;
	private Bitmap enemyImage;
	private Bitmap cannonBallImage;
	private Bitmap cannonBallExplosionImage;
	
	public TerrainMap myTerrainMap;
	private Bitmap cursorImage;
	public GameEngine myGameEngine;
	private Context context;
	private SpriteDrawer mySpriteDrawer;

	/*
	public TowerDefenseGame(Context context) {
		super(context);
		super.setUpdatePeriod(Constants.UPDATE_DELAY);
		this.context = context;
		initialize();
	}*/

	public TowerDefenseGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setUpdatePeriod(Constants.UPDATE_DELAY);
		this.context = context;
		initialize();

	}

	public void initialize() {
		int width = this.getWidth();
		int height = this.getHeight();
		myTerrainMap = new TerrainMap(width, height);
		myTerrainMap.setFocus(new Point(0,0));
		mySpriteDrawer = new SpriteDrawer(context);
		myGameEngine = new GameEngine(myTerrainMap,mySpriteDrawer);
		
	


	}

	@Override
	protected void onDraw(Canvas canvas) 
	{

		super.dispatchDraw(canvas);
		myGameEngine.drawAll(canvas);
		//paint(canvas);
	}
	/*
	public void paint(Canvas g) {
		if (ingame)
			playGame(g);
	}
	private void playGame(Canvas c) {
		myGameEngine.drawPlayField(c);
	}
	*/
	public void GameStart() {
	}

	public void GameOver() {
		ingame = false;
	}


	public boolean onTouchEvent(MotionEvent event){
		int tx = (int) event.getX();
		int ty = (int) event.getY();
		Log.e("touch event point", "x=" + tx+"  y="+ty);

		Point nearestTowerLocation = myTerrainMap.computeNearestTowerLocation(new Point(tx, ty));
		myTerrainMap.setFocus(nearestTowerLocation);

		if (!myTerrainMap.isTowerAt(new Point(nearestTowerLocation.x, nearestTowerLocation.y))){
			myTerrainMap.setFocus(new Point(nearestTowerLocation.x, nearestTowerLocation.y));

			//myWorld.setTower(new Tower(nearestTowerLocation.x, nearestTowerLocation.y));
			//show place tower buttons
		}
		else{
			//Tower tower = myTerrainMap.getTowerAt(nearestTowerLocation);
			//show tower upgrade buttom and tower stats
		}



		if ( !ingame ) {
			ingame = true;
			GameStart();
		}

		return true;

	}

	@Override
	protected void updatePhysics() {
		myGameEngine.updatePhysics();
	}

	@Override
	protected boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected long getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}
