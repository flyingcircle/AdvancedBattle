package me.capstone.advancedbattle.resources;

import java.util.ArrayList;
import java.util.List;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.manager.GameManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;

public class ResourcesManager {
	private static ResourcesManager instance = new ResourcesManager();
	
	// Splash scene
	private BitmapTextureAtlas splashTextureAtlas;
	private ITextureRegion splashRegion;
	
	// Menu scene
	private BitmapTextureAtlas menuTextureAtlas;
	private ITextureRegion menuBackgroundRegion;
	private ITextureRegion playRegion;
	private ITextureRegion optionsRegion;
	
	//OptionsScene
	private BitmapTextureAtlas optionsTextureAtlas;
	private ITextureRegion musicOptionRegion;
	
	// Level selector scene
	private static int LEVEL_ITEMS = 2;
	
	private BitmapTextureAtlas backgroundTextureAtlas;
	private TextureRegion backgroundTextureRegion;
	
	private BitmapTextureAtlas titleTextureAtlas;
	private TextureRegion titleTextureRegion;
	
	private BitmapTextureAtlas levelTextureAtlas;
	private TextureRegion menuLeftTextureRegion;
	private TextureRegion menuRightTextureRegion;
	
	private List<BitmapTextureAtlas> levels = new ArrayList<BitmapTextureAtlas>();
	private List<TextureRegion> columns = new ArrayList<TextureRegion>();
	
	// Game scene
	private TiledTextureRegion playerTextureRegion;
	
	private TMXTiledMap gameMap;
	
	private GameManager gameManager;
	
	private int cursorRow = 0;
    private int cursorColumn = 0;
    
  	private BitmapTextureAtlas victoryTextureAtlas;
  	private ITextureRegion redVictoryTextureRegion;
  	private ITextureRegion blueVictoryTextureRegion;
	
	// Font
	private Font font;
	    
	// Attributes
    private Engine engine;
    private AdvancedBattleActivity activity;
    private ZoomCamera camera;
    private VertexBufferObjectManager vbom;

