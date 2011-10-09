package com.naivesoft.game.supermusic.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.naivesoft.game.supermusic.entity.GameObject;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNODE_LEVEL;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Prop;
import com.naivesoft.game.supermusic.entity.Prop.PROP_KIND;
import com.naivesoft.game.supermusic.entity.RandomBackground.RAND_BACKGROUND;
import com.naivesoft.game.supermusic.entity.RandomBackground;
import com.naivesoft.game.supermusic.entity.Superman;
import com.naivesoft.game.supermusic.system.Art;
import com.naivesoft.game.supermusic.system.Stats;
import com.naivesoft.game.supermusic.util.Enums;
import com.naivesoft.game.supermusic.util.OverlapTester;

public class FlyWorld {
	public interface FlyWorldListener {
		public void catchNote(MUSICNODE_LEVEL level, MUSICNOTE_KIND kind);
		public void catchProp(PROP_KIND kind);
	}
	
	public static final float WORLD_WIDTH = 10;
//    public static final float WORLD_HEIGHT = 15 * 20;
	public static final Vector2 gravity = new Vector2(0, 0);
	
	public final Superman superman;
	public final List<MusicNote> musicNotes;
	public final List<RandomBackground> randomBackgrounds;
	public final List<Prop> props; 
	
	public final FlyWorldListener flyWorldListener;
	public final Random rand;
	
	public FlyWorld(FlyWorldListener flyWorldListener){
		this.superman = new Superman(5, 1);
		this.musicNotes = new ArrayList<MusicNote>();
		this.randomBackgrounds = new ArrayList<RandomBackground>();
		this.props = new ArrayList<Prop>();
		
		this.flyWorldListener = flyWorldListener;
		rand = new Random();
		generateLevel();
	}
	
	public void catchAllNotesInScreen(Vector3 currentCamPosition) {
		int len = musicNotes.size();
		for(int i = 0; i < len; i++){
			MusicNote musicNote = musicNotes.get(i);
			if(musicNote.position.y > currentCamPosition.y - FlyWorldRender.FRUSTUM_HEIGHT / 2
					&& musicNote.position.y < currentCamPosition.y + FlyWorldRender.FRUSTUM_HEIGHT / 2) {
				flyWorldListener.catchNote(musicNote.musicLevel, musicNote.musicKind);
				musicNotes.remove(musicNote);
				len = musicNotes.size();
			}
		}
	}
	
	public void generateMaxNotesInScreen(Vector3 currentCamPosition) {
		for(float i = currentCamPosition.y - FlyWorldRender.FRUSTUM_HEIGHT / 2;
				i < currentCamPosition.y + FlyWorldRender.FRUSTUM_HEIGHT / 2;
				i++) {
			int factor = rand.nextInt(2);
			if(factor == 0) {
				generateMusicNote(i);
			}
		}
	}
	
	private void generateLevel(){
		int start = 0;
		RandomBackground randomBackground = null;
		Prop prop = null;
		
		Art.current_change_rates = Art.changeRates.get(Stats.gameStyle);
		while(start < Stats.currentWorldHeight * 1.5){//for the speed of 1.5
			start += 4;//superman.velocity.y * Stats.currentSong.getPauseTime() / 1000;
			
			generateMusicNote(start);
			
			int factor = rand.nextInt(10);
			if(factor < 10) {
				randomBackground = new RandomBackground(rand.nextFloat()*WORLD_WIDTH, start + rand.nextInt(5) - 2,
						Enums.random(RAND_BACKGROUND.class));
				float random = rand.nextFloat();
				if(random > 0.5)
					randomBackground.getAnimation().setX_rate(rand.nextFloat());
				else
					randomBackground.getAnimation().setX_rate(-0.5f - rand.nextFloat());
				
				randomBackgrounds.add(randomBackground);
			}
			factor = rand.nextInt(10);
			if(factor < 3) {
				prop = new Prop(rand.nextFloat()*WORLD_WIDTH, start + rand.nextInt(5) - 2,
						Enums.random(PROP_KIND.class));
				props.add(prop);
			}
		}
		
	}
	
	private void generateMusicNote(float position) {
		MusicNote musicNote = null;
		int factor = rand.nextInt(10) + 1;
		switch(factor) {
		case 8:
		case 9:
			musicNote = new MusicNote(rand.nextFloat()*WORLD_WIDTH, position,
					Enums.random(MUSICNOTE_KIND.class),
					MUSICNODE_LEVEL.LEVEL2);
			break;
		case 10:
			musicNote = new MusicNote(rand.nextFloat()*WORLD_WIDTH, position,
					Enums.random(MUSICNOTE_KIND.class),
					MUSICNODE_LEVEL.LEVEL3);
			break;
		default:
			musicNote = new MusicNote(rand.nextFloat()*WORLD_WIDTH, position,
					Enums.random(MUSICNOTE_KIND.class),
					MUSICNODE_LEVEL.LEVEL1);
		}
		musicNotes.add(musicNote);
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
		checkPropsCollision(deltaTime);
	}
	
	//have the ability to update music note, to avoid double 'for'
	private void checkMusicNoteCollision(float deltaTime){
		int len = musicNotes.size();
		for(int i = 0; i < len; i++){
			MusicNote musicNote = musicNotes.get(i);
			if(musicNote.position.y > superman.position.y - FlyWorldRender.FRUSTUM_HEIGHT
					&& musicNote.position.y < superman.position.y + FlyWorldRender.FRUSTUM_HEIGHT) {
				
				updateMusicNotePosition(deltaTime, musicNote);
				if(OverlapTester.overlapRectangles(superman.bounds, musicNote.bounds)){
					flyWorldListener.catchNote(musicNote.musicLevel, musicNote.musicKind);
					musicNotes.remove(musicNote);
					len = musicNotes.size();
				}
				
			}
		}
	}
	
	private void checkPropsCollision(float deltaTime){
		int len = props.size();
		for(int i = 0; i < len; i++){
			Prop prop = props.get(i);
			if(OverlapTester.overlapRectangles(superman.bounds, prop.bounds)){
				flyWorldListener.catchProp(prop.kind);
				props.remove(prop);
				len = props.size();
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
