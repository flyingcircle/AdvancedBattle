package me.capstone.advancedbattle.manager.managers;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.util.Util;

public class BuyMenuManager implements IOnMenuItemClickListener {	
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	private Entity buyMenu;
	
	private boolean hasBuyMenu = false;	
	private MenuScene buyMenuOptions;
	
	public BuyMenuManager(GameManager game) {
		this.game = game;
	}
	
	public void createBuyMenu() {
		int items = Util.getBuyAmount();
		
		this.buyMenu = new Entity(0, 0);
		
		Rectangle backgroundRect = new Rectangle(0, 0, 800, 480, resourcesManager.getVbom());
	    backgroundRect.setColor(1.0F, 1.0F, 1.0F, 0.75F);
	    buyMenu.attachChild(backgroundRect);
	    
	    int size = items * 50;
	    Rectangle menuRect = new Rectangle(140, 240 - size / 2, 520, size + 10, resourcesManager.getVbom());
	    menuRect.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    
	    this.buyMenuOptions = new MenuScene(resourcesManager.getCamera());
	    buyMenuOptions.setPosition(0, 0);
	    
	    if (items >= 2) {
	    	final IMenuItem infantryMenuItem = new ColorMenuItemDecorator(new TextMenuItem(Util.getTurnInfantry().getId(), resourcesManager.getFont(), Util.getTurnInfantry().getName() + " - " + Integer.toString(Util.getTurnInfantry().getCost()) , resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(infantryMenuItem);
	    }
	    
	    if (items >= 3) {
	    	final IMenuItem mechMenuItem = new ColorMenuItemDecorator(new TextMenuItem(Util.getTurnMech().getId(), resourcesManager.getFont(), Util.getTurnMech().getName() + " - " + Integer.toString(Util.getTurnMech().getCost()) , resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(mechMenuItem);
	    }
    	
	    if (items >= 4) {
	    	final IMenuItem reconMenuItem = new ColorMenuItemDecorator(new TextMenuItem(Util.getTurnRecon().getId(), resourcesManager.getFont(), Util.getTurnRecon().getName() + " - " + Integer.toString(Util.getTurnRecon().getCost()), resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(reconMenuItem);
	    }
	    
	    if (items >= 5) {
	    	final IMenuItem tankMenuItem = new ColorMenuItemDecorator(new TextMenuItem(Util.getTurnTank().getId(), resourcesManager.getFont(), Util.getTurnTank().getName() + " - " + Integer.toString(Util.getTurnTank().getCost()), resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(tankMenuItem);
	    }
	    
	    final IMenuItem cancelMenuItem = new ColorMenuItemDecorator(new TextMenuItem(0, resourcesManager.getFont(), "Cancel", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    buyMenuOptions.addMenuItem(cancelMenuItem);
	    
	    buyMenuOptions.buildAnimations();
	    buyMenuOptions.setBackgroundEnabled(false);
	    
	    buyMenu.attachChild(menuRect);
	    
	    game.getHud().getHud().attachChild(buyMenu);
	    game.getHud().getHud().setChildScene(buyMenuOptions);
	    
	    buyMenuOptions.setOnMenuItemClickListener(this);
	}

	public void destroyBuyMenu(){
		game.getHud().getHud().detachChild(buyMenu);
		game.getHud().getHud().detachChild(buyMenuOptions);
		
		buyMenuOptions.clearMenuItems();
		buyMenuOptions.clearTouchAreas();
		buyMenuOptions.closeMenuScene();
		buyMenuOptions.dispose();
		this.buyMenuOptions = null;
		
		buyMenu.dispose();
		this.buyMenu = null;
		
		this.hasBuyMenu = false;
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		int subtractCost = 0;
		int id = pMenuItem.getID();
		if (id == Util.getTurnInfantry().getId()) {
			PieceTile infantry = Util.getTurnInfantry();
			subtractCost = infantry.getCost();
			createPiece(infantry);
			destroyBuyMenu();
		} else if (id == Util.getTurnMech().getId()) {
			PieceTile mech = Util.getTurnMech();
			subtractCost = mech.getCost();
			createPiece(mech);
			destroyBuyMenu();
		} else if (id == Util.getTurnRecon().getId()) {
			PieceTile recon = Util.getTurnRecon();
			subtractCost = recon.getCost();
			createPiece(recon);
			destroyBuyMenu();
		} else if (id == Util.getTurnTank().getId()) {
			PieceTile tank = Util.getTurnTank();
			subtractCost = tank.getCost();
			createPiece(tank);
			destroyBuyMenu();
		} else if (id == 0) {
			destroyBuyMenu();
		} else {
			return false;
		}
		
		Util.setCurrentFunds(Util.getCurrentFunds() - subtractCost);
		
		game.getHud().updateHUD();
		return true;
	}
	
	private void createPiece(PieceTile pieceTile) {
		TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
		TMXTile tile = pieceLayer.getTMXTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		tile.setGlobalTileID(resourcesManager.getGameMap(), pieceTile.getId());
		pieceLayer.setIndex(tile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + tile.getTileColumn());
		pieceLayer.drawWithoutChecks(tile.getTextureRegion(), tile.getTileX(), tile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
		pieceLayer.submit();
		
		Tile newTile = game.getMap().getTile(tile.getTileColumn(), tile.getTileRow());
		newTile.setPieceTileID(pieceTile.getId());
		newTile.setPiece(newTile.createPieceByID(newTile.getPieceTileID()));
		
		game.disable(newTile);
	}

	public Entity getBuyMenu() {
		return buyMenu;
	}

	public void setBuyMenu(Entity buyMenu) {
		this.buyMenu = buyMenu;
	}

	public boolean hasBuyMenu() {
		return hasBuyMenu;
	}

	public void setHasBuyMenu(boolean hasBuyMenu) {
		this.hasBuyMenu = hasBuyMenu;
	}

	public MenuScene getBuyMenuOptions() {
		return buyMenuOptions;
	}

	public void setBuyMenuOptions(MenuScene buyMenuOptions) {
		this.buyMenuOptions = buyMenuOptions;
	}

}
