package me.capstone.advancedbattle.manager.managers;

import org.andengine.engine.camera.hud.HUD;
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
import me.capstone.advancedbattle.manager.hud.GameHUD;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.tile.Tile;

public class BuyMenuManager implements IOnMenuItemClickListener {
	
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	private GameManager game;
	private Entity buyMenu;
	private MenuScene buyMenuOptions;
	private Tile currentTile;
	
	public BuyMenuManager(GameManager game) {
		this.game = game;
	}
	
	public void createBuyMenu(){
		buyMenu = new Entity(0, 0);
		int currentFunds = game.getCurrentFunds();
		currentTile  = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		
		Rectangle backgroundRect = new Rectangle(0, 0, 800, 480, resourcesManager.getVbom());
	    backgroundRect.setColor(1.0F, 1.0F, 1.0F, 0.75F);
	    buyMenu.attachChild(backgroundRect);
	    
	    Rectangle menuRect = new Rectangle(240, 240, 320, 10, resourcesManager.getVbom());
	    menuRect.setColor(0.0F, 0.0F, 0.0F, 0.75F);
	    
	    buyMenuOptions = new MenuScene(resourcesManager.getCamera());
	    buyMenuOptions.setPosition(0, 0);
	    
	    if(currentFunds >= PieceTile.RED_INFANTRY.getCost()){
	    	final IMenuItem infantryMenuItem = new ColorMenuItemDecorator(new TextMenuItem(PieceTile.RED_INFANTRY.getId(), resourcesManager.getFont(), "Infantry $" + Integer.toString(PieceTile.RED_INFANTRY.getCost()) , resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(infantryMenuItem);
	    }
    	
	    if(currentFunds >= PieceTile.RED_TANK.getCost()) {
	    	final IMenuItem tankMenuItem = new ColorMenuItemDecorator(new TextMenuItem(PieceTile.RED_TANK.getId(), resourcesManager.getFont(), "Tank $" + Integer.toString(PieceTile.RED_TANK.getCost()), resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(tankMenuItem);
	    }
	    
	    if(currentFunds >= PieceTile.RED_RECON.getCost()){
	    	final IMenuItem reconMenuItem = new ColorMenuItemDecorator(new TextMenuItem(PieceTile.RED_RECON.getId(), resourcesManager.getFont(), "Recon $" + Integer.toString(PieceTile.RED_RECON.getCost()), resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(reconMenuItem);
	    }
    	
	    if(currentFunds >= PieceTile.RED_MECH.getCost()){
	    	final IMenuItem mechMenuItem = new ColorMenuItemDecorator(new TextMenuItem(PieceTile.RED_MECH.getId(), resourcesManager.getFont(), "Mech $" + Integer.toString(PieceTile.RED_MECH.getCost()), resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
	    	buyMenuOptions.addMenuItem(mechMenuItem);
	    }
	    
	    buyMenuOptions.buildAnimations();
	    buyMenuOptions.setBackgroundEnabled(false);
	    
	    buyMenu.attachChild(menuRect);
	    
	    game.getHud().getHud().attachChild(buyMenu);
	    game.getHud().getHud().setChildScene(buyMenuOptions);
	    
	    buyMenuOptions.setOnMenuItemClickListener(this);
	}

	private void destroyBuyMenu(){
		game.getHud().getHud().detachChild(buyMenu);
		game.getHud().getHud().detachChild(buyMenuOptions);
		buyMenuOptions.clearMenuItems();
		//buyMenuOptions.clearTouchAreas();
		//buyMenuOptions.closeMenuScene();
		buyMenuOptions.dispose();
		this.buyMenuOptions = null;
		buyMenu.dispose();
		this.buyMenu = null;
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		int subtractCost = 0;
		if(pMenuItem.getID() == PieceTile.RED_INFANTRY.getId()){
			subtractCost = PieceTile.RED_INFANTRY.getCost();
			currentTile.setPiece( currentTile.createPieceByID( PieceTile.RED_INFANTRY.getId()));
		}
		else if(pMenuItem.getID() == PieceTile.RED_TANK.getId()){
			subtractCost = PieceTile.RED_TANK.getCost();
			currentTile.setPiece( currentTile.createPieceByID( PieceTile.RED_TANK.getId()));
		}
		else if(pMenuItem.getID() == PieceTile.RED_RECON.getId()){
			subtractCost = PieceTile.RED_RECON.getCost();
			currentTile.setPiece( currentTile.createPieceByID( PieceTile.RED_RECON.getId()));
		}
		else if(pMenuItem.getID() == PieceTile.RED_MECH.getId()){
			subtractCost = PieceTile.RED_MECH.getCost();
			currentTile.setPiece( currentTile.createPieceByID( PieceTile.RED_MECH.getId()));
		}
		game.setCurrentFunds(game.getCurrentFunds() - subtractCost);
		TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
		TMXTile pieceTile = pieceLayer.getTMXTile(currentTile.getColumn(), currentTile.getRow());
		pieceTile.setGlobalTileID(resourcesManager.getGameMap(), PieceTile.RED_INFANTRY.getId());
		pieceLayer.setIndex(pieceTile.getTileRow() * resourcesManager.getGameMap().getTileColumns() + pieceTile.getTileColumn());
		pieceLayer.drawWithoutChecks(pieceTile.getTextureRegion(), pieceTile.getTileX(), pieceTile.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
		pieceLayer.submit();
		game.getHud().updateHUD();
		destroyBuyMenu();
		return false;
	}

}
