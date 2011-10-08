package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.SuperMusic;
import com.naivesoft.game.supermusic.system.Art;

public abstract class Screen {
	protected SuperMusic superMusic;
	public SpriteBatch spriteBatch;
	
	public void removed(){
		spriteBatch.dispose();
	}
	
	public final void init(SuperMusic superMusic){
		this.superMusic = superMusic;
	}
	
	protected void setScreen(Screen screen){
		superMusic.setScreen(screen);
	}
	
	public void draw (TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if(width <0) width = -width;
		spriteBatch.draw(region, x, y, width, -region.getRegionHeight());
	}
	
	public void drawDigital(int number, int x, int y) {
		int out;
		while(number / 10 != 0){
			out = number % 10;
			spriteBatch.draw(Art.digitalTexture, x, y, 0.1f, 0.1f, 0, 150, 120, 300);
			number /= 10;
		}
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void render();
	
	//below methods implement by sub class, however not all are necessary, so without abstract
	public void resize(int width, int height) {}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {}
}
