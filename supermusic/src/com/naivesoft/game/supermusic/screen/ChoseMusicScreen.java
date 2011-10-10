package com.naivesoft.game.supermusic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	
	private boolean start = false;
	private float startY = 0;
	private int yOffs = 0;
	
	private Rectangle playButton;
	
	public ChoseMusicScreen() {
		super();
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 / 2, 480 / 2, 0);
		spriteBatch = new SpriteBatch();
		touchPoint = new Vector3();
		playButton = new Rectangle(160 - 193/2, 240 - 73/2, 193, 73);
		
		if(Stats.currentSongNumber == 0) {
			Stats.gameStyle = GameStyle.STYLE1;
			Stats.currentSongNumber = 1;
		}
		loadCurrentState();
	}
	
	@Override
	public void update(float deltaTime) {
		if(yOffs != 0) {
			yOffs++;
			if(yOffs == 480) {
				yOffs = 0;
			}
			return;
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACK)) {
			setScreen(new StartScreen());
		}
		
		if(Gdx.input.isTouched()){
			if(!start) {
				startY = Gdx.input.getY();
				start = true;
			}
		} else {
			if(start) {
				if(Math.abs(Gdx.input.getY() - startY) > 30) {
					storeCurrentState();
					if((Gdx.input.getY() - startY) > 0) {
						switchAddState();
						yOffs++;
					} else {
						switchDeleteState();
						yOffs++;
					}
					loadCurrentState();
				}
				start = false;
				startY = 0;
			}
		}
		
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if(OverlapTester.pointInRectangle(playButton, touchPoint.x, touchPoint.y)) {
				Stats.currentSong = superMusic.getControl().getJetList().get(Stats.currentSongNumber - 1);
				superMusic.getControl().loadJetFile(superMusic.getControl().getJetList().get(Stats.currentSongNumber - 1).getFileID());
				superMusic.getControl().queueJetSegment(0, -1, 0, 0, 0, (byte) 0);
				Stats.currentWorldHeight = superMusic.getControl().getJetList().get(0).getTotalTime() * 5;
				setScreen(new FlyGameScreen());
			}
		}
	}

	private void loadCurrentState() {
		Art.current_choseLevel_background = Art.choseLevel_background.get(Stats.gameStyle);
		Art.current_choseLevel_title = Art.choseLevel_title.get(Stats.gameStyle);
		Art.current_choseLevel_title_pressed = Art.choseLevel_title_pressed.get(Stats.gameStyle);
	}
	
	private void storeCurrentState() {
		Art.lastest_choseLevel_background = Art.current_choseLevel_background;
		Art.lastest_choseLevel_title = Art.current_choseLevel_title;
		Art.lastest_choseLevel_title_pressed = Art.current_choseLevel_title_pressed;
	}
	
	private void switchAddState() {
		switch(Stats.gameStyle) {
		case STYLE1:
			Stats.gameStyle = GameStyle.STYLE2;
			Stats.currentSongNumber = 2;
			break;
		case STYLE2:
			Stats.gameStyle = GameStyle.STYLE3;
			Stats.currentSongNumber = 3;
			break;
		case STYLE3:
			
			Stats.gameStyle = GameStyle.STYLE4;
			Stats.currentSongNumber = 4;
			break;
		case STYLE4:
			Stats.gameStyle = GameStyle.STYLE1;
			Stats.currentSongNumber = 1;
			break;
		}
	}
	private void switchDeleteState() {
		switch(Stats.gameStyle) {
		case STYLE1:
			Stats.gameStyle = GameStyle.STYLE4;
			Stats.currentSongNumber = 4;
			break;
		case STYLE2:
			Stats.gameStyle = GameStyle.STYLE1;
			Stats.currentSongNumber = 1;
			break;
		case STYLE3:
			Stats.gameStyle = GameStyle.STYLE2;
			Stats.currentSongNumber = 2;
			break;
		case STYLE4:
			Stats.gameStyle = GameStyle.STYLE3;
			Stats.currentSongNumber = 3;
			break;
		}
	}
	
	@Override
	public void render() {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		if(yOffs == 0) {
			spriteBatch.disableBlending();
			spriteBatch.begin();
			spriteBatch.draw(Art.current_choseLevel_background, 0, 0, 320, 480);
			spriteBatch.end();
			
			spriteBatch.enableBlending();
			spriteBatch.begin();
			
			spriteBatch.draw(Art.current_choseLevel_title, 160 - 193/2, 240 - 73/2, 193, 73);
			//spriteBatch.draw(Art.current_choseLevel_title_pressed, 160 - 193/2, 240 - 73/2, 193, 73);
		} else {
			spriteBatch.disableBlending();
			spriteBatch.begin();
			spriteBatch.draw(Art.current_choseLevel_background, 0, 480 - yOffs, 320, 480);
			spriteBatch.draw(Art.lastest_choseLevel_background, 0, 0 - yOffs, 320, 480);
			spriteBatch.end();
			
			spriteBatch.enableBlending();
			spriteBatch.begin();
			
			spriteBatch.draw(Art.current_choseLevel_title, 160 - 193/2, 480 - yOffs + 240 - 73/2, 193, 73);
			spriteBatch.draw(Art.lastest_choseLevel_title, 160 - 193/2, 0 - yOffs + 240 - 73/2, 193, 73);
			//spriteBatch.draw(Art.current_choseLevel_title_pressed, 160 - 193/2, 240 - 73/2, 193, 73);
		}
		
		
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
		spriteBatch.dispose();
	}

}
