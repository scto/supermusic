package com.naivesoft.game.supermusic.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNODE_LEVEL;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Superman;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.Enums;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class MusicWorld {
	public interface MusicWorldListener {
		public void catchNote(MUSICNOTE_KIND kind);
	}
	
	public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;
	public static final Vector2 gravity = new Vector2(0, 0);
	
	public final Superman superman;
	public final List<MusicNote> musicNotes;
	
	public final MusicWorldListener musicWorldListener;
	public final Random rand;
	
	public MusicWorld(MusicWorldListener musicWorldListener){
		this.superman = new Superman(5, 1);
		this.musicNotes = new ArrayList<MusicNote>();
		
		this.musicWorldListener = musicWorldListener;
		rand = new Random();
		generateLevel();
	}
	
	private void generateLevel(){
		int start = 0;
		while(start < WORLD_HEIGHT){
			start += superman.velocity.y * Stats.currentSong.getPauseTime() / 1000;
			MusicNote musicNote = new MusicNote(rand.nextFloat()*WORLD_WIDTH, start,
					Enums.random(MUSICNOTE_KIND.class),
					Enums.random(MUSICNODE_LEVEL.class));
			musicNotes.add(musicNote);
		}
		
	}
	
	public void update(float deltaTime, float accelX){
		updateSuperman(deltaTime, accelX);
		
		checkCollisions();
	}
	
	private void updateSuperman(float deltaTime, float accelX){
		superman.velocity.x = -accelX / 10 * Superman.SUPERMAN_MOVE_VELOCITY;
		//System.out.println(superman.velocity.x);
		superman.update(deltaTime);
	}
	
	private void checkCollisions(){
		checkMusicNoteCollision();
	}
	
	private void checkMusicNoteCollision(){
		int len = musicNotes.size();
		for(int i = 0; i < len; i++){
			MusicNote musicNote = musicNotes.get(i);
			if(OverlapTester.overlapRectangles(superman.bounds, musicNote.bounds)){
				musicWorldListener.catchNote(musicNote.musicKind);
				musicNotes.remove(musicNote);
				len = musicNotes.size();
//				if(new Random().nextInt(6) > 1){
//					superman.velocity.y = superman.velocity.y + 2 > 10 ? 10 : superman.velocity.y + 2;
//				}else{
//					superman.velocity.y = superman.velocity.y - 1 < 0 ? 0 : superman.velocity.y - 1;
//				}
				
			}
		}
	}
}
