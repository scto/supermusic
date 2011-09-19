package com.naivesoft.game.supermusic.system;

import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;

public class Art {
	
	public static Texture background;
	public static TextureRegion startBackground;
	public static TextureRegion startButton;
	public static TextureRegion backgroundRegion;
	public static TextureRegion timerAndroid;
	public static Texture musicNotesTexture;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> musicNotes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
	public static BitmapFont font;
	
	//menu screen
	private static Texture menuTexture;
	public static TextureRegion menuButton;
	public static TextureRegion menuMusic1;
	public static TextureRegion menuMusic2;
	public static TextureRegion menuMusic3;
	
	//game screen
	private static Texture timeTexture;
	public static TextureRegion time3;
	public static TextureRegion time2;
	public static TextureRegion time1;
	private static Texture processTexture;
	public static TextureRegion processBar;
	public static TextureRegion processElement;
	
	/**
	 * load the resource for the loading page
	 */
	public static void loadPre() {
		background = loadTexture("images/background2.png");
		backgroundRegion = new TextureRegion(background, 400, 0, (int)(856/1.5), 856);
		startBackground = load("images/mainbackground.jpg", 320, 480);
	}
	
	/**
	 * load all the resources
	 */
	public static void load(){
		background = loadTexture("images/background2.png");
		backgroundRegion = new TextureRegion(background, 400, 0, (int)(856/1.5), 856);
		startBackground = load("images/mainbackground.jpg", 320, 480);
		startButton = load("images/start.png", 128, 128);
		
		menuTexture = loadTexture("images/menu.png");
		menuButton = new TextureRegion(menuTexture, 0, 0, 90, 30);
		menuMusic1 = new TextureRegion(menuTexture, 0, 32, 90, 16);
		menuMusic2 = new TextureRegion(menuTexture, 0, 48, 90, 16);
		menuMusic3 = new TextureRegion(menuTexture, 0, 64, 90, 16);
		
		timeTexture = loadTexture("images/time321.png");
		time3 = new TextureRegion(timeTexture, 0, 0, 32, 56);
		time2 = new TextureRegion(timeTexture, 0, 56, 32, 56);
		time1 = new TextureRegion(timeTexture, 0, 112, 32, 56);
		
		processTexture = loadTexture("images/processbar.png");
		processBar = new TextureRegion(processTexture, 0, 0, 24, 176);
		processElement = new TextureRegion(processTexture, 0, 184, 24, 16);
		
		//backgroundRegion = load("images/background.png", 320, 480);
		timerAndroid = load("images/timerandroid.png", 64, 64);
		musicNotesTexture = loadTexture("images/musicnote.png");
		musicNotes.put(MUSICNOTE_KIND.DO, new TextureRegion(musicNotesTexture, 90, 158, 511, 963));
		musicNotes.put(MUSICNOTE_KIND.RE, new TextureRegion(musicNotesTexture, 658, 359, 183, 183));
		musicNotes.put(MUSICNOTE_KIND.MI, new TextureRegion(musicNotesTexture, 647, 561, 292, 408));
		musicNotes.put(MUSICNOTE_KIND.FA, new TextureRegion(musicNotesTexture, 945, 509, 329, 399));
		musicNotes.put(MUSICNOTE_KIND.SO, new TextureRegion(musicNotesTexture, 1266, 777, 184, 290));
		musicNotes.put(MUSICNOTE_KIND.LA, new TextureRegion(musicNotesTexture, 1477, 645, 157, 261));
		musicNotes.put(MUSICNOTE_KIND.XI, new TextureRegion(musicNotesTexture, 1621, 548, 157, 214));
		
		font = new BitmapFont(Gdx.files.internal("images/font.fnt"), Gdx.files.internal("images/font.png"), false);
	}
	
	public static TextureRegion load (String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		//region.flip(false, true);
		return region;
	}
	
	public static Texture loadTexture(String file) {
    	return new Texture(Gdx.files.internal(file));
    }
}
