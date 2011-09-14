package com.naivesoft.game.supermusic;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.naivesoft.game.supermusic.control.impl.MusicControlImpl;
import com.naivesoft.game.supermusic.midi.MidiSong;

import android.app.Activity;
import android.media.JetPlayer;
import android.os.Bundle;

public class SuperMusicActivity extends AndroidApplication{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	MusicControlImpl control = MusicControlImpl.getMusicControlImpl();
    	control.init(this);
//    	control.loadJetFile(R.raw.shuaicong);
//    	byte sSegmentID = 0;
//    	control.queueJetSegment(0, -1, 0, 0, 0, sSegmentID);
    	
        super.onCreate(savedInstanceState);
        initialize(new SuperMusic(control), false);
//        setContentView(R.layout.main);
//        MidiSong midiSong = new MidiSong(R.raw.shuaicong,120,new int[]{1,5,6});
//        JetPlayer jetPlayer = JetPlayer.getJetPlayer();
//        jetPlayer.loadJetFile(getResources().openRawResourceFd(R.raw.shuaicong));
//        byte sSegmentID = 0;
//        jetPlayer.queueJetSegment(0, -1, 0, 0, 0, sSegmentID);
//        jetPlayer.play();
    }
}