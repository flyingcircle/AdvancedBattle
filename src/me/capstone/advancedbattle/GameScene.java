package me.capstone.advancedbattle;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
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
    	onLoadScene();
    }
    
    private void onLoadScene()
    {    		
    	final Scene scene = new Scene();
    	try {
    		AdvancedBattleActivity instance = AdvancedBattleActivity.getInstance();

    		final TMXLoader tmxLoader = new TMXLoader(instance.getAssets(), engine.getTextureManager(), engine.getVertexBufferObjectManager());
    		map = tmxLoader.loadFromAsset("tmx/sample.tmx");
    	} catch (final TMXLoadException tmxle) {
    		Debug.e(tmxle);
    	}

    	final TMXLayer tmxLayer = map.getTMXLayers().get(0);
    	scene.attachChild(tmxLayer);
    		
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