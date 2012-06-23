package com.game.main;

import java.util.concurrent.Semaphore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TowerDefenseView extends LinearLayout {

	private boolean gameStarted = false;
	private static Semaphore gameStartedMutex;

	private int screen_width;
	private int screen_height;

	public static int X_offset;
	public static int Y_offset;

	private Handler mHandler = new Handler();

	private TowerDefenseGame towerDefenseGame;
	private TowerDefenseActivity towerDefenseActivity;

	private GameEngine gameEngine;
	private ButtonsWrapper buttonsWrapper;
	private GameStatistics gameStatistics;


	public TowerDefenseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gameStatistics = new GameStatistics(this);
		buttonsWrapper = new ButtonsWrapper(getContext(),towerDefenseGame,gameStatistics);
		this.addView(buttonsWrapper.getButtons());
		gameStartedMutex = new Semaphore(1, true);
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		screen_width = this.getWidth();
		screen_height = this.getHeight();

		if(!gameStarted){
			towerDefenseGame = new TowerDefenseGame(this,gameStatistics);
			gameEngine = towerDefenseGame.getGameEngine();
			buttonsWrapper.setTowerDefenseGame(towerDefenseGame);
			setGameStarted(true);
		}

		towerDefenseActivity.setTowerDefenseGame();
		towerDefenseGame.getUpdateTaskManager().startUpdateTimer();
	}


	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.dispatchDraw(canvas);
		towerDefenseGame.getGameEngine().drawAll(canvas);
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
				gameEngine.tileClicked(new Point((int) event.getX()-X_offset,(int)event.getY()-Y_offset));
			}
		}
		return true;
	}


	// This gets executed in a non-UI thread:
	public void refreshButtons() {
		mHandler.post(new Runnable() {
			public void run() {
				buttonsWrapper.refreshButtons();
			}
		});
	}
	
	// This gets executed in a non-UI thread:
	public void togglePausePlayButtons() {
		mHandler.post(new Runnable() {
			public void run() {
				buttonsWrapper.togglePausePlayButtons();
			}
		});
	}

	public void showGameOver() {
		mHandler.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setMessage("YOU LOSE!!!")
				.setCancelable(false)
				.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						towerDefenseActivity.startNewGame();
					}
				})
				.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						towerDefenseActivity.backToMenu();
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	public void showRoundOver() {
		mHandler.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setMessage("Round " + gameStatistics.getRound() + " Complete!")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id){
					}
				});

				AlertDialog alert = builder.create();
				alert.show();
			}
		});

	}
	public void showTowerOptions() {
		mHandler.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setMessage("Choose a tower!")
				.setCancelable(false)
				.setPositiveButton("regular", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						towerDefenseGame.buildTowerClicked();
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});


	}

	public TowerDefenseGame getGame() {
		return towerDefenseGame;
	}

	public void setTowerDefenseActivity(TowerDefenseActivity towerDefenseActivity) {
		this.towerDefenseActivity = towerDefenseActivity;	
	}

	private void setGameStarted(boolean b){
		try {
			gameStartedMutex.acquire();
			gameStarted = b;
			gameStartedMutex.release();
		} catch (InterruptedException e) {
			System.out.println("Exception " + e.toString());
		}
	}

	public ButtonsWrapper getButtonsWrapper() {
		return buttonsWrapper;
	}

	public void showTowerUpgradeOptions() {
		mHandler.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setMessage("Choose an upgrade!")
				.setCancelable(false)
				.setPositiveButton("shoot farther", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						//towerDefenseGame.buildTowerClicked();
					}
				})
				.setNegativeButton("shoot faster", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
	}


}
