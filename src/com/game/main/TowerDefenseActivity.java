package com.game.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class TowerDefenseActivity extends Activity{
	private View view;
	private TowerDefenseView towerDefenseView;
	private TowerDefenseGame towerDefenseGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LayoutInflater factory = LayoutInflater.from(this);

		view = factory.inflate(R.layout.main, null);
		setContentView(view);

		towerDefenseView = (TowerDefenseView) this.findViewById(R.id.tower_defense_view);
		towerDefenseView.setTowerDefenseActivity(this);

		view.setFocusable(true);
		view.setFocusableInTouchMode(true);

	}

	public void setTowerDefenseGame (){
		towerDefenseGame = towerDefenseView.getGame();
	}

	@Override
	protected void onStop() {
		super.onStop();
		towerDefenseGame.getUpdateTaskManager().halt();
	}

	@Override
	protected void onPause() {
		super.onPause();
		towerDefenseGame.getUpdateTaskManager().pause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		towerDefenseGame.getUpdateTaskManager().resume();
		if(towerDefenseView.getButtonsWrapper().isShowingPaused()){
			towerDefenseView.togglePausePlayButtons();
		}
	}

	protected void backToMenu(){
		Intent i = new Intent(TowerDefenseActivity.this, GameMainMenuActivity.class);
		startActivity(i);
		finish();
	}

	public void startNewGame(){
		Intent i = new Intent(TowerDefenseActivity.this, TowerDefenseActivity.class);
		startActivity(i);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		towerDefenseGame.showMenu();

	}



}
