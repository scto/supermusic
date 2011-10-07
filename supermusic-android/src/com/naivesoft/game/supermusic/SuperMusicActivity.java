package com.naivesoft.game.supermusic;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.naivesoft.game.supermusic.control.impl.MusicControlImpl;
import com.naivesoft.game.supermusic.midi.MidiSong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.JetPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

public class SuperMusicActivity extends AndroidApplication{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	MusicControlImpl control = MusicControlImpl.getMusicControlImpl();
    	control.init(this);
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
    	
        super.onCreate(savedInstanceState);
        initialize(new SuperMusic(control), false);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK) {
    		return true;
    	}
    	return false;
    }
}