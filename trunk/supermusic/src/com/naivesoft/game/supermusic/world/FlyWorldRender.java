package com.naivesoft.game.supermusic.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.entity.AnimationBackground;
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
	//考虑到手机屏幕的长宽比不一样，所以FRUSTUM_HEIGHT要根据屏幕比例动态的计算
	static final float FRUSTUM_WIDTH = 10f;
	static final float FRUSTUM_HEIGHT = (float)(1.0 * Gdx.graphics.getHeight()/Gdx.graphics.getWidth()) * FRUSTUM_WIDTH;
	private FlyWorld flyWorld;
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	public final static float DEFAULT_CAM_SPEED = 5f;
	public static float camSpeed = 0;
	public static float speedRate = 1;
//	private static float backgroundSpeed;
	float scaleBackgroundHeight;
	float scaleBackgroundWidth;
	private float backgroundPositionRate = 0.9f; 
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
		
		
		//绘制背景图片的大小，宽度与FRUSTUM_WIDTH相同，高度根据图片高宽比计算
		float rate = (float) (1.0 * Art.currentBackground.getRegionHeight() / Art.currentBackground.getRegionWidth());
		scaleBackgroundWidth = FRUSTUM_WIDTH;
		scaleBackgroundHeight = scaleBackgroundWidth * rate;
//		scaleBackgroundWidth = Art.currentBackground.getRegionWidth()/scaleWidth;
		
		//backgroundPositionRate = (FlyWorld.WORLD_HEIGHT - scaleBackgroundHe  ight)/(FlyWorld.WORLD_HEIGHT - FRUSTUM_HEIGHT);
		System.out.println(backgroundPositionRate);
		
	}
	
	public Vector3 getCurrentCamPosition() {
		return cam.position;
	}
	
	public void setFastCamSpeed() {
		camSpeed = DEFAULT_CAM_SPEED * 1.5f;
	}
	
	public void resetCamSpeed() {
		camSpeed = DEFAULT_CAM_SPEED;
	}
	
	public static void initCamSpeed() {
		camSpeed = camSpeed == 0 ? DEFAULT_CAM_SPEED : camSpeed;
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
		renderRandomBackground();
		renderObjects();
	}
	
	private void renderBackground() {
		spriteBatch.disableBlending();
		spriteBatch.begin();
		
	//	spriteBatch.draw(Art.backgroundRegion, 0, 0, scaleBackgroundWidth,scaleBackgroundHeight);
		//System.out.println(scaleBackgroundWidth + " " + scaleBackgroundHeight);
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
	
	private void renderRandomBackground() {
		spriteBatch.enableBlending();
		spriteBatch.begin();
		int len = flyWorld.randomBackgrounds.size();
//		System.out.println(len);
		RandomBackground randomBackground;
		ArrayList<TextureRegion> textureRegions;
		for(int i = 0; i < len; i++){
			randomBackground = flyWorld.randomBackgrounds.get(i);
			textureRegions = Art.current_random_backgrounds.get(randomBackground.type);
			int index = randomBackground.getCount() % textureRegions.size();
			AnimationBackground animation = randomBackground.getAnimation();
			if(animation.canMove()) {
				float x = (randomBackground.position.x + (cam.position.y - FRUSTUM_HEIGHT / 2) * animation.getXrate()) % (FlyWorld.WORLD_WIDTH+8) - 2.5f;
				float y = randomBackground.position.y + (cam.position.y - FRUSTUM_HEIGHT / 2) * animation.getYrate();
				if(x > FlyWorld.WORLD_WIDTH + 5) {
					randomBackground.position.x = -2f;
					y = randomBackground.position.y;
				}
				if(x < -2.001) {
					randomBackground.position.x = FlyWorld.WORLD_WIDTH + 5;
					y = randomBackground.position.y;
				}
				spriteBatch.draw(textureRegions.get(index), x, y, 4f, 4f);
			}
			else {
				float y = randomBackground.position.y + (cam.position.y - FRUSTUM_HEIGHT / 2) * animation.getYrate();
				spriteBatch.draw(textureRegions.get(index), randomBackground.position.x - 1f, y, 4f, 4f);
			}
		}
		spriteBatch.end();
	}
	
	private void renderObjects() {
		spriteBatch.enableBlending();
		spriteBatch.begin();
		renderSuperman();
		renderMusicNote();
		renderProps();
		spriteBatch.end();
	}
	
	
	private void renderSuperman(){
		TextureRegion superman = Art.timerAndroid;
		float side = flyWorld.superman.velocity.x <0 ? -1 : 1;
		if(side < 0){
			spriteBatch.draw(superman, flyWorld.superman.position.x + 0.5f, flyWorld.superman.position.y - 0.5f, side * 1, 1);
		}else{
			spriteBatch.draw(superman, flyWorld.superman.position.x - 0.5f, flyWorld.superman.position.y - 0.5f, side * 1, 1);
		}
		//spriteBatch.draw(superman, flyWorld.superman.position.x, flyWorld.superman.position.y,32,32);
	}
	
	private void renderMusicNote(){
		int len = flyWorld.musicNotes.size();
		MusicNote musicNote;
		TextureRegion textureRegion;
		for(int i = 0; i < len; i++){
			musicNote = flyWorld.musicNotes.get(i);
			if(musicNote.position.y < cam.position.y - FRUSTUM_HEIGHT / 2) {
				continue;
			} else if (musicNote.position.y > cam.position.y + FRUSTUM_HEIGHT / 2) {
				break;
			} else {
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
