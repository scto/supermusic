package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNODE_LEVEL;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Prop.PROP_KIND;
import com.naivesoft.game.supermusic.service.MusicService;
import com.naivesoft.game.supermusic.store.ScoreStore;
import com.naivesoft.game.supermusic.style.GameStyle;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.GameSound;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.OverlapTester;
import com.naivesoft.game.supermusic.world.FlyWorld;
import com.naivesoft.game.supermusic.world.FlyWorld.FlyWorldListener;
import com.naivesoft.game.supermusic.world.FlyWorldRender;

public class FlyGameScreen extends Screen{
	static final int GAME_PREPARING = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSE = 2;
	static final int GAME_END = 3;
	static final int GAME_OVER = 4;
	static final int GAME_FINISH = GAME_END;
	public static final float yExcursion = -4.0f;
	
	private int state;
	private float time = 0f;
	private float endingTime = 0f;
	private int threeSecond = 0;
	private int totalTime = 0;
	
	private float pressTime = 0f;
	
	private OrthographicCamera guiCam;
	private Vector3 touchPoint;	
	
	private boolean menuShown = false;
	
	private Rectangle menuBackButton;
	private Rectangle menuPlayButton;
	private Rectangle menuRetryButton;
	
	private FlyWorld flyWorld;
	private FlyWorldListener flyWorldListener;
	private FlyWorldRender flyWorldRender;
	
	private boolean isPlaying = false;
	//private MusicService musicService;
	
	private String scoreString;
	private String magnetismString;
	private MusicService musicService;
	
