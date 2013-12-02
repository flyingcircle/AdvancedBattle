package me.capstone.advancedbattle.scene.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;

public class OptionsScene extends BaseScene implements IOnMenuItemClickListener{

	private ResourcesManager resourcesManager;
	private MenuScene optionsChildScene;
	private final int MUSIC_OPTION = 0;
	
	@Override
	public void createScene() {
		resourcesManager = ResourcesManager.getInstance();
		createBackground();
		createOptionsChildScene();
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()){
		case MUSIC_OPTION:
			//set music option off
			return true;
		default:
			return false;
		}
	}
	
	private void createBackground() {
		setBackground(new Background(Color.BLACK));
	}

	private void createOptionsChildScene() {
		optionsChildScene = new MenuScene(resourcesManager.getCamera());
		optionsChildScene.setPosition(0,0);
		
		final IMenuItem musicOption = new ScaleMenuItemDecorator(new SpriteMenuItem(MUSIC_OPTION, resourcesManager.getMusicOptionRegion(), resourcesManager.getVbom()), 1.2f, 1);
		
		optionsChildScene.addMenuItem(musicOption);
		optionsChildScene.buildAnimations();
		optionsChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(optionsChildScene);
	}
	
	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(resourcesManager.getEngine());
	}

	@Override
	public void onMenuKeyPressed() {
		return;
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_OPTIONS;
	}

	@Override
	public void disposeScene(){
		resourcesManager.unloadOptionsResources();
		detachSelf();
		dispose();
	}

}
