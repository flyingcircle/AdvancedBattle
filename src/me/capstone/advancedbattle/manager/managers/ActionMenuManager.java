package me.capstone.advancedbattle.manager.managers;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.util.color.Color;

public class ActionMenuManager implements IOnMenuItemClickListener {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	// Action Menu
	private Entity actionMenu;

	private boolean hasActionMenu = false;

	private MenuScene actionMenuOptions;
	private static final int ATTACK = 0;
	private static final int BUY = 1;
	private static final int LIBERATE = 2;
	private static final int MOVE = 3;
	private static final int CANCEL = 4;
	private static final int END_TURN = 5;
	
	public ActionMenuManager(GameManager game) {
		this.game = game;
	}
	
	public void createActionMenu(int items, boolean isFactory, boolean canAttack, boolean canLiberate, boolean canMove) {	
		this.actionMenu = new Entity(0, 0);
		
		Rectangle backgroundRect = new Rectangle(0, 0, 800, 480, resourcesManager.getVbom());
	    backgroundRect.setColor(1.0F, 1.0F, 1.0F, 0.75F);
	    actionMenu.attachChild(backgroundRect);
	    
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
	    		if (canMove) {
	    			final IMenuItem moveMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MOVE, resourcesManager.getFont(), "Move", resourcesManager.getVbom()), new Color(1.0f, 1.0f, 1.0f, 0.75f), new Color(0.7f, 0.7f, 0.7f, 0.75f));
		    	    actionMenuOptions.addMenuItem(moveMenuItem);	
	    		}
	    	    
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
	    
	    
	    game.getHud().getHud().attachChild(actionMenu);
	    game.getHud().getHud().setChildScene(actionMenuOptions);
	    
	    actionMenuOptions.setOnMenuItemClickListener(this);
	    
	    this.hasActionMenu = true;
	}
	
	public void destroyActionMenu() {
		game.getHud().getHud().detachChild(actionMenu);
		game.getHud().getHud().detachChild(actionMenuOptions);
		
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
			game.getAttackManager().createAttackAction();
			return true;
		case BUY:
			destroyActionMenu();
			game.getBuyMenuManager().createBuyMenu();
			return true;
		case LIBERATE:
			destroyActionMenu();
			game.getLiberateManager().liberate();
			return true;
		case MOVE:
			destroyActionMenu();
			game.getMoveManager().createMoveAction();
			return true;
		case CANCEL:
			destroyActionMenu();
			return true;
		case END_TURN:
			destroyActionMenu();
			game.getEndTurnManager().endTurn();
			return true;
		default:
			return false;
		}
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

}
