package me.capstone.advancedbattle.touchhandlers;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.scene.scenes.GameScene;

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

			TMXLayer layer = resourcesManager.getGameMap().getTMXLayers().get(3);
			
			TMXTile cursorTile = layer.getTMXTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
			
			cursorTile.setGlobalTileID(resourcesManager.getGameMap(), 97);
			layer.setIndex(cursorTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + cursorTile.getTileColumn());
			layer.drawWithoutChecks(cursorTile.getTextureRegion(), cursorTile.getTileX(), cursorTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			float clickedX;
			float clickedY;
			float ratioX = lastX / AdvancedBattleActivity.CAMERA_WIDTH;;
			float ratioY = lastY / AdvancedBattleActivity.CAMERA_HEIGHT;
			if (camera.getZoomFactor() == 1) {
				clickedX = lastX;
				clickedY = lastY;
			} else {
				clickedX = camera.getCenterX() + (ratioX - 1/2F) * camera.getWidth();	
				clickedY = camera.getCenterY() + (ratioY - 1/2F) * camera.getHeight();
			}
			
			int column = (int) Math.floor(clickedX / 32);
			int row = (int) Math.floor(clickedY / 32);	
			TMXTile newTile = layer.getTMXTile(column, row);
			
			resourcesManager.setCursorColumn(newTile.getTileColumn());
			resourcesManager.setCursorRow(newTile.getTileRow());
			
			newTile.setGlobalTileID(resourcesManager.getGameMap(), 96);
			layer.setIndex(newTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + newTile.getTileColumn());
			layer.drawWithoutChecks(newTile.getTextureRegion(), newTile.getTileX(), newTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			layer.submit();
						
			BaseScene scene = SceneManager.getInstance().getCurrentScene();
			if (scene.getSceneType() == SceneType.SCENE_GAME) {
				GameScene game = (GameScene) scene;
				
				//game.getText().setText(game.getText(resourcesManager.getCursorRow(), resourcesManager.getCursorColumn()));
				
				if (ratioX > 0.8) {
					game.getRectangleGroup().setPosition(25, 240);
				} else if (ratioX < 0.2) {
					game.getRectangleGroup().setPosition(650, 240);
				}
			}
		}
		
		if (evt.getAction() == MotionEvent.ACTION_UP) {
			touchDown = false;
		}
		
		return false;
	}
 
 
	@Override
	public void onUpdate(float pSecondsElapsed) {
		// TODO : This isn't needed until the flinger is used
		/*if (!isTouchDown() && (speedX != 0 || speedY != 0)) {
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
		}*/
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