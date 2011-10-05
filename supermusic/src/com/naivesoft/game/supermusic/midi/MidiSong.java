package com.naivesoft.game.supermusic.midi;
import java.util.*;

public class MidiSong {
	
	//对应R.raw.filename
	private int fileID;
	
	//歌曲的节拍速度，每分钟多少拍
	private double BPM;	
	
	//需要静音的音轨
	private int[] muteTracks;
	
	//总时间
	private float totalTime;
	
	public MidiSong(int fileID, int[] muteTracks, float totalTime) {
		super();
		this.fileID = fileID;
		this.muteTracks = muteTracks;
		this.setTotalTime(totalTime);
	}
	
	//根据BPM计算静音间隔
	public long getPauseTime() {
		return (long) (120000 / BPM); 
	}
	
	public int getFileID() {
		return fileID;
	}
	public double getBPM() {
		return BPM;
	}
	public int[] getMuteTracks() {
		return muteTracks;
	}

	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}

	public float getTotalTime() {
		return totalTime;
	}
	
	
}
