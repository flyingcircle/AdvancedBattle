package me.capstone.advancedbattle.scene.scenes;

import me.capstone.advancedbattle.AdvancedBattleActivity;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.BaseScene;
import me.capstone.advancedbattle.scene.SceneManager;
import me.capstone.advancedbattle.scene.SceneManager.SceneType;
import me.capstone.advancedbattle.touch.LevelScroller;
import me.capstone.advancedbattle.touch.TouchDistributor;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class LevelScene extends BaseScene {
	private ResourcesManager resourcesManager;
	
	private static int PADDING = 50;
	
	private LevelScroller level;
	
	private SpriteBackground levelBackground;
	private Sprite levelLeft;
	private Sprite levelRight;
	private Rectangle scrollBar;
	
	private int itemClicked = -1;
	
	private TouchDistributor touchDistributor;
	
	@Override
	public void createScene() {
		this.resourcesManager = ResourcesManager.getInstance();
		createBackground();
		createTouchListeners();
		createLevelBoxes();
		createMusic();
	}
	
	private void createBackground() {
		setBackground(new Background(0, 0, 0, 1));
		this.levelBackground = new SpriteBackground(new Sprite(0, 0, resourcesManager.getBackgroundTextureRegion(), resourcesManager.getVbom()));
		setBackground(levelBackground);
	}
	
	private void createTouchListeners() {
		this.level = new LevelScroller(this);
		this.touchDistributor = new TouchDistributor();
		touchDistributor.addTouchListener(level);
		setOnSceneTouchListener(touchDistributor);
	}
	
	private void createLevelBoxes() {
		int spriteX = PADDING;
		int spriteY = 30;
		
		int width = AdvancedBattleActivity.CAMERA_WIDTH;
		int height = AdvancedBattleActivity.CAMERA_HEIGHT;
						
		for (int item = 0; item < resourcesManager.getColumns().size(); item++) {
			final int itemToLoad = item;
			
			Sprite sprite = new Sprite(spriteX, spriteY, resourcesManager.getColumns().get(item), resourcesManager.getVbom()) {
			
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					itemClicked = itemToLoad;
					return false;
				}
			};
						
			attachChild(sprite);
			registerTouchArea(sprite);
			
			spriteX = (int) (spriteX + 2 *PADDING + sprite.getWidth());
		}
		
		spriteX -= 50;
		
		level.setMaxX(spriteX - width);
		
		float scrollBarSize = width / ((level.getMaxX() + width) / width);
		this.scrollBar = new Rectangle(0, height - 10, scrollBarSize, 10, resourcesManager.getVbom());
		scrollBar.setColor(1, 1, 1);
		attachChild(scrollBar);
		
		this.levelLeft = new Sprite(0, height / 2 - resourcesManager.getMenuLeftTextureRegion().getHeight() / 2, resourcesManager.getMenuLeftTextureRegion(), resourcesManager.getVbom());
		this.levelRight = new Sprite(width - resourcesManager.getMenuRightTextureRegion().getWidth(), height / 2 - resourcesManager.getMenuRightTextureRegion().getHeight() / 2, resourcesManager.getMenuRightTextureRegion(), resourcesManager.getVbom());
		levelLeft.setVisible(false);
		attachChild(levelLeft);		
		attachChild(levelRight);
	}
	
	public void loadLevel(final int levelClicked) {
		if (levelClicked != -1) {
			resourcesManager.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {					
					SceneManager.getInstance().loadGameScene(resourcesManager.getEngine(), itemClicked);
					itemClicked = -1;
				}
			});
		}
	}
	
	private void createMusic() {
		resourcesManager.getLevelMusic().play();
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
		return SceneType.SCENE_LEVEL;
	}
	
	@Override
	public void disposeScene() {
		resourcesManager.getCamera().setCenter(400, 240);
		
		touchDistributor.removeAllTouchListeners();
		
		resourcesManager.unloadLevelResources();
		
		detachSelf();
		dispose();
	}
	
	public SpriteBackground getLevelBackground() {
		return levelBackground;
	}

	public void setLevelBackground(SpriteBackground levelBackground) {
		this.levelBackground = levelBackground;
	}

	public Sprite getMenuLeft() {
		return levelLeft;
	}

	public void setMenuLeft(Sprite menuLeft) {
		this.levelLeft = menuLeft;
	}

	public Sprite getMenuRight() {
		return levelRight;
	}

	public void setMenuRight(Sprite menuRight) {
		this.levelRight = menuRight;
	}

	public Rectangle getScrollBar() {
		return scrollBar;
	}

	public void setScrollBar(Rectangle scrollBar) {
		this.scrollBar = scrollBar;
	}

	public int getItemClicked() {
		return itemClicked;
	}

	public void setItemClicked(int itemClicked) {
		this.itemClicked = itemClicked;
	}

}
