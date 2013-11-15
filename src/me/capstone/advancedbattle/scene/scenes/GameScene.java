package me.capstone.advancedbattle.scene.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.util.HorizontalAlign;

import android.widget.Toast;

import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.touchhandlers.MapScroller;
import me.capstone.advancedbattle.touchhandlers.PinchZoomDetector;
import me.capstone.advancedbattle.touchhandlers.TouchDistributor;

public class GameScene extends BaseScene {
	private HUD hud;
	private Text text;
	private Entity rectangleGroup;

    @Override
    public void createScene() {
    	createBackground();
    	createHUD();
    	setCamera();
    	createTouchListeners();
    }
    
    private void createBackground() {
    	for (TMXLayer layer : getResourcesManager().getGameMap().getTMXLayers()) {
    		attachChild(layer);
    	}
    }
    
    private void createHUD() {
    	this.hud = new HUD();
    	
    	this.text = new Text(650, 10, getResourcesManager().getFont(), "(00, 00)", new TextOptions(HorizontalAlign.RIGHT), getResourcesManager().getVbom());
    	text.setText(getText(0, 0));   	
    	text.setScale(1.3F);
    	//hud.attachChild(text);
    	
    	this.rectangleGroup = new Entity(650, 240);
    	Rectangle rectangle = new Rectangle(0, 0, 125, 215, getResourcesManager().getVbom());
    	rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
    	rectangleGroup.attachChild(rectangle);
    	hud.attachChild(rectangleGroup);
    	
    	getResourcesManager().getCamera().setHUD(hud);
    }
    
    private void setCamera() {
		getResourcesManager().getCamera().setBoundsEnabled(true);
		TMXLayer layer = getResourcesManager().getGameMap().getTMXLayers().get(0);
		getResourcesManager().getCamera().setBounds(0, 0, layer.getWidth(), layer.getHeight());
    }
    
    private void createTouchListeners() {
    	MapScroller mapScroller = new MapScroller(getResourcesManager().getCamera());
    	PinchZoomDetector zoom = new PinchZoomDetector(getResourcesManager().getCamera(), mapScroller);
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(zoom);
        touchDistributor.addTouchListener(mapScroller);
        setOnSceneTouchListener(touchDistributor);
    }
    
    public String getText(int row, int column) {
		return "(" + row + ", " + column + ")";
	}

    @Override
    public void onBackKeyPressed() {
    	SceneManager.getInstance().loadMenuScene(getResourcesManager().getEngine());
    }
    
    @Override
    public void onMenuKeyPressed() {
    	Toast.makeText(getResourcesManager().getActivity().getApplicationContext(), "Menu button pressed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	getResourcesManager().getCamera().setHUD(null);
    	getResourcesManager().getCamera().setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Entity getRectangleGroup() {
		return rectangleGroup;
	}

	public void setRectangleGroup(Entity rectangleGroup) {
		this.rectangleGroup = rectangleGroup;
	}
}