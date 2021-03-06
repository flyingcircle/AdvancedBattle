package me.capstone.advancedbattle.scene.scenes;

import org.andengine.extension.tmx.TMXLayer;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.touch.CursorSelector;
import me.capstone.advancedbattle.touch.MapScroller;
import me.capstone.advancedbattle.touch.PinchZoomDetector;
import me.capstone.advancedbattle.touch.TouchDistributor;

public class GameScene extends BaseScene {
	private ResourcesManager resourcesManager;
	
	private TouchDistributor touchDistributor;
	
    @Override
    public void createScene() {
    	this.resourcesManager = ResourcesManager.getInstance();
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
		resourcesManager.getCamera().setBounds(0, 0, 800, 480);
    }
    
    private void createTouchListeners() {
    	MapScroller mapScroller = new MapScroller(resourcesManager.getCamera());
    	PinchZoomDetector zoom = new PinchZoomDetector(resourcesManager.getCamera(), mapScroller);
    	CursorSelector cursor = new CursorSelector(resourcesManager.getCamera());
        this.touchDistributor = new TouchDistributor();
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
    	if (resourcesManager.getGameManager().getActionMenuManager().hasActionMenu()) {
    		resourcesManager.getGameManager().getActionMenuManager().destroyActionMenu();
    	} else if (resourcesManager.getGameManager().getMoveManager().isMoving()) {
    		resourcesManager.getGameManager().getMoveManager().destroyMoveAction(false);
    	} else if (resourcesManager.getGameManager().getAttackManager().isAttacking()) {
    		resourcesManager.getGameManager().getAttackManager().destroyAttackAction(false);
    	} else if (resourcesManager.getGameManager().getBuyMenuManager().hasBuyMenu()) {
    		resourcesManager.getGameManager().getBuyMenuManager().destroyBuyMenu();
    	}
    }
    
    @Override
    public void onMenuKeyPressed() {
    	if (resourcesManager.getGameManager().getActionMenuManager().hasActionMenu()) {
    		resourcesManager.getGameManager().getActionMenuManager().destroyActionMenu();
    	} else if (resourcesManager.getGameManager().getMoveManager().isMoving()) {
    		// Do nothing if the player is moving
    	} else {
    		resourcesManager.getGameManager().handleAction();
    	}
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	resourcesManager.getCamera().setZoomFactor(1.0F);
    	resourcesManager.getCamera().setHUD(null);
    	resourcesManager.getCamera().setBoundsEnabled(false);
    	resourcesManager.getCamera().setCenter(400, 240);
    	
    	touchDistributor.removeAllTouchListeners();
    	
    	resourcesManager.unloadGameResources();
    	
    	detachSelf();
    	dispose();
    }
    
}