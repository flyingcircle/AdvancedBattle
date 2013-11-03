package me.capstone.advancedbattle;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.entity.Entity;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;

import me.capstone.advancedbattle.SceneManager.SceneType;
import me.capstone.advancedbattle.touchhandlers.MapScroller;
import me.capstone.advancedbattle.touchhandlers.PinchZoomDetector;
import me.capstone.advancedbattle.touchhandlers.TouchDistributor;

public class GameScene extends BaseScene
{
	
	private TMXLayer landscapeLayer;
	private TMXLayer cursorLayer;
	private ZoomCamera camera;
	
	private static final int LANDSCAPE = 0;
	private static final int PIECES = 1;
	private static final int CURSOR = 2;
	
    @Override
    public void createScene()
    {
    	camera = resourcesManager.camera;
    	createBackground();
    	setCamera();
    	//setEntities();
    	createTouchListeners();
    }
    
    private void setEntities(){
//    	final ArrayList<TMXObjectGroup> groups = resourcesManager.game_background_tmx.getTMXObjectGroups();
//    	ArrayList<TMXObject> objects;
//    	
//    	for(final TMXObjectGroup group: groups){
//    		objects = group.getTMXObjects();
//    		for(final TMXObject object: objects){
//    			String type = "";
//    			if(group.getTMXObjectGroupProperties().size() > 0) {
//    				type = group.getTMXObjectGroupProperties().get(0).getValue();
//    			}
//    			
//    			HashMap<String, String> properties = new HashMap<String, String> ();
//    			int size = object.getTMXObjectProperties().size();
//    			for(int i=0;i<size;i++){
//    				properties.put(object.getTMXObjectProperties().get(i).getName(), object.getTMXObjectProperties().get(i).getValue());
//    			}
//    			
//    			if(properties.containsKey("type")){
//    				type = properties.get("type");
//    			}
//    			
//    			Entities.addEntity(resourcesManager.activity, this, object.getX(), object.getY(), 
//    					object.getWidth(), object.getHeight(), type, properties);
//    		}
//    	}
    	cursorLayer = resourcesManager.game_background_tmx.getTMXLayers().get(1);
    	attachChild(cursorLayer);
    }
    
    private void setCamera()
    {
		camera.setBoundsEnabled(true);
		camera.setBounds(0, 0, landscapeLayer.getWidth(), landscapeLayer.getHeight());
    }
    
    private void createBackground()
    {
    	landscapeLayer = resourcesManager.game_background_tmx.getTMXLayers().get(0);
    	attachChild(landscapeLayer); //add landscape layer
    }
    
    private void createTouchListeners()
    {
    	MapScroller mapScroller = new MapScroller(camera);
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(new PinchZoomDetector(camera, mapScroller));
        touchDistributor.addTouchListener(mapScroller);
        this.setOnSceneTouchListener(touchDistributor);
    }

    @Override
    public void onBackKeyPressed()
    {
    	SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
    	camera.setHUD(null);
        camera.setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }
}