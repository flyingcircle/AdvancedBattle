package me.capstone.advancedbattle;

import java.io.IOException;

import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.entity.scene.Scene;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;

import android.view.KeyEvent;

public class AdvancedBattleActivity extends BaseGameActivity {
	private static AdvancedBattleActivity instance;
	private ResourcesManager resourcesManager;
	
	public static final int CAMERA_WIDTH = 800;
	public static final int CAMERA_HEIGHT = 480;
		
	private ZoomCamera camera = null;
	
	public AdvancedBattleActivity() {
		instance = this;
	}
	
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, 60);
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		this.camera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.setBounds(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.setBoundsEnabled(true);
		
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	    engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
	    engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
	    return engineOptions;
	}
	
	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
		ResourcesManager.prepareManager(getEngine(), this, camera, getVertexBufferObjectManager());
	    pOnCreateResourcesCallback.onCreateResourcesFinished();
	    this.resourcesManager = ResourcesManager.getInstance();
	}
	
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	camera.setZoomFactor(1);
	        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	        return true;
	    } else if (keyCode == KeyEvent.KEYCODE_MENU) {
	    	SceneManager.getInstance().getCurrentScene().onMenuKeyPressed();
	    	return true;
	    }
	    return false; 
	}
	
	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
		getEngine().registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
	            @Override
				public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	                getEngine().unregisterUpdateHandler(pTimerHandler);
	                SceneManager.getInstance().createMenuScene();
	            }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	    System.exit(0);	
	}
	
	public static AdvancedBattleActivity getInstance() {
		return instance;
	}
	
	public ResourcesManager getResourcesManager() {
		return resourcesManager;
	}

	public void setResourcesManager(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
	}

	public ZoomCamera getCamera() {
		return camera;
	}

	public void setCamera(ZoomCamera camera) {
		this.camera = camera;
	}
}