    public void loadSplashResources() {
    	loadSplashGraphics();
    	loadFonts();
    }
    
    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
    }
    
    public void loadOptionsResources(){
    	loadOptionsGraphics();
    }
    
    public void loadLevelResources() {
    	loadLevelGraphics();
    	loadLevelAudio();
    }
    
    public void loadGameResources(int id) {
    	this.cursorColumn = 0;
    	this.cursorRow = 0;
        loadGameGraphics(id);
        loadGameAudio();
    }
    
    public void unloadSplashResources() {
    	unloadSplashGraphics();
    }
    
    public void unloadMenuResources() {
    	unloadMenuGraphics();
    	unloadMenuAudio();
    }
    
    public void unloadOptionsResources(){
    	unloadOptionsGraphics();
    }
    
    public void unloadLevelResources() {
    	unloadLevelGraphics();
    	unloadLevelAudio();
    }
    
    public void unloadGameResources() {
    	this.gameManager = null;
    	unloadGameGraphics();
    	unloadGameAudio();
    }
    
    public void createSplashGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
    	
    	this.splashTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    	this.splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(getSplashTextureAtlas(), getActivity(), "splash.png", 0, 0);
    }
    
    private void loadSplashGraphics() {
    	splashTextureAtlas.load();
    }
    
    private void unloadSplashGraphics() {
    	splashTextureAtlas.unload();
    }
    
    public void createMenuGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	
    	this.menuTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	this.menuBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, getActivity(), "menuBackground.png", 0, 0);
    	this.playRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, getActivity(), "play.png", 200, 140);
    	this.optionsRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, getActivity(), "options.png", 200, 240);    	       
    }
    
    private void loadMenuGraphics() {
    	menuTextureAtlas.load();
    }
    
    private void unloadMenuGraphics() {
    	menuTextureAtlas.unload();
    }
    
    private void loadMenuAudio() {
        // TODO : Audio
    }
    
    private void unloadMenuAudio() {
    	// TODO : Audio
    }
    
    public void createOptionsGraphics(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/options/");
    	
    	this.optionsTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	this.setMusicOptionRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionsTextureAtlas, getActivity(), "musicOptionOn.png", 200, 0));
    }
    
    private void loadOptionsGraphics(){
    	optionsTextureAtlas.load();
    }
    
    private void unloadOptionsGraphics(){
    	optionsTextureAtlas.unload();
    }
    
    public void createLevelGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/level/");
    	
    	this.backgroundTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	this.backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(backgroundTextureAtlas, getActivity(), "levelBackground.png", 0, 0);
    	
    	// TODO : Title stuff
    	    	
    	for (int i = 0; i < LEVEL_ITEMS; i++) {
    		BitmapTextureAtlas levelAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    		TextureRegion texture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(levelAtlas, getActivity(), "level" + i + ".png", 0, 0);
    		
    		levels.add(levelAtlas);
    		columns.add(texture);
    	}
    	
    	this.levelTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	this.menuLeftTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(levelTextureAtlas, getActivity(), "level_left.png", 0, 0);
    	this.menuRightTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(levelTextureAtlas, getActivity(), "level_right.png", 672, 0);
    }
    
    private void loadLevelGraphics() {
    	backgroundTextureAtlas.load();
    	levelTextureAtlas.load();
    	
    	for (int i = 0; i < levels.size(); i++) {	
    		levels.get(i).load();
    	}
    }
    
    private void unloadLevelGraphics() {
    	backgroundTextureAtlas.unload();
    	levelTextureAtlas.unload();
    	
    	for (int i = 0; i < levels.size(); i++) {	
    		levels.get(i).unload();
    	}
    }
    
    private void loadLevelAudio() {
    	// TODO : Audio
    }
    
    private void unloadLevelAudio() {
    	// TODO : Audio
    }
    
    public void createGameGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
		this.victoryTextureAtlas = new BitmapTextureAtlas(getActivity().getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    	this.redVictoryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(victoryTextureAtlas, getActivity(), "RedWins.png", 0, 0);
    	this.blueVictoryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(victoryTextureAtlas, getActivity(), "BlueWins.png", 0, 0);
    }

    private void loadGameGraphics(int id) {
    	try {
    		final TMXLoader tmxLoader = new TMXLoader(getActivity().getAssets(), getEngine().getTextureManager(), getVbom());
    		if (id == 0) {
    			this.gameMap = tmxLoader.loadFromAsset("tmx/Canal.tmx");
    		} else if (id == 1) {
    			this.gameMap = tmxLoader.loadFromAsset("tmx/Islands.tmx");
    		}
    	} catch(final TMXLoadException tmxle) {
    		Debug.e(tmxle);
    	}
    }
    
    private void unloadGameGraphics() {
    	victoryTextureAtlas.unload();
    	this.gameMap = null;
    }
    
    private void loadGameAudio() {
    	// TODO : Audio
    }
    
    private void unloadGameAudio() {
    	// TODO : Audio
    }
    
    public void createFonts() {
        FontFactory.setAssetBasePath("font/");
        
        final ITexture mainFontTexture = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.font = FontFactory.createStrokeFromAsset(getActivity().getFontManager(), mainFontTexture, getActivity().getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
    }
    
    private void loadFonts() {
    	font.load();
    }
    
    private void unloadFonts() {
    	font.unload();
    }
    
    public static void prepareManager(Engine engine, AdvancedBattleActivity activity, ZoomCamera camera, VertexBufferObjectManager vbom) {
        getInstance().setEngine(engine);
        getInstance().setActivity(activity);
        getInstance().setCamera(camera);
        getInstance().setVbom(vbom);
    }

	public static ResourcesManager getInstance() {
		return instance;
	}

	public BitmapTextureAtlas getSplashTextureAtlas() {
		return splashTextureAtlas;
	}

	public void setSplashTextureAtlas(BitmapTextureAtlas splashTextureAtlas) {
		this.splashTextureAtlas = splashTextureAtlas;
	}

	public ITextureRegion getSplashRegion() {
		return splashRegion;
	}

	public void setSplashRegion(ITextureRegion splashRegion) {
		this.splashRegion = splashRegion;
	}

	public BitmapTextureAtlas getMenuTextureAtlas() {
		return menuTextureAtlas;
	}

	public void setMenuTextureAtlas(BitmapTextureAtlas menuTextureAtlas) {
		this.menuTextureAtlas = menuTextureAtlas;
	}

	public ITextureRegion getMenuBackgroundRegion() {
		return menuBackgroundRegion;
	}

	public void setMenuBackgroundRegion(ITextureRegion menuBackgroundRegion) {
		this.menuBackgroundRegion = menuBackgroundRegion;
	}

	public ITextureRegion getPlayRegion() {
		return playRegion;
	}

	public void setPlayRegion(ITextureRegion playRegion) {
		this.playRegion = playRegion;
	}

	public ITextureRegion getOptionsRegion() {
		return optionsRegion;
	}

	public void setOptionsRegion(ITextureRegion optionsRegion) {
		this.optionsRegion = optionsRegion;
	}

	public BitmapTextureAtlas getOptionsTextureAtlas() {
		return optionsTextureAtlas;
	}

	public void setOptionsTextureAtlas(BitmapTextureAtlas optionsTextureAtlas) {
		this.optionsTextureAtlas = optionsTextureAtlas;
	}

	public ITextureRegion getMusicOptionRegion() {
		return musicOptionRegion;
	}

	public void setMusicOptionRegion(ITextureRegion musicOptionRegion) {
		this.musicOptionRegion = musicOptionRegion;
	}

	public BitmapTextureAtlas getBackgroundTextureAtlas() {
		return backgroundTextureAtlas;
	}

	public void setBackgroundTextureAtlas(BitmapTextureAtlas backgroundTextureAtlas) {
		this.backgroundTextureAtlas = backgroundTextureAtlas;
	}

	public TextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}

	public void setBackgroundTextureRegion(TextureRegion backgroundTextureRegion) {
		this.backgroundTextureRegion = backgroundTextureRegion;
	}

	public BitmapTextureAtlas getTitleTextureAtlas() {
		return titleTextureAtlas;
	}

	public void setTitleTextureAtlas(BitmapTextureAtlas titleTextureAtlas) {
		this.titleTextureAtlas = titleTextureAtlas;
	}

	public TextureRegion getTitleTextureRegion() {
		return titleTextureRegion;
	}

	public void setTitleTextureRegion(TextureRegion titleTextureRegion) {
		this.titleTextureRegion = titleTextureRegion;
	}

	public BitmapTextureAtlas getLevelTextureAtlas() {
		return levelTextureAtlas;
	}

	public void setLevelTextureAtlas(BitmapTextureAtlas levelTextureAtlas) {
		this.levelTextureAtlas = levelTextureAtlas;
	}

	public TextureRegion getMenuLeftTextureRegion() {
		return menuLeftTextureRegion;
	}

	public void setMenuLeftTextureRegion(TextureRegion menuLeftTextureRegion) {
		this.menuLeftTextureRegion = menuLeftTextureRegion;
	}

	public TextureRegion getMenuRightTextureRegion() {
		return menuRightTextureRegion;
	}

	public void setMenuRightTextureRegion(TextureRegion menuRightTextureRegion) {
		this.menuRightTextureRegion = menuRightTextureRegion;
	}

	public List<BitmapTextureAtlas> getLevels() {
		return levels;
	}

	public void setLevels(List<BitmapTextureAtlas> levels) {
		this.levels = levels;
	}

	public List<TextureRegion> getColumns() {
		return columns;
	}

	public void setColumns(List<TextureRegion> columns) {
		this.columns = columns;
	}

	public TiledTextureRegion getPlayerTextureRegion() {
		return playerTextureRegion;
	}

	public void setPlayerTextureRegion(TiledTextureRegion playerTextureRegion) {
		this.playerTextureRegion = playerTextureRegion;
	}

	public TMXTiledMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(TMXTiledMap gameMap) {
		this.gameMap = gameMap;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public int getCursorRow() {
		return cursorRow;
	}

	public void setCursorRow(int cursorRow) {
		this.cursorRow = cursorRow;
	}

	public int getCursorColumn() {
		return cursorColumn;
	}

	public void setCursorColumn(int cursorColumn) {
		this.cursorColumn = cursorColumn;
	}

	public BitmapTextureAtlas getVictoryTextureAtlas() {
		return victoryTextureAtlas;
	}

	public void setVictoryTextureAtlas(BitmapTextureAtlas victoryTextureAtlas) {
		this.victoryTextureAtlas = victoryTextureAtlas;
	}

	public ITextureRegion getRedVictoryTextureRegion() {
		return redVictoryTextureRegion;
	}

	public void setRedVictoryTextureRegion(ITextureRegion redVictoryTextureRegion) {
		this.redVictoryTextureRegion = redVictoryTextureRegion;
	}

	public ITextureRegion getBlueVictoryTextureRegion() {
		return blueVictoryTextureRegion;
	}

	public void setBlueVictoryTextureRegion(ITextureRegion blueVictoryTextureRegion) {
		this.blueVictoryTextureRegion = blueVictoryTextureRegion;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public AdvancedBattleActivity getActivity() {
		return activity;
	}

	public void setActivity(AdvancedBattleActivity activity) {
		this.activity = activity;
	}

	public ZoomCamera getCamera() {
		return camera;
	}

	public void setCamera(ZoomCamera camera) {
		this.camera = camera;
	}

	public VertexBufferObjectManager getVbom() {
		return vbom;
	}

	public void setVbom(VertexBufferObjectManager vbom) {
		this.vbom = vbom;
	}
    
}