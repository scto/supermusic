package com.naivesoft.game.supermusic.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.naivesoft.game.supermusic.entity.GameObject;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Superman;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.Enums;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class FlyWorld {
	public interface FlyWorldListener {
		public void catchNote(MUSICNOTE_KIND kind);
	}
	
	public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;
	public static final Vector2 gravity = new Vector2(0, 0);
	
	public final Superman superman;
	public final List<MusicNote> musicNotes;
	
	public final FlyWorldListener flyWorldListener;
	public final Random rand;
	
	public FlyWorld(FlyWorldListener flyWorldListener){
		this.superman = new Superman(5, 1);
		this.musicNotes = new ArrayList<MusicNote>();
		
		this.flyWorldListener = flyWorldListener;
		rand = new Random();
		generateLevel();
	}
	
	private void generateLevel(){
		int start = 0;
		while(start < WORLD_HEIGHT){
			start += 4;//superman.velocity.y * Stats.currentSong.getPauseTime() / 1000;
			MusicNote musicNote = new MusicNote(rand.nextFloat()*WORLD_WIDTH, start,Enums.random(MUSICNOTE_KIND.class));
			musicNotes.add(musicNote);
		}
		
	}
	
	public void update(float deltaTime, float accelX, float accelY){
		updateSuperman(deltaTime, accelX, accelY);
		
		checkCollisions(deltaTime);
	}
	
	private void updateSuperman(float deltaTime, float accelX, float accelY){
		superman.accel.x = -accelX;
		superman.accel.y = -accelY;
		
//		superman.velocity.x = -accelX / 10 * Superman.SUPERMAN_MOVE_VELOCITY;
//		superman.velocity.y = -accelY / 10 * Superman.SUPERMAN_MOVE_VELOCITY;
		//System.out.println(superman.velocity.x);
		superman.update(deltaTime);
	}
	
	private void updateMusicNote() {//put the function to the checkMusicNoteCollision
		int len = musicNotes.size();
	}
	
	private void checkCollisions(float deltaTime){
		checkMusicNoteCollision(deltaTime);
	}
	
	//have the ability to update music note, to avoid double for
	private void checkMusicNoteCollision(float deltaTime){
		int len = musicNotes.size();
		for(int i = 0; i < len; i++){
			MusicNote musicNote = musicNotes.get(i);
			updateMusicNotePosition(deltaTime, musicNote);
			if(OverlapTester.overlapRectangles(superman.bounds, musicNote.bounds)){
				flyWorldListener.catchNote(musicNote.musicKind);
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
	
	private void updateMusicNotePosition(float deltaTime, MusicNote musicNote) {
		if(musicNote.position.y > superman.position.y - FlyWorldRender.FRUSTUM_HEIGHT
				&& musicNote.position.y < superman.position.y + FlyWorldRender.FRUSTUM_HEIGHT) {
			float distanceFactor = 1 / (float)calTwoObjectDistance(superman, musicNote);
			musicNote.position.x += (superman.position.x - musicNote.position.x) * deltaTime * Stats.magnetism * distanceFactor;
			musicNote.position.y += (superman.position.y - musicNote.position.y) * deltaTime * Stats.magnetism * distanceFactor;
			musicNote.resetBoundsWithPosition();
		}
	}
	
	private double calTwoObjectDistance(GameObject object1, GameObject object2) {
		return Math.sqrt((object1.position.x - object2.position.x) * (object1.position.x - object2.position.x)
				+ (object1.position.y - object2.position.y) * (object1.position.y - object2.position.y));
	}
}
