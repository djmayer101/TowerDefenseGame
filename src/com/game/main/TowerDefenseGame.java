package com.game.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TowerDefenseGame extends ArcadeGame{

	public TerrainMap myTerrainMap;
	public GameEngine myGameEngine;
	private Context context;
	private SpriteDrawer mySpriteDrawer;
	private PathBuilder myPathBuilder;
	
	private int screen_width;
	private int screen_height;


	private ObstacleManager myObstacleManager;
	private GameStatistics gameStatistics;
	private TextView moneyView;
	private TextView roundView;
	private LinearLayout buttons;
	
	private Handler mHandler = new Handler();

	private TextView livesView;
	private TowerDefenseActivity activity;

	static public int X_offset= 0;
	static public int Y_offset = 0;

	public TowerDefenseGame(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		gameStatistics = new GameStatistics(this);
		//gameRound = new GameRound(gameStatistics.getRound());
		myGameEngine = new GameEngine(myTerrainMap,mySpriteDrawer,myPathBuilder,myObstacleManager,gameStatistics);

	}

	private void initializeButtons() {
		buttons = new LinearLayout(context);
		
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
		
		ImageButton startButton= new ImageButton(context);
		Bitmap startImage = getImage(R.drawable.start_button);
		startImage = Bitmap.createScaledBitmap( startImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		startButton.setImageBitmap(startImage);
		startButton.setBackgroundResource(0);
		startButton.setClickable(true);
		startButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		startButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				startRound();
			}


		});

		roundView = new TextView(context);
		roundView.setTextSize(24);
		roundView.setTextColor(Color.WHITE);
		roundView.setText("Round: " + gameStatistics.getRound() + " ");
		roundView.setBackgroundColor(Color.BLACK);

		moneyView = new TextView(context);
		moneyView.setTextSize(24);
		moneyView.setText(" Cash: " + gameStatistics.getMoney() + " ");
		moneyView.setTextColor(Color.WHITE);
		moneyView.setBackgroundColor(Color.BLACK);
		
		livesView = new TextView(context);
		livesView.setTextSize(24);
		livesView.setText(" Lives: " + gameStatistics.getLives()  + " ");
		livesView.setTextColor(Color.WHITE);
		livesView.setBackgroundColor(Color.BLACK);
		
		buttons.addView(roundView);
		buttons.addView(moneyView);
		buttons.addView(livesView);
		buttons.addView(buildTowerButton);
		buttons.addView(pauseButton);
		buttons.addView(startButton);
	
		this.addView(buttons);
		
	}
	protected void startRound() {
		if(!ingame){
			gameStatistics.incrementRound();
			gameStatistics.startRound();
			ingame = true;
		}
	}

	private void pauseClicked() {
		if (ingame){
			pause();
		}
		else{
			go();
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

	public void gameOver() {
		ingame = false;
		 mHandler.post(new Runnable() {
	            public void run() {
	            	AlertDialog.Builder builder = new AlertDialog.Builder(context);
	        		builder.setMessage("YOU LOSE!!!")
	        		       .setCancelable(false)
	        		       .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
	        		           public void onClick(DialogInterface dialog, int id) {
	        		                activity.startNewGame();
	        		           }
	        		       })
	        		       .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
	        		           public void onClick(DialogInterface dialog, int id) {
	        		        	   activity.backToMenu();
	        		           }
	        		       });
	        		AlertDialog alert = builder.create();
	        		alert.show();
	            }
	        });
	
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

		return true;
	}

	@Override
	protected void updatePhysics() {
		if (ingame){
			myGameEngine.updatePhysics();
		}
	}

	@Override
	protected boolean isGameOver() {
		
		return false;
	}
	


    // This gets executed in a non-UI thread:
    public void refreshButtons() {
        mHandler.post(new Runnable() {
            public void run() {
                // This gets executed on the UI thread so it can safely modify Views
        		moneyView.setText(" Cash: " + gameStatistics.getMoney() + " ");
        		livesView.setText(" Lives: " + gameStatistics.getLives()  + " ");
        		roundView.setText("Round: " + gameStatistics.getRound() + " ");
            }
        });
    }

	public void setTowerActivity(TowerDefenseActivity towerDefenseActivity) {
		this.activity = towerDefenseActivity;
		
	}
	



}
