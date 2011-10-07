package com.naivesoft.game.supermusic.store;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreStore {
	public final static int HIGH_SCORE_MISSION_1 = 1;
	public final static int HIGH_SCORE_MISSION_2 = 2;
	public final static int HIGH_SCORE_MISSION_3 = 3;
	public final static int HIGH_SCORE_MISSION_4 = 4;
	
	private final static String pref = "HIGHSCORE";
	private final static String fileName = "highScore";
	
	private static Preferences preferences = Gdx.app.getPreferences("highScore" + ".prefs");
	
	public static int getHighScore(int mission) {
		return preferences.getInteger(pref + mission, 0);
	}
	
	public static void setHighScore(int mission, int score) {
		if(score > preferences.getInteger(pref + mission, 0)) {
			preferences.putInteger(pref + mission, score);
		}
	}
	
}
