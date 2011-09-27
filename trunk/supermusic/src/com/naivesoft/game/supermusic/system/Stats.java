package com.naivesoft.game.supermusic.system;

import com.naivesoft.game.supermusic.midi.MidiSong;
import com.naivesoft.game.supermusic.style.GameStyle;

public class Stats {
	public static final int MAX_BLOOD = 10;
	public static final int MIN_BLOOD = 0;
	
	public static final float MAX_MAGNET = 5.0f;
	public static final float MIN_MAGNET = 0.4f;
	
	public static MidiSong currentSong;
	
	/**
	 * 0 - 10 DO NOT SET IT BY YOURSELF 
	 */
	public static int blood = 5;
	
	public static int score = 0;
	
	public static int doubleScore = 1;
	
	/**
	 * 0.4 - 5 DO NOT SET IT BY YOURSELF 
	 */
	public static float magnetism = 1f;//ci li
	
	public static int magnetTime = 0;
	public static int projectiveTime = 0;//TODO
	public static int doubleScoreTime = 0;
	
	public static void addBlood(int newBlood) {
		if(blood + newBlood > MAX_BLOOD) {
			score += (blood + newBlood - MAX_BLOOD) * 100;
		}
		blood = blood + newBlood > MAX_BLOOD ? MAX_BLOOD : blood + newBlood;
	}
	
	public static void removeBlood(int newBlood) {
		blood = blood - newBlood < MIN_BLOOD ? MIN_BLOOD : blood - newBlood;
	}
	
	public static void fillBlood() {
		blood = MAX_BLOOD;
	}
	
	public static void halfBlood() {
		blood /= 2;
	}
	
	public static void addScore(int addedScore) {
		score += addedScore * doubleScore;
	}
	
	public static void doubleScore() {
		doubleScore = 2;
		doubleScoreTime = 5;
	}
	
	public static void removeDoubleScore() {
		doubleScore = 1;
	}
	
	public static void addMagnetLevel() {
		if(magnetism != MAX_MAGNET) {
			magnetism *= 2.5f;
		}
		magnetTime = 5;
	}
	
	public static void cutDownMagnetLevel() {
		if(magnetism != MIN_MAGNET) {
			magnetism /= 2.5f;
		}
		magnetTime = 5;
	}
	
	/**
	 * call every second
	 */
	public static void removeEffect() {
		if(magnetTime > 0) {
			magnetTime -= 1;
			if(magnetTime == 0) {
				magnetism = 1f;
			}
		}
		if(projectiveTime > 0) {
			projectiveTime -= 1;
			if(projectiveTime == 0) {
				//TODO remove projective effect
			}
		}
		if(doubleScoreTime > 0) {
			doubleScoreTime -= 1;
			if(doubleScoreTime == 0) {
				removeDoubleScore();
			}
		}
	}
	
	public static GameStyle gameStyle;
}
