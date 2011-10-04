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
	
	private boolean canMove;
	private int changeRate;
	//x, y���ƶ����ٶȱ���, �ڼ���ʱ������ڱ����ģ�y���ٶ�Ҫ���ϱ������ٶ�
	private float x_rate;
	private float y_rate;
}
