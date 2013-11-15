package me.capstone.advancedbattle.scene.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;

public class SplashScene extends BaseScene {
	private Sprite splash;
	
    @Override
	public void createScene() {
    	this.splash = new Sprite(0, 0, getResourcesManager().getSplashRegion(), getResourcesManager().getVbom()) {
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
    	        
    	splash.setScale(2f);
    	splash.setPosition(235, 200);
    	attachChild(splash);
    }

    @Override
    public void onBackKeyPressed() {
    	return;
    }
    
    @Override
    public void onMenuKeyPressed() {
    	return;
    }
    
    @Override
    public SceneType getSceneType() {
    	return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene() {
    	splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }

	public Sprite getSplash() {
		return splash;
	}

	public void setSplash(Sprite splash) {
		this.splash = splash;
	}
}
