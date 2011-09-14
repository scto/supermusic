package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.naivesoft.game.supermusic.SuperMusic;

public abstract class Screen {
	protected SuperMusic superMusic;
	public SpriteBatch spriteBatch;
	
	public void removed(){
		spriteBatch.dispose();
	}
	
	public final void init(SuperMusic superMusic){
		this.superMusic = superMusic;
//		Matrix4 projection = new Matrix4();
//		projection.setToOrtho(0, SuperMusic.GAME_WIDTH, SuperMusic.GAME_HEIGHT, 0, -1, 1);//make the x y to the top left
//		spriteBatch = new SpriteBatch();
//		spriteBatch.setProjectionMatrix(projection);
	}
	
	protected void setScreen(Screen screen){
		superMusic.setScreen(screen);
	}
	
	public void draw (TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if(width <0) width = -width;
		spriteBatch.draw(region, x, y, width, -region.getRegionHeight());
	}
	
	public abstract void update(float deltaTime);
	public abstract void render();
	
//	public void tick(Input input){
//		
//	}
}
