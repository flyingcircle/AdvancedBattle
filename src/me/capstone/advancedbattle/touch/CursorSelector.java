package me.capstone.advancedbattle.touch;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.resources.CursorTile;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.scene.scenes.GameScene;
import me.capstone.advancedbattle.tile.Tile;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import android.view.MotionEvent;

public class CursorSelector implements IOnSceneTouchListener, IUpdateHandler {
	ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private ZoomCamera camera;
	
	private float lastX;
	private float lastY;
	private float clickedX;
	private float clickedY;
	private float ratioX;
	private float ratioY;
	
	public CursorSelector(ZoomCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent touchEvent) {
		MotionEvent evt = touchEvent.getMotionEvent();
		
		if (evt.getAction() == MotionEvent.ACTION_DOWN) {			
			lastX = evt.getRawX();
			lastY = evt.getRawY();
			
			ratioX = lastX / AdvancedBattleActivity.CAMERA_WIDTH;;
			ratioY = lastY / AdvancedBattleActivity.CAMERA_HEIGHT;
			if (camera.getZoomFactor() == 1) {
				clickedX = lastX;
				clickedY = lastY;
			} else {
				clickedX = camera.getCenterX() + (ratioX - 1/2F) * camera.getWidth();	
				clickedY = camera.getCenterY() + (ratioY - 1/2F) * camera.getHeight();
			}
			
			TMXLayer layer = resourcesManager.getGameMap().getTMXLayers().get(3);
			
			TMXTile cursorTile = layer.getTMXTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());		
			cursorTile.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR_NULL.getId());
			layer.setIndex(cursorTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + cursorTile.getTileColumn());
			layer.drawWithoutChecks(cursorTile.getTextureRegion(), cursorTile.getTileX(), cursorTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			resourcesManager.setCursorColumn((int) Math.floor(clickedX / 32));
			resourcesManager.setCursorRow((int) Math.floor(clickedY / 32));
			
			TMXTile newTile = layer.getTMXTile((int) Math.floor(clickedX / 32), (int) Math.floor(clickedY / 32));		
			newTile.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR.getId());
			layer.setIndex(newTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + newTile.getTileColumn());
			layer.drawWithoutChecks(newTile.getTextureRegion(), newTile.getTileX(), newTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			
			layer.submit();
						
			BaseScene scene = SceneManager.getInstance().getCurrentScene();
			if (scene.getSceneType() == SceneType.SCENE_GAME) {
				GameScene game = (GameScene) scene;
								
				Tile tile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
				
				game.getRectangleGroup().detachChild(game.getTerrainSprite());
				game.getRectangleGroup().detachChild(game.getStructureSprite());
				game.getRectangleGroup().detachChild(game.getPieceSprite());
				
				Sprite terrainSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
		    	Sprite structureSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
		    	Sprite pieceSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
		    	
		    	terrainSprite.setScale(1.5F);
		    	structureSprite.setScale(1.5F);
		    	pieceSprite.setScale(1.5F);
		    	
		    	game.getRectangleGroup().attachChild(terrainSprite);
		    	game.getRectangleGroup().attachChild(structureSprite);
		    	game.getRectangleGroup().attachChild(pieceSprite);
				
				if (ratioX > 0.8) {
					game.getRectangleGroup().setPosition(25, 240);
				} else if (ratioX < 0.2) {
					game.getRectangleGroup().setPosition(650, 240);
				}
			}
		}
		
		return true;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {		
	}

	@Override
	public void reset() {		
	}

}
