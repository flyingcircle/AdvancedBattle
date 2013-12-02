package me.capstone.advancedbattle.manager;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.util.color.Color;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.util.HorizontalAlign;

import android.widget.Toast;

import me.capstone.advancedbattle.map.Map;
import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class GameManager implements IOnMenuItemClickListener{
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private Map map;
	private HUD hud;
	private Entity rectangleGroup;
	private Text tileName;
	private Sprite terrainSprite;
	private Sprite structureSprite;
	private Sprite pieceSprite;
	private Text defense;
	
	private Entity actionMenu;
	private boolean hasActionMenu = false;

	private MenuScene actionMenuOptions;
	private static final int ATTACK = 0;
	private static final int LIBERATE = 1;
	private static final int MOVE = 2;
	private static final int BUY = 3;
	private static final int CANCEL = 4;
	private static final int END_TURN = 5;
	
	public GameManager() {
		createMap();
		createHUD();
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
	
	public void handleAction() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		// We always want to display Cancel and End Turn in the action menu
		int count = 2;
		if (tile.getPiece() != null) {
			Piece piece = tile.getPiece();
			
			// If piece has not been used this turn
			
			// Show the move action
			count++;
			
			// Can the piece attack without moving?
			boolean canAttack = true;
			count++;
			
			// Can the piece liberate?
			boolean canLiberate = false;
			if (piece.getPiece().canLiberate()) {
				if (tile.getStructureTileID() == TerrainTile.CITY_WHITE.getId()) {
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
		case 0:
			destroyActionMenu();
			// Attack stuff
			return true;
		case 1:
			destroyActionMenu();
			// Liberate stuff
			return true;
		case 2:
			destroyActionMenu();
			// Move stuff
			return true;
		case 3:
			destroyActionMenu();
			// Buy stuff
			return true;
		case 4:
			destroyActionMenu();
			// Cancel stuff
			return true;
		case 5:
			destroyActionMenu();
			// End turn stuff
			return true;
		default:
			return false;
		}
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
		if (hasActionMenu) {
			return true;
		}
		return false;
	}   
}
