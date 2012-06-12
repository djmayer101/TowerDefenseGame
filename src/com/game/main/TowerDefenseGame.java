package com.game.main;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class TowerDefenseGame extends ArcadeGame{

	public static final String GAMENAME = "TowerDefense";
	private static final long UPDATE_DELAY = 40;
	private Context mContext;

	private Paint mTextPaint = new Paint();
	private Paint mBitmapPaint = new Paint();
	private Bitmap towerImage;
	private ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
	private Point mCursor = new Point(0,0);
	private Bitmap enemyImage;


	public World myWorld;

	public TowerDefenseGame(Context context) {
		super(context);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);
	}

	public TowerDefenseGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);

	}

	public void initialize() {
		int width = this.getWidth();
		int height = this.getHeight();
		myWorld = new World(width, height);
		myWorld.setFocus(new Point(0,0));
		
		towerImage = getImage(R.drawable.awesome_castle);
		towerImage = towerImage.createScaledBitmap(towerImage, 50, 50, false);
		enemyImage = getImage(R.drawable.awesome_castle);
		enemyImage = enemyImage.createScaledBitmap(enemyImage, 50, 50, false);
		BasicEnemy enemy = new BasicEnemy(0,0);
		basicEnemies.add(enemy);

	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.dispatchDraw(canvas);
		paint(canvas);
	}
	public void paint(Canvas g) {
		if (ingame)
			playGame(g);
	}
	private void playGame(Canvas c) {
		drawPlayField(c);
	}
	public void GameStart() {
	}

	public void GameOver() {
		ingame = false;
	}

	public void drawPlayField(Canvas canvas) {
		for (int i=0; i<myWorld.numRows; i++){
			for (int j=0; j<myWorld.numColumns; j++){
				if(myWorld.worldTowerGrid[i][j]!=null){
					canvas.drawBitmap(towerImage, myWorld.worldTowerGrid[i][j].x, myWorld.worldTowerGrid[i][j].y, mBitmapPaint);
				}
			}
		}
		for (BasicEnemy enemy:basicEnemies){
			canvas.drawBitmap(enemyImage, enemy.x,enemy.y, mBitmapPaint);
		}
	}



	public boolean onTouchEvent(MotionEvent event){
		int tx = (int) event.getX();
		int ty = (int) event.getY();

		Point nearestTowerLocation = myWorld.computeNearestTowerLocation(new Point(tx, ty));
		myWorld.setFocus(nearestTowerLocation);

		if (!myWorld.isTowerAt(new Point(nearestTowerLocation.x, nearestTowerLocation.y))){
			myWorld.setFocus(new Point(nearestTowerLocation.x, nearestTowerLocation.y));

			//myWorld.setTower(new Tower(nearestTowerLocation.x, nearestTowerLocation.y));
			//show place tower buttons
		}
		else{
			Tower tower = myWorld.getTowerAt(nearestTowerLocation);
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
		for (BasicEnemy enemy: basicEnemies){
			enemy.update();
		}

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
