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
	
	public void drawDigital(int number, int x, int y, float size) {
		int out;
		if(number == 0) {
			spriteBatch.draw(Art.digital0, x, y, 10 * size, 10 * size);
			return;
		}
		while(number != 0){
			out = number % 10;
			switch(out) {
			case 0:
				spriteBatch.draw(Art.digital0, x, y, 10 * size, 10 * size);
				break;
			case 1:
				spriteBatch.draw(Art.digital1, x, y, 10 * size, 10 * size);
				break;
			case 2:
				spriteBatch.draw(Art.digital2, x, y, 10 * size, 10 * size);
				break;
			case 3:
				spriteBatch.draw(Art.digital3, x, y, 10 * size, 10 * size);
				break;
			case 4:
				spriteBatch.draw(Art.digital4, x, y, 10 * size, 10 * size);
				break;
			case 5:
				spriteBatch.draw(Art.digital5, x, y, 10 * size, 10 * size);
				break;
			case 6:
				spriteBatch.draw(Art.digital6, x, y, 10 * size, 10 * size);
				break;
			case 7:
				spriteBatch.draw(Art.digital7, x, y, 10 * size, 10 * size);
				break;
			case 8:
				spriteBatch.draw(Art.digital8, x, y, 10 * size, 10 * size);
				break;
			case 9:
				spriteBatch.draw(Art.digital9, x, y, 10 * size, 10 * size);
				break;
			}
			x -= 10f * size;
			number /= 10;
		}
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void render();
	
	public abstract void resize(int width, int height);
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void dispose();
}
