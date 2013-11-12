package me.capstone.advancedbattle;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.extension.tmx.TMXLayer;

import me.capstone.advancedbattle.SceneManager.SceneType;
import me.capstone.advancedbattle.touchhandlers.MapScroller;
import me.capstone.advancedbattle.touchhandlers.PinchZoomDetector;
import me.capstone.advancedbattle.touchhandlers.TouchDistributor;

public class GameScene extends BaseScene
{
	
	private ZoomCamera camera;
	
    @Override
    public void createScene()
    {
    	camera = resourcesManager.camera;
    	createBackground();
    	setCamera();
    	createTouchListeners();
    }
    
    private void setCamera()
    {
		camera.setBoundsEnabled(true);
		camera.setBounds(0, 0, resourcesManager.game_background_tmx.getTMXLayers().get(0).getWidth(), resourcesManager.game_background_tmx.getTMXLayers().get(0).getHeight());
    }
    
    private void createBackground()
    {
    	for (TMXLayer layer : resourcesManager.game_background_tmx.getTMXLayers()) {
    		attachChild(layer);
    	}
    }
    
    private void createTouchListeners()
    {
    	MapScroller mapScroller = new MapScroller(camera);
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(new PinchZoomDetector(camera, mapScroller));
        touchDistributor.addTouchListener(mapScroller);
        this.setOnSceneTouchListener(touchDistributor);
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