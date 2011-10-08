package com.naivesoft.game.supermusic.store;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.naivesoft.game.supermusic.system.Stats;

public class ScoreStore {
	
	private final static String pref = "HIGHSCORE";
	private final static String fileName = "highScore";
	
	private static Preferences preferences = Gdx.app.getPreferences(fileName + ".prefs");
	
	/**
	 * @param fileId use Stats.currentSong.getFileID()
	 * @return the exist high score, default is 0
	 */
	public static int getHighScore(int fileId) {
		return preferences.getInteger(pref + fileId, 0);
	}
	
	/**
	 * @param fileId use Stats.currentSong.getFileID()
	 * @param the new score
	 */
	public static void setHighScore(int fileId, int score) {
		if(score > preferences.getInteger(pref + fileId, 0)) {
			preferences.putInteger(pref + fileId, score);
		}
	}
	
}
