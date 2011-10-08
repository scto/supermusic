package com.naivesoft.game.supermusic.system;

import java.util.ArrayList;
import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naivesoft.game.supermusic.entity.AnimationBackground;
import com.naivesoft.game.supermusic.entity.MusicNote;
import com.naivesoft.game.supermusic.entity.RandomBackground;
import com.naivesoft.game.supermusic.entity.MusicNote.MUSICNOTE_KIND;
import com.naivesoft.game.supermusic.entity.Prop.PROP_KIND;
import com.naivesoft.game.supermusic.entity.RandomBackground.RAND_BACKGROUND;
import com.naivesoft.game.supermusic.style.GameStyle;

public class Art {
	
	public static Texture background;
	public static TextureRegion startBackground;
	public static TextureRegion startButton;
	public static TextureRegion backgroundRegion;
	public static TextureRegion[][] timerAndroid = new TextureRegion[8][2];
	public static Texture musicNotesTexture;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> musicNotes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
	public static BitmapFont font;
	
	public static Texture digitalTexture;
	
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
	
	//props
	public static TextureRegion prop_magnet_positive;
	public static TextureRegion prop_magnet_negative;
	public static TextureRegion prop_eatAll;
	public static TextureRegion prop_fillBlood;
	public static TextureRegion prop_halfBlood;
	public static TextureRegion prop_doubleScores;
	public static TextureRegion prop_maxNotes;
	public static TextureRegion prop_protective;
	public static EnumMap<PROP_KIND, TextureRegion> props = new EnumMap<PROP_KIND, TextureRegion>(PROP_KIND.class);
	
	//styles
	public static EnumMap<GameStyle, ArrayList<TextureRegion>> backgrounds = new EnumMap<GameStyle, ArrayList<TextureRegion>>(GameStyle.class);
	public static EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>> nodes_level1 = new EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>>(GameStyle.class);
	public static EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>> nodes_level2 = new EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>>(GameStyle.class);
	public static EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>> nodes_level3 = new EnumMap<GameStyle, EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>>(GameStyle.class);
	public static EnumMap<GameStyle, EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>> random_backgrounds = new EnumMap<GameStyle, EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>>(GameStyle.class);
	
	//AnimationBackground对象，表示移动的背景元素
	public static EnumMap<GameStyle, EnumMap<RandomBackground.RAND_BACKGROUND, AnimationBackground>> changeRates = new EnumMap<GameStyle, EnumMap<RandomBackground.RAND_BACKGROUND, AnimationBackground>>(GameStyle.class);
	
	//current styles
	public static ArrayList<TextureRegion> currentBackground;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> current_nodes_level1;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> current_nodes_level2;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> current_nodes_level3;
	public static EnumMap<RAND_BACKGROUND, ArrayList<TextureRegion>> current_random_backgrounds;
	
	public static EnumMap<RAND_BACKGROUND, AnimationBackground> current_change_rates;
	
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
		
		loadDigitals();
		
		loadStyle1();
//		loadStyle2();
		loadProps();
		
		//backgroundRegion = load("images/background.png", 320, 480);
		Texture timerAndroidPic = Art.loadTexture("images/timerandroid.png");
		timerAndroid[0][0] = new TextureRegion(timerAndroidPic,0, 0, 183, 61);
		timerAndroid[0][1] = new TextureRegion(timerAndroidPic,183, 0, 183, 61);
		timerAndroid[1][0] = new TextureRegion(timerAndroidPic,0, 61, 183, 61);
		timerAndroid[1][1] = new TextureRegion(timerAndroidPic,183, 61, 183, 61);
		timerAndroid[2][0] = new TextureRegion(timerAndroidPic,0, 122, 183, 61);
		timerAndroid[2][1] = new TextureRegion(timerAndroidPic,183, 122, 183, 61);
		timerAndroid[3][0] = new TextureRegion(timerAndroidPic,0, 183, 183, 61);
		timerAndroid[3][1] = new TextureRegion(timerAndroidPic,183, 183, 183, 61);
		timerAndroid[4][0] = new TextureRegion(timerAndroidPic,0, 244, 183, 61);
		timerAndroid[4][1] = new TextureRegion(timerAndroidPic,183, 244, 183, 61);
		timerAndroid[5][0] = new TextureRegion(timerAndroidPic,0, 305, 183, 61);
		timerAndroid[5][1] = new TextureRegion(timerAndroidPic,183, 305, 183, 61);
		timerAndroid[6][0] = new TextureRegion(timerAndroidPic,0, 366, 183, 61);
		timerAndroid[6][1] = new TextureRegion(timerAndroidPic,183, 366, 183, 61);
		timerAndroid[7][0] = new TextureRegion(timerAndroidPic,0, 427, 183, 61);
		timerAndroid[7][1] = new TextureRegion(timerAndroidPic,183, 366, 183, 61);
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
	
