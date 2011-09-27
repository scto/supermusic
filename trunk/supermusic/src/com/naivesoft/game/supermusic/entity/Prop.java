package com.naivesoft.game.supermusic.entity;

public class Prop extends GameObject{

	public static final float PROP_WIDTH = 0.8f;
	public static final float PROP_HEIGHT = 0.8f;
	
	public Prop(float x, float y, PROP_KIND kind) {
		super(x, y, PROP_WIDTH, PROP_HEIGHT);
		this.kind = kind;
	}
	
	public static enum PROP_KIND {
		magnet_positive,
		magnet_negative,
		eatAll,
		fillBlood,
		halfBlood,
		doubleScores,
		maxNotes,
		protective,
	}

	public final PROP_KIND kind;
}
