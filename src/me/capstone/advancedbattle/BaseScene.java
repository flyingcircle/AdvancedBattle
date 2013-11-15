package me.capstone.advancedbattle;

import org.andengine.entity.scene.Scene;
import me.capstone.advancedbattle.SceneManager.SceneType;

public abstract class BaseScene extends Scene {  
    private ResourcesManager resourcesManager;
  
    public BaseScene() {
        this.resourcesManager = ResourcesManager.getInstance();
        createScene();
    }
    
    public abstract void createScene();
    
    public abstract void onBackKeyPressed();
    
    public abstract void onMenuKeyPressed();
        
    public abstract SceneType getSceneType();
    
    public abstract void disposeScene();

	public ResourcesManager getResourcesManager() {
		return resourcesManager;
	}
}