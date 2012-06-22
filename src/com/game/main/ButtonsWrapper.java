package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ButtonsWrapper {

	private LinearLayout buttons;
	private Context context;
	public TextView roundView;
	public TextView moneyView;
	public TextView livesView;
	private TowerDefenseGame towerDefenseGame;
	private GameStatistics gameStatistics;
	private ImageButton pauseButton;
	private Bitmap pauseImage;
	private boolean showingPaused = true;
	private Bitmap startImage;

	public ButtonsWrapper(Context context, TowerDefenseGame towerDefenseGame,GameStatistics gameStatistics){
		this.context = context;
		this.towerDefenseGame = towerDefenseGame;
		this.gameStatistics = gameStatistics;
		buttons = new LinearLayout(context);
		initializeButtons();
	}

	public LinearLayout getButtons(){
		return buttons;
	}

	public void refreshButtons(){
		moneyView.setText(" Cash: " + gameStatistics.getMoney() + " ");
		livesView.setText(" Lives: " + gameStatistics.getLives()  + " ");
		roundView.setText("Round: " + gameStatistics.getRound() + " ");
		Log.e("refreshing buttons", "money: " + gameStatistics.getMoney() + moneyView.getText());
	}

	private void initializeButtons() {
		ImageButton buildTowerButton= new ImageButton(context);
		Bitmap buttonImage = getImage(R.drawable.build_tower_button);
		buttonImage = Bitmap.createScaledBitmap( buttonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildTowerButton.setImageBitmap(buttonImage);
		buildTowerButton.setBackgroundResource(0);
		buildTowerButton.setClickable(true);
		buildTowerButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		buildTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {

				towerDefenseGame.showTowerOptions();
			}
		});


		pauseButton= new ImageButton(context);
		pauseImage = getImage(R.drawable.pause_button);
		pauseImage = Bitmap.createScaledBitmap( pauseImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		
		startImage = getImage(R.drawable.start_button);
		startImage = Bitmap.createScaledBitmap( startImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		pauseButton.setImageBitmap(startImage);
		pauseButton.setBackgroundResource(0);
		pauseButton.setClickable(true);
		pauseButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));

		pauseButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.pauseOrStartRoundClicked();
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
		buttons.addView(pauseButton);
		buttons.addView(buildTowerButton);
	}

	private Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}
	public void setTowerDefenseGame(TowerDefenseGame towerDefenseGame){
		this.towerDefenseGame = towerDefenseGame;
	}
	
	public void togglePausePlayButtons(){
		if (showingPaused){
			pauseButton.setImageBitmap(pauseImage);
			showingPaused = false;
		}
		else{
			showingPaused = true;
			pauseButton.setImageBitmap(startImage);
		}
	}
}
