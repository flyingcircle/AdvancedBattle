package me.capstone.advancedbattle.manager.managers;

import java.util.ArrayList;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.Direction;
import me.capstone.advancedbattle.resources.data.MovementType;
import me.capstone.advancedbattle.resources.tile.CursorTile;
import me.capstone.advancedbattle.resources.tile.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;

public class MoveManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	private boolean isMoving = false;
	private Tile movingPieceTile = null;
	private ArrayList<Tile> moves;
	
	public MoveManager(GameManager game) {
		this.game = game;
	}
	
	public void createMoveAction() {
		this.isMoving = true;
		
		this.movingPieceTile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		this.moves = new ArrayList<Tile>();
		
		int movement = movingPieceTile.getPiece().getPieceTile().getMovement();
		MovementType moveType = movingPieceTile.getPiece().getPieceTile().getMoveType();
		moves = calculatePath(0, movement, movingPieceTile, new ArrayList<Tile>(), moveType, Direction.NULL);
		
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile move : moves) {
			TMXTile highlighted = statusLayer.getTMXTile(move.getColumn(), move.getRow());
			highlighted.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.HIGHLIGHT.getId());
			statusLayer.setIndex(highlighted.getTileRow() * resourcesManager.getGameMap().getTileColumns() + highlighted.getTileColumn());
			statusLayer.drawWithoutChecks(highlighted.getTextureRegion(), highlighted.getTileX(), highlighted.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			statusLayer.submit();
		}
	}
	
	public void destroyMoveAction(boolean successful) {
		this.isMoving = false;
		
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile move : moves) {
			TMXTile highlighted = statusLayer.getTMXTile(move.getColumn(), move.getRow());
			highlighted.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR_NULL.getId());
			statusLayer.setIndex(highlighted.getTileRow() * resourcesManager.getGameMap().getTileColumns() + highlighted.getTileColumn());
			statusLayer.drawWithoutChecks(highlighted.getTextureRegion(), highlighted.getTileX(), highlighted.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			statusLayer.submit();
		}
		
		this.movingPieceTile = null;
		this.moves = null;
	}
	
	public ArrayList<Tile> calculatePath(int current, int movement, Tile tile, ArrayList<Tile> result, MovementType moveType, Direction lastDirection) {
		Tile nTile;
		Tile eTile;
		Tile sTile;
		Tile wTile;
		
		if (tile.getRow() == 0) {
			nTile = null;
		} else {
			nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
		}
		
		if (tile.getColumn() == game.getMap().getColumns() - 1) {
			eTile = null;
		} else {
			eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
		}
		
		if (tile.getRow() == game.getMap().getRows() - 1) {
			sTile = null;
		} else {
			sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
		}
		
		if (tile.getRow() == 0) {
			wTile = null;
		} else {
			wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
		}
		
		int nCost = -1;
		int eCost = -1;
		int wCost = -1;
		int sCost = -1;
		
		if (moveType == MovementType.INFANTRY) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getInfantryMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getInfantryMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getInfantryMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getInfantryMovement();
	    		}
	    	}
		} else if (moveType == MovementType.MECH) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getMechMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getMechMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getMechMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getMechMovement();
	    		}
	    	}
		} else if (moveType == MovementType.TIRES) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getTireMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getTireMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getTireMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getTireMovement();
	    		}
	    	}
		} else if (moveType == MovementType.TREAD) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getTreadMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getTreadMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getTreadMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getTreadMovement();
	    		}
	    	}
		} else if (moveType == MovementType.SEA) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getSeaMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getSeaMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getSeaMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getSeaMovement();
	    		}
	    	}
		} else if (moveType == MovementType.LANDER) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getLanderMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getLanderMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getLanderMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getLanderMovement();
	    		}
	    	}
		} else if (moveType == MovementType.AIR) {
			for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == nTile.getTerrainTileID()) {
	    			nCost = terrain.getAirMovement();
	    		}
	    		
	    		if (terrain.getId() == eTile.getTerrainTileID()) {
	    			eCost = terrain.getAirMovement();
	    		}
	    		
	    		if (terrain.getId() == sTile.getTerrainTileID()) {
	    			sCost = terrain.getAirMovement();
	    		}
	    		
	    		if (terrain.getId() == wTile.getTerrainTileID()) {
	    			wCost = terrain.getAirMovement();
	    		}
	    	}
		}
		
		if (nTile.getPiece() != null) {
			nCost = -1;
		}
		
		if (eTile.getPiece() != null) {
			eCost = -1;
		}
		
		if (sTile.getPiece() != null) {
			sCost = -1;
		}
		
		if (wTile.getPiece() != null) {
			wCost = -1;
		}
				
		if (nCost != -1 && current + nCost <= movement && lastDirection != Direction.NORTH) {
			result.add(nTile);
			ArrayList<Tile> temp = calculatePath(current + nCost, movement, nTile, result, moveType, Direction.SOUTH);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		if (eCost != -1 && current + eCost <= movement && lastDirection != Direction.EAST) {
			result.add(eTile);
			ArrayList<Tile> temp = calculatePath(current + eCost, movement, eTile, result, moveType, Direction.WEST);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		if (sCost != -1 && current + sCost <= movement && lastDirection != Direction.SOUTH) {
			result.add(sTile);
			ArrayList<Tile> temp = calculatePath(current + sCost, movement, sTile, result, moveType, Direction.NORTH);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		if (wCost != -1 && current + wCost <= movement && lastDirection != Direction.WEST) {
			result.add(wTile);
			ArrayList<Tile> temp = calculatePath(current + wCost, movement, wTile, result, moveType, Direction.EAST);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
				
		return result;
	}

	public static ResourcesManager getResourcesManager() {
		return resourcesManager;
	}

	public static void setResourcesManager(ResourcesManager resourcesManager) {
		MoveManager.resourcesManager = resourcesManager;
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Tile getMovingPieceTile() {
		return movingPieceTile;
	}

	public void setMovingPieceTile(Tile movingPieceTile) {
		this.movingPieceTile = movingPieceTile;
	}

	public ArrayList<Tile> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Tile> moves) {
		this.moves = moves;
	}

}
