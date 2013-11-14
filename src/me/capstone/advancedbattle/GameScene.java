package me.capstone.advancedbattle;

import org.andengine.extension.tmx.TMXLayer;

import me.capstone.advancedbattle.SceneManager.SceneType;
import me.capstone.advancedbattle.touchhandlers.MapScroller;
import me.capstone.advancedbattle.touchhandlers.PinchZoomDetector;
import me.capstone.advancedbattle.touchhandlers.TouchDistributor;

public class GameScene extends BaseScene {
		
    @Override
    public void createScene() {  	
    	createBackground();
    	setCamera();
    	createTouchListeners();
    }
    
    private void setCamera() {
		getResourcesManager().getCamera().setBoundsEnabled(true);
		TMXLayer layer = getResourcesManager().getGameMap().getTMXLayers().get(0);
		getResourcesManager().getCamera().setBounds(0, 0, layer.getWidth(), layer.getHeight());
    }
    
    private void createBackground() {
    	for (TMXLayer layer : getResourcesManager().getGameMap().getTMXLayers()) {
    		attachChild(layer);
    	}
    }
    
    private void createTouchListeners() {
    	MapScroller mapScroller = new MapScroller(getResourcesManager().getCamera());
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(new PinchZoomDetector(getResourcesManager().getCamera(), mapScroller));
        touchDistributor.addTouchListener(mapScroller);
        this.setOnSceneTouchListener(touchDistributor);
    }

    @Override
    public void onBackKeyPressed() {
    	SceneManager.getInstance().loadMenuScene(getResourcesManager().getEngine());
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	getResourcesManager().getCamera().setHUD(null);
    	getResourcesManager().getCamera().setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }
}