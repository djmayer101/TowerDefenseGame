package com.game.main;

import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;


public class UpdateTaskManager{
	private TowerDefenseView towerDefenseView;
	private TowerDefenseGame towerDefenseGame;
	private GameEngine gameEngine;

	private static Timer mUpdateTimer = new Timer();

	public UpdateTaskManager(TowerDefenseView towerDefenseView, TowerDefenseGame towerDefenseGame){
		this.towerDefenseView = towerDefenseView;
		this.towerDefenseGame = towerDefenseGame;
		this.gameEngine = towerDefenseGame.getGameEngine();
	}

	private class UpdateTask extends TimerTask {
		@Override
		public void run() {
			long start_time = System.currentTimeMillis();
			towerDefenseGame.updatePhysics();

			towerDefenseView.postInvalidate();
			long end_time = System.currentTimeMillis();
			long remaining_time = Constants.UPDATE_CYCLE_TIME -end_time+start_time;
			if (remaining_time < Constants.UPDATE_CYCLE_TIME/2.0){
				Log.e("Physics Update Time", "Time left over: " + remaining_time);
			}


			if (remaining_time < 0){
				remaining_time =0;
			}
			try {
				Thread.sleep(remaining_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startUpdateTimer();

		}
	}

	protected void startUpdateTimer() {
		//this.ingame = true;
		//stopUpdateTimer();
		if (mUpdateTimer == null){
			mUpdateTimer = new Timer();
		}
		mUpdateTimer.schedule(new UpdateTask(), 0);
	}

	protected void stopUpdateTimer() {

		if (mUpdateTimer != null){
			mUpdateTimer.cancel();
			mUpdateTimer = null;
		}
	}

	public void pause() {
		towerDefenseGame.setIngame(false);
		//stopUpdateTimer();
	}

	public void halt() {
		towerDefenseGame.setIngame(false);
		stopUpdateTimer();
	}

	//opposite of halt, game comes back from home
	public void resume() {
		towerDefenseGame.setIngame(false);
		towerDefenseView.initialize();
		startUpdateTimer();
	}

	//start round
	public void go() {
		towerDefenseGame.setIngame(true);
	}
}



