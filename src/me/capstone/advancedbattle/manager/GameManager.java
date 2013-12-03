package me.capstone.advancedbattle.manager;

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

import me.capstone.advancedbattle.manager.hud.GameHUD;
import me.capstone.advancedbattle.manager.managers.AttackManager;
import me.capstone.advancedbattle.manager.managers.LiberateManager;
import me.capstone.advancedbattle.manager.managers.MoveManager;
import me.capstone.advancedbattle.map.Map;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.TeamColor;
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
	
	// Managers
	private AttackManager attackManager;
	private LiberateManager liberateManager;
	private MoveManager moveManager;
	
	// Team
	private int blueFunds;
	private int redFunds;
	private TeamColor turn;
	
	// Victory
	private Sprite victoryImage;
	
	public GameManager() {
		createMap();
		initGame();
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
		
		this.hud = new GameHUD(this);
		
		this.attackManager = new AttackManager(this);
		this.liberateManager = new LiberateManager(this);
		this.moveManager = new MoveManager(this);
	}
	
	private TeamColor getPieceColor(Piece piece) {
		if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116) {
			return TeamColor.RED;
		} else if (piece.getPieceTile().getId() >= 116 && piece.getPieceTile().getId() < 134) {
			return TeamColor.BLUE;
		} else {
			return TeamColor.NULL;
		}
	}
	
	public void handleAction() {
		Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		int count = 2;
		if (tile.getPiece() != null) {
			Piece piece = tile.getPiece();
			
			if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116 && turn == TeamColor.RED) {
				// TODO : Has piece been used this turn?
				
				count++;
				
				boolean canAttack = false;
				if (tile.getRow() == 0) {
					Tile sTile = map.getTile(tile.getColumn(), tile.getRow() + 1);
					if (sTile.getPiece() != null && getPieceColor(sTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getRow() == map.getRows() - 1) {
					Tile nTile = map.getTile(tile.getColumn(), tile.getRow() - 1);
					if (nTile.getPiece() != null && getPieceColor(nTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getColumn() == 0) {
					Tile eTile = map.getTile(tile.getColumn() + 1, tile.getRow());
					if (eTile.getPiece() != null && getPieceColor(eTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getColumn() == map.getColumns() - 1) {
					Tile wTile = map.getTile(tile.getColumn() - 1, tile.getRow());
					if (wTile.getPiece() != null && getPieceColor(wTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else {
					Tile nTile = map.getTile(tile.getColumn(), tile.getRow() - 1);
					if (nTile.getPiece() != null && getPieceColor(nTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile sTile = map.getTile(tile.getColumn(), tile.getRow() + 1);
					if (sTile.getPiece() != null && getPieceColor(sTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile eTile = map.getTile(tile.getColumn() + 1, tile.getRow());
					if (eTile.getPiece() != null && getPieceColor(eTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile wTile = map.getTile(tile.getColumn() - 1, tile.getRow());
					if (wTile.getPiece() != null && getPieceColor(wTile.getPiece()) != turn) {
						canAttack = true;
					}
				}

				if (canAttack) {
					count++;
				}
				
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
				
				boolean canAttack = false;
				if (tile.getRow() == 0) {
					Tile sTile = map.getTile(tile.getColumn(), tile.getRow() + 1);
					if (sTile.getPiece() != null && getPieceColor(sTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getRow() == map.getRows() - 1) {
					Tile nTile = map.getTile(tile.getColumn(), tile.getRow() - 1);
					if (nTile.getPiece() != null && getPieceColor(nTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getColumn() == 0) {
					Tile eTile = map.getTile(tile.getColumn() + 1, tile.getRow());
					if (eTile.getPiece() != null && getPieceColor(eTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else if (tile.getColumn() == map.getColumns() - 1) {
					Tile wTile = map.getTile(tile.getColumn() - 1, tile.getRow());
					if (wTile.getPiece() != null && getPieceColor(wTile.getPiece()) != turn) {
						canAttack = true;
					}
				} else {
					Tile nTile = map.getTile(tile.getColumn(), tile.getRow() - 1);
					if (nTile.getPiece() != null && getPieceColor(nTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile sTile = map.getTile(tile.getColumn(), tile.getRow() + 1);
					if (sTile.getPiece() != null && getPieceColor(sTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile eTile = map.getTile(tile.getColumn() + 1, tile.getRow());
					if (eTile.getPiece() != null && getPieceColor(eTile.getPiece()) != turn) {
						canAttack = true;
					}
					
					Tile wTile = map.getTile(tile.getColumn() - 1, tile.getRow());
					if (wTile.getPiece() != null && getPieceColor(wTile.getPiece()) != turn) {
						canAttack = true;
					}
				}

				if (canAttack) {
					count++;
				}
				
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
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case ATTACK:
			destroyActionMenu();
			// Attack stuff
			return true;
		case LIBERATE:
			destroyActionMenu();
			liberateManager.liberate();
			return true;
		case MOVE:
			destroyActionMenu();
			moveManager.createMoveAction();
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
	
	public void createVictoryImage() {
		if (turn == TeamColor.RED) {
			resourcesManager.getRedVictoryTextureAtlas().load();
			this.victoryImage = new Sprite(0, 0, resourcesManager.getRedVictoryTextureRegion(), resourcesManager.getVbom());
		} else if (turn == TeamColor.BLUE) {
			resourcesManager.getBlueVictoryTextureAtlas().load();
			this.victoryImage = new Sprite(0, 0, resourcesManager.getBlueVictoryTextureRegion(), resourcesManager.getVbom());
		} else {
			this.victoryImage = null;
		}
		
		victoryImage.setScale(2F);
		victoryImage.setPosition(400 - victoryImage.getWidth() / 2, 240 - victoryImage.getHeight() / 2);
		hud.getHud().attachChild(victoryImage);
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

	public AttackManager getAttackManager() {
		return attackManager;
	}

	public void setAttackManager(AttackManager attackManager) {
		this.attackManager = attackManager;
	}

	public LiberateManager getLiberateManager() {
		return liberateManager;
	}

	public void setLiberateManager(LiberateManager liberateManager) {
		this.liberateManager = liberateManager;
	}

	public MoveManager getMoveManager() {
		return moveManager;
	}

	public void setMoveManager(MoveManager moveManager) {
		this.moveManager = moveManager;
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
