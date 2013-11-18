package me.capstone.advancedbattle.scene.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;

import android.widget.Toast;

import me.capstone.advancedbattle.map.Map;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.touch.CursorSelector;
import me.capstone.advancedbattle.touch.MapScroller;
import me.capstone.advancedbattle.touch.PinchZoomDetector;
import me.capstone.advancedbattle.touch.TouchDistributor;

public class GameScene extends BaseScene {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private Map map;
	private HUD hud;
	private Entity rectangleGroup;
	private Sprite terrainSprite;
	private Sprite structureSprite;
	private Sprite pieceSprite;

    @Override
    public void createScene() {
    	createBackground();
    	createMap();
    	createHUD();
    	setCamera();
    	createTouchListeners();
    }
    
    private void createBackground() {
    	for (TMXLayer layer : resourcesManager.getGameMap().getTMXLayers()) {
    		attachChild(layer);
    	}
    }
    
    private void createMap() {
    	this.map = new Map(resourcesManager.getGameMap().getTileColumns(), resourcesManager.getGameMap().getTileRows());
    	Tile[][] arrayMap = map.getMap();
    	
    	TMXLayer terrainLayer = resourcesManager.getGameMap().getTMXLayers().get(0);
    	TMXLayer structureLayer = resourcesManager.getGameMap().getTMXLayers().get(1);
    	TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
    	    	
    	for (int i = 0; i < map.getColumns(); i++) {
    		for (int j = 0; j < map.getRows(); j++) {
    			arrayMap[i][j] = new Tile(terrainLayer.getTMXTile(i, j).getGlobalTileID(), structureLayer.getTMXTile(i, j).getGlobalTileID(), pieceLayer.getTMXTile(i, j).getGlobalTileID());
    		}
    	}
    	
    	map.setMap(arrayMap);
    }
    
    private void createHUD() {
    	this.hud = new HUD();
    	
    	this.rectangleGroup = new Entity(650, 240);
    	Rectangle rectangle = new Rectangle(0, 0, 124, 214, resourcesManager.getVbom());
    	rectangle.setColor(0.0F, 0.0F, 0.0F, 0.75F);
    	rectangleGroup.attachChild(rectangle);
    	
    	Tile tile = map.getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
    	this.terrainSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getTerrainTileID()), resourcesManager.getVbom());
    	this.structureSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getStructureTileID()), resourcesManager.getVbom());
    	this.pieceSprite = new Sprite(46, 30, resourcesManager.getGameMap().getTextureRegionFromGlobalTileID(tile.getPieceTileID()), resourcesManager.getVbom());
    	terrainSprite.setScale(1.5F);
    	structureSprite.setScale(1.5F);
    	pieceSprite.setScale(1.5F);
    	rectangleGroup.attachChild(terrainSprite);
    	rectangleGroup.attachChild(structureSprite);
    	rectangleGroup.attachChild(pieceSprite);
    	
    	hud.attachChild(rectangleGroup);
    	getResourcesManager().getCamera().setHUD(hud);
    }
    
    private void setCamera() {
		resourcesManager.getCamera().setBoundsEnabled(true);
		TMXLayer layer = resourcesManager.getGameMap().getTMXLayers().get(0);
		resourcesManager.getCamera().setBounds(0, 0, layer.getWidth(), layer.getHeight());
    }
    
    private void createTouchListeners() {
    	MapScroller mapScroller = new MapScroller(resourcesManager.getCamera());
    	PinchZoomDetector zoom = new PinchZoomDetector(resourcesManager.getCamera(), mapScroller);
    	CursorSelector cursor = new CursorSelector(resourcesManager.getCamera());
        TouchDistributor touchDistributor = new TouchDistributor();
        touchDistributor.addTouchListener(zoom);
        touchDistributor.addTouchListener(mapScroller);
        touchDistributor.addTouchListener(cursor);
        setOnSceneTouchListener(touchDistributor);
    }
    
    public String getText(int row, int column) {
		return "(" + row + ", " + column + ")";
	}

    @Override
    public void onBackKeyPressed() {
    	SceneManager.getInstance().loadMenuScene(resourcesManager.getEngine());
    }
    
    @Override
    public void onMenuKeyPressed() {
    	Toast.makeText(resourcesManager.getActivity().getApplicationContext(), "Menu button pressed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	resourcesManager.getCamera().setHUD(null);
    	resourcesManager.getCamera().setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public Entity getRectangleGroup() {
		return rectangleGroup;
	}

	public void setRectangleGroup(Entity rectangleGroup) {
		this.rectangleGroup = rectangleGroup;
	}

	public Sprite getTerrainSprite() {
		return terrainSprite;
	}

	public void setTerrainSprite(Sprite terrainSprite) {
		this.terrainSprite = terrainSprite;
	}

	public Sprite getStructureSprite() {
		return structureSprite;
	}

	public void setStructureSprite(Sprite structureSprite) {
		this.structureSprite = structureSprite;
	}

	public Sprite getPieceSprite() {
		return pieceSprite;
	}

	public void setPieceSprite(Sprite pieceSprite) {
		this.pieceSprite = pieceSprite;
	}
}