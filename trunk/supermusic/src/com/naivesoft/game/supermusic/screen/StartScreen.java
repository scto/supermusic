package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.GameSound;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class StartScreen extends Screen {
	private OrthographicCamera camera;
	private Rectangle playBounds;
	private Vector3 touchPoint;
	
	public StartScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
		playBounds = new Rectangle(160 - 64, 240 - 64, 128, 128);
		touchPoint = new Vector3();
	}
	
	@Override
	public void update(float deltaTime) {
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if (OverlapTester.pointInRectangle(playBounds, touchPoint.x, touchPoint.y)) {
				//GameSound.playSound(GameSound.hit);
				setScreen(new ChoseMusicScreen());
				return;
			}
		}
	}

	@Override
	public void render() {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.disableBlending();
		spriteBatch.begin();
		spriteBatch.draw(Art.startBackground, 0, 0, 320, 480);
		spriteBatch.end();
		
		spriteBatch.enableBlending();
		spriteBatch.begin();
		spriteBatch.draw(Art.startButton, 160 - 64, 240 - 64, 128, 128);
		spriteBatch.end();
		
	}

}
