package me.capstone.advancedbattle.touch;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.scenes.LevelScene;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ClickDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;

public class LevelScroller implements IOnSceneTouchListener, IScrollDetectorListener, IClickDetectorListener {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private LevelScene level;
	private ZoomCamera camera = resourcesManager.getCamera();
	
	private float minX = 0;
	private float maxX = 0;
	private float currentX = 0;
	
	private SurfaceScrollDetector scrollDetector;
	private ClickDetector clickDetector;

	public LevelScroller(LevelScene level) {
		this.level = level;
		this.scrollDetector = new SurfaceScrollDetector(this);
		this.clickDetector = new ClickDetector(this);
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		scrollDetector.onTouchEvent(pSceneTouchEvent);
		clickDetector.onTouchEvent(pSceneTouchEvent);
		return true;
	}

	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
	}

	@Override
	public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		if (camera.getXMin() <= 15) {
			level.getMenuLeft().setVisible(false);
		} else {
			level.getMenuLeft().setVisible(true);
		}
		
		if (camera.getXMin() > maxX - 15) {
			level.getMenuRight().setVisible(false);
		} else {
			level.getMenuRight().setVisible(true);
		}
		
		if ((currentX - pDistanceX) < minX) {
			return;
		} else if ((currentX - pDistanceX) > maxX) {
			return;
		}
		
		camera.offsetCenter(-pDistanceX, 0);
		currentX -= pDistanceX;
		
		int width = AdvancedBattleActivity.CAMERA_WIDTH;
		float tempX = camera.getCenterX() - width / 2;
		tempX += (tempX / (maxX + width)) * width;
		level.getScrollBar().setPosition(tempX, level.getScrollBar().getY());
		
		level.getMenuRight().setPosition(camera.getCenterX() + width / 2 - level.getMenuRight().getWidth(), level.getMenuRight().getY());
		level.getMenuLeft().setPosition(camera.getCenterX() - width / 2, level.getMenuLeft().getY());
		level.getLevelBackground().setPosition(camera.getCenterX() - level.getLevelBackground().getWidth() / 2, level.getLevelBackground().getY());
		// TODO : Title stuff?
		
		if (camera.getXMin() < 0) {
			camera.offsetCenter(0, 0);
			currentX = 0;
		}
	}

	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
	}
	
	@Override
	public void onClick(ClickDetector pClickDetector, int pPointerID, float pSceneX, float pSceneY) {
		level.loadLevel(level.getItemClicked());
	}

	public LevelScene getLevel() {
		return level;
	}

	public void setLevel(LevelScene level) {
		this.level = level;
	}

	public float getMinX() {
		return minX;
	}

	public void setMinX(float minX) {
		this.minX = minX;
	}

	public float getMaxX() {
		return maxX;
	}

	public void setMaxX(float maxX) {
		this.maxX = maxX;
	}

	public float getCurrentX() {
		return currentX;
	}

	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}
	
}
