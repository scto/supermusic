package com.naivesoft.game.supermusic.entity;

import com.naivesoft.game.supermusic.world.MusicWorld;


public class Superman extends DynamicGameObject{
	public static final float SUPERMAN_WIDTH = 0.8f;
	public static final float SUPERMAN_HEIGHT = 0.8f;
	public static final float SUPERMAN_MOVE_VELOCITY = 18f;
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
		
		velocity.add(MusicWorld.gravity.x * deltaTime, MusicWorld.gravity.y * deltaTime);
		if(velocity.y < 2) velocity.y = 2;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
//        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
        
        if(position.x < 0)
            position.x = MusicWorld.WORLD_WIDTH;
        if(position.x > MusicWorld.WORLD_WIDTH)
            position.x = 0;
        
		stateTime += deltaTime;
	}

}
