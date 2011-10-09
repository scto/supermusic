package com.naivesoft.game.supermusic.entity;

public class AnimationBackground {
	public int getChangeRate() {
		return changeRate;
	}
	public float getXrate() {
		return x_rate;
	}
	public float getYrate() {
		return y_rate;
	}
	public boolean canMove() {
		return canMove;
	}
	public AnimationBackground(int changeRate, float xRate, float yRate, boolean canMove) {
		super();
		this.changeRate = changeRate;
		x_rate = xRate;
		y_rate = yRate;
		this.canMove = canMove;
	}
	
	public void setX_rate(float x_rate) {
		this.x_rate = x_rate;
	}
	public void setY_rate(float y_rate) {
		this.y_rate = y_rate;
	}
	
	private boolean canMove;
	private int changeRate;
	//x, y轴移动的速度比率, 在计算时是相对于背景的，y轴速度要加上背景的速度
	private float x_rate;
	private float y_rate;
	public static float speed = 0.08f;

}
