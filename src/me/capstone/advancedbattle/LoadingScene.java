package me.capstone.advancedbattle;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.SceneManager.SceneType;

public class LoadingScene extends BaseScene {
	
    @Override
    public void createScene() {
        setBackground(new Background(Color.WHITE));
        Text text = new Text(350, 200, getResourcesManager().getFont(), "Loading...", getResourcesManager().getVbom());
        text.setScale(1.5F);
        attachChild(text);
    }

    @Override
    public void onBackKeyPressed() {
        return;
    }
    
    @Override
    public void onMenuKeyPressed() {
    	return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene() {

    }
}