package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.service.MusicService;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.GameSound;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.world.MusicWorld;
import com.naivesoft.game.supermusic.world.MusicWorld.MusicWorldListener;
import com.naivesoft.game.supermusic.world.MusicWorldRender;

public class JumpGameScreen extends Screen{
	static final int GAME_PREPARING = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSE = 2;
	
	private int state;
	private float time = 0f;
	
	private OrthographicCamera guiCam;
	private Vector3 touchPoint;	
	
	private MusicWorld musicWorld;
	private MusicWorldListener musicWorldListener;
	private MusicWorldRender musicWorldRender;
	
	private boolean isPlaying = false;
	private MusicService musicService;
	
//	OrthographicCamera guiCam;
//	Vector3 touchPoint;
	
	public JumpGameScreen(){
		super();
		state = GAME_PREPARING;
		
		guiCam = new OrthographicCamera(320, 480);		
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		spriteBatch = new SpriteBatch();
		
		musicWorldListener = new MusicWorldListener() {
			
			@Override
			public void catchNote(MUSICNOTE_KIND kind) {
				//GameSound.noteSounds.get(kind).play(1);
				musicService.setMuteOff();
			}
		};
		musicWorld = new MusicWorld(musicWorldListener);
		musicWorldRender = new MusicWorldRender(musicWorld, spriteBatch);
		
	}
	
	@Override
	public void render() {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		musicWorldRender.render();
		
		guiCam.update();
		spriteBatch.setProjectionMatrix(guiCam.combined);	
		spriteBatch.enableBlending();
		spriteBatch.begin();
		switch(state) {
		case GAME_PREPARING:
			renderPreparing();
			break;
		case GAME_RUNNING:
			renderRunning();
			break;
		case GAME_PAUSE:
			break;
		}
		spriteBatch.end();
	}
	
	private void renderPreparing() {
		if(time < 1f) {
			spriteBatch.draw(Art.time3, 160 - 16, 240 - 28, 32, 56);
		} else if(time < 2f) {
			spriteBatch.draw(Art.time2, 160 - 16, 240 - 28, 32, 56);
		} else if(time < 3f) {
			spriteBatch.draw(Art.time1, 160 - 16, 240 - 28, 32, 56);
		}
	}
	
	private void renderRunning() {
		
	}

	@Override
	public void update(float deltaTime) {
		switch (state) {
		case GAME_PREPARING:
			updatePreparing(deltaTime);
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSE:
			break;
		}
	}
	
	private void updatePreparing(float deltaTime) {
		time += deltaTime;
		System.out.println(time);
		if(time > 3f) {
			state = GAME_RUNNING;
		}
	}
	
	private void updateRunning(float deltaTime) {
		if(!isPlaying) {
			superMusic.getControl().play();
			musicService = new MusicService(superMusic.getControl(), Stats.currentSong);
			isPlaying = true;
		}
		if(Gdx.app.getType() == Application.ApplicationType.Android) { 
			musicWorld.update(deltaTime, Gdx.input.getAccelerometerX());
		}
		else {
			float accel = 0;
			if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
				accel = 5f;
			if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
				accel = -5f;
			musicWorld.update(deltaTime, accel);
		}
	}

	@Override
	public void pause() {
		superMusic.getControl().pause();
	}
	
	@Override
	public void dispose() {
	}
}
