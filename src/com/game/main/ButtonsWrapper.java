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
	private boolean isShowingUpgradeView = false;
	private Bitmap startImage;
	private RelativeLayout towerBuildView;
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
	private RelativeLayout towerUpgradeView;
	private ImageButton sellTowerButton;
	private int upgradeTowerButtonId;
	private boolean isShowingBuildView = true;

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
		initializeTowerUpgradeView();
		initializeTowerSelectorToggleButton();
		initializePauseButton();
		initializeInfoView();

		buttons.addView(infoView);
		buttons.addView(pauseButton);
		buttons.addView(towerSelectorToggleButton);
		buttons.addView(towerBuildView);
		buttons.addView(towerUpgradeView);
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
				towerDefenseGame.toggleBuildAndUpgradeButtonClicked();
			}
		});


	}

	private void initializeTowerSelectorView() {
		
		towerBuildView = new RelativeLayout(context);
		LayoutParams towerSelectorViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		towerSelectorViewLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		towerSelectorViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		towerBuildView.setLayoutParams(towerSelectorViewLayout);
		
		initializeBuildNormalTowerButton();
		initializeBuildFastTowerButton();
		initializeBuildHeavyTowerButton();
		
		towerBuildView.addView(buildNormalTowerButton);
		towerBuildView.addView(buildFastTowerButton);
		towerBuildView.addView(buildHeavyTowerButton);

	}
	
private void initializeTowerUpgradeView() {
		
		towerUpgradeView = new RelativeLayout(context);
		LayoutParams towerUpgradeViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		towerUpgradeViewLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		towerUpgradeViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		towerUpgradeView.setLayoutParams(towerUpgradeViewLayout);
		
		
		initializeUpgradeTowerButton();
		initializeSellTowerButton();
		
		
		
		towerUpgradeView.addView(sellTowerButton);
		towerUpgradeView.addView(upgradeTowerButton);
		towerUpgradeView.setVisibility(View.GONE);

	}

	private void initializeUpgradeTowerButton() {
		upgradeTowerButton= new ImageButton(context);
		upgradeTowerButtonId = 143497;
		upgradeTowerButton.setId(upgradeTowerButtonId);
		Bitmap upgradeTowerButtonImage = getImage(R.drawable.upgrade_tower_button);
		upgradeTowerButtonImage = Bitmap.createScaledBitmap( upgradeTowerButtonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		upgradeTowerButton.setImageBitmap(upgradeTowerButtonImage);


		LayoutParams upgradeTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		upgradeTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		upgradeTowerButton.setLayoutParams(upgradeTowerButtonLayout);

		upgradeTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.showTowerUpgradeOptions();
			}
		});
	
}

	private void initializeSellTowerButton() {
		sellTowerButton= new ImageButton(context);
		Bitmap sellTowerButtonImage = getImage(R.drawable.sell_tower);
		sellTowerButtonImage = Bitmap.createScaledBitmap( sellTowerButtonImage, Constants.GRID_SQUARE_SIZE, Constants.GRID_SQUARE_SIZE, false);
		sellTowerButton.setImageBitmap(sellTowerButtonImage);


		LayoutParams sellTowerButtonLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		sellTowerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		sellTowerButtonLayout.addRule(RelativeLayout.RIGHT_OF, upgradeTowerButtonId);
		sellTowerButton.setLayoutParams(sellTowerButtonLayout);

		sellTowerButton.setOnClickListener(new OnClickListener() {            
			public void onClick(View v) {
				towerDefenseGame.showSellTowerDialog();
			}
		});
	
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


	public boolean isShowingUpgradeView() {
		return isShowingUpgradeView;
	}
	
	public boolean isShowingBuildView() {
		return isShowingBuildView;
	}

	public void hideBuildView(){
		isShowingBuildView = false;
		towerBuildView.setClickable(false);
		TranslateAnimation a = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				1.0f);
		a.setDuration(200);
		towerBuildView.startAnimation(a);
		towerBuildView.setVisibility(View.GONE);
	}
	
	public void showBuildView() {
		isShowingBuildView = true;
		//towerBuildView.setClickable(true);
		towerBuildView.setVisibility(View.VISIBLE);
		TranslateAnimation a = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				1.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f);

		a.setDuration(200);
		towerBuildView.startAnimation(a);

	}
	
	public void hideUpgradeView(){
		isShowingUpgradeView = false;
		towerUpgradeView.setClickable(false);
		TranslateAnimation b = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				1.0f);
		b.setDuration(200);
		towerUpgradeView.startAnimation(b);
		towerUpgradeView.setVisibility(View.GONE);
	}

	public void showUpgradeView() {
		isShowingUpgradeView = true;
		//towerUpgradeView.setClickable(true);
		towerUpgradeView.setVisibility(View.VISIBLE);
		TranslateAnimation b = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f,
				Animation.RELATIVE_TO_SELF,
				1.0f,
				Animation.RELATIVE_TO_SELF,
				0.0f);

		b.setDuration(200);
		towerUpgradeView.startAnimation(b);
		
	}
}
