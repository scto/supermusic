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
	
	public static TextureRegion loadingBackground1;
	public static TextureRegion loadingBackground2;
	public static TextureRegion loadingBackground3;
	
	public static Texture background;
	public static TextureRegion startBackground;
	public static TextureRegion startButton;
	public static TextureRegion startButton_pressed;
	
	public static TextureRegion backgroundRegion;
	public static TextureRegion[][] timerAndroid1 = new TextureRegion[6][2];
	public static TextureRegion[][] timerAndroid2 = new TextureRegion[6][2];
	public static Texture musicNotesTexture;
	public static EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> musicNotes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
	public static BitmapFont font;
	
	public static Texture digitalTexture;
	public static TextureRegion digital0;
	public static TextureRegion digital1;
	public static TextureRegion digital2;
	public static TextureRegion digital3;
	public static TextureRegion digital4;
	public static TextureRegion digital5;
	public static TextureRegion digital6;
	public static TextureRegion digital7;
	public static TextureRegion digital8;
	public static TextureRegion digital9;
	
	//highScore
	private static Texture highScoreTexture;
	public static TextureRegion highScoreBackground;
	public static TextureRegion highScoreButton;
	public static TextureRegion highScoreButton_pressed;
	
	//help
	private static Texture helpTexture;
	public static TextureRegion helpBackground;
	public static TextureRegion helpButton;
	
	//menu screen
	private static Texture menuTexture;
	public static TextureRegion menuBackground;
	public static TextureRegion menuPlay;
	public static TextureRegion menuRetry;
	public static TextureRegion menuBack;
	public static TextureRegion menuPlay_pressed;
	public static TextureRegion menuRetry_pressed;
	public static TextureRegion menuBack_pressed;
	
	public static EnumMap<GameStyle, TextureRegion> choseLevel_background = new EnumMap<GameStyle, TextureRegion>(GameStyle.class);
	public static EnumMap<GameStyle, TextureRegion> choseLevel_title = new EnumMap<GameStyle, TextureRegion>(GameStyle.class);
	public static EnumMap<GameStyle, TextureRegion> choseLevel_title_pressed = new EnumMap<GameStyle, TextureRegion>(GameStyle.class);
	
	public static TextureRegion current_choseLevel_background;
	public static TextureRegion current_choseLevel_title;
	public static TextureRegion current_choseLevel_title_pressed;
	public static TextureRegion lastest_choseLevel_background;
	public static TextureRegion lastest_choseLevel_title;
	public static TextureRegion lastest_choseLevel_title_pressed;
	
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
	
	public static int SUPERMAN_STATE = 0;
	
	/**
	 * load the resource for the loading page
	 */
	public static void loadPre() {
		loadingBackground1 = new TextureRegion(loadTexture("images/loading1.png"), 0, 0, 320, 480);
		loadingBackground2 = new TextureRegion(loadTexture("images/loading2.png"), 0, 0, 320, 480);
		loadingBackground3 = new TextureRegion(loadTexture("images/loading3.png"), 0, 0, 320, 480);
	}
	
	/**
	 * load all the resources
	 */
	public static void load(){
		
		
		
		loadStartScreen();
		
		loadMenu();
		
		loadChoseLevel();
		
		loadHighScore();
		
		loadHelp();
		
		timeTexture = loadTexture("images/time321.png");
		time3 = new TextureRegion(timeTexture, 0, 0, 32, 56);
		time2 = new TextureRegion(timeTexture, 0, 56, 32, 56);
		time1 = new TextureRegion(timeTexture, 0, 112, 32, 56);
		
		processTexture = loadTexture("images/processbar.png");
		processBar = new TextureRegion(processTexture, 0, 0, 256, 37);
		processElement = new TextureRegion(processTexture, 0, 37, 24, 18);
		
		loadDigitals();
		
		loadProps();
		
		loadStarStyle();
		loadChinaStyle();
		loadPunkStyle();
		loadSkyStyle();
		
		//backgroundRegion = load("images/background.png", 320, 480);
		
			Texture timerAndroidPic = Art.loadTexture("images/timerandroid.png");
			timerAndroid1[0][0] = new TextureRegion(timerAndroidPic,0, 0, 183, 61);
			timerAndroid1[0][1] = new TextureRegion(timerAndroidPic,183, 0, 183, 61);
			timerAndroid1[1][0] = new TextureRegion(timerAndroidPic,0, 61, 183, 61);
			timerAndroid1[1][1] = new TextureRegion(timerAndroidPic,183, 61, 183, 61);
			timerAndroid1[3][0] = new TextureRegion(timerAndroidPic,0, 183, 183, 61);
			timerAndroid1[3][1] = new TextureRegion(timerAndroidPic,183, 183, 183, 61);
			timerAndroid1[2][0] = new TextureRegion(timerAndroidPic,0, 305, 183, 61);
			timerAndroid1[2][1] = new TextureRegion(timerAndroidPic,183, 305, 183, 61);
			timerAndroid1[4][0] = new TextureRegion(timerAndroidPic,0, 366, 183, 61);
			timerAndroid1[4][1] = new TextureRegion(timerAndroidPic,183, 366, 183, 61);
			timerAndroid1[5][0] = new TextureRegion(timerAndroidPic,0, 427, 183, 61);
			timerAndroid1[5][1] = new TextureRegion(timerAndroidPic,183, 366, 183, 61);
		
			timerAndroidPic = Art.loadTexture("images/timerandroid_china.png");
			Texture timerAndroidPic1 = Art.loadTexture("images/timerandroid_china1.png");
			timerAndroid2[0][0] = new TextureRegion(timerAndroidPic1,0, 0, 183, 108);
			timerAndroid2[0][1] = new TextureRegion(timerAndroidPic1,183, 0, 183, 108);
			timerAndroid2[1][0] = new TextureRegion(timerAndroidPic,0, 324, 183, 108);
			timerAndroid2[1][1] = new TextureRegion(timerAndroidPic,183, 324, 183, 108);
			timerAndroid2[3][0] = new TextureRegion(timerAndroidPic1,0, 108, 183, 108);
			timerAndroid2[3][1] = new TextureRegion(timerAndroidPic1,183, 108, 183, 108);
			timerAndroid2[2][0] = new TextureRegion(timerAndroidPic,0, 0, 183, 108);
			timerAndroid2[2][1] = new TextureRegion(timerAndroidPic,183, 0, 183, 108);
			timerAndroid2[4][0] = new TextureRegion(timerAndroidPic,0, 108, 183, 108);
			timerAndroid2[4][1] = new TextureRegion(timerAndroidPic,183, 108, 183, 108);
			timerAndroid2[5][0] = new TextureRegion(timerAndroidPic,0, 216, 183, 108);
			timerAndroid2[5][1] = new TextureRegion(timerAndroidPic,183, 216, 183, 108);
		
		
		
	}
	
	private static void loadStartScreen() {
		Texture startBackgroundTexture = loadTexture("images/mainbackground.png");
		startBackground = new TextureRegion(startBackgroundTexture, 0, 0, 320, 480);
		startButton = new TextureRegion(startBackgroundTexture, 320, 0, 180, 165);
		startButton_pressed = new TextureRegion(startBackgroundTexture, 320, 165, 180, 165);
	}
	
	private static void loadMenu() {
		menuTexture = loadTexture("images/menu.png");
		menuBackground = new TextureRegion(menuTexture, 0, 0, 320, 480);
		menuBack = new TextureRegion(menuTexture, 320 + 96, 0, 96, 106);
		menuBack_pressed = new TextureRegion(menuTexture, 320, 0, 96, 106);
		menuPlay = new TextureRegion(menuTexture, 320 + 96, 106, 96, 106);
		menuPlay_pressed = new TextureRegion(menuTexture, 320, 106, 96, 106);
		menuRetry = new TextureRegion(menuTexture, 320 + 96, 106 + 106, 96, 106);
		menuRetry_pressed = new TextureRegion(menuTexture, 320, 106 + 106, 96, 106);
	}
	
	private static void loadChoseLevel(){
		Texture choseLevelTexture = loadTexture("images/choselevel.png");
		
		choseLevel_background.put(GameStyle.STYLE1, new TextureRegion(choseLevelTexture, 320, 0, 320, 480));
		choseLevel_title.put(GameStyle.STYLE1, new TextureRegion(choseLevelTexture, 640, 0, 193, 73));
		choseLevel_title_pressed.put(GameStyle.STYLE1, new TextureRegion(choseLevelTexture, 640, 73, 193, 73));
		
		choseLevel_background.put(GameStyle.STYLE2, new TextureRegion(choseLevelTexture, 0, 480, 320, 480));
		choseLevel_title.put(GameStyle.STYLE2, new TextureRegion(choseLevelTexture, 640, 73 * 2, 193, 73));
		choseLevel_title_pressed.put(GameStyle.STYLE2, new TextureRegion(choseLevelTexture, 640, 73 * 3, 193, 73));
		
		choseLevel_background.put(GameStyle.STYLE3, new TextureRegion(choseLevelTexture, 320, 480, 320, 480));
		choseLevel_title.put(GameStyle.STYLE3, new TextureRegion(choseLevelTexture, 640, 73 * 4, 193, 73));
		choseLevel_title_pressed.put(GameStyle.STYLE3, new TextureRegion(choseLevelTexture, 640, 73 * 5, 193, 73));
		
		choseLevel_background.put(GameStyle.STYLE4, new TextureRegion(choseLevelTexture, 0, 0, 320, 480));
		choseLevel_title.put(GameStyle.STYLE4, new TextureRegion(choseLevelTexture, 640, 73 * 6, 193, 73));
		choseLevel_title_pressed.put(GameStyle.STYLE4, new TextureRegion(choseLevelTexture, 640, 73 * 7, 193, 73));
		
	}
	
	private static void loadHighScore() {
		highScoreTexture = loadTexture("images/highscore.png");
		highScoreBackground = new TextureRegion(highScoreTexture, 0, 0, 320, 480);
		highScoreButton = new TextureRegion(highScoreTexture, 320, 106, 96, 106);
		highScoreButton_pressed = new TextureRegion(highScoreTexture, 320 + 96, 0, 96, 106);
	}
	
	private static void loadHelp() {
		helpTexture = loadTexture("images/help.png");
		helpBackground = new TextureRegion( helpTexture, 0,0,320,480);
		helpButton = new TextureRegion(highScoreTexture, 320, 0, 96, 106);
	}
	
	private static void loadDigitals() {
		digitalTexture = loadTexture("images/digital.png");
		digital0 = new TextureRegion(digitalTexture, 0, 0, 120, 150);
		digital1 = new TextureRegion(digitalTexture, 120, 0, 120, 150);
		digital2 = new TextureRegion(digitalTexture, 240, 0, 120, 150);
		digital3 = new TextureRegion(digitalTexture, 360, 0, 120, 150);
		digital4 = new TextureRegion(digitalTexture, 0, 150, 120, 150);
		digital5 = new TextureRegion(digitalTexture, 120, 150, 120, 150);
		digital6 = new TextureRegion(digitalTexture, 240, 150, 120, 150);
		digital7 = new TextureRegion(digitalTexture, 360, 150, 120, 150);
		digital8 = new TextureRegion(digitalTexture, 0, 300, 120, 150);
		digital9 = new TextureRegion(digitalTexture, 120, 300, 120, 150);
	}
	
	private static void loadProps() {
		props.put(PROP_KIND.magnet_positive, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 0, 100, 100));
		props.put(PROP_KIND.magnet_negative, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				100, 0, 100, 100));
		props.put(PROP_KIND.protective, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				200, 0, 100, 100));
		props.put(PROP_KIND.maxNotes, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				300, 0, 100, 100));
		props.put(PROP_KIND.doubleScores, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				400, 0, 100, 100));
		props.put(PROP_KIND.eatAll, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				0, 100, 100, 100));
		props.put(PROP_KIND.fillBlood, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				100, 100, 100, 100));
		props.put(PROP_KIND.halfBlood, new TextureRegion(
				loadTexture("images/props/prop_magnet_positive.png"),
				200, 100, 100, 100));
	}
	
 static void loadChinaStyle() {                        //中国主题
		
		Texture china = Art.loadTexture("images/china_style/china.png");
		ArrayList<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
		textureRegions.add(new TextureRegion(china,640,0,320,600));
		backgrounds.put(GameStyle.STYLE3, textureRegions);
		
		Texture musicNode = Art.loadTexture("images/style1/musicnode.png");
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 0, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 100, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 200, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 300, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 400, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes_level1.put(GameStyle.STYLE3, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 100, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 200, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 300, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 400, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 0, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes_level2.put(GameStyle.STYLE3, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 200, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 300, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 400, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 0, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 100, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes_level3.put(GameStyle.STYLE3, nodes);
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(china, 0, 602, 86, 164));
		tr.add(new TextureRegion(china, 86, 602, 86, 164));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(30, 0.9f, 0.9f, false));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(china, 172, 602, 70, 109));
		tr.add(new TextureRegion(china, 242, 602, 70, 109));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(5, 0.3f, 0.5f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(china, 312, 602, 70, 77));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE3, changeRate);
		random_backgrounds.put(GameStyle.STYLE3, rbs);
	}
	
	private static void loadSkyStyle() {                //天空主题
		Texture sky = Art.loadTexture("images/sky_style/sky.png");
		ArrayList<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
		textureRegions.add(new TextureRegion(sky,0,0,320,600));
		textureRegions.add(new TextureRegion(sky,320,0,320,600));
		textureRegions.add(new TextureRegion(sky,640,0,320,600));
		textureRegions.add(new TextureRegion(Art.loadTexture("images/china_style/china.png"),0,0,320,600));
		backgrounds.put(GameStyle.STYLE1, textureRegions);
		
		Texture musicNode = Art.loadTexture("images/style1/musicnode.png");
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 0, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 100, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 200, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 300, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 400, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes_level1.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 100, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 200, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 300, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 400, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 0, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes_level2.put(GameStyle.STYLE1, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 200, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 300, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 400, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 0, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 100, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes_level3.put(GameStyle.STYLE1, nodes);
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(sky, 0, 601, 124, 65));
		tr.add(new TextureRegion(sky, 124, 601, 124, 65));
		tr.add(new TextureRegion(sky, 0, 601, 124, 65));
		tr.add(new TextureRegion(sky, 248, 601, 124, 65));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(5, 0.9f, 0.9f, false));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(sky, 0, 666, 111, 26));
		tr.add(new TextureRegion(sky, 111, 666, 111, 26));
		tr.add(new TextureRegion(sky, 0, 666, 111, 26));
		tr.add(new TextureRegion(sky, 222, 666, 111, 26));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(5, 0.5f, 0.5f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(sky, 0, 692, 200, 160));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE1, changeRate);
		random_backgrounds.put(GameStyle.STYLE1, rbs);
	}
	
	private static void loadPunkStyle() {
		Texture punk = Art.loadTexture("images/punk_style/punk.png");
		ArrayList<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
		textureRegions.add(new TextureRegion(punk,0,0,320,600));
		textureRegions.add(new TextureRegion(punk,320,0,320,600));
		textureRegions.add(new TextureRegion(punk,640,0,320,600));
		textureRegions.add(new TextureRegion(Art.loadTexture("images/china_style/china.png"),320,0,320,600));
		backgrounds.put(GameStyle.STYLE4, textureRegions);
		
		Texture musicNode = Art.loadTexture("images/style1/musicnode.png");
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 0, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 100, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 200, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 300, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 400, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes_level1.put(GameStyle.STYLE4, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 100, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 200, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 300, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 400, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 0, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes_level2.put(GameStyle.STYLE4, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 200, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 300, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 400, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 0, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 100, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes_level3.put(GameStyle.STYLE4, nodes);
		
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 601, 126, 120));
		tr.add(new TextureRegion(punk, 126, 601, 126, 120));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(10, 0.5f, 0.9f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 601, 126, 120));
		tr.add(new TextureRegion(punk, 126, 601, 126, 120));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(5, 0.6f, 0.7f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(punk, 0, 601, 126, 120));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE4, changeRate);
		random_backgrounds.put(GameStyle.STYLE4, rbs);
	}
	
	private static void loadStarStyle() {
		Texture star = Art.loadTexture("images/star_style/star.png");
		ArrayList<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
		textureRegions.add(new TextureRegion(star,0,0,256,480));
		backgrounds.put(GameStyle.STYLE2, textureRegions);
		
		Texture musicNode = Art.loadTexture("images/style1/musicnode.png");
		EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion> nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 0, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 100, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 200, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 300, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 400, 0, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 0, 120, 100, 120));
		nodes_level1.put(GameStyle.STYLE2, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 100, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 200, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 300, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 400, 120, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 0, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 100, 240, 100, 120));
		nodes_level2.put(GameStyle.STYLE2, nodes);
		
		nodes = new EnumMap<MusicNote.MUSICNOTE_KIND, TextureRegion>(MusicNote.MUSICNOTE_KIND.class);
		nodes.put(MUSICNOTE_KIND.DO, new TextureRegion(
				musicNode, 200, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.RE, new TextureRegion(
				musicNode, 300, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.MI, new TextureRegion(
				musicNode, 400, 240, 100, 120));
		nodes.put(MUSICNOTE_KIND.FA, new TextureRegion(
				musicNode, 0, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.SO, new TextureRegion(
				musicNode, 100, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.LA, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes.put(MUSICNOTE_KIND.XI, new TextureRegion(
				musicNode, 200, 360, 100, 120));
		nodes_level3.put(GameStyle.STYLE2, nodes);
		
		EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>> rbs = new EnumMap<RandomBackground.RAND_BACKGROUND, ArrayList<TextureRegion>>(RandomBackground.RAND_BACKGROUND.class);
		EnumMap<RAND_BACKGROUND, AnimationBackground> changeRate = new EnumMap<RAND_BACKGROUND, AnimationBackground>(RAND_BACKGROUND.class);
		
		ArrayList<TextureRegion> tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(star, 320, 0, 80, 80));
		tr.add(new TextureRegion(star, 460, 23, 14, 14));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB1, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB1, new AnimationBackground(20, 0.9f, 0.9f, false));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(star, 460, 0, 23, 23));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB2, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB2, new AnimationBackground(60, 0.6f, 0.2f, true));
		
		tr = new ArrayList<TextureRegion>();
		tr.add(new TextureRegion(star, 400, 0, 60, 60));
		rbs.put(RandomBackground.RAND_BACKGROUND.RB3, tr);
		changeRate.put(RandomBackground.RAND_BACKGROUND.RB3, new AnimationBackground(60, 0, 0.9f, false));
		
		changeRates.put(GameStyle.STYLE2, changeRate);
		random_backgrounds.put(GameStyle.STYLE2, rbs);
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
