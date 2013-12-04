package me.capstone.advancedbattle.manager.managers;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.TeamColor;
import me.capstone.advancedbattle.resources.tile.CursorTile;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.resources.tile.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

public class HUDManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	private HUD hud;
	
	private Entity rectangleGroup;
	
	private Text tileName;
	
	private Sprite terrainSprite;
	private Sprite structureSprite;
	private Sprite pieceSprite;
	
	private Text defense;
	
	private Sprite healthSprite;
	private Text health;
	
	private Sprite buildingSprite;
	private Text building;
	
	private Entity playerRectangleGroup;
	
	private Text currentPlayer;
	private Text currentFunds;
	
	public HUDManager(GameManager game) {
		this.game = game;
		this.hud = new HUD();
		createHUD();
	}
	
	private void createHUD() {	
	    this.playerRectangleGroup = new Entity(10, 10);
	    Rectangle rectangle = new Rectangle(0, 0, 200, 60, resourcesManager.getVbom());
	    rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    playerRectangleGroup.attachChild(rectangle);
	    
	    this.currentPlayer = new Text(0, 0, resourcesManager.getFont(), "abcdefghijklmnopqrstuvwxyz", new TextOptions(HorizontalAlign.LEFT), resourcesManager.getVbom());
	    currentPlayer.setText(game.getTurn().getName());
	    currentPlayer.setColor(game.getTurn().getColor());
	    currentPlayer.setScale(0.75F);
	    playerRectangleGroup.attachChild(currentPlayer);
	    
	    this.currentFunds = new Text(0, 0, resourcesManager.getFont(), "$123456789", new TextOptions(HorizontalAlign.RIGHT), resourcesManager.getVbom());
	    currentFunds.setText(Integer.toString(game.getRedFunds()));
	    currentFunds.setScale(0.5F);
	    currentFunds.setPosition(200 - currentFunds.getWidth() / 1.3F, 60 - currentFunds.getHeight());
	    playerRectangleGroup.attachChild(currentFunds);
	    
	    hud.attachChild(playerRectangleGroup);
	    
	    this.rectangleGroup = new Entity(650, 291);
	    rectangle = new Rectangle(0, 0, 124, 165, resourcesManager.getVbom());
	    rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    rectangleGroup.attachChild(rectangle);
	    
	    Tile tile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
	    
	    this.tileName = new Text(0, 0, resourcesManager.getFont(), "abcdefghijklmnopqrstuvwxyz", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
	    if (tile.getPieceTileID() != PieceTile.PIECE_NULL.getId()) {
	    	for (PieceTile piece : PieceTile.values()) {
	    		if (piece.getId() == tile.getPieceTileID()) {
	    			tileName.setText(piece.getName());
	    			break;
	    		}
	    	}
	    } else {
	    	if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getStructureTileID()) {
	    				tileName.setText(terrain.getName());
	    				break;
	    			}
	    		}
	    	} else {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getTerrainTileID()) {
	    				tileName.setText(terrain.getName());
	    				break;
	    			}
	    		}
	    	}
	    }
	    tileName.setScale(0.3F);
	    tileName.setPosition(62 - tileName.getWidth() / 2, -5);
	    rectangleGroup.attachChild(tileName);
	    
	    this.terrainSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
	    this.structureSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
	    this.pieceSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
	    terrainSprite.setScale(1.25F);
	    terrainSprite.setPosition(62 - terrainSprite.getWidth() / 2, 35);
	    structureSprite.setScale(1.25F);
	    structureSprite.setPosition(62 - structureSprite.getWidth() / 2, 35);
	    pieceSprite.setScale(1.25F);
	    pieceSprite.setPosition(62 - pieceSprite.getWidth() / 2, 35);
	    rectangleGroup.attachChild(terrainSprite);
	    rectangleGroup.attachChild(structureSprite);
	    rectangleGroup.attachChild(pieceSprite);
	    
	    this.defense = new Text(0, 0, resourcesManager.getFont(), "abcdefghijklmnopqrstuvwxyz", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
	    if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getStructureTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			break;
	    		}
	    	}
	    } else {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getTerrainTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			break;
	    		}
	    	}
	    }
	    defense.setScale(0.25F);
	    defense.setPosition(62 - defense.getWidth() / 2, 65);
	    rectangleGroup.attachChild(defense);
	    
	    if (tile.getPiece() != null) {
	    	Piece piece = tile.getPiece();
	    	
		    this.healthSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(CursorTile.HEALTH.getId()), resourcesManager.getVbom());
		    healthSprite.setPosition(31 - healthSprite.getWidth() / 2, 95);
		    
		    this.health = new Text(0, 0, resourcesManager.getFont(), "0123456789/", new TextOptions(HorizontalAlign.RIGHT), resourcesManager.getVbom());
		    health.setText(piece.getHealth() + " / " + piece.MAX_HEALTH);
		    health.setScale(0.3F);
		    health.setPosition(77 - health.getWidth() / 2, 95);
		    
		    rectangleGroup.attachChild(healthSprite);
		    rectangleGroup.attachChild(health);
		    
		    if (piece.getPieceTile().canLiberate()) {
		    	this.buildingSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(CursorTile.BUILDING.getId()), resourcesManager.getVbom());
		    	buildingSprite.setPosition(31 - buildingSprite.getWidth() / 2, 125);
		    	
		    	this.building = new Text(0, 0, resourcesManager.getFont(), "0123456789/", new TextOptions(HorizontalAlign.RIGHT), resourcesManager.getVbom());
		    	building.setText(piece.getCurrentBuildingHealth() + " / " + piece.MAX_BUILDING_HEALTH);
		    	building.setScale(0.3F);
		    	building.setPosition(77 - building.getWidth() / 2, 125);
		    	
		    	rectangleGroup.attachChild(buildingSprite);
		    	rectangleGroup.attachChild(building);
		    }
	    }
	    
	    hud.attachChild(rectangleGroup);
	    resourcesManager.getCamera().setHUD(hud);
	}
	
	public void updateHUD() {
		currentPlayer.setText(game.getTurn().getName());
	    currentPlayer.setColor(game.getTurn().getColor());
	    
	    if (game.getTurn() == TeamColor.RED) {
	    	currentFunds.setText(Integer.toString(game.getRedFunds()));
	    } else if (game.getTurn() == TeamColor.BLUE) {
	    	currentFunds.setText(Integer.toString(game.getBlueFunds()));
	    } else {
	    	currentFunds.setText("0");
	    }
	    currentFunds.setScale(0.5F);
	    currentFunds.setPosition(200 - currentFunds.getWidth() / 1.3F, 60 - currentFunds.getHeight());
	    
		Tile tile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		rectangleGroup.detachChild(terrainSprite);
		rectangleGroup.detachChild(structureSprite);
		rectangleGroup.detachChild(pieceSprite);
			
		if (tile.getPieceTileID() != PieceTile.PIECE_NULL.getId()) {
	    	for (PieceTile piece : PieceTile.values()) {
	    		if (piece.getId() == tile.getPieceTileID()) {
	    			tileName.setText(piece.getName());
	    			tileName.setPosition(62 - tileName.getWidth() / 2, -5);
	    			break;
	    		}
	    	}
	    } else {
	    	if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getStructureTileID()) {
	    				tileName.setText(terrain.getName());
	    				tileName.setPosition(62 - tileName.getWidth() / 2, -5);
	    				break;
	    			}
	    		}
	    	} else {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getTerrainTileID()) {
	    				tileName.setText(terrain.getName());
	    				tileName.setPosition(62 - tileName.getWidth() / 2, -5);
	    				break;
	    			}
	    		}
	    	}
	    }
		
		this.terrainSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
	    this.structureSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
	    this.pieceSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
	    
	    terrainSprite.setScale(1.25F);
	    terrainSprite.setPosition(62 - terrainSprite.getWidth() / 2, 35);
	    structureSprite.setScale(1.25F);
	    structureSprite.setPosition(62 - structureSprite.getWidth() / 2, 35);
	    pieceSprite.setScale(1.25F);
	    pieceSprite.setPosition(62 - pieceSprite.getWidth() / 2, 35);
	    
	    rectangleGroup.attachChild(terrainSprite);
	    rectangleGroup.attachChild(structureSprite);
	    rectangleGroup.attachChild(pieceSprite);
	    
	    if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getStructureTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			defense.setPosition(62 - defense.getWidth() / 2, 65);
	    			break;
	    		}
	    	}
	    } else {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getTerrainTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			defense.setPosition(62 - defense.getWidth() / 2, 65);
	    			break;
	    		}
	    	}
	    }
	    
	    rectangleGroup.detachChild(healthSprite);
	    rectangleGroup.detachChild(health);
	    rectangleGroup.detachChild(buildingSprite);
	    rectangleGroup.detachChild(building);
	    
	    if (tile.getPiece() != null) {
	    	Piece piece = tile.getPiece();
	    	this.healthSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(CursorTile.HEALTH.getId()), resourcesManager.getVbom());
	    	healthSprite.setPosition(31 - healthSprite.getWidth() / 2, 95);
	    
	    	this.health = new Text(0, 0, resourcesManager.getFont(), "0123456789/", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
	    	health.setText(piece.getHealth() + " / " + piece.MAX_HEALTH);
	    	health.setScale(0.3F);
	    	health.setPosition(77 - health.getWidth() / 2, 95);
	    	
	    	rectangleGroup.attachChild(healthSprite);
	    	rectangleGroup.attachChild(health);
	    
	    	if (piece.getPieceTile().canLiberate() && (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId() || tile.getStructureTileID() == TerrainTile.CITY_BLUE.getId() 
					|| tile.getStructureTileID() == TerrainTile.CITY_RED.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() 
					|| tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_WHITE.getId() 
					|| tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId() || tile.getStructureTileID() == TerrainTile.HQ_RED.getId())) {
		    	this.buildingSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(CursorTile.BUILDING.getId()), resourcesManager.getVbom());
		    	buildingSprite.setPosition(31 - buildingSprite.getWidth() / 2, 125);
		    	
		    	this.building = new Text(0, 0, resourcesManager.getFont(), "0123456789/", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
		    	building.setText(piece.getCurrentBuildingHealth() + " / " + piece.MAX_BUILDING_HEALTH);
		    	building.setScale(0.3F);
		    	building.setPosition(77 - building.getWidth() / 2, 125);
		    	
		    	rectangleGroup.attachChild(buildingSprite);
		    	rectangleGroup.attachChild(building);
		    }
	    }
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public Entity getRectangleGroup() {
		return rectangleGroup;
	}

	public void setRectangleGroup(Entity rectangleGroup) {
		this.rectangleGroup = rectangleGroup;
	}

	public Text getTileName() {
		return tileName;
	}

	public void setTileName(Text tileName) {
		this.tileName = tileName;
	}

	public Sprite getTerrainSprite() {
		return terrainSprite;
	}

	public void setTerrainSprite(Sprite terrainSprite) {
		this.terrainSprite = terrainSprite;
	}

	public Sprite getStructureSprite() {
		return structureSprite;
	}

	public void setStructureSprite(Sprite structureSprite) {
		this.structureSprite = structureSprite;
	}

	public Sprite getPieceSprite() {
		return pieceSprite;
	}

	public void setPieceSprite(Sprite pieceSprite) {
		this.pieceSprite = pieceSprite;
	}

	public Text getDefense() {
		return defense;
	}

	public void setDefense(Text defense) {
		this.defense = defense;
	}

	public Sprite getHealthSprite() {
		return healthSprite;
	}

	public void setHealthSprite(Sprite healthSprite) {
		this.healthSprite = healthSprite;
	}

	public Text getHealth() {
		return health;
	}

	public void setHealth(Text health) {
		this.health = health;
	}

	public Sprite getBuildingSprite() {
		return buildingSprite;
	}

	public void setBuildingSprite(Sprite buildingSprite) {
		this.buildingSprite = buildingSprite;
	}

	public Text getBuilding() {
		return building;
	}

	public void setBuilding(Text building) {
		this.building = building;
	}

	public Entity getPlayerRectangleGroup() {
		return playerRectangleGroup;
	}

	public void setPlayerRectangleGroup(Entity playerRectangleGroup) {
		this.playerRectangleGroup = playerRectangleGroup;
	}

	public Text getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Text currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Text getCurrentFunds() {
		return currentFunds;
	}

	public void setCurrentFunds(Text currentFunds) {
		this.currentFunds = currentFunds;
	}

}
