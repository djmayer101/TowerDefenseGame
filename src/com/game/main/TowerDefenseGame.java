package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TowerDefenseGame extends ArcadeGame{

	public TerrainMap myTerrainMap;
	public GameEngine myGameEngine;
	private Context context;
	private SpriteDrawer mySpriteDrawer;
	private PathBuilder myPathBuilder;
	
	private int screen_width;
	private int screen_height;

	private int score = 0;
	private int money = 100;

	private ObstacleManager myObstacleManager;


	static public int X_offset= 0;
	static public int Y_offset = 0;

	public TowerDefenseGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setUpdatePeriod(Constants.UPDATE_DELAY);
		this.context = context;
		
		initialize();
		initializeClasses();
		initializeButtons();
	}

	private void initializeClasses() {
		mySpriteDrawer = new SpriteDrawer(context);
		myObstacleManager = new ObstacleManager();
		myTerrainMap = new TerrainMap(screen_width, screen_height, myObstacleManager);
		myTerrainMap.setFocus(new Point(Constants.GRID_SQUARE_SIZE,Constants.GRID_SQUARE_SIZE));
		myPathBuilder = new PathBuilder(myTerrainMap,myObstacleManager);
		myGameEngine = new GameEngine(myTerrainMap,mySpriteDrawer,myPathBuilder,myObstacleManager);
	}

	private void initializeButtons() {
		LinearLayout buttons = new LinearLayout(context);
		
		ImageButton buildTowerButton= new ImageButton(context);
		Bitmap buttonImage = getImage(R.drawable.build_tower_button);
		buttonImage = Bitmap.createScaledBitmap( buttonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildTowerButton.setImageBitmap(buttonImage);
		buildTowerButton.setBackgroundResource(0);
		buildTowerButton.setClickable(true);
		buildTowerButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		buildTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				buildTowerClicked();
			}
		});
		
		
		ImageButton pauseButton= new ImageButton(context);
		Bitmap pauseImage = getImage(R.drawable.pause_button);
		pauseImage = Bitmap.createScaledBitmap( pauseImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		pauseButton.setImageBitmap(pauseImage);
		pauseButton.setBackgroundResource(0);
		pauseButton.setClickable(true);
		pauseButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		pauseButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				pauseClicked();
			}


		});

		TextView scoreView = new TextView(context);
		scoreView.setTextSize(24);
		scoreView.setTextColor(Color.WHITE);
		scoreView.setText("Score: " + score + " ");
		scoreView.setBackgroundColor(Color.BLACK);

		TextView moneyView = new TextView(context);
		moneyView.setTextSize(24);
		moneyView.setText(" Cash: " + money + " ");
		moneyView.setTextColor(Color.WHITE);
		moneyView.setBackgroundColor(Color.BLACK);
		
		buttons.addView(scoreView);
		buttons.addView(moneyView);
		buttons.addView(buildTowerButton);
		buttons.addView(pauseButton);
	
		this.addView(buttons);
		
	}
	private void pauseClicked() {
		if (ingame){
			halt();
		}
		else{
			resume();
		}
		
	}
	public void initialize() {
		screen_width = this.getWidth();
		screen_height = this.getHeight();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.dispatchDraw(canvas);
		myGameEngine.drawAll(canvas);
	}

	public void GameOver() {
		ingame = false;
	}


	private void buildTowerClicked() {
		myGameEngine.buildTowerClicked();
		
	}
	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}


	public boolean onTouchEvent(MotionEvent event){

		int size;
		Log.e("offsets", "x: " + X_offset + " y: " + Y_offset );
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

			X_offset -= event.getHistorySize()*(endTouchX - initialTouchX);
			Y_offset -= event.getHistorySize()*(endTouchY- initialTouchY);
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
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				myGameEngine.tileClicked(new Point((int) event.getX()-X_offset,(int)event.getY()-Y_offset));
			}

		}
		if ( !ingame ) {
			ingame = true;
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
		return this.score;
	}

}
