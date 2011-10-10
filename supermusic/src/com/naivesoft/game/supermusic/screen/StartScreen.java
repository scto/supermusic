package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class StartScreen extends Screen {
	private OrthographicCamera camera;
	private Rectangle playBounds;
	private Rectangle highScore;
	private Vector3 touchPoint;
	
	public StartScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
		playBounds = new Rectangle(160 - 180/2, 240, 180, 165);
		highScore = new Rectangle(0, 0, 96, 106);
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
			if (OverlapTester.pointInRectangle(highScore, touchPoint.x, touchPoint.y)) {
				setScreen(new HighScoreScreen());
				return;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACK)) {
			Gdx.app.exit();
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
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if (OverlapTester.pointInRectangle(playBounds, touchPoint.x, touchPoint.y)) {
				//GameSound.playSound(GameSound.hit);
				spriteBatch.draw(Art.startButton_pressed, 70, 240, 180, 165);
				return;
			}
		}
		else
			spriteBatch.draw(Art.startButton, 70, 240, 180, 165);
		spriteBatch.draw(Art.highScoreButton, 0, 0, 96, 106);
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
