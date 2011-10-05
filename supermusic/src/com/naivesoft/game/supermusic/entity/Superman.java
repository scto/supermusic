package com.naivesoft.game.supermusic.entity;

import com.naivesoft.game.supermusic.world.FlyWorld;


public class Superman extends DynamicGameObject{
	public static final float SUPERMAN_WIDTH = 0.8f;
	public static final float SUPERMAN_HEIGHT = 0.8f;
	public static final float SUPERMAN_MOVE_VELOCITY = 18f;
	public static final float SUPERMAN_MOVE_MAX_VELOCITY = 18f;
	public static final float SUPERMAN_MAX_SPEED = 1f;
	
	private float stateTime;

	public Superman(float x, float y) {
		super(x, y, SUPERMAN_WIDTH, SUPERMAN_HEIGHT);
		stateTime = 0;
		this.velocity.y = 5;
	}

	@Override
	public void update(float deltaTime) {
//		velocity.add(MusicWorld.gravity.x, 0);
//		velocity.y = MusicWorld.gravity.y + (float)Math.sin(stateTime);
		
	//	velocity.add(MusicWorld.gravity.x * deltaTime, MusicWorld.gravity.y * deltaTime);
	//	if(velocity.y < 2) velocity.y = 2;
//		if(velocity.x >= 0 && accel.x >= 0 || (velocity.x <= 0 && accel.x <= 0)) {
//			velocity.add(accel.x * deltaTime, 0);
//		} else {
//			velocity.add(10 * accel.x * deltaTime, 0);
//		}
//		if(velocity.y >= 5 && accel.y >= 0 || (velocity.y <= 5 && accel.y <= 0)) {
//			velocity.add(0, accel.y * deltaTime);
//		} else {
//			velocity.add(0, 10 * accel.y * deltaTime);
//		}
		
		velocity.x = accel.x;
		velocity.y = accel.y + 5;
		
		//velocity.add(accel.x * deltaTime, accel.y * deltaTime);
		
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
//        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
        
        if(position.x - 0.5f < 0) {
        	position.x = 0.5f;
        	velocity.x = 0;
        }
        if(position.x + 0.5f> FlyWorld.WORLD_WIDTH) {
        	position.x = FlyWorld.WORLD_WIDTH - 0.5f;
        	velocity.x = 0;
        }
		stateTime += deltaTime;
	}

}