	private static void loadDigitals() {
		digitalTexture = loadTexture("images/digital.png");
	}
	
	private static void loadProps() {
		props.put(PROP_KIND.magnet_positive, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.magnet_negative, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.protective, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.maxNotes, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.doubleScores, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.eatAll, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.fillBlood, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
		props.put(PROP_KIND.halfBlood, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 140, 180));
	}
	
	/*private static void loadStyle1() {
		backgrounds.put(GameStyle.STYLE1, new TextureRegion(
				loadTexture("images/star/bg.png"),0,0,320,600));
		
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level1_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level1_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level1_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level1_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level1_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level1_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level1_6.png"), 0, 0, 350, 369));
		nodes_level1.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level2_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level2_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level2_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level2_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level2_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level2_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level2_6.png"), 0, 0, 350, 369));
		nodes_level2.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level3_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level3_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level3_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level3_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level3_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level3_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level3_6.png"), 0, 0, 350, 369));
		nodes_level3.put(GameStyle.STYLE1, nodes);
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(loadTexture("images/style1/random_background1.png"), 0, 0, 200, 200));
		tr.add(new TextureRegion(loadTexture("images/style1/random_background2.png"), 0, 0, 200, 200));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(200, 0.9f, 0.9f, false));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(loadTexture("images/style1/random_background3.png"), 0, 0, 200, 200));
		tr.add(new TextureRegion(loadTexture("images/style1/random_background4.png"), 0, 0, 200, 200));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(60, 0.5f, 0.5f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(loadTexture("images/style1/random_background5.png"), 0, 0, 200, 200));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE1, changeRate);
		random_backgrounds.put(GameStyle.STYLE1, rbs);
	}*/
	
	private static void loadStyle1() {
		Texture punk = Art.loadTexture("images/punk_style/punk.png");
		ArrayList<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
		textureRegions.add(new TextureRegion(punk,0,0,320,600));
		textureRegions.add(new TextureRegion(punk,320,0,320,600));
		textureRegions.add(new TextureRegion(punk,640,0,320,600));
		backgrounds.put(GameStyle.STYLE1, textureRegions);
		
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level1_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level1_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level1_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level1_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level1_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level1_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level1_6.png"), 0, 0, 350, 369));
		nodes_level1.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level2_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level2_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level2_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level2_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level2_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level2_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level2_6.png"), 0, 0, 350, 369));
		nodes_level2.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				loadTexture("images/style1/node_level3_1.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				loadTexture("images/style1/node_level3_2.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				loadTexture("images/style1/node_level3_3.png"), 0, 0, 282, 369));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				loadTexture("images/style1/node_level3_4.png"), 0, 0, 293, 359));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				loadTexture("images/style1/node_level3_5.png"), 0, 0, 330, 369));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				loadTexture("images/style1/node_level3_6.png"), 0, 0, 350, 369));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				loadTexture("images/style1/node_level3_6.png"), 0, 0, 350, 369));
		nodes_level3.put(GameStyle.STYLE1, nodes);
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 600, 124, 66));
		tr.add(new TextureRegion(punk, 124, 600, 124, 66));
		tr.add(new TextureRegion(punk, 0, 600, 124, 66));
		tr.add(new TextureRegion(punk, 248, 600, 124, 66));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(200, 0.9f, 0.9f, false));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 666, 111, 26));
		tr.add(new TextureRegion(punk, 111, 666, 111, 26));
		tr.add(new TextureRegion(punk, 0, 666, 111, 26));
		tr.add(new TextureRegion(punk, 222, 666, 111, 26));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(60, 0.5f, 0.5f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 692, 200, 160));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE1, changeRate);
		random_backgrounds.put(GameStyle.STYLE1, rbs);
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
