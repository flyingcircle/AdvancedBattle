package me.capstone.advancedbattle.scene.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {
	private MenuScene menuChildScene;
	
	public final int MENU_PLAY = 0;
	public final int MENU_OPTIONS = 1;

	@Override
	public void createScene() {
		createBackground();	
		createMenuChildScene();
	}
	
	private void createBackground() {
	    attachChild(new Sprite(0, 0, getResourcesManager().getMenuBackgroundRegion(), getResourcesManager().getVbom()) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	
	private void createMenuChildScene() {
	    this.menuChildScene = new MenuScene(getResourcesManager().getCamera());
	    menuChildScene.setPosition(0, 0);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, getResourcesManager().getPlayRegion(), getResourcesManager().getVbom()), 1.2f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, getResourcesManager().getOptionsRegion(), getResourcesManager().getVbom()), 1.2f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY());
	    optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY());
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(getMenuChildScene());
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}
	
	@Override
	public void onMenuKeyPressed() {
		return;
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
        case MENU_PLAY:
        	SceneManager.getInstance().loadGameScene(getResourcesManager().getEngine());
            return true;
        case MENU_OPTIONS:
            return true;
        default:
            return false;
        }
	}

	public MenuScene getMenuChildScene() {
		return menuChildScene;
	}

	public void setMenuChildScene(MenuScene menuChildScene) {
		this.menuChildScene = menuChildScene;
	}

}
