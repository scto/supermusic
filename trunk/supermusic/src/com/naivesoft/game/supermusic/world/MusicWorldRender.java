package com.naivesoft.game.supermusic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.system.Art;

public class MusicWorldRender {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	private MusicWorld musicWorld;
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	private static final float camSpeed = 5;
//	private static float backgroundSpeed;
	float scaleBackgroundHeight;
	float scaleBackgroundWidth;
	private float backgroundPositionRate = 0.7f; 
	private static float bottom = 0f;
	
	public MusicWorldRender(MusicWorld musicWorld, SpriteBatch spriteBatch) {
		this.musicWorld = musicWorld;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);		
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.spriteBatch = spriteBatch;
		
		float scaleHeight = Gdx.graphics.getHeight()/FRUSTUM_HEIGHT;
		float scaleWidth = Gdx.graphics.getWidth()/FRUSTUM_WIDTH;
		scaleBackgroundHeight = Art.backgroundRegion.getRegionHeight()/scaleHeight;
//		scaleBackgroundWidth = Art.backgroundRegion.getRegionHeight()/scaleWidth;
		scaleBackgroundWidth = Art.backgroundRegion.getRegionWidth()/scaleHeight;
		
		//backgroundPositionRate = (MusicWorld.WORLD_HEIGHT - scaleBackgroundHeight)/(MusicWorld.WORLD_HEIGHT - FRUSTUM_HEIGHT);
		System.out.println(backgroundPositionRate);
		
	}
	
	public void render(){
		//cam.position.add(0, camSpeed*Gdx.graphics.getDeltaTime(), 0);
		if(musicWorld.superman.position.y > cam.position.y) cam.position.y = musicWorld.superman.position.y;
		if(musicWorld.superman.position.y < cam.position.y - FRUSTUM_HEIGHT / 2) cam.position.y = musicWorld.superman.position.y;
		cam.update();
		spriteBatch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}
	
	public void renderBackground(){
		spriteBatch.disableBlending();
		spriteBatch.begin();
	//	spriteBatch.draw(Art.backgroundRegion, 0, 0, scaleBackgroundWidth,scaleBackgroundHeight);
		spriteBatch.draw(Art.backgroundRegion, (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT / 2) * backgroundPositionRate, scaleBackgroundWidth,scaleBackgroundHeight);
		if((cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight < cam.position.y + FRUSTUM_HEIGHT / 2){
			spriteBatch.draw(Art.backgroundRegion, (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight, scaleBackgroundWidth,scaleBackgroundHeight);
		}
		if((cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight + bottom < cam.position.y - FRUSTUM_HEIGHT / 2){
			bottom += scaleBackgroundHeight;
		}
		//spriteBatch.draw(Art.backgroundRegion, 0, 0);
		spriteBatch.end();
	}
	
	private void renderObjects() {
		spriteBatch.enableBlending();
		spriteBatch.begin();
		renderSuperman();
		renderMusicNote();
		spriteBatch.end();
	}
	
	
	public void renderSuperman(){
		TextureRegion superman = Art.timerAndroid;
		float side = musicWorld.superman.velocity.x <0 ? -1 : 1;
		if(side < 0){
			spriteBatch.draw(superman, musicWorld.superman.position.x + 0.5f, musicWorld.superman.position.y - 0.5f, side * 1, 1);
		}else{
			spriteBatch.draw(superman, musicWorld.superman.position.x - 0.5f, musicWorld.superman.position.y - 0.5f, side * 1, 1);
		}
		//spriteBatch.draw(superman, musicWorld.superman.position.x, musicWorld.superman.position.y,32,32);
	}
	
	public void renderMusicNote(){
		int len = musicWorld.musicNotes.size();
		MusicNote musicNote;
		TextureRegion textureRegion;
		for(int i = 0; i < len; i++){
			musicNote = musicWorld.musicNotes.get(i);
			textureRegion = Art.time1;//Art.musicNotes.get(musicNote.musicKind);
			spriteBatch.draw(textureRegion, musicNote.position.x - 0.5f, musicNote.position.y - 0.5f, 1f, 1f);
		}
	}
}
