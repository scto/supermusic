package com.naivesoft.game.supermusic;

import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;

public class Art {
	
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static TextureRegion timerAndroid;
	public static Texture musicNotesTexture;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> musicNotes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
	public static BitmapFont font;
	
	public static void load(){
		background = loadTexture("images/background2.png");
		backgroundRegion = new TextureRegion(background, 400, 0, (int)(856/1.5), 856);
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
		//font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
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
