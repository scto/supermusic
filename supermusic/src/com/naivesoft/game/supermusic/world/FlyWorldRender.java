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
import com.naivesoft.game.supermusic.screen.FlyGameScreen;
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
	
	static int STATE = 0;
	static int STATE_STAY = 0;
	static int timer = 0;
	
	static int BACKGROUND_TIMER = 0;
	static int changeRate = 600;
	static int THIS_BACKGROUND = 0;
	static int NEXT_BACKGROUND = 0;
	static boolean CHANGE_THIS = false;
	static boolean CHANGE_NEXT = true;
	
	
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
		float rate = (float) (1.0 * Art.currentBackground.get(0).getRegionHeight() / Art.currentBackground.get(0).getRegionWidth());
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
		

		if(( bottom + (cam.position.y - FRUSTUM_HEIGHT / 2) * backgroundPositionRate) % (scaleBackgroundHeight*2) < 1 && CHANGE_NEXT) {
			NEXT_BACKGROUND = (NEXT_BACKGROUND + 1) % Art.currentBackground.size();
			CHANGE_NEXT = false;
		}
		
		if(( bottom + (cam.position.y - FRUSTUM_HEIGHT / 2) * backgroundPositionRate) % (scaleBackgroundHeight*2) > scaleBackgroundHeight && !CHANGE_NEXT) {
			CHANGE_NEXT = true;
		}
		
		if (CHANGE_NEXT)
			THIS_BACKGROUND = NEXT_BACKGROUND;
		
		spriteBatch.draw(Art.currentBackground.get(THIS_BACKGROUND), (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT / 2) * backgroundPositionRate, scaleBackgroundWidth,scaleBackgroundHeight);
		if((cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight < cam.position.y + FRUSTUM_HEIGHT / 2){
			spriteBatch.draw(Art.currentBackground.get(NEXT_BACKGROUND), (cam.position.x - FRUSTUM_WIDTH / 2) * backgroundPositionRate, bottom + (cam.position.y - FRUSTUM_HEIGHT/ 2) * backgroundPositionRate + scaleBackgroundHeight, scaleBackgroundWidth,scaleBackgroundHeight);
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
			TextureRegion texture = textureRegions.get(index);
			float drawWidth = 4;
			float drawHeight = drawWidth * texture.getRegionHeight() / texture.getRegionWidth();
			
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
				spriteBatch.draw(texture, x, y, drawWidth, drawHeight);
			}
			else {
				float y = randomBackground.position.y + (cam.position.y - FRUSTUM_HEIGHT / 2) * animation.getYrate();
				spriteBatch.draw(texture, randomBackground.position.x - 1f, y, drawWidth, drawHeight);
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
		float yExcursion = FlyGameScreen.yExcursion;

		float sideX = flyWorld.superman.accel.x;
		float sideY = flyWorld.superman.accel.y;

		TextureRegion superman = Art.timerAndroid[6][1];
		if(sideX>-1.0&&sideX<1.0&&sideY>1.0){ //上
			if(STATE != 0 ){
				timer = 0;
				STATE = 0;
			}
			if(timer <= 5)
				superman = Art.timerAndroid[0][0];
			else
				superman = Art.timerAndroid[0][1];
			timer++;
		}else if(sideX>-1.0&&sideX<1.0&&sideY<-1.0){  //下
			if(STATE != 1 ){
				timer = 0;
				STATE= 1;
			}
			if(timer <= 5)
				superman = Art.timerAndroid[1][0];
			else
				superman = Art.timerAndroid[1][1];
			timer++;
		}
//		else if(sideY<-1.0&&sideY>1.0&&sideX<-1.0){  //右
//			if(STATE != 2 ){
//				timer = 0;
//				STATE = 2;
//			}
//			if(timer <= 5)
//				superman = Art.timerAndroid[2][0];
//			else
//				superman = Art.timerAndroid[2][1];
//			timer++;
//		}
//		else if(sideY<-1.0&&sideY>1.0&&sideX>1.0){  //左
//			if(STATE != 3 ){
//				timer = 0;
//				STATE = 3;
//			}
//			if(timer <= 5)
//				superman = Art.timerAndroid[4][0];
//			else
//				superman = Art.timerAndroid[4][1];
//			timer++;
//		}
		else if(sideY>1.0&&sideX>1.0){     //右上
			superman = Art.timerAndroid[3][0];
		}else if(sideY<-1.0&&sideX>1.0){     //右下
			superman = Art.timerAndroid[3][1];
		}else if(sideY>1.0&&sideX<-1.0){     //左上
			superman = Art.timerAndroid[5][0];
		}else if(sideY<-1.0&&sideX<-1.0){     //左下
			superman = Art.timerAndroid[5][1];
		}else{
			if(STATE != 5) {
				timer = 0;
				STATE=5;
			}
			switch ((timer / 5 )% 4){
				case 0:
					superman = Art.timerAndroid[6][0];
					break;
				case 1:
					superman = Art.timerAndroid[6][1];
					break;
				case 2:
					superman = Art.timerAndroid[7][0];
					break;
				case 3:
					superman = Art.timerAndroid[7][1];
					break;
			}
			timer++;
		}
		float superManWidth = 3;
		float superManHeight = superManWidth * Art.timerAndroid[0][0].getRegionHeight() / Art.timerAndroid[0][0].getRegionWidth();
		spriteBatch.draw(superman, flyWorld.superman.position.x - 1.5f,flyWorld.superman.position.y - 1.5f* Art.timerAndroid[0][0].getRegionHeight() / Art.timerAndroid[0][0].getRegionWidth(), superManWidth, superManHeight);
		
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
