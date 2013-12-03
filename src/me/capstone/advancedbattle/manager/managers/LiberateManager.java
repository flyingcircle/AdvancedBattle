package me.capstone.advancedbattle.manager.managers;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.TeamColor;
import me.capstone.advancedbattle.resources.tile.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class LiberateManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	public LiberateManager(GameManager game) {
		this.game = game;
	}
	
	public boolean canLiberate(Tile tile) {
		Piece piece = tile.getPiece();
		if (piece.getPieceTile().canLiberate()) {
			if (game.getTurn() == TeamColor.RED) {
				if (tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.CITY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId() || tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_WHITE.getId()) {
					return true;
				}
			} else if (game.getTurn() == TeamColor.BLUE) {
				if (tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId() || tile.getStructureTileID() == TerrainTile.CITY_RED.getId() || tile.getStructureTileID() == TerrainTile.HQ_RED.getId() || tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_WHITE.getId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void liberate() {
		Tile tile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		Piece piece = tile.getPiece();
		
		int pieceHealth = piece.getHealth();
		int buildingHealth = piece.getCurrentBuildingHealth() - pieceHealth / 2;
		if (buildingHealth <= 0) {
			buildingHealth = 0;
		}
		piece.setCurrentBuildingHealth(buildingHealth);
				
		if (piece.getCurrentBuildingHealth() == 0) {
			if (game.getTurn() == TeamColor.RED) {
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_RED.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_RED.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					game.getMap().setRedCities(game.getMap().getRedCities() + 1);
				} else if (tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.FACTORY_RED.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile factory = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					factory.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.FACTORY_RED.getId());
					structureLayer.setIndex(factory.getTileRow() * resourcesManager.getGameMap().getTileColumns() + factory.getTileColumn());
					structureLayer.drawWithoutChecks(factory.getTextureRegion(), factory.getTileX(), factory.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				} else if (tile.getStructureTileID() == TerrainTile.CITY_BLUE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_RED.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_RED.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					game.getMap().setRedCities(game.getMap().getRedCities() + 1);
					game.getMap().setBlueCities(game.getMap().getBlueCities() - 1);
				} else if (tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.HQ_RED.getId());
					Tile top = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
					top.setStructureTileID(TerrainTile.HQ_RED_TOP.getId());
					
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile hqbase = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					hqbase.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.HQ_RED.getId());
					structureLayer.setIndex(hqbase.getTileRow() * resourcesManager.getGameMap().getTileColumns() + hqbase.getTileColumn());
					structureLayer.drawWithoutChecks(hqbase.getTextureRegion(), hqbase.getTileX(), hqbase.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				
					TMXTile hqtop = structureLayer.getTMXTile(top.getColumn(), top.getRow());
					hqtop.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.HQ_RED_TOP.getId());
					structureLayer.setIndex(hqtop.getTileRow() * resourcesManager.getGameMap().getTileColumns() + hqtop.getTileColumn());
					structureLayer.drawWithoutChecks(hqtop.getTextureRegion(), hqtop.getTileX(), hqtop.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
					
					game.createVictoryImage();
				}
			} else if (game.getTurn() == TeamColor.BLUE) {
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_BLUE.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_BLUE.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					game.getMap().setBlueCities(game.getMap().getBlueCities() + 1);
				} else if (tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.FACTORY_BLUE.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile factory = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					factory.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.FACTORY_BLUE.getId());
					structureLayer.setIndex(factory.getTileRow() * resourcesManager.getGameMap().getTileColumns() + factory.getTileColumn());
					structureLayer.drawWithoutChecks(factory.getTextureRegion(), factory.getTileX(), factory.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				} else if (tile.getStructureTileID() == TerrainTile.CITY_RED.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_BLUE.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_BLUE.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					game.getMap().setBlueCities(game.getMap().getBlueCities() + 1);
					game.getMap().setRedCities(game.getMap().getRedCities() - 1);
				} else if (tile.getStructureTileID() == TerrainTile.HQ_RED.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.HQ_BLUE.getId());
					Tile top = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
					top.setStructureTileID(TerrainTile.HQ_BLUE_TOP.getId());
					
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile hqbase = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					hqbase.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.HQ_BLUE.getId());
					structureLayer.setIndex(hqbase.getTileRow() * resourcesManager.getGameMap().getTileColumns() + hqbase.getTileColumn());
					structureLayer.drawWithoutChecks(hqbase.getTextureRegion(), hqbase.getTileX(), hqbase.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				
					TMXTile hqtop = structureLayer.getTMXTile(top.getColumn(), top.getRow());
					hqtop.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.HQ_BLUE_TOP.getId());
					structureLayer.setIndex(hqtop.getTileRow() * resourcesManager.getGameMap().getTileColumns() + hqtop.getTileColumn());
					structureLayer.drawWithoutChecks(hqtop.getTextureRegion(), hqtop.getTileX(), hqtop.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
					
					game.createVictoryImage();
				}
			}
		}
		
		game.disable(tile);
		game.getHud().updateHUD();
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

}
