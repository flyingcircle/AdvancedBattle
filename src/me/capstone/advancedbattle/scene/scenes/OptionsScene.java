package me.capstone.advancedbattle.scene.scenes;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;

public class OptionsScene extends BaseScene {

	private ResourcesManager resourcesManager;
	private Scene optionsChildScene;
	
	@Override
	public void createScene() {
		resourcesManager = ResourcesManager.getInstance();
		createBackground();
		createOptionsChildScene();
	}
	
	private void createBackground() {
		setBackground(new Background(Color.BLACK));
	}

	private void createOptionsChildScene() {
		optionsChildScene = new Scene();
		optionsChildScene.setPosition(0,0);
		
		if (!resourcesManager.isMusicOn()) {
			final Sprite off = new Sprite(0, 0, resourcesManager.getMusicOffRegion(), resourcesManager.getVbom());
			final Sprite on = new Sprite(0, 0, resourcesManager.getMusicOnRegion(), resourcesManager.getVbom()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					if (pSceneTouchEvent.isActionUp()) {
						resourcesManager.toggleMusic();
						setVisible(!resourcesManager.isMusicOn());
						off.setVisible(resourcesManager.isMusicOn());
					}
					return true;
				}
			};
			
			off.setVisible(false);
			
			on.setPosition(400 - on.getWidth() / 2, 240 - on.getHeight() / 2);
			off.setPosition(400 - off.getWidth() / 2, 240 - off.getHeight() / 2);
			
			optionsChildScene.attachChild(on);
			optionsChildScene.attachChild(off);
					
			optionsChildScene.registerTouchArea(on);
		} else {
			final Sprite on = new Sprite(0, 0, resourcesManager.getMusicOnRegion(), resourcesManager.getVbom());
			final Sprite off = new Sprite(0, 0, resourcesManager.getMusicOffRegion(), resourcesManager.getVbom()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					if (pSceneTouchEvent.isActionUp()) {
						resourcesManager.toggleMusic();
						setVisible(resourcesManager.isMusicOn());
						on.setVisible(!resourcesManager.isMusicOn());
					}
					return true;
				}
			};
			
			on.setVisible(false);
			
			on.setPosition(400 - on.getWidth() / 2, 240 - on.getHeight() / 2);
			off.setPosition(400 - off.getWidth() / 2, 240 - off.getHeight() / 2);
			
			optionsChildScene.attachChild(on);
			optionsChildScene.attachChild(off);
					
			optionsChildScene.registerTouchArea(off);
		}
		
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
