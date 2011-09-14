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
	private int totalTime = 146;
	
	public MidiSong(int fileID, double bPM, int[] muteTracks) {
		super();
		this.fileID = fileID;
		BPM = bPM;
		this.muteTracks = muteTracks;
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
	
	
}
