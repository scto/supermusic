package com.naivesoft.game.supermusic.service;

import com.naivesoft.game.supermusic.control.IMusicControl;
import com.naivesoft.game.supermusic.midi.MidiSong;

public class MusicService extends Thread{
	private IMusicControl control;
	private MidiSong song;
	
	//��ǰ�Ƿ���Ҫ����
	private boolean isMute;	
	
	private long startTime = System.currentTimeMillis();
	private boolean isPlaying;
	private boolean stop = false;
	
	public MusicService(IMusicControl control, MidiSong song) {
		this.control = control;
		isMute = true;
		this.song = song;
	}
	
	@Override
	public void run() {
		isPlaying = true;
		while(!stop) {
			setTrackMuteInTime(song.getPauseTime());
		}
		isPlaying = false;
	}
	
	//������ֹͣ
	public void done(){
		stop = true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	//����ֹͣ��������������
	public void setMuteOff() {
		isMute = false;
		for(int i = 0; i < song.getMuteTracks().length; i++)
			control.setMuteFlag(song.getMuteTracks()[i], false, false);
	}
	
	private void setTrackMuteInTime(long millis) {
		try {
			if(isMute) {
				for(int i = 0; i < song.getMuteTracks().length; i++)
					control.setMuteFlag(song.getMuteTracks()[i], true, false);
			}
			Thread.sleep(millis);
			isMute = true;
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