	public FlyGameScreen(){
		super();
		state = GAME_PREPARING;
		
		guiCam = new OrthographicCamera(320, 480);		
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		spriteBatch = new SpriteBatch();
		
		menuBackButton = new Rectangle(30, 110, 96, 106);
		menuPlayButton = new Rectangle(30 + 96 + 10, 60, 96, 106);
		menuRetryButton = new Rectangle(30 + 96 + 10 + 96 + 10, 60, 96, 106);
		
		flyWorldListener = new FlyWorldListener() {
			
			@Override
			public void catchNote(MUSICNODE_LEVEL level, MUSICNOTE_KIND kind) {
				//GameSound.noteSounds.get(kind).play(1);
				switch(level) {
				case LEVEL1:
					Stats.addBlood(1);
					updateMuteState();
					Stats.addScore(10);
					break;
				case LEVEL2:
					Stats.addBlood(2);
					updateMuteState();
					Stats.addScore(20);
					break;
				case LEVEL3:
					Stats.addBlood(3);
					updateMuteState();
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
			FlyWorldRender.initCamSpeed();
			renderRunning();
			break;
		case GAME_PAUSE:
			FlyWorldRender.camSpeed = 0;
			renderPausing();
			break;
		case GAME_END:
			FlyWorldRender.camSpeed = 0;
			renderEnding();
			break;
		case GAME_OVER:
			FlyWorldRender.camSpeed = 0;
			renderOvering();
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
		if(Stats.noBlood()) {
			displayEnding();
		} else {
			endingTime = 0f;
		}
	}
	
	//game pause
	private void renderPausing() {
		renderStats();
		renderProcessBar();
		renderMenu();
	}
	
	//game finish
	private void renderEnding() {
		renderStats();
		renderProcessBar();
		renderMenu();
	}
	
	private void renderMenu() {
		menuShown = true;
		spriteBatch.draw(Art.menuBackground, 0, 0, 320, 480);
		spriteBatch.draw(Art.menuBack, 30, 110, 96, 106);
		spriteBatch.draw(Art.menuPlay, 30 + 96 + 10, 60, 96, 106);
		spriteBatch.draw(Art.menuRetry, 30 + 96 + 10 + 96 + 10, 60, 96, 106);
	}
	
	//game over
	private void renderOvering() {
		renderStats();
		renderProcessBar();
		renderMenu();
	}
	
	// will fail if blood is null, it belong to state running
	private void displayEnding() {
		if(endingTime < 1f) {
			spriteBatch.draw(Art.time3, 160 - 16, 240 - 28, 32, 56);
		} else if(endingTime < 2f) {
			spriteBatch.draw(Art.time2, 160 - 16, 240 - 28, 32, 56);
		} else if(endingTime < 3f) {
			spriteBatch.draw(Art.time1, 160 - 16, 240 - 28, 32, 56);
		}
	}
	
	private void renderStats() {
		drawDigital(Stats.score, 160 + 40, 480 - 30, 2f);
		//Art.font.draw(spriteBatch, scoreString, 16, 480 - 20);
		//Art.font.draw(spriteBatch, magnetismString, 16, 480 - 40);
	}
	
	private void renderProcessBar() {
		spriteBatch.draw(Art.processBar, 100, 400, 256, 37);
		for(int i = 0; i < Stats.blood; i++) {
			spriteBatch.draw(Art.processElement, 125+i*19, 405, 24, 18);
		}
	}

	@Override
	public void update(float deltaTime) {
		
		if(Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.MENU)) {
			if(pressTime != 0f && pressTime < 0.5f) {
				
			} else {
				switch(state) {
				case GAME_RUNNING:
					pauseGame();
					break;
				case GAME_PAUSE:
					resumeGame();
					break;
				case GAME_FINISH:
				case GAME_OVER:
					backToMainScreen();
					break;
				}
			}
			pressTime += deltaTime;
		}
		if(pressTime != 0) {
			pressTime += deltaTime;
			if(pressTime >= 0.5f) {
				pressTime = 0f;
			}
		}
		
		
		
		if(menuShown) {
			if(Gdx.input.justTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				if (OverlapTester.pointInRectangle(menuBackButton, touchPoint.x, touchPoint.y)) {
					backToMainScreen();
					menuShown = false;
				}
				if (OverlapTester.pointInRectangle(menuPlayButton, touchPoint.x, touchPoint.y)) {
					if(state == GAME_OVER || state ==GAME_FINISH) {
						restartGame();
						menuShown = false;
						return;
					}
					resumeGame();
					menuShown = false;
				}
				if (OverlapTester.pointInRectangle(menuRetryButton, touchPoint.x, touchPoint.y)) {
					restartGame();
					menuShown = false;
				}
			}
		}
		
		switch (state) {
		case GAME_PREPARING:
			updatePreparing(deltaTime);
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSE:
			break;
		case GAME_END:
			break;
		case GAME_OVER:
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
		if(Gdx.input.isTouched()) {
			flyWorldRender.setFastCamSpeed();
		} else {
			flyWorldRender.resetCamSpeed();
		}
		if(!isPlaying) {
			superMusic.getControl().play();
			musicService = new MusicService(superMusic.getControl(), Stats.currentSong);
			isPlaying = true;
		}
		if(Gdx.app.getType() == Application.ApplicationType.Android) { 
			flyWorld.update(deltaTime, Gdx.input.getAccelerometerX() * 2, (Gdx.input.getAccelerometerY() + yExcursion )*1.5f);
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
			totalTime++;
			time = 0f;
			changeMagnetismString();
		}
		if(threeSecond == 2) {// make it harder
			threeSecond = 0;
			Stats.removeBlood(1);
			updateMuteState();
		}
		if(totalTime >= Stats.currentSong.getTotalTime()) {
			state = GAME_END;
			storeScore();
			totalTime = 0;
		}
		if(Stats.noBlood()) {
			endingTime += deltaTime;
			if(endingTime > 3) {
				state = GAME_OVER;
				storeScore();
				endingTime = 0;
			}
		} else {
			endingTime = 0f;
		}
	}
	
	/**
	 * return true if it is a new high score 
	 */
	private boolean storeScore() {
		if(Stats.score > ScoreStore.getHighScore(Stats.currentSong.getFileID())) {
			ScoreStore.setHighScore(Stats.currentSong.getFileID(), Stats.score);
			return true;
		} else {
			return false;
		}
	}
	
	//更新静音状态，更改血的时候调用，传入更改，减少传入负数
	private void updateMuteState() {
		//血为零时全静音
		if(Stats.blood <= 4)
			musicService.setAllTracksMute();
		//血为5以下静音部分音轨
		else if (Stats.blood <= 6) {
			musicService.setMuteOff();
			musicService.setTrackMute();
		}
		//血在5以上
		else 
			musicService.setMuteOff();
	}
	
	private void backToMainScreen() {
		if(musicService != null) {
			musicService.release();
		}
		spriteBatch.dispose();
		setScreen(new ChoseMusicScreen());
	}
	
	private void restartGame() {
		spriteBatch.dispose();
		setScreen(new FlyGameScreen());
	}
	
	private void pauseGame() {
		menuShown = true;
		state = GAME_PAUSE;
		musicService.pause();
	}
	
	private void resumeGame() {
		menuShown = false;
		state = GAME_RUNNING;
		musicService.play();
	}
	
	private void endGame() {
		musicService.release();
		spriteBatch.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void pause() {
		pauseGame();
	}
	
	@Override
	public void resume() {
		pauseGame();
	}
	
	@Override
	public void dispose() {
		switch(state) {
		case GAME_RUNNING:
			pauseGame();
			break;
		case GAME_PAUSE:
			resumeGame();
			break;
		case GAME_FINISH:
		case GAME_OVER:
			backToMainScreen();
			break;
		}
	}
}
