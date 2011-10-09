package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.style.GameStyle;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class ChoseMusicScreen extends Screen{
	private OrthographicCamera camera;
	private Vector3 touchPoint;
	
	private Rectangle music1;
	private Rectangle music2;
	private Rectangle music3;
	
	private Rectangle highScore;
	
	public ChoseMusicScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
		touchPoint = new Vector3();
		music1 = new Rectangle(160 + 80 - 45, 240, 90, 30);
		music2 = new Rectangle(160 + 80 - 45, 240 + 40, 90, 30);
		music3 = new Rectangle(160 + 80 - 45, 240 + 80, 90, 30);
		
		highScore = new Rectangle(0, 0, 30, 30);
	}
	
	@Override
	public void update(float deltaTime) {
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if (OverlapTester.pointInRectangle(music1, touchPoint.x, touchPoint.y)) {
				//GameSound.playSound(GameSound.hit);				
				Stats.currentSong = superMusic.getControl().getJetList().get(0);
				superMusic.getControl().loadJetFile(superMusic.getControl().getJetList().get(0).getFileID());
				superMusic.getControl().queueJetSegment(0, -1, 0, 0, 0, (byte) 0);
				Stats.gameStyle = GameStyle.STYLE1;
				Stats.currentWorldHeight = superMusic.getControl().getJetList().get(0).getTotalTime() * 5;
				setScreen(new FlyGameScreen());
				return;
			}
			if (OverlapTester.pointInRectangle(music2, touchPoint.x, touchPoint.y)) {
				//GameSound.playSound(GameSound.hit);
//				Stats.currentSong = superMusic.getControl().getJetList().get(1);
//				superMusic.getControl().loadJetFile(superMusic.getControl().getJetList().get(1).getFileID());
//				superMusic.getControl().queueJetSegment(0, -1, 0, 0, 0, (byte) 0);
				Stats.gameStyle = GameStyle.STYLE2;
				setScreen(new FlyGameScreen());
				return;
			}
			if (OverlapTester.pointInRectangle(music3, touchPoint.x, touchPoint.y)) {
				//GameSound.playSound(GameSound.hit);
				Stats.currentSong = superMusic.getControl().getJetList().get(2);
				superMusic.getControl().loadJetFile(superMusic.getControl().getJetList().get(2).getFileID());
				superMusic.getControl().queueJetSegment(0, -1, 0, 0, 0, (byte) 0);
				setScreen(new FlyGameScreen());
				return;
			}
			
			if (OverlapTester.pointInRectangle(highScore, touchPoint.x, touchPoint.y)) {
				setScreen(new HighScoreScreen());
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
		spriteBatch.draw(Art.menuButton, 160 + 80 - 45, 240, 90, 30);
		spriteBatch.draw(Art.menuMusic1, 160 + 80 - 45, 240 + 15 - 8, 90, 16);
		spriteBatch.draw(Art.menuButton, 160 + 80 - 45, 240 + 40, 90, 30);
		spriteBatch.draw(Art.menuMusic2, 160 + 80 - 45, 240 + 40 + 15 - 8, 90, 16);
		spriteBatch.draw(Art.menuButton, 160 + 80 - 45, 240 + 80, 90, 30);
		spriteBatch.draw(Art.menuMusic3, 160 + 80 - 45, 240 + 80+ 15 - 8, 90, 16);
		
		spriteBatch.draw(Art.startButton, 0, 0, 30, 30);
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
