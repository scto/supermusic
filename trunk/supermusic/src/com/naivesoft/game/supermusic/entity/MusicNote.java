package com.naivesoft.game.supermusic.entity;

public class MusicNote extends GameObject{
	public static final float MUSICNOTE_WIDTH = 0.5f;
	public static final float MUSICNOTE_HEIGHT = 0.8f;

	public final MUSICNOTE_KIND musicKind;
	public final MUSICNODE_LEVEL musicLevel;
	
	public static enum MUSICNOTE_KIND{
		DO,
		RE,
		MI,
		FA,
		SO,
		LA,
		XI,
	}
	
	public static enum MUSICNODE_LEVEL {
		LEVEL1,
		LEVEL2,
		LEVEL3,
	}
	
	float stateTime;
	public MusicNote(float x, float y, MUSICNOTE_KIND musicKind, MUSICNODE_LEVEL musicLevel) {
		super(x,y,MUSICNOTE_WIDTH,MUSICNOTE_HEIGHT);
		stateTime = 0;
		this.musicKind = musicKind;
		this.musicLevel = musicLevel;
	}
	
	public void update(float deltaTime) {
        stateTime += deltaTime;
    }

}
