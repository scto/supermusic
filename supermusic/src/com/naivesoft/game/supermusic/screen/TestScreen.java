package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.GameSound;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.world.MusicWorld;
import com.naivesoft.game.supermusic.world.MusicWorld.MusicWorldListener;
import com.naivesoft.game.supermusic.world.MusicWorldRender;

public class TestScreen extends Screen{
	private int time = 0;
	
	private MusicWorld musicWorld;
	private MusicWorldListener musicWorldListener;
	private MusicWorldRender musicWorldRender;
	
//	OrthographicCamera guiCam;
//	Vector3 touchPoint;
	
	public TestScreen(){
		//Matrix4 projection = new Matrix4();
		//projection.setToOrtho(0, SuperMusic.GAME_WIDTH, SuperMusic.GAME_HEIGHT, 0, -1, 1);//make the x y to the top left
		spriteBatch = new SpriteBatch();
//		
//		guiCam = new OrthographicCamera(320, 480);		
//		guiCam.position.set(320 / 2, 480 / 2, 0);
//		touchPoint = new Vector3();
		//spriteBatch.setProjectionMatrix(projection);
		
		musicWorldListener = new MusicWorldListener() {
			
			@Override
			public void catchNote(MUSICNOTE_KIND kind) {
				GameSound.noteSounds.get(kind).play(1);
			}
		};
		musicWorld = new MusicWorld(musicWorldListener);
		musicWorldRender = new MusicWorldRender(musicWorld, spriteBatch);
	}
	
	@Override
	public void render() {
		time += Gdx.graphics.getDeltaTime();
//		spriteBatch.begin();
//		spriteBatch.draw(Art.backgroundRegion, 0, 480);
//		spriteBatch.end();
		musicWorldRender.render();
		
	}

	@Override
	public void update(float deltaTime) {
//		if (Gdx.input.justTouched()) {
//			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));			
//		}
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

}
