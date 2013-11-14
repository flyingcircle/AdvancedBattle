package me.capstone.advancedbattle.touchhandlers;

import me.capstone.advancedbattle.ResourcesManager;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
 
import android.view.MotionEvent;
 
public class MapScroller implements IOnSceneTouchListener, IUpdateHandler {
	ResourcesManager resourcesManager = ResourcesManager.getInstance();

	private ZoomCamera camera;

	private boolean touchDown = false;
	private float speedX;
	private float speedY;
	private float lastX;
	private float lastY;

	public MapScroller(ZoomCamera camera) {
		this.camera = camera;
	}
       
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent touchEvent) {
		MotionEvent evt = touchEvent.getMotionEvent();

		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
			touchDown = true;
			lastX = evt.getRawX();
			lastY = evt.getRawY();
			speedX = 0;
			speedY = 0;
		}
		if (isTouchDown()) {
			camera.setCenter(camera.getCenterX() + (lastX - evt.getRawX()) / camera.getZoomFactor(), camera.getCenterY() + (lastY - evt.getRawY()) / camera.getZoomFactor());
			lastX = evt.getRawX();
			lastY = evt.getRawY();

			TMXLayer layer = getResourcesManager().getGameMap().getTMXLayers().get(3);
			
			TMXTile tile = layer.getTMXTile(getResourcesManager().getCursorColumn(), getResourcesManager().getCursorRow());
			tile.setGlobalTileID(getResourcesManager().getGameMap(), 97);
			layer.draw(tile.getTextureRegion(), tile.getTileX(), tile.getTileY(), tile.getTileWidth(), tile.getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			tile = layer.getTMXTileAt(lastX, lastY);
			
			getResourcesManager().setCursorColumn(tile.getTileColumn());
			getResourcesManager().setCursorRow(tile.getTileRow());
			
			tile.setGlobalTileID(getResourcesManager().getGameMap(), 96);
			layer.draw(tile.getTextureRegion(), tile.getTileX(), tile.getTileY(), tile.getTileWidth(), tile.getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			layer.submit();
		}
		if (evt.getAction() == MotionEvent.ACTION_UP) {
			touchDown = false;
		}
		return false;
	}
 
 
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (!isTouchDown() && (speedX != 0 || speedY != 0)) {
			// Log.v("AndEngine", "SpeedX: " + String.valueOf(this.speedX) +
			// " SpeedY: " + String.valueOf(this.speedY));
			
			camera.setCenter(camera.getCenterX() - speedX * pSecondsElapsed / camera.getZoomFactor(), camera.getCenterY() - speedY * pSecondsElapsed / camera.getZoomFactor());

			speedX *= (1.0f - 1.2f * pSecondsElapsed);
			speedY *= (1.0f - 1.2f * pSecondsElapsed);

			if (speedX < 10 && speedX > -10) {
				speedX = 0;
			}
			
			if (speedY < 10 && speedY > -10) {
				speedY = 0;
			}
		}
	}
 
	@Override
	public void reset() {

	}

	public void cancelCurScrolling() {
		setTouchDown(false);
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

	public boolean isTouchDown() {
		return touchDown;
	}

	public void setTouchDown(boolean touchDown) {
		this.touchDown = touchDown;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public float getLastX() {
		return lastX;
	}

	public void setLastX(float lastX) {
		this.lastX = lastX;
	}

	public float getLastY() {
		return lastY;
	}

	public void setLastY(float lastY) {
		this.lastY = lastY;
	}
}