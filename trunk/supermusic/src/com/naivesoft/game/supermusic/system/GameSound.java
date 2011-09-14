package com.naivesoft.game.supermusic.system;

import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;

public class GameSound {
	
	public static Sound hit;//"Sound" only for small audio files, load into RAM, in Android cannot larger than 24M
	public static Sound noteDo;
	public static Sound noteRe;
	public static Sound noteMi;
	public static Sound noteFa;
	public static Sound noteSo;
	public static Sound noteLa;
	public static Sound noteXi;
	public static EnumMap<MUSICNOTE_KIND, Sound> noteSounds = new EnumMap<MUSICNOTE_KIND, Sound>(MUSICNOTE_KIND.class);
	
	
	public static void load(){
		hit = load("sound/hit.wav");
		noteSounds.put(MUSICNOTE_KIND.DO, load("sound/p1.mp3"));//can replace with p1 ganggu1 drum for different music
		noteSounds.put(MUSICNOTE_KIND.RE, load("sound/p2.mp3"));
		noteSounds.put(MUSICNOTE_KIND.MI, load("sound/p3.mp3"));
		noteSounds.put(MUSICNOTE_KIND.FA, load("sound/p4.mp3"));
		noteSounds.put(MUSICNOTE_KIND.SO, load("sound/p5.mp3"));
		noteSounds.put(MUSICNOTE_KIND.LA, load("sound/p6.mp3"));
		noteSounds.put(MUSICNOTE_KIND.XI, load("sound/p7.mp3"));
	}
	
	public static void playSound(Sound sound){
		if(Settings.soundEnable){
			sound.play(1);
		}
	}
	
	private static Sound load(String name){
		return Gdx.audio.newSound(Gdx.files.internal(name));
	}
}
