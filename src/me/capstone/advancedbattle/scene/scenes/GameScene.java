package me.capstone.advancedbattle.scene.scenes;

import org.andengine.extension.tmx.TMXLayer;

import android.widget.Toast;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.touch.CursorSelector;
import me.capstone.advancedbattle.touch.MapScroller;
import me.capstone.advancedbattle.touch.PinchZoomDetector;
import me.capstone.advancedbattle.touch.TouchDistributor;

public class GameScene extends BaseScene {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();

    @Override
    public void createScene() {
    	createBackground();
    	setCamera();
    	createTouchListeners();
    	createGameManager();
    }
    
    private void createBackground() {
    	for (TMXLayer layer : resourcesManager.getGameMap().getTMXLayers()) {
    		attachChild(layer);
    	}
    }
    
    private void setCamera() {
		resourcesManager.getCamera().setBoundsEnabled(true);
		TMXLayer layer = resourcesManager.getGameMap().getTMXLayers().get(0);
		resourcesManager.getCamera().setBounds(0, 0, layer.getWidth(), layer.getHeight());
    }
    
    private void createTouchListeners() {
    	MapScroller mapScroller = new MapScroller(resourcesManager.getCamera());
    	PinchZoomDetector zoom = new PinchZoomDetector(resourcesManager.getCamera(), mapScroller);
    	CursorSelector cursor = new CursorSelector(resourcesManager.getCamera());
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(zoom);
        touchDistributor.addTouchListener(mapScroller);
        touchDistributor.addTouchListener(cursor);
        setOnSceneTouchListener(touchDistributor);
    }
    
    private void createGameManager() {
    	resourcesManager.setGameManager(new GameManager());
    }

    @Override
    public void onBackKeyPressed() {
    	SceneManager.getInstance().loadMenuScene(resourcesManager.getEngine());
    }
    
    @Override
    public void onMenuKeyPressed() {
    	Toast.makeText(resourcesManager.getActivity().getApplicationContext(), "Menu button pressed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	resourcesManager.getCamera().setHUD(null);
    	resourcesManager.getCamera().setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }
}