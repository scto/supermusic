package com.naivesoft.game.supermusic.service;

import com.naivesoft.game.supermusic.control.IMusicControl;
import com.naivesoft.game.supermusic.midi.MidiSong;

public class MusicService {
	private IMusicControl control;
	private MidiSong song;
	
	//��ǰ�Ƿ���Ҫ����
	private boolean isMute;	
	
	private boolean isPlaying;
	
	public MusicService(IMusicControl control, MidiSong song) {
		this.control = control;
		isMute = true;
		this.song = song;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	//����ֹͣ��������������
	public void setMuteOff() {
		control.setAllTrackMuteOff();
	}
	
	public void setTrackMute() {
		for(int i = 0; i < song.getMuteTracks().length; i++)
			control.setMuteFlag(song.getMuteTracks()[i], true, false);
	}
	
	public void setAllTracksMute() {
		control.setAllTrackMute();
	}
}
