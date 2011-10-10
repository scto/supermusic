package com.naivesoft.game.supermusic.control;

import java.util.List;

import com.naivesoft.game.supermusic.midi.MidiSong;

public interface IMusicControl {
	
	/**
	 * return the available jet music
	 * @return
	 */
	public List<MidiSong> getJetList();
	
	/**
	 * load a existing jet music
	 * @param rawId
	 * @return
	 */
	public boolean loadJetFile(int rawId);
	
	/**
	 * make the jet player to play, please load a raw first
	 * @return
	 */
	public boolean play();
	
	public boolean pause();
	
	/**
	 * make the jet player to stop
	 * @return
	 */
	public boolean clearQueue();
	
	/**
	 * @see JetPlayer
	 * @return
	 */
	
	public void closeFile();
	
	public boolean setMuteFlag(int trackId, boolean muteFlag, boolean sync);
	
	public void release();
	
	public boolean setAllTrackMute();
	
	public boolean setAllTrackMuteOff();
	
	public boolean queueJetSegment(int segmentNum, int libNum, int repeatCount, int transpose, int muteFlags, byte userID);
	
}
