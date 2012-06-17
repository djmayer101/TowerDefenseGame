package com.game.main;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class ArcadeGame extends LinearLayout {

	private Context mContex;
	
	private Timer mUpdateTimer;
	
	private long mPeriod = 1000;
	protected boolean ingame = false;
	final int screendelay = 300;
	
	public ArcadeGame(Context context) {
		super(context);
		mContex = context;
	}
	
	public ArcadeGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContex = context;
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
	
	public void setUpdatePeriod(long period) {
		mPeriod = period;
	}
	
	
	

	protected void startUpdateTimer() {
		this.ingame = true;
		mUpdateTimer = new Timer();
		mUpdateTimer.schedule(new UpdateTask(), 0);
	}
	
	protected void stopUpdateTimer() {
		if (mUpdateTimer != null)
			mUpdateTimer.cancel();
	}
	
	public Context getContex() {
		return mContex;
	}
	

	
	abstract protected void updatePhysics();
	
	abstract protected void initialize();
	
	abstract protected boolean gameOver();
	
	abstract protected long getScore();
	
	private class UpdateTask extends TimerTask {
		@Override
		public void run() {
			updatePhysics();
			
			postInvalidate();
			mUpdateTimer.schedule(new UpdateTask(), mPeriod);
		}
	}
	
	public void halt() {
		stopUpdateTimer();
	}
	
	public void resume() {
		initialize();
		startUpdateTimer();
	}
	
	
	

}
