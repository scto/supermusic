package com.naivesoft.game.supermusic;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class SuperMusicDesktop {
	
	public static void main(String[] args){
		new LwjglApplication(new SuperMusic(), "Game", 320, 480, false);
	}
}
