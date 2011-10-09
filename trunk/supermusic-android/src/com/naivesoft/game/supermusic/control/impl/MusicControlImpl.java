package com.naivesoft.game.supermusic.control.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.JetPlayer;
import android.util.Log;

import com.naivesoft.game.supermusic.R;
import com.naivesoft.game.supermusic.control.IMusicControl;
import com.naivesoft.game.supermusic.midi.MidiSong;

public class MusicControlImpl implements IMusicControl{

	private static MusicControlImpl control = new MusicControlImpl();
	
	private Context context;
	
	private JetPlayer jetPlayer;
	private List<MidiSong> musicList;
	
	private MusicControlImpl() {
		
	}
	
	public void init(Context context) {
		this.context = context;

		jetPlayer = JetPlayer.getJetPlayer();

		musicList = new ArrayList<MidiSong>();
		musicList.add(new MidiSong(R.raw.star, new int[] {4}, 239 ));
		musicList.add(new MidiSong(R.raw.china,new int[]{1}, 217));
		musicList.add(new MidiSong(R.raw.sky,new int[]{9}, 151));
		musicList.add(new MidiSong(R.raw.gowest,new int[]{4,7}, 200));
		musicList.add(new MidiSong(R.raw.punk,new int[]{5}, 161));
	}
	
	public static MusicControlImpl getMusicControlImpl() {
		return control;
	}
	
	@Override
	public List<MidiSong> getJetList() {
		return musicList;
	}

	@Override
	public boolean loadJetFile(int rawId) {
		return jetPlayer.loadJetFile(context.getResources().openRawResourceFd(rawId));
	}

	@Override
	public boolean play() {
		return jetPlayer.play();
	}

	@Override
	public boolean pause() {
		return jetPlayer.pause();
	}
	
	@Override
	public boolean clearQueue() {
		return jetPlayer.clearQueue();
	}

	@Override
	public boolean setMuteFlag(int trackId, boolean muteFlag, boolean sync) {
		return jetPlayer.setMuteFlag(trackId, muteFlag, sync);
	}

	@Override
	public boolean queueJetSegment(int segmentNum, int libNum, int repeatCount,
			int transpose, int muteFlags, byte userID) {
		return jetPlayer.queueJetSegment(segmentNum, libNum, repeatCount, transpose, muteFlags, userID);
	}
	
	@Override
	public boolean setAllTrackMute() {
		boolean[] flags = new boolean[JetPlayer.getMaxTracks()];
		for(int i = 0; i < JetPlayer.getMaxTracks(); i++)
			flags[i] = true;
		return jetPlayer.setMuteArray(flags, false);
	}
	
	@Override
	public boolean setAllTrackMuteOff() {
		boolean[] flags = new boolean[JetPlayer.getMaxTracks()];
		for(int i = 0; i < JetPlayer.getMaxTracks(); i++)
			flags[i] = false;
		return jetPlayer.setMuteArray(flags, false);
	}

	@Override
	public void release() {
		jetPlayer.release();
	}
	
}
