package com.naivesoft.game.supermusic.service;

import com.naivesoft.game.supermusic.control.IMusicControl;
import com.naivesoft.game.supermusic.midi.MidiSong;

public class MusicService {
	private IMusicControl control;
	private MidiSong song;
	
	//当前是否需要静音
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
	
	public void pause() {
		control.pause();
	}
	
	public void play() {
		control.play();
	}
	
	public void stop() {
		control.clearQueue();
		control.closeFile();
	}
	
	public void restart() {
		control.clearQueue();
		control.queueJetSegment(0, -1, 0, 0, 0, (byte)0);
	}
	
	//设置停止静音，播放音轨
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
