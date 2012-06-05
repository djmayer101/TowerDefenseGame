package com.game.main;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TowerDefenseGame extends ArcadeGame{

	public static final String GAMENAME = "TowerDefence";
	private static final long UPDATE_DELAY = 40;
	
	private Context mContext;
	
	private Paint mTextPaint = new Paint();
	private Paint mBitmapPaint = new Paint();
	private Bitmap towerImage;
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	
	
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
		//int width = this.getWidth();
		//int height = this.getHeight();
		
		towerImage = getImage(R.drawable.awesome_castle);
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
		for (Tower tower : towers){
			canvas.drawBitmap(towerImage, tower.x, tower.y, mBitmapPaint);
		}
	}



	public boolean onTouchEvent(MotionEvent event){
		int tx = (int) event.getX();
		int ty = (int) event.getY();
		towers.add(new Tower(tx,ty));
		if ( !ingame ) {
			ingame = true;
			GameStart();
		}

		return true;

	}

	@Override
	protected void updatePhysics() {
		// TODO Auto-generated method stub
		
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
