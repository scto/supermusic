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
import com.naivesoft.game.supermusic.world.FlyWorld;
import com.naivesoft.game.supermusic.world.FlyWorld.FlyWorldListener;
import com.naivesoft.game.supermusic.world.FlyWorldRender;

public class FlyGameScreen extends Screen{
	static final int GAME_PREPARING = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSE = 2;
	
	private int state;
	private float time = 0f;
	
	private OrthographicCamera guiCam;
	private Vector3 touchPoint;	
	
	private FlyWorld flyWorld;
	private FlyWorldListener flyWorldListener;
	private FlyWorldRender flyWorldRender;
	
	private boolean isPlaying = false;
	//private MusicService musicService;
	
	private String scoreString;
	
	public FlyGameScreen(){
		super();
		state = GAME_PREPARING;
		
		guiCam = new OrthographicCamera(320, 480);		
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		spriteBatch = new SpriteBatch();
		
		flyWorldListener = new FlyWorldListener() {
			
			@Override
			public void catchNote(MUSICNOTE_KIND kind) {
				GameSound.noteSounds.get(kind).play(1);
				//musicService.setMuteOff();
				Stats.addBlood(1);
			}
		};
		flyWorld = new FlyWorld(flyWorldListener);
		flyWorldRender = new FlyWorldRender(flyWorld, spriteBatch);
		
		scoreString = "SCORE: 0";
		Stats.blood = 5;
		Stats.magnetism = 5f;
	}
	
	@Override
	public void render() {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		flyWorldRender.render();
		
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
		Art.font.draw(spriteBatch, scoreString, 16, 480 - 20);
		renderProcessBar();
	}
	
	private void renderRunning() {
		Art.font.draw(spriteBatch, scoreString, 16, 480 - 20);
		renderProcessBar();
	}
	
	private void renderProcessBar() {
		spriteBatch.draw(Art.processBar, 320 - 24 - 5, 240 - 176/2, 24, 176);
		for(int i = 0; i < Stats.blood; i++) {
			spriteBatch.draw(Art.processElement, 320 - 24 - 5, 240 - 176/2 + 8 + i*16, 24, 16);
		}
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
		if(time > 3f) {
			state = GAME_RUNNING;
			time = 0f;
		}
	}
	
	private void updateRunning(float deltaTime) {
		if(!isPlaying) {
	//		superMusic.getControl().play();
	//		musicService = new MusicService(superMusic.getControl(), Stats.currentSong);
	//		musicService.start();
			isPlaying = true;
		}
		if(Gdx.app.getType() == Application.ApplicationType.Android) { 
			flyWorld.update(deltaTime, Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
		}
		time += deltaTime;
		if(time > 3f) {
			Stats.removeBlood(1);
			time = 0f;
		}
		else {
			float accelX = 0;
			float accelY = 0;
			if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
				accelX = 5f;
			if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
				accelX = -5f;
			if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
				accelY = 5f;
			if(Gdx.input.isKeyPressed(Keys.DPAD_UP))
				accelY = -5f;
			flyWorld.update(deltaTime, accelX, accelY);
		}
	}

}
