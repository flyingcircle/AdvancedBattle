package me.capstone.advancedbattle.resources;

import me.capstone.advancedbattle.AdvancedBattleActivity;

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
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;

public class ResourcesManager {
	private static ResourcesManager instance = new ResourcesManager();
	
	private ITextureRegion splashRegion;
	private BitmapTextureAtlas splashTextureAtlas;
	
	private ITextureRegion menuBackgroundRegion;
	private ITextureRegion playRegion;
	private ITextureRegion optionsRegion;
	
	private TMXTiledMap gameMap;
	private TiledTextureRegion playerTextureRegion;
	    
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	private Font font;
	    
    private Engine engine;
    private AdvancedBattleActivity activity;
    private ZoomCamera camera;
    private VertexBufferObjectManager vbom;
    
    private int cursorRow = 0;
    private int cursorColumn= 0;

    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }
    
    public void loadGameResources() {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }
    
    private void loadMenuGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	setMenuTextureAtlas(new BuildableBitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR));
    	setMenuBackgroundRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(getMenuTextureAtlas(), getActivity(), "menuBackground2.png"));
    	setPlayRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(getMenuTextureAtlas(), getActivity(), "play.png"));
    	setOptionsRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(getMenuTextureAtlas(), getActivity(), "options.png"));
    	       
    	try {
    	    getMenuTextureAtlas().build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    getMenuTextureAtlas().load();
    	} 
    	catch (final TextureAtlasBuilderException e) {
    	        Debug.e(e);
    	}
    }
    
    private void loadMenuFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(getActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        this.font = FontFactory.createStrokeFromAsset(getActivity().getFontManager(), mainFontTexture, getActivity().getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
        getFont().load();
    }
    
    public void unloadMenuTextures() {
        getMenuTextureAtlas().unload();
    }
        
    public void loadMenuTextures() {
        getMenuTextureAtlas().load();
    }
    
    private void loadMenuAudio() {
        // TODO : Audio
    }

    private void loadGameGraphics() {
    	try {
    		final TMXLoader tmxLoader = new TMXLoader(getActivity().getAssets(), getEngine().getTextureManager(), getEngine().getVertexBufferObjectManager());
    		this.gameMap = tmxLoader.loadFromAsset("tmx/Map.tmx");
    	} catch(final TMXLoadException tmxle) {
    		Debug.e(tmxle);
    	}
    }
    
    private void loadGameFonts() {
        // TODO : Are there any game fonts? Probably?
    }
    
    private void loadGameAudio() {
    	// TODO : Audio
    }
    
    public void loadSplashScreen() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	setSplashTextureAtlas(new BitmapTextureAtlas(getActivity().getTextureManager(), 256, 256, TextureOptions.BILINEAR));
    	setSplashRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(getSplashTextureAtlas(), getActivity(), "splash.png", 0, 0));
    	getSplashTextureAtlas().load();
    }
    
    public void unloadSplashScreen() {
    	getSplashTextureAtlas().unload();
    	setSplashRegion(null);
    }
    
    public void unloadGameTextures() {
    	// TODO : Are there any game textures?
    }
    
    public static void prepareManager(Engine engine, AdvancedBattleActivity activity, ZoomCamera camera, VertexBufferObjectManager vbom) {
        getInstance().setEngine(engine);
        getInstance().setActivity(activity);
        getInstance().setCamera(camera);
        getInstance().setVbom(vbom);
    }
    
    public static ResourcesManager getInstance()
    {
        return instance;
    }

	public ITextureRegion getSplashRegion() {
		return splashRegion;
	}

	public void setSplashRegion(ITextureRegion splashRegion) {
		this.splashRegion = splashRegion;
	}

	public BitmapTextureAtlas getSplashTextureAtlas() {
		return splashTextureAtlas;
	}

	public void setSplashTextureAtlas(BitmapTextureAtlas splashTextureAtlas) {
		this.splashTextureAtlas = splashTextureAtlas;
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

	public TMXTiledMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(TMXTiledMap gameMap) {
		this.gameMap = gameMap;
	}

	public TiledTextureRegion getPlayerTextureRegion() {
		return playerTextureRegion;
	}

	public void setPlayerTextureRegion(TiledTextureRegion playerTextureRegion) {
		this.playerTextureRegion = playerTextureRegion;
	}

	public BuildableBitmapTextureAtlas getMenuTextureAtlas() {
		return menuTextureAtlas;
	}

	public void setMenuTextureAtlas(BuildableBitmapTextureAtlas menuTextureAtlas) {
		this.menuTextureAtlas = menuTextureAtlas;
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
}