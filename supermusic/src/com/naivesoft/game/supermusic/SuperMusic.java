package com.naivesoft.game.supermusic;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.naivesoft.game.supermusic.control.IMusicControl;
import com.naivesoft.game.supermusic.screen.Screen;
import com.naivesoft.game.supermusic.screen.StartScreen;
import com.naivesoft.game.supermusic.screen.JumpGameScreen;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.GameSound;

public class SuperMusic implements ApplicationListener{
	public static final int GAME_WIDTH = 320;
	public static final int GAME_HEIGHT = 480;

	private Screen screen;
	
	private  IMusicControl control;
	
	public SuperMusic() {
		super();
	}
	
	public SuperMusic(IMusicControl control) {
		super();
		this.control = control;
	}
	
	public IMusicControl getControl(){
		return this.control;
	}

	@Override
	public void create() {
		Art.load();
		GameSound.load();
		setScreen(new StartScreen());
	}

	public void setScreen(Screen newScreen) {
		if (screen != null)
			screen.removed();
		screen = newScreen;
		if (screen != null)
			screen.init(this);
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		screen.update(Gdx.graphics.getDeltaTime());
		screen.render();
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
