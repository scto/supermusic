package com.naivesoft.game.supermusic.entity;

public class RandomBackground extends GameObject{
	
	public RandomBackground(float x, float y, RAND_BACKGROUND type) {
		super(x, y, RB_WIDTH, RB_HEIGHT);
		this.type = type;
	}

	public static final float RB_WIDTH = 2f;
	public static final float RB_HEIGHT = 2f;
	
	public final RAND_BACKGROUND type;
	
	public static enum RAND_BACKGROUND {
		RB1,
		RB2,
		RB3,
		RB4,
		RB5,
		RB6,
	}
}

