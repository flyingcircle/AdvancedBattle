package me.capstone.advancedbattle.manager;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.util.color.Color;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;

import me.capstone.advancedbattle.manager.hud.GameHUD;
import me.capstone.advancedbattle.map.Map;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.Direction;
import me.capstone.advancedbattle.resources.data.MovementType;
import me.capstone.advancedbattle.resources.data.TeamColor;
import me.capstone.advancedbattle.resources.tile.CursorTile;
import me.capstone.advancedbattle.resources.tile.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class GameManager implements IOnMenuItemClickListener{
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private Map map;
	
	private GameHUD hud;
	
	// Action Menu
	private Entity actionMenu;
	private boolean hasActionMenu = false;

	private MenuScene actionMenuOptions;
	private static final int ATTACK = 0;
	private static final int LIBERATE = 1;
	private static final int MOVE = 2;
	private static final int BUY = 3;
	private static final int CANCEL = 4;
	private static final int END_TURN = 5;
	
	// Moving
	private boolean isMoving = false;
	private Tile movingPieceTile = null;
	private ArrayList<Tile> moves;
	
	// Team
	private int blueFunds;
	private int redFunds;
	private TeamColor turn;
	
	// Victory
	private Sprite victoryImage;
	
	public GameManager() {
		createMap();
		initGame();
		this.hud = new GameHUD(this);
	}
	
	private void createMap() {
	    this.map = new Map(resourcesManager.getGameMap().getTileColumns(), resourcesManager.getGameMap().getTileRows());
	    Tile[][] arrayMap = map.getMap();
	    
	    TMXLayer terrainLayer = resourcesManager.getGameMap().getTMXLayers().get(0);
	    TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
	    TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
	        	
	    for (int i = 0; i < map.getColumns(); i++) {
	    	for (int j = 0; j < map.getRows(); j++) {
	    		arrayMap[i][j] = new Tile(i, j, terrainLayer.getTMXTile(i, j).getGlobalTileID(), structureLayer.getTMXTile(i, j).getGlobalTileID(), pieceLayer.getTMXTile(i, j).getGlobalTileID());
	    		
	    		if (structureLayer.getTMXTile(i, j).getGlobalTileID() == TerrainTile.CITY_BLUE.getId()) {
	    			map.setBlueCities(map.getBlueCities() + 1);
	    		} else if (structureLayer.getTMXTile(i, j).getGlobalTileID() == TerrainTile.CITY_RED.getId()) {
	    			map.setRedCities(map.getRedCities() + 1);
	    		} else {
	    			// Other team colors
	    		}
	    	}
	    }
	    
	    System.out.println(map.getBlueCities());
	    System.out.println(map.getRedCities());
	    
	    map.setMap(arrayMap);
	}
	
	private void initGame() {
		this.turn = TeamColor.RED;
		
		this.blueFunds = map.getBlueCities() * 1000;
		this.redFunds = map.getRedCities() * 1000;
	}
	
	public void handleAction() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		int count = 2;
		if (tile.getPiece() != null) {
			Piece piece = tile.getPiece();
			
			if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116 && turn == TeamColor.RED) {
				// TODO : Has piece been used this turn?
				
				count++;
				
				boolean canAttack = true;
				count++;
				
				boolean canLiberate = false;
				if (piece.getPieceTile().canLiberate()) {
					if (tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.CITY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId() || tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
						count++;
						canLiberate = true;
					}
				}
				
				createActionMenu(count, false, canAttack, canLiberate);
			} else if (piece.getPieceTile().getId() >= 116 && piece.getPieceTile().getId() < 134 && turn == TeamColor.BLUE) {
				// TODO : Has piece been used this turn?
				
				count++;
				
				boolean canAttack = true;
				count++;
				
				boolean canLiberate = false;	
				if (tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId() || tile.getStructureTileID() == TerrainTile.CITY_RED.getId() || tile.getStructureTileID() == TerrainTile.HQ_RED.getId() || tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
						count++;
						canLiberate = true;
				}
				
				createActionMenu(count, false, canAttack, canLiberate);
			} else {
				createActionMenu(count, false, false, false);
			}
		} else {
			if ((tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() && turn == TeamColor.BLUE) || (tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId() && turn == TeamColor.RED)) {
				count++;
				createActionMenu(count, true, false, false);
			} else {
				createActionMenu(count, false, false, false);
			}
		}
	}
	
	public void createActionMenu(int items, boolean isFactory, boolean canAttack, boolean canLiberate) {	
		this.actionMenu = new Entity(0, 0);
		
		Rectangle backGroundRect = new Rectangle(0, 0, 800, 480, resourcesManager.getVbom());
	    backGroundRect.setColor(1.0F, 1.0F, 1.0F, 0.75F);
	    actionMenu.attachChild(backGroundRect);
	    
	    int size = items * 50;
	    Rectangle menuRect = new Rectangle(240, 240 - size / 2, 320, size + 10, resourcesManager.getVbom());
	    menuRect.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    
	    this.actionMenuOptions = new MenuScene(resourcesManager.getCamera());
	    actionMenuOptions.setPosition(0, 0);
	    
	    if (isFactory) {
	    	final IMenuItem buyMenuItem = new ColorMenuItemDecorator(new TextMenuItem(BUY, resourcesManager.getFont(), "Buy", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	actionMenuOptions.addMenuItem(buyMenuItem);
	    } else {
	    	// There isn't a factory in that tile, but it has a piece in it
	    	if (items > 2) {
	    	    final IMenuItem moveMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MOVE, resourcesManager.getFont(), "Move", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	    actionMenuOptions.addMenuItem(moveMenuItem);
	    	    
	    	    if (canAttack) {
	    		    final IMenuItem attackMenuItem = new ColorMenuItemDecorator(new TextMenuItem(ATTACK, resourcesManager.getFont(), "Attack", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    		    actionMenuOptions.addMenuItem(attackMenuItem);
	    	    }
	    	    
	    	    if (canLiberate) {
	    		    final IMenuItem liberateMenuItem = new ColorMenuItemDecorator(new TextMenuItem(LIBERATE, resourcesManager.getFont(), "Liberate", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    		    actionMenuOptions.addMenuItem(liberateMenuItem);
	    	    }
	    	}
	    }
	    final IMenuItem cancelMenuItem = new ColorMenuItemDecorator(new TextMenuItem(CANCEL, resourcesManager.getFont(), "Cancel", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    final IMenuItem endTurnMenuItem = new ColorMenuItemDecorator(new TextMenuItem(END_TURN, resourcesManager.getFont(), "EndTurn", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    
	    actionMenuOptions.addMenuItem(cancelMenuItem);
	    actionMenuOptions.addMenuItem(endTurnMenuItem);
	    
	    actionMenuOptions.buildAnimations();
	    actionMenuOptions.setBackgroundEnabled(false);
	    
	    actionMenu.attachChild(menuRect);
	    
	    
	    hud.getHud().attachChild(actionMenu);
	    hud.getHud().setChildScene(actionMenuOptions);
	    
	    actionMenuOptions.setOnMenuItemClickListener(this);
	    
	    this.hasActionMenu = true;
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case ATTACK:
			destroyActionMenu();
			// Attack stuff
			return true;
		case LIBERATE:
			destroyActionMenu();
			liberate();
			return true;
		case MOVE:
			destroyActionMenu();
			createMoveAction();
			return true;
		case BUY:
			destroyActionMenu();
			// Buy stuff
			return true;
		case CANCEL:
			destroyActionMenu();
			return true;
		case END_TURN:
			destroyActionMenu();
			endTurn();
			return true;
		default:
			return false;
		}
	}
	
	public void destroyActionMenu() {
		hud.getHud().detachChild(actionMenu);
		hud.getHud().detachChild(actionMenuOptions);
		
		actionMenuOptions.clearMenuItems();
		actionMenuOptions.clearTouchAreas();
		actionMenuOptions.closeMenuScene();
		actionMenuOptions.dispose();
		this.actionMenuOptions = null;
		
		actionMenu.dispose();
		this.actionMenu = null;
		
		this.hasActionMenu = false;
	}
	
	public void liberate() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		Piece piece = tile.getPiece();
		
		int pieceHealth = piece.getHealth();
		int buildingHealth = piece.getCurrentBuildingHealth() - pieceHealth / 2;
		if (buildingHealth <= 0) {
			buildingHealth = 0;
		}
		piece.setCurrentBuildingHealth(buildingHealth);
				
		if (piece.getCurrentBuildingHealth() == 0) {
			if (turn == TeamColor.RED) {
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_RED.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_RED.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					map.setRedCities(map.getRedCities() + 1);
				} else if (tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.FACTORY_RED.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile factory = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					factory.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.FACTORY_RED.getId());
					structureLayer.setIndex(factory.getTileRow() * resourcesManager.getGameMap().getTileColumns() + factory.getTileColumn());
					structureLayer.drawWithoutChecks(factory.getTextureRegion(), factory.getTileX(), factory.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				} else if (tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.HQ_RED.getId());
					Tile top = map.getTile(tile.getColumn(), tile.getRow() - 1);
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
					
					createVictoryImage();
				}
			} else if (turn == TeamColor.BLUE) {
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.CITY_BLUE.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_BLUE.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
						
					map.setBlueCities(map.getBlueCities() + 1);
				} else if (tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.FACTORY_BLUE.getId());
						
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile factory = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					factory.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.FACTORY_BLUE.getId());
					structureLayer.setIndex(factory.getTileRow() * resourcesManager.getGameMap().getTileColumns() + factory.getTileColumn());
					structureLayer.drawWithoutChecks(factory.getTextureRegion(), factory.getTileX(), factory.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				} else if (tile.getStructureTileID() == TerrainTile.HQ_RED.getId()) {
					piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
					
					tile.setStructureTileID(TerrainTile.HQ_BLUE.getId());
					Tile top = map.getTile(tile.getColumn(), tile.getRow() - 1);
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
					
					createVictoryImage();
				}
			} else {
				// Not sure what to do here. Should always be red or blue.
			}
		}
		
		hud.updateHUD();
	}
	
	public void createVictoryImage() {
		resourcesManager.getVictoryTextureAtlas().load();
		if (turn == TeamColor.RED) {
			this.victoryImage = new Sprite(0, 0, resourcesManager.getRedVictoryTextureRegion(), resourcesManager.getVbom());
		} else if (turn == TeamColor.BLUE) {
			this.victoryImage = new Sprite(0, 0, resourcesManager.getBlueVictoryTextureRegion(), resourcesManager.getVbom());
		} else {
			this.victoryImage = null;
		}
		
		victoryImage.setScale(2F);
		victoryImage.setPosition(400 - victoryImage.getWidth() / 2, 240 - victoryImage.getHeight() / 2);
		hud.getHud().attachChild(victoryImage);
	}
	
	public void createMoveAction() {
		this.isMoving = true;
		
		this.movingPieceTile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
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
		// Check if move was successful. If so, check to see if there are nearby attacks to be made. If so, offer that option to the player.
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
		
		// Get the north, south, east, and west tiles
		if (tile.getRow() == 0) {
			nTile = null;
		} else {
			nTile = map.getTile(tile.getColumn(), tile.getRow() - 1);
		}
		
		if (tile.getColumn() == map.getColumns() - 1) {
			eTile = null;
		} else {
			eTile = map.getTile(tile.getColumn() + 1, tile.getRow());
		}
		
		if (tile.getRow() == map.getRows() - 1) {
			sTile = null;
		} else {
			sTile = map.getTile(tile.getColumn(), tile.getRow() + 1);
		}
		
		if (tile.getRow() == 0) {
			wTile = null;
		} else {
			wTile = map.getTile(tile.getColumn() - 1, tile.getRow());
		}
		
		int nCost = -1;
		int eCost = -1;
		int wCost = -1;
		int sCost = -1;
		
		// From the type of movement that we are, get the cost to enter the north, south, east, and west tiles
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
				
		// Check to see if we can enter the north tile
		if (nCost != -1 && current + nCost <= movement && lastDirection != Direction.NORTH) {
			// Add north tile to the list of allowed moves
			result.add(nTile);
			// Run again for the north tile
			ArrayList<Tile> temp = calculatePath(current + nCost, movement, nTile, result, moveType, Direction.SOUTH);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			// Add all accepted tiles to the list of allowed moves
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		// Check to see if we can enter the east tile
		if (eCost != -1 && current + eCost <= movement && lastDirection != Direction.EAST) {
			// Add east tile to the list of allowed moves
			result.add(eTile);
			// Run again for the east tile
			ArrayList<Tile> temp = calculatePath(current + eCost, movement, eTile, result, moveType, Direction.WEST);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			// Add all accepted tiles to the list of allowed moves
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		// Check to see if we can enter the south tile
		if (sCost != -1 && current + sCost <= movement && lastDirection != Direction.SOUTH) {
			// Add south tile to the list of allowed moves
			result.add(sTile);
			// Run again for the south tile
			ArrayList<Tile> temp = calculatePath(current + sCost, movement, sTile, result, moveType, Direction.NORTH);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			// Add all accepted tiles to the list of allowed moves
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
		
		// Check to see if we can enter the west tile
		if (wCost != -1 && current + wCost <= movement && lastDirection != Direction.WEST) {
			// Add west tile to the list of allowed moves
			result.add(wTile);
			// Run again for the west tile
			ArrayList<Tile> temp = calculatePath(current + wCost, movement, wTile, result, moveType, Direction.EAST);
			Tile[] array = temp.toArray(new Tile[temp.size()]);
			// Add all accepted tiles to the list of allowed moves
			for (int i = 0; i < array.length; i++) {
				if (result.contains(array[i])) {
					continue;
				}
				result.add(array[i]);
			}
		}
				
		return result;
	}
	
	public void endTurn() {
		if (turn == TeamColor.RED) {
			turn = TeamColor.BLUE;
			blueFunds += map.getBlueCities() * 1000;
			System.out.println("Blue funds are: " + blueFunds);
			System.out.println("Blue cities are: " + map.getBlueCities());
		} else if (turn == TeamColor.BLUE) {
			turn = TeamColor.RED;
			redFunds += map.getRedCities() * 1000;
			System.out.println("Red funds are: " + redFunds);
			System.out.println("Red cities are: " + map.getRedCities());
		} else {
			turn = TeamColor.NULL;
		}
		
		hud.updateHUD();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public GameHUD getHud() {
		return hud;
	}

	public void setHud(GameHUD hud) {
		this.hud = hud;
	}

	public Entity getActionMenu() {
		return actionMenu;
	}

	public void setActionMenu(Entity actionMenu) {
		this.actionMenu = actionMenu;
	}

	public boolean hasActionMenu() {
		return hasActionMenu;
	}

	public void setHasActionMenu(boolean hasActionMenu) {
		this.hasActionMenu = hasActionMenu;
	}

	public MenuScene getActionMenuOptions() {
		return actionMenuOptions;
	}

	public void setActionMenuOptions(MenuScene actionMenuOptions) {
		this.actionMenuOptions = actionMenuOptions;
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

	public int getBlueFunds() {
		return blueFunds;
	}

	public void setBlueFunds(int blueFunds) {
		this.blueFunds = blueFunds;
	}

	public int getRedFunds() {
		return redFunds;
	}

	public void setRedFunds(int redFunds) {
		this.redFunds = redFunds;
	}

	public TeamColor getTurn() {
		return turn;
	}

	public void setTurn(TeamColor turn) {
		this.turn = turn;
	}

	public Sprite getVictoryImage() {
		return victoryImage;
	}

	public void setVictoryImage(Sprite victoryImage) {
		this.victoryImage = victoryImage;
	}

}
