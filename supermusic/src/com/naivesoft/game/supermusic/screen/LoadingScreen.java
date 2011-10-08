package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.naivesoft.game.supermusic.system.Art;

public class LoadingScreen extends Screen{
	private OrthographicCamera camera;
	private boolean isPost = false;
	private float time = 0;
	
	public LoadingScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
	}
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			Art.load();
			setScreen(new StartScreen());
			
		}
	};
	
	@Override
	public void update(float deltaTime) {
		if(!isPost) {
			Gdx.app.postRunnable(runnable);
			isPost = true;
		}
		time += deltaTime;
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
		if(time < 1f) {
			spriteBatch.draw(Art.loadingBackground3, 0, 0, 320, 480);
		} else if(time < 2f) {
			spriteBatch.draw(Art.loadingBackground2, 0, 0, 320, 480);
		} else if(time < 3f) {
			spriteBatch.draw(Art.loadingBackground1, 0, 0, 320, 480);
		} else {
			time = 0f;
		}
		spriteBatch.end();
	}

}
