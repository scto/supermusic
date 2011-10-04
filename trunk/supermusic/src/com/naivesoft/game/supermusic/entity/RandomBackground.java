package com.naivesoft.game.supermusic.entity;

import java.util.*;

import com.naivesoft.game.supermusic.system.Art;
import java.util.Random;

public class RandomBackground extends GameObject{
	
	public RandomBackground(float x, float y, RAND_BACKGROUND type) {
		super(x, y, RB_WIDTH, RB_HEIGHT);
		this.type = type;
		this.animation = Art.current_change_rates.get(type);
		this.frameCount = new Random().nextInt(animation.getChangeRate());
	}
	
	
	public int getCount() {
		frameCount++;
		return frameCount / animation.getChangeRate();
	}
	
	public AnimationBackground getAnimation() {
		return animation;
	}

	public static final float RB_WIDTH = 2f;
	public static final float RB_HEIGHT = 2f;
	
	public final RAND_BACKGROUND type;
	private int frameCount;
	private AnimationBackground animation;
	
	public static enum RAND_BACKGROUND {
		RB1,
		RB2,
		RB3
	}
}

