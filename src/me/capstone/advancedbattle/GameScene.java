package me.capstone.advancedbattle;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.input.touch.TouchEvent;
import me.capstone.advancedbattle.SceneManager.SceneType;

public class GameScene extends BaseScene
{
	
	private TMXLayer tmxLayer;
	
    @Override
    public void createScene()
    {
    	setCamera();
    	createBackground();
    }
    
    private void setCamera()
    {
    	resourcesManager.camera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
    	resourcesManager.camera.setBoundsEnabled(true);
    }
    
    private void createBackground()
    {
    	tmxLayer = resourcesManager.game_background_tmx.getTMXLayers().get(0);
    	attachChild(tmxLayer);
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