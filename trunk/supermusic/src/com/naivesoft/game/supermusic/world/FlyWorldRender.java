package com.naivesoft.game.supermusic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.Prop;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.RandomBackground;
import com.naivesoft.game.supermusic.entity.RandomBackground.RAND_BACKGROUND;
import com.naivesoft.game.supermusic.style.GameStyle;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.Enums;

public class FlyWorldRender {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	private FlyWorld flyWorld;
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	public static float camSpeed = 0;
//	private static float backgroundSpeed;
	float scaleBackgroundHeight;
	float scaleBackgroundWidth;
	private float backgroundPositionRate = 0.7f; 
	private static float bottom = 0f;
	
	public FlyWorldRender(FlyWorld flyWorld, SpriteBatch spriteBatch) {
		this.flyWorld = flyWorld;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);		
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.spriteBatch = spriteBatch;
		
		//set current style
		Art.currentBackground = Art.backgrounds.get(Stats.gameStyle);
		Art.current_nodes_level1 = Art.nodes_level1.get(Stats.gameStyle);
		Art.current_nodes_level2 = Art.nodes_level2.get(Stats.gameStyle);
		Art.current_nodes_level3 = Art.nodes_level3.get(Stats.gameStyle);
		Art.current_random_backgrounds = Art.random_backgrounds.get(Stats.gameStyle);
		
		float scaleHeight = Gdx.graphics.getHeight()/FRUSTUM_HEIGHT;
		float scaleWidth = Gdx.graphics.getWidth()/FRUSTUM_WIDTH;
		scaleBackgroundHeight = Art.currentBackground.getRegionHeight()/scaleHeight;
//		scaleBackgroundWidth = Art.backgroundRegion.getRegionHeight()/scaleWidth;
		scaleBackgroundWidth = Art.currentBackground.getRegionWidth()/scaleHeight;
		
		//backgroundPositionRate = (FlyWorld.WORLD_HEIGHT - scaleBackgroundHeight)/(FlyWorld.WORLD_HEIGHT - FRUSTUM_HEIGHT);
		System.out.println(backgroundPositionRate);
		
	}
	
	public void render(){
		cam.position.add(0, camSpeed*Gdx.graphics.getDeltaTime(), 0);
		//if(flyWorld.superman.position.y > cam.position.y) cam.position.y = flyWorld.superman.position.y;
		if(flyWorld.superman.position.y - 0.5f < cam.position.y - FRUSTUM_HEIGHT / 2) {
			flyWorld.superman.position.y = cam.position.y - FRUSTUM_HEIGHT / 2 + 0.5f;
			flyWorld.superman.velocity.y = camSpeed;
		}
		if(flyWorld.superman.position.y + 0.5f > cam.position.y + FRUSTUM_HEIGHT / 2) {
			flyWorld.superman.position.y = cam.position.y + FRUSTUM_HEIGHT / 2 - 0.5f;
			flyWorld.superman.velocity.y = camSpeed;
		}

		cam.update();
		spriteBatch.setProjectionMatrix(cam.combined);
		renderBackground();
//		renderRandomBackground();
		renderObjects();
	}
	
	public void renderBackground() {
		spriteBatch.disableBlending();
		spriteBatch.begin();
	//	spriteBatch.draw(Art.backgroundRegion, 0, 0, scaleBackgroundWidth,scaleBackgroundHeight);
		spriteBatch.draw(Art.currentBackground, (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT / 2) * backgroundPositionRate, scaleBackgroundWidth,scaleBackgroundHeight);
		if((cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight < cam.position.y + FRUSTUM_HEIGHT / 2){
			spriteBatch.draw(Art.currentBackground, (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight, scaleBackgroundWidth,scaleBackgroundHeight);
		}
		if((cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight + bottom < cam.position.y - FRUSTUM_HEIGHT / 2){
			bottom += scaleBackgroundHeight;
		}
		//spriteBatch.draw(Art.backgroundRegion, 0, 0);
		spriteBatch.end();
	}
	
//	private void renderRandomBackground() {
//		spriteBatch.enableBlending();
//		spriteBatch.begin();
//		int len = flyWorld.randomBackgrounds.size();
//		RandomBackground randomBackground;
//		TextureRegion textureRegion;
//		for(int i = 0; i < len; i++){
//			randomBackground = flyWorld.randomBackgrounds.get(i);
//			textureRegion = Art.current_random_backgrounds.get(randomBackground.type);
//			spriteBatch.draw(textureRegion, randomBackground.position.x - 1f, (randomBackground.position.y - 1f)*3, 2f, 2f);
//		}
//		spriteBatch.end();
//	}
	
	private void renderObjects() {
		spriteBatch.enableBlending();
		spriteBatch.begin();
		renderSuperman();
		renderMusicNote();
		renderProps();
		spriteBatch.end();
	}
	
	
	public void renderSuperman(){
		TextureRegion superman = Art.timerAndroid;
		float side = flyWorld.superman.velocity.x <0 ? -1 : 1;
		if(side < 0){
			spriteBatch.draw(superman, flyWorld.superman.position.x + 0.5f, flyWorld.superman.position.y - 0.5f, side * 1, 1);
		}else{
			spriteBatch.draw(superman, flyWorld.superman.position.x - 0.5f, flyWorld.superman.position.y - 0.5f, side * 1, 1);
		}
		//spriteBatch.draw(superman, flyWorld.superman.position.x, flyWorld.superman.position.y,32,32);
	}
	
	public void renderMusicNote(){
		int len = flyWorld.musicNotes.size();
		MusicNote musicNote;
		TextureRegion textureRegion;
		for(int i = 0; i < len; i++){
			musicNote = flyWorld.musicNotes.get(i);
			textureRegion = Art.current_nodes_level1.get(musicNote.musicKind);
			switch(musicNote.musicLevel) {
			case LEVEL1:
				textureRegion = Art.current_nodes_level1.get(musicNote.musicKind);
				break;
			case LEVEL2:
				textureRegion = Art.current_nodes_level2.get(musicNote.musicKind);
				break;
			case LEVEL3:
				textureRegion = Art.current_nodes_level3.get(musicNote.musicKind);
				break;
			}
			spriteBatch.draw(textureRegion, musicNote.position.x - 0.5f, musicNote.position.y - 0.5f, 1f, 1f);
		}
	}
	
	private void renderProps() {
		int len = flyWorld.props.size();
		Prop prop;
		TextureRegion textureRegion;
		for(int i = 0; i < len; i++){
			prop = flyWorld.props.get(i);
			textureRegion = Art.props.get(prop.kind);
			spriteBatch.draw(textureRegion, prop.position.x - 0.5f, prop.position.y - 0.5f, 1f, 1f);
		}
	}
}
