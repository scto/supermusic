package com.naivesoft.game.supermusic;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.naivesoft.game.supermusic.control.impl.MusicControlImpl;

public class GameActivity extends AndroidApplication{
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	MusicControlImpl control = MusicControlImpl.getMusicControlImpl();
    	control.init(this);

        initialize(new SuperMusic(control), false);
        
    }
}
