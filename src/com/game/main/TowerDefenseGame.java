package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class TowerDefenseGame extends ArcadeGame{

	public TerrainMap myTerrainMap;
	public GameEngine myGameEngine;
	private Context context;
	private SpriteDrawer mySpriteDrawer;
	private PathBuilder myPathBuilder;
	private int screen_width;
	private int screen_height;
	private TowerManager myTowerManager;

	static public int X_offset= 0;
	static public int Y_offset = 0;
	ImageButton buildTowerButton;

	public TowerDefenseGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setUpdatePeriod(Constants.UPDATE_DELAY);
		this.context = context;
		initialize();

		ImageButton buildTowerButton= new ImageButton(context);
		Bitmap buttonImage = getImage(R.drawable.build_tower_button);
		buttonImage = Bitmap.createScaledBitmap( buttonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildTowerButton.setImageBitmap(buttonImage);
		buildTowerButton.setBackgroundResource(0);
		buildTowerButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		buildTowerButton.setOnClickListener(new View.OnClickListener() {            
			public void onClick(View v) {

				myGameEngine.buildTowerClicked();

			}
		});

		this.addView(buildTowerButton);

	}

	public void initialize() {
		screen_width = this.getWidth();
		screen_height = this.getHeight();
		myTerrainMap = new TerrainMap(screen_width, screen_height);
		myTerrainMap.setFocus(new Point(0,0));
		mySpriteDrawer = new SpriteDrawer(context);
		myTowerManager = new TowerManager();
		myPathBuilder = new PathBuilder(myTerrainMap,myTowerManager);
		myGameEngine = new GameEngine(myTerrainMap,mySpriteDrawer,myPathBuilder,myTowerManager);



	}
	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.dispatchDraw(canvas);
		myGameEngine.drawAll(canvas);
	}

	public void GameStart() {
	}

	public void GameOver() {
		ingame = false;
	}





	public boolean onTouchEvent(MotionEvent event){

		int size;
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE:
			float initialTouchX = event.getX();
			float initialTouchY = event.getY();
			size = event.getHistorySize()-1;
			if (size == -1){
				size = 0;
			}
			float endTouchX = event.getHistoricalX(size);
			float endTouchY = event.getHistoricalY(size);

			X_offset -= endTouchX - initialTouchX;
			Y_offset -= endTouchY- initialTouchY;
			if(X_offset > 0){
				X_offset = 0;
			}
			if(Y_offset > 0){
				Y_offset = 0;
			}

			if(X_offset < this.screen_width-Constants.WORLD_WIDTH){
				X_offset = this.screen_width-Constants.WORLD_WIDTH;
			}
			if(Y_offset < this.screen_height-Constants.WORLD_HEIGHT){
				Y_offset = this.screen_height-Constants.WORLD_HEIGHT;
			}
			break;
		default:
			Log.e("offsets", "x: " + X_offset + " y:_" + Y_offset );
			myGameEngine.tileClicked(new Point((int) event.getX()-X_offset,(int)event.getY()-Y_offset));
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
		return false;
	}

	@Override
	protected long getScore() {
		return 0;
	}

}
