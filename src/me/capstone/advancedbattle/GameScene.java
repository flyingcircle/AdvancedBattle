package me.capstone.advancedbattle;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.util.debug.Debug;

import me.capstone.advancedbattle.SceneManager.SceneType;

public class GameScene extends BaseScene
{
	private TMXTiledMap map;
	
    @Override
    public void createScene()
    {
    	createBackground();
    }
    
    private void createBackground()
    {
    	try {
    		AdvancedBattleActivity instance = AdvancedBattleActivity.getInstance();
    		final TMXLoader tmxLoader = new TMXLoader(instance.getAssets(), engine.getTextureManager(), engine.getVertexBufferObjectManager());
    		map = tmxLoader.loadFromAsset("gfx/map.tmx");
    	} catch (final TMXLoadException tmxle) {
    		Debug.e(tmxle);
    	}

    	for(TMXLayer tmxLayer : map.getTMXLayers()) {
    		this.getChildByIndex(1).attachChild(tmxLayer);
    	}    	
    }

    @Override
    public void onBackKeyPressed()
    {
    	SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
    	camera.setHUD(null);
        camera.setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }
}