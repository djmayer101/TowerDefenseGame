package com.game.main;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoView extends RelativeLayout{

	private TextView roundView;
	private GameStatistics gameStatistics;
	private TextView moneyView;
	private TextView livesView;

	public InfoView(Context context, GameStatistics gameStatistics) {
		super(context);
		this.gameStatistics = gameStatistics;
		LayoutParams infoViewLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		infoViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		infoViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.setLayoutParams(infoViewLayout);

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
		
		addView(roundView);
		addView(moneyView);
		addView(livesView);
	}
	
	public void refreshButtons(){
		moneyView.setText(" Cash: " + gameStatistics.getMoney() + " ");
		livesView.setText(" Lives: " + gameStatistics.getLives()  + " ");
		roundView.setText("Round: " + gameStatistics.getRound() + " ");
	}

}
