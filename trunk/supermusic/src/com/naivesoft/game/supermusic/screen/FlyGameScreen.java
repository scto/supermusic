package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNODE_LEVEL;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Prop.PROP_KIND;
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
	private int threeSecond = 0;
	
	private OrthographicCamera guiCam;
	private FlyWorld flyWorld;
	private FlyWorldListener flyWorldListener;
	private FlyWorldRender flyWorldRender;
	
	private boolean isPlaying = false;
	//private MusicService musicService;
	
	private String scoreString;
	private String magnetismString;
	
	public FlyGameScreen(){
		super();
		state = GAME_PREPARING;
		
		guiCam = new OrthographicCamera(320, 480);		
		guiCam.position.set(320 / 2, 480 / 2, 0);
		new Vector3();
		spriteBatch = new SpriteBatch();
		
		flyWorldListener = new FlyWorldListener() {
			
			@Override
			public void catchNote(MUSICNODE_LEVEL level, MUSICNOTE_KIND kind) {
				GameSound.noteSounds.get(kind).play(1);
				switch(level) {
				case LEVEL1:
					Stats.addBlood(1);
					Stats.addScore(10);
					break;
				case LEVEL2:
					Stats.addBlood(2);
					Stats.addScore(20);
					break;
				case LEVEL3:
					Stats.addBlood(3);
					Stats.addScore(30);
					break;
				}
				changeScoreString();
			}

			@Override
			public void catchProp(PROP_KIND kind) {
				switch(kind) {
				case magnet_positive:
					Stats.addMagnetLevel();
					changeMagnetismString();
					break;
				case magnet_negative:
					Stats.cutDownMagnetLevel();
					changeMagnetismString();
					break;
				case protective:
					Stats.addProjectiveEffect();
					break;
				case maxNotes:
					flyWorld.generateMaxNotesInScreen(flyWorldRender.getCurrentCamPosition());
					break;
				case eatAll:
					flyWorld.catchAllNotesInScreen(flyWorldRender.getCurrentCamPosition());
					break;
				case doubleScores:
					Stats.doubleScore();
					break;
				case fillBlood:
					Stats.fillBlood();
					break;
				case halfBlood:
					Stats.halfBlood();
					break;
				}
				
			}
		};
		flyWorld = new FlyWorld(flyWorldListener);
		flyWorldRender = new FlyWorldRender(flyWorld, spriteBatch);
		
		Stats.score = 0;
		Stats.blood = 5;
		Stats.magnetism = 1f;
		changeScoreString();
		changeMagnetismString();
	}
	
	private void changeScoreString() {
		scoreString = "SCORE: " + Stats.score;
	}
	
	private void changeMagnetismString() {
		magnetismString = "MAGNET: " + Stats.magnetism;
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
			FlyWorldRender.camSpeed = 0;
			renderPreparing();
			break;
		case GAME_RUNNING:
			FlyWorldRender.camSpeed = 5;
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
		renderStats();
		renderProcessBar();
	}
	
	private void renderRunning() {
		renderStats();
		renderProcessBar();
	}
	
	private void renderStats() {
		Art.font.draw(spriteBatch, scoreString, 16, 480 - 20);
		Art.font.draw(spriteBatch, magnetismString, 16, 480 - 40);
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
		time += deltaTime;
		if(time > 1f) {
			Stats.removeEffect();
			threeSecond += 1;
			time = 0f;
			changeMagnetismString();
		}
		if(threeSecond == 3) {
			threeSecond = 0;
			Stats.removeBlood(1);
		}
	}

}
