package com.naivesoft.game.supermusic.system;

import com.naivesoft.game.supermusic.midi.MidiSong;
import com.naivesoft.game.supermusic.style.GameStyle;

public class Stats {
	public static final int MAX_BLOOD = 10;
	public static final int MIN_BLOOD = 0;
	
	public static MidiSong currentSong;
	
	/**
	 * 0 - 10
	 */
	public static int blood;
	
	public static int score;
	
	/**
	 * 0 - 1
	 */
	public static float magnetism;//ci li
	
	public static void addBlood(int newBlood) {
		if(blood + newBlood > MAX_BLOOD) {
			score += (blood + newBlood - MAX_BLOOD) * 100;
		}
		blood = blood + newBlood > MAX_BLOOD ? MAX_BLOOD : blood + newBlood;
	}
	
	public static void removeBlood(int newBlood) {
		blood = blood - newBlood < MIN_BLOOD ? MIN_BLOOD : blood - newBlood;
	}
	
	public static void addScore(int addedScore) {
		score += addedScore;
	}
	
	public static GameStyle gameStyle;
}
