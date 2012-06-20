package com.game.main;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public abstract class ArcadeGame extends LinearLayout {

	private Context mContext;
	
	private Timer mUpdateTimer;
	protected boolean ingame = false;
	
	public ArcadeGame(Context context) {
		super(context);
		mContext = context;
	}
	
	public ArcadeGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		try {
			initialize();
			startUpdateTimer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	

	protected void startUpdateTimer() {
		//this.ingame = true;
		stopUpdateTimer();
		mUpdateTimer = new Timer();
		mUpdateTimer.schedule(new UpdateTask(), 0);
	}
	
	protected void stopUpdateTimer() {

		if (mUpdateTimer != null)
			mUpdateTimer.cancel();
	}
	
	
	abstract protected void updatePhysics();
	
	abstract protected void initialize();
	
	abstract protected boolean isGameOver();
	
	
	private class UpdateTask extends TimerTask {
		@Override
		public void run() {
			long start_time = System.currentTimeMillis();
			updatePhysics();
			
			postInvalidate();
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
	
	public void pause() {
		this.ingame = false;
		//stopUpdateTimer();
	}
	
	public void halt() {
		this.ingame = false;
		stopUpdateTimer();
	}
	
	public void resume() {
		this.ingame = false;
		initialize();
		startUpdateTimer();
	}
	
	public void go() {
		this.ingame = true;
		initialize();
		//startUpdateTimer();
	}
	
	
	

}
