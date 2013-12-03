package me.capstone.advancedbattle.touch;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.CursorTile;
import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
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
			clickedX = (float) Math.floor(clickedX / 32);
			clickedY = (float) Math.floor(clickedY / 32);
			
			GameManager game = resourcesManager.getGameManager();
			if (clickedX != resourcesManager.getCursorColumn() || clickedY != resourcesManager.getCursorRow()) {
				TMXLayer cursorLayer = resourcesManager.getGameMap().getTMXLayers().get(4);
				
				TMXTile cursorTile = cursorLayer.getTMXTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());		
				cursorTile.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR_NULL.getId());
				cursorLayer.setIndex(cursorTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + cursorTile.getTileColumn());
				cursorLayer.drawWithoutChecks(cursorTile.getTextureRegion(), cursorTile.getTileX(), cursorTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);			
				cursorLayer.submit();
				
				resourcesManager.setCursorColumn((int) clickedX);
				resourcesManager.setCursorRow((int) clickedY);
				
				TMXTile newTile = cursorLayer.getTMXTile((int) clickedX, (int) clickedY);		
				newTile.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR.getId());
				cursorLayer.setIndex(newTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + newTile.getTileColumn());
				cursorLayer.drawWithoutChecks(newTile.getTextureRegion(), newTile.getTileX(), newTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
				cursorLayer.submit();
				
				TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
				if (game.isMoving()) {
					for (Tile tile : game.getMoves()) {
						if (tile.getRow() == (int) clickedY && tile.getColumn() == (int) clickedX) {
							
							Piece piece = game.getMovingPieceTile().getPiece();
							// TODO : Need to check if player walks off enemy HQ also
							if (game.getMovingPieceTile().getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
								piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
							}
							
							TMXTile pieceTile = pieceLayer.getTMXTile(game.getMovingPieceTile().getColumn(), game.getMovingPieceTile().getRow());
							pieceTile.setGlobalTileID(resourcesManager.getGameMap(), PieceTile.PIECE_NULL.getId());
							pieceLayer.setIndex(pieceTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + pieceTile.getTileColumn());
							pieceLayer.drawWithoutChecks(pieceTile.getTextureRegion(), pieceTile.getTileX(), pieceTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
							
							game.getMovingPieceTile().setPiece(null);
							game.getMovingPieceTile().setPieceTileID(PieceTile.PIECE_NULL.getId());			
							pieceLayer.submit();
							
							TMXTile moveTile = pieceLayer.getTMXTile((int) clickedX, (int) clickedY);
							moveTile.setGlobalTileID(resourcesManager.getGameMap(), piece.getPieceTile().getId());
							pieceLayer.setIndex(moveTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + moveTile.getTileColumn());
							pieceLayer.drawWithoutChecks(moveTile.getTextureRegion(), moveTile.getTileX(), moveTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);								
							pieceLayer.submit();
							
							game.getMap().getTile(moveTile.getTileColumn(), moveTile.getTileRow()).setPiece(piece);
							game.getMap().getTile(moveTile.getTileColumn(), moveTile.getTileRow()).setPieceTileID(piece.getPieceTile().getId());
							
							game.destroyMoveAction(true);
							break;
						}
					}
				}
				
				game.updateHUD();
				if (ratioX > 0.8) {
					game.getRectangleGroup().setPosition(25, 240);
				} else if (ratioX < 0.2) {
					game.getRectangleGroup().setPosition(650, 240);
				}
			} else {
				game.handleAction();
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
