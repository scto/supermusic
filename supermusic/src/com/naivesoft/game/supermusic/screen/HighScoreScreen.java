package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.store.ScoreStore;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class HighScoreScreen extends Screen{
	private OrthographicCamera camera;
	private Rectangle playBounds;
	private Vector3 touchPoint;
	
	public HighScoreScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
		playBounds = new Rectangle(160 - 64, 240 - 64, 128, 128);
		touchPoint = new Vector3();
	}
	
	@Override
	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			setScreen(new ChoseMusicScreen());
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
		spriteBatch.draw(Art.highScoreBackground, 0, 0, 320, 480);
		spriteBatch.end();
		
		spriteBatch.enableBlending();
		spriteBatch.begin();
		
		drawDigital(ScoreStore.getHighScore(superMusic.getControl().getJetList().get(0).getFileID()), 320 - 50, 480 - 115 - 40, 2f);
		drawDigital(ScoreStore.getHighScore(superMusic.getControl().getJetList().get(2).getFileID()), 320 - 50, 480 - 115 - 40 - 50, 2f);
		drawDigital(ScoreStore.getHighScore(superMusic.getControl().getJetList().get(1).getFileID()), 320 - 50, 480 - 115 - 40 - 100, 2f);
		drawDigital(ScoreStore.getHighScore(superMusic.getControl().getJetList().get(3).getFileID()), 320 - 50, 480 - 115 - 40 - 150, 2f);
//		Art.font.drawMultiLine(spriteBatch, 
//				ScoreStore.getHighScore(superMusic.getControl().getJetList().get(0).getFileID())
//				+ "\n" + ScoreStore.getHighScore(superMusic.getControl().getJetList().get(1).getFileID())
//				+ "\n" + ScoreStore.getHighScore(superMusic.getControl().getJetList().get(2).getFileID())
//				+ "\n" + ScoreStore.getHighScore(superMusic.getControl().getJetList().get(3).getFileID()), 320 - 16, 480 - 40);
		
		spriteBatch.end();
	}
	
	@Override
	public void dispose() {
		setScreen(new StartScreen());
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

	
}
