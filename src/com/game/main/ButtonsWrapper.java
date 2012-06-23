package com.game.main;

import com.game.main.Constants.TowerType;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ButtonsWrapper {

	private RelativeLayout buttons;
	private Context context;
	public TextView roundView;
	public TextView moneyView;
	public TextView livesView;
	private TowerDefenseGame towerDefenseGame;
	private GameStatistics gameStatistics;
	private ImageButton pauseButton;
	private Bitmap pauseImage;
	private boolean showingPaused = false;
	private Bitmap startImage;
	private RelativeLayout towerSelectorView;
	private ImageButton towerSelectorToggleButton;
	private int buildTowerId;
	private ImageButton upgradeTowerButton;
	private ImageButton buildNormalTowerButton;
	private int buildNormalTowerId;
	private ImageButton buildFastTowerButton;
	private int buildHeavyTowerId;
	private ImageButton buildHeavyTowerButton;
	private RelativeLayout infoView;
	private int buildFastTowerId;
	private int pauseButtonId;
	
	public ButtonsWrapper(Context context, TowerDefenseGame towerDefenseGame,GameStatistics gameStatistics){
		this.context = context;
		this.towerDefenseGame = towerDefenseGame;
		this.gameStatistics = gameStatistics;
		buttons = new RelativeLayout(context);
		buttons.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		initializeButtons();
	}

	private void initializeButtons() {

		initializeTowerSelectorView();
		initializeTowerSelectorToggleButton();
		initializeTowerUpgradeButton();
		initializePauseButton();
		initializeInfoView();

		buttons.addView(infoView);
		buttons.addView(pauseButton);
		buttons.addView(towerSelectorToggleButton);
		buttons.addView(upgradeTowerButton);
		buttons.addView(towerSelectorView);
	}


	private void initializeInfoView() {
		infoView = new RelativeLayout(context);
		LayoutParams infoViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		infoViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		infoViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		infoView.setLayoutParams(infoViewLayout);

		int roundViewId = 1234667;
		roundView = new TextView(context);
		roundView.setId(roundViewId);
		roundView.setTextSize(24);
		roundView.setTextColor(Color.WHITE);
		roundView.setText("Round:" + gameStatistics.getRound() + " ");
		roundView.setBackgroundColor(Color.BLACK);

		int moneyViewId = 9876;
		moneyView = new TextView(context);
		moneyView.setId(moneyViewId);
		moneyView.setTextSize(24);
		moneyView.setText(" $:" + gameStatistics.getMoney() + " ");
		moneyView.setTextColor(Color.WHITE);
		moneyView.setBackgroundColor(Color.BLACK);

		LayoutParams moneyViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		moneyViewLayout.addRule(RelativeLayout.RIGHT_OF,roundViewId);
		moneyViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		moneyView.setLayoutParams(moneyViewLayout);

		livesView = new TextView(context);
		livesView.setTextSize(24);
		livesView.setText(" Lives:" + gameStatistics.getLives()  + " ");
		livesView.setTextColor(Color.WHITE);
		livesView.setBackgroundColor(Color.BLACK);

		LayoutParams livesViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		livesViewLayout.addRule(RelativeLayout.RIGHT_OF,moneyViewId);
		livesViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		livesView.setLayoutParams(livesViewLayout);
		
		infoView.addView(roundView);
		infoView.addView(moneyView);
		infoView.addView(livesView);

	}

	private void initializeBuildHeavyTowerButton() {
		buildHeavyTowerButton= new ImageButton(context);
		buildHeavyTowerId = 243;
		buildHeavyTowerButton.setId(buildHeavyTowerId);
		Bitmap buildHeavyTowerImage = getImage(R.drawable.heavy_tower_button);
		buildHeavyTowerImage = Bitmap.createScaledBitmap( buildHeavyTowerImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildHeavyTowerButton.setImageBitmap(buildHeavyTowerImage);
		//buildHeavyTowerButton.setBackgroundResource(0);


		LayoutParams buildHeavyTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		buildHeavyTowerButtonLayout.addRule(RelativeLayout.RIGHT_OF, buildFastTowerId);
		buildHeavyTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		buildHeavyTowerButton.setLayoutParams(buildHeavyTowerButtonLayout);


		buildHeavyTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.buildTowerClicked(TowerType.HEAVY);
			}
		});

	}

	private void initializeBuildFastTowerButton() {
		buildFastTowerButton= new ImageButton(context);
		buildFastTowerId = 243523;
		buildFastTowerButton.setId(buildFastTowerId);
		Bitmap BuildFastTowerImage = getImage(R.drawable.fast_tower_button);
		BuildFastTowerImage = Bitmap.createScaledBitmap( BuildFastTowerImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildFastTowerButton.setImageBitmap(BuildFastTowerImage);
		//buildFastTowerButton.setBackgroundResource(0);


		LayoutParams buildFastTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		buildFastTowerButtonLayout.addRule(RelativeLayout.RIGHT_OF, buildNormalTowerId);
		buildFastTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		buildFastTowerButton.setLayoutParams(buildFastTowerButtonLayout);


		buildFastTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.buildTowerClicked(TowerType.FAST);
			}
		});


	}

	private void initializeBuildNormalTowerButton() {
		buildNormalTowerButton= new ImageButton(context);
		buildNormalTowerId = 1434523;
		buildNormalTowerButton.setId(buildNormalTowerId);
		Bitmap BuildnormalTowerImage = getImage(R.drawable.build_tower_button);
		BuildnormalTowerImage = Bitmap.createScaledBitmap( BuildnormalTowerImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		buildNormalTowerButton.setImageBitmap(BuildnormalTowerImage);
		//buildNormalTowerButton.setBackgroundResource(0);


		LayoutParams buildNormalTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		buildNormalTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		buildNormalTowerButton.setLayoutParams(buildNormalTowerButtonLayout);

		buildNormalTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.buildTowerClicked(TowerType.BASIC);
			}
		});


	}

	private void initializePauseButton() {
		pauseButton= new ImageButton(context);
		pauseButtonId = 67543;
		pauseButton.setId(pauseButtonId);
		pauseImage = getImage(R.drawable.pause_button);
		pauseImage = Bitmap.createScaledBitmap( pauseImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);

		startImage = getImage(R.drawable.start_button);
		startImage = Bitmap.createScaledBitmap( startImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		pauseButton.setImageBitmap(startImage);
		pauseButton.setBackgroundResource(0);
		pauseButton.setClickable(true);
		LayoutParams pauseButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		pauseButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		pauseButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		pauseButton.setLayoutParams(pauseButtonLayout);


		pauseButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.pauseOrStartRoundClicked();
			}


		});


	}

	private void initializeTowerUpgradeButton() {
		upgradeTowerButton= new ImageButton(context);
		Bitmap upgradeImage = getImage(R.drawable.upgrade_tower_button);
		upgradeImage = Bitmap.createScaledBitmap( upgradeImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		upgradeTowerButton.setImageBitmap(upgradeImage);
		upgradeTowerButton.setBackgroundResource(0);


		LayoutParams upgradeTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		upgradeTowerButtonLayout.addRule(RelativeLayout.ABOVE,buildTowerId);
		upgradeTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		upgradeTowerButton.setLayoutParams(upgradeTowerButtonLayout);

		upgradeTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {

				towerDefenseGame.showTowerUpgradeOptions();
			}
		});




	}

	private void initializeTowerSelectorToggleButton() {

		towerSelectorToggleButton= new ImageButton(context);
		buildTowerId = 12235423;
		towerSelectorToggleButton.setId(buildTowerId);
		Bitmap buttonImage = getImage(R.drawable.build_tower_button);
		buttonImage = Bitmap.createScaledBitmap( buttonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		towerSelectorToggleButton.setImageBitmap(buttonImage);
		towerSelectorToggleButton.setBackgroundResource(0);


		LayoutParams towerSelectorToggleButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		towerSelectorToggleButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		towerSelectorToggleButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		towerSelectorToggleButton.setLayoutParams(towerSelectorToggleButtonLayout);

		towerSelectorToggleButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {

				if(towerSelectorView.getVisibility() == View.GONE){
					towerSelectorView.setVisibility(View.VISIBLE);
					TranslateAnimation a = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF,
							0.0f,
							Animation.RELATIVE_TO_SELF,
							0.0f,
							Animation.RELATIVE_TO_SELF,
							1.0f,
							Animation.RELATIVE_TO_SELF,
							0.0f);

					a.setDuration(500);
					towerSelectorView.startAnimation(a);
				}else{

					TranslateAnimation a = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF,
							0.0f,
							Animation.RELATIVE_TO_SELF,
							0.0f,
							Animation.RELATIVE_TO_SELF,
							0.0f,
							Animation.RELATIVE_TO_SELF,
							1.0f);
					a.setDuration(500);
					towerSelectorView.startAnimation(a);
					towerSelectorView.setVisibility(View.GONE);
				}


			}
		});


	}

	private void initializeTowerSelectorView() {
		
		towerSelectorView = new RelativeLayout(context);
		LayoutParams towerSelectorViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		towerSelectorViewLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		towerSelectorViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		towerSelectorView.setLayoutParams(towerSelectorViewLayout);
		
		initializeBuildNormalTowerButton();
		initializeBuildFastTowerButton();
		initializeBuildHeavyTowerButton();
		
		towerSelectorView.addView(buildNormalTowerButton);
		towerSelectorView.addView(buildFastTowerButton);
		towerSelectorView.addView(buildHeavyTowerButton);

	}

	private Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}
	public void setTowerDefenseGame(TowerDefenseGame towerDefenseGame){
		this.towerDefenseGame = towerDefenseGame;
	}

	public void togglePausePlayButtons(){
		if (!showingPaused){
			pauseButton.setImageBitmap(pauseImage);
			showingPaused = true;
		}
		else{
			showingPaused = false;
			pauseButton.setImageBitmap(startImage);
		}
	}

	public boolean isShowingPaused(){
		return showingPaused;
	}

	public RelativeLayout getButtons(){
		return buttons;
	}

	public void refreshButtons(){
		moneyView.setText(" Cash: " + gameStatistics.getMoney() + " ");
		livesView.setText(" Lives: " + gameStatistics.getLives()  + " ");
		roundView.setText("Round: " + gameStatistics.getRound() + " ");
		Log.e("refreshing buttons", "money: " + gameStatistics.getMoney() + moneyView.getText());
	}
}
