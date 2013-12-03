package me.capstone.advancedbattle.manager;

import java.util.ArrayList;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.TextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.color.Color;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.HorizontalAlign;

import me.capstone.advancedbattle.map.Map;
import me.capstone.advancedbattle.resources.CursorTile;
import me.capstone.advancedbattle.resources.Direction;
import me.capstone.advancedbattle.resources.MovementType;
import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.TeamColor;
import me.capstone.advancedbattle.resources.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class GameManager implements IOnMenuItemClickListener{
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private Map map;
	
	//tile info hud
	private HUD hud;
	private Entity rectangleGroup;
	private Text tileName;
	private Sprite terrainSprite;
	private Sprite structureSprite;
	private Sprite pieceSprite;
	private Text defense;
	
	//playerinfo hud
	private Entity playerRectangleGroup;
	private Text currentPlayer;
	private Text currentFunds;
	
	//action menu
	private Entity actionMenu;
	private boolean hasActionMenu = false;

	private MenuScene actionMenuOptions;
	private static final int ATTACK = 0;
	private static final int LIBERATE = 1;
	private static final int MOVE = 2;
	private static final int BUY = 3;
	private static final int CANCEL = 4;
	private static final int END_TURN = 5;
	
	//Victory image
	private BitmapTextureAtlas victoryTextureAtlas;
	private ITextureRegion victoryTextureRegion;
	private Sprite victoryImage;
	
	private boolean isMoving = false;
	private Tile movingPieceTile = null;
	private ArrayList<Tile> moves;
	
	public GameManager() {
		createMap();
		createHUD();
		createPlayerHud();
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
	    	}
	    }
	    
	    map.setMap(arrayMap);
	}
	    
	private void createHUD() {
	    this.hud = new HUD();
	    
	    this.rectangleGroup = new Entity(650, 240);
	    Rectangle rectangle = new Rectangle(0, 0, 124, 214, resourcesManager.getVbom());
	    rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    rectangleGroup.attachChild(rectangle);
	    
	    Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
	    
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
	    tileName.setPosition(62 - tileName.getWidth() / 2, 0);
	    rectangleGroup.attachChild(tileName);
	    
	    this.terrainSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
	    this.structureSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
	    this.pieceSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
	    terrainSprite.setScale(1.25F);
	    terrainSprite.setPosition(62 - terrainSprite.getWidth() / 2, 40);
	    structureSprite.setScale(1.25F);
	    structureSprite.setPosition(62 - structureSprite.getWidth() / 2, 40);
	    pieceSprite.setScale(1.25F);
	    pieceSprite.setPosition(62 - pieceSprite.getWidth() / 2, 40);
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
	    defense.setPosition(62 - defense.getWidth() / 2, 70);
	    rectangleGroup.attachChild(defense);
	    
	    hud.attachChild(rectangleGroup);
	    resourcesManager.getCamera().setHUD(hud);
	}
	
	public void updateHUD() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		rectangleGroup.detachChild(terrainSprite);
		rectangleGroup.detachChild(structureSprite);
		rectangleGroup.detachChild(pieceSprite);
			
		if (tile.getPieceTileID() != PieceTile.PIECE_NULL.getId()) {
	    	for (PieceTile piece : PieceTile.values()) {
	    		if (piece.getId() == tile.getPieceTileID()) {
	    			tileName.setText(piece.getName());
	    			tileName.setPosition(62 - tileName.getWidth() / 2, 0);
	    			break;
	    		}
	    	}
	    } else {
	    	if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getStructureTileID()) {
	    				tileName.setText(terrain.getName());
	    				tileName.setPosition(62 - tileName.getWidth() / 2, 0);
	    				break;
	    			}
	    		}
	    	} else {
	    		for (TerrainTile terrain : TerrainTile.values()) {
	    			if (terrain.getId() == tile.getTerrainTileID()) {
	    				tileName.setText(terrain.getName());
	    				tileName.setPosition(62 - tileName.getWidth() / 2, 0);
	    				break;
	    			}
	    		}
	    	}
	    }
		
		Sprite terrainSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
	    Sprite structureSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
	    Sprite pieceSprite = new Sprite(0, 0, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
	    
	    terrainSprite.setScale(1.25F);
	    terrainSprite.setPosition(62 - terrainSprite.getWidth() / 2, 40);
	    structureSprite.setScale(1.25F);
	    structureSprite.setPosition(62 - structureSprite.getWidth() / 2, 40);
	    pieceSprite.setScale(1.25F);
	    pieceSprite.setPosition(62 - pieceSprite.getWidth() / 2, 40);
	    
	    rectangleGroup.attachChild(terrainSprite);
	    rectangleGroup.attachChild(structureSprite);
	    rectangleGroup.attachChild(pieceSprite);
	    
	    if (tile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && tile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && tile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getStructureTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			defense.setPosition(62 - defense.getWidth() / 2, 70);
	    			break;
	    		}
	    	}
	    } else {
	    	for (TerrainTile terrain : TerrainTile.values()) {
	    		if (terrain.getId() == tile.getTerrainTileID()) {
	    			defense.setText("Def: " + terrain.getDefense());
	    			defense.setPosition(62 - defense.getWidth() / 2, 70);
	    			break;
	    		}
	    	}
	    }
	}
	
	private void createPlayerHud() {
	    playerRectangleGroup = new Entity(10, 10);
	    Rectangle rectangle = new Rectangle(0, 0, 240, 80, resourcesManager.getVbom());
	    rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    playerRectangleGroup.attachChild(rectangle);
	    
	    //TODO get Player for current Player
	    
	    currentPlayer = new Text(0, 0, resourcesManager.getFont(), "RED", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
	    //TODO currentPlayer.setText(player.getColor());
	    
	    currentPlayer.setScale(1F);
	    currentPlayer.setPosition(5, 5);
	    playerRectangleGroup.attachChild(currentPlayer);
	    
	    currentFunds = new Text(0, 0, resourcesManager.getFont(), "$1000", new TextOptions(HorizontalAlign.CENTER), resourcesManager.getVbom());
	    //TODO currentFunds.setText(player.getFunds());
	    currentFunds.setScale(0.5F);
	    currentFunds.setPosition( 80, 40);
	    playerRectangleGroup.attachChild(currentFunds);
	    
	    hud.attachChild(playerRectangleGroup);
	}
	
	public void updatePlayerHud() {
		//TODO get player info
		//TODO set new currentPlayer
	    //currentPlayer.setText(player.getColor());
		//currentFunds.setText(player.getFunds());
	}
	
	public void handleAction() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		// We always want to display Cancel and End Turn in the action menu
		int count = 2;
		if (tile.getPiece() != null) {
			Piece piece = tile.getPiece();
			
			// If piece has not been used this turn (assume it hasn't for now)
			
			// Show the move action
			count++;
			
			// Can the piece attack without moving?
			boolean canAttack = true;
			count++;
			
			// Can the piece liberate?
			boolean canLiberate = false;
			if (piece.getPieceTile().canLiberate()) {
				TeamColor color = TeamColor.NULL;
				if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116) {
					color = TeamColor.RED;
				} else if (piece.getPieceTile().getId() >= 116 && piece.getPieceTile().getId() < 134) {
					color = TeamColor.BLUE;
				}
				
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId() || (color == TeamColor.RED && tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId()) || (color == TeamColor.BLUE && tile.getStructureTileID() == TerrainTile.HQ_RED.getId())) {
					count++;
					canLiberate = true;
				}
			}
			
			createActionMenu(count, false, canAttack, canLiberate);
		} else {
			// No piece there. Is there a factory there? If not, just show Cancel and End Turn.
			if (tile.getStructureTileID() == TerrainTile.FACTORY_BLUE.getId() || tile.getStructureTileID() == TerrainTile.FACTORY_RED.getId()) {
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
	    
	    
	    hud.attachChild(actionMenu);
	    hud.setChildScene(actionMenuOptions);
	    
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
			// Cancel stuff
			return true;
		case END_TURN:
			destroyActionMenu();
			// End turn stuff
			return true;
		default:
			return false;
		}
	}
	
	public void createVictoryImage() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
		victoryTextureAtlas = new BitmapTextureAtlas(resourcesManager.getActivity().getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    	victoryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(victoryTextureAtlas, resourcesManager.getActivity(), "RedWins.png", 0, 0);
		victoryImage = new Sprite(0, 0, victoryTextureRegion, resourcesManager.getVbom());
		
		victoryImage.setScale(2F);
		victoryImage.setPosition(235, 200);
		hud.attachChild(victoryImage);
	}
	
	public void destroyActionMenu() {
		hud.detachChild(actionMenu);
		hud.detachChild(actionMenuOptions);
		
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
		
		TeamColor color = TeamColor.NULL;
		if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116) {
			color = TeamColor.RED;
		} else if (piece.getPieceTile().getId() >= 116 && piece.getPieceTile().getId() < 134) {
			color = TeamColor.BLUE;
		}
		
		
		if (piece.getCurrentBuildingHealth() == 0) {
			if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
				piece.setCurrentBuildingHealth(piece.MAX_BUILDING_HEALTH);
				
				// TODO : Add images for other colors
				if (color == TeamColor.RED) {
					tile.setStructureTileID(TerrainTile.CITY_RED.getId());
					
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_RED.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				} else if (color == TeamColor.BLUE) {
					tile.setStructureTileID(TerrainTile.CITY_BLUE.getId());
					
					TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
					TMXTile city = structureLayer.getTMXTile(tile.getColumn(), tile.getRow());
					city.setGlobalTileID(resourcesManager.getGameMap(), TerrainTile.CITY_BLUE.getId());
					structureLayer.setIndex(city.getTileRow() * resourcesManager.getGameMap().getTileColumns() + city.getTileColumn());
					structureLayer.drawWithoutChecks(city.getTextureRegion(), city.getTileX(), city.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
					structureLayer.submit();
				}
			} else if (tile.getStructureTileID() == TerrainTile.HQ_BLUE.getId() && color == TeamColor.RED) {
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
			} else if (tile.getStructureTileID() == TerrainTile.HQ_RED.getId() && color == TeamColor.BLUE) {
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
			}
		}
		
		updateHUD();
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
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
}