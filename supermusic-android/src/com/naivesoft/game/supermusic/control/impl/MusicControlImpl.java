package com.naivesoft.game.supermusic.control.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.JetPlayer;

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
		
//		jetPlayer = JetPlayer.getJetPlayer();
//		jetPlayer.queueJetSegment(0, -1, 0, 0, 0, (byte) 0);
		jetPlayer = JetPlayer.getJetPlayer();
		//jetPlayer.loadJetFile(context.getResources().openRawResourceFd(R.raw.shuaicong));
       
     //  jetPlayer.play();
		
		musicList = new ArrayList<MidiSong>();
		musicList.add(new MidiSong(R.raw.shuaicong,120,new int[]{1,5,6}));
		musicList.add(new MidiSong(R.raw.beatit,138,new int[]{6,8,9,10}));
		musicList.add(new MidiSong(R.raw.gowest,120,new int[]{4,7}));
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
		jetPlayer.loadJetFile(context.getResources().openRawResourceFd(rawId));
		return false;
	}

	@Override
	public boolean play() {
		jetPlayer.play();
		return false;
	}

	@Override
	public boolean pause() {
		jetPlayer.pause();
		return false;
	}
	
	@Override
	public boolean clearQueue() {
		jetPlayer.clearQueue();
		return false;
	}

	@Override
	public boolean setMuteFlag(int trackId, boolean muteFlag, boolean sync) {
		jetPlayer.setMuteFlag(trackId, muteFlag, sync);
		return false;
	}

	@Override
	public boolean queueJetSegment(int segmentNum, int libNum, int repeatCount,
			int transpose, int muteFlags, byte userID) {
		jetPlayer.queueJetSegment(segmentNum, libNum, repeatCount, transpose, muteFlags, userID);
		return false;
	}

}
