package com.naivesoft.game.supermusic.midi;
import java.util.*;

public class MidiSong {
	
	//��ӦR.raw.filename
	private int fileID;
	
	//�����Ľ����ٶȣ�ÿ���Ӷ�����
	private double BPM;	
	
	//��Ҫ����������
	private int[] muteTracks;
	
	//��ʱ��
	private float totalTime;
	
	public MidiSong(int fileID, int[] muteTracks, float totalTime) {
		super();
		this.fileID = fileID;
		this.muteTracks = muteTracks;
		this.setTotalTime(totalTime);
	}
	
	//����BPM���㾲�����
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
