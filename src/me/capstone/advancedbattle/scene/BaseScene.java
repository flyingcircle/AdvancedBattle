package me.capstone.advancedbattle.scene;

import org.andengine.entity.scene.Scene;

import me.capstone.advancedbattle.scene.SceneManager.SceneType;

public abstract class BaseScene extends Scene {
  
    public BaseScene() {
        createScene();
    }
    
    public abstract void createScene();
    
    public abstract void onBackKeyPressed();
    
    public abstract void onMenuKeyPressed();
        
    public abstract SceneType getSceneType();
    
    public abstract void disposeScene();
    
}