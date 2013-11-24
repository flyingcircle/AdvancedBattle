package me.capstone.advancedbattle.scene;

import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.scene.scenes.GameScene;
import me.capstone.advancedbattle.scene.scenes.LevelScene;
import me.capstone.advancedbattle.scene.scenes.LoadingScene;
import me.capstone.advancedbattle.scene.scenes.MainMenuScene;
import me.capstone.advancedbattle.scene.scenes.OptionsScene;
import me.capstone.advancedbattle.scene.scenes.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	private static SceneManager instance = new SceneManager();
    
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene optionsScene;
    private BaseScene levelScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
        
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    
    private BaseScene currentScene;
        
    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_LEVEL,
        SCENE_GAME,
        SCENE_LOADING, 
        SCENE_OPTIONS,
    }
    
    public void setScene(BaseScene scene) {
        ResourcesManager.getInstance().getEngine().setScene(scene);
        if (currentScene != null) {
        	currentScene.disposeScene();
        }
        this.currentScene = scene;
        this.currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_OPTIONS:
            	setScene(optionsScene);
            	break;
            case SCENE_LEVEL:
            	setScene(levelScene);
            	break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    	resourcesManager.createSplashGraphics();
	    resourcesManager.createMenuGraphics();
	    resourcesManager.createOptionsGraphics();
	    resourcesManager.createLevelGraphics();
	    resourcesManager.createFonts();
    	
        resourcesManager.loadSplashResources();
        this.splashScene = new SplashScene();
        this.currentScene = splashScene;
        this.currentSceneType = splashScene.getSceneType();
        pOnCreateSceneCallback.onCreateSceneFinished(getSplashScene());
    }
    
    public void loadMenuScene(final Engine mEngine) {
    	this.loadingScene = new LoadingScene();
        setScene(loadingScene);
        
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                resourcesManager.loadMenuResources();
                menuScene = new MainMenuScene();
                setScene(menuScene);
            }
        }));
        
        if (splashScene != null) {
        	this.splashScene = null;
        }
        
        if (optionsScene != null) {
        	optionsScene = null;
        }
        
        if (levelScene != null) {
        	this.levelScene = null;
        }
        
        if (gameScene != null) {
        	this.gameScene = null;
        }
        
        if (loadingScene != null) {
        	loadingScene = null;
        }
        
    }
    
    public void loadOptionsScene(final Engine mEngine){
    	loadingScene = new LoadingScene();
    	setScene(loadingScene);
    	
    	mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                resourcesManager.loadOptionsResources();
                optionsScene = new OptionsScene();
                setScene(optionsScene);
            }
        }));
    	if (splashScene != null) {
        	this.splashScene = null;
        }
        
        if (menuScene != null) {
        	this.menuScene = null;
        }
        
        if (levelScene != null) {
        	this.levelScene = null;
        }
        
        if (gameScene != null) {
        	this.gameScene = null;
        }
        
        if (loadingScene != null) {
        	loadingScene = null;
        }
    }
    
    public void loadLevelScene(final Engine mEngine) {
    	this.loadingScene = new LoadingScene();
        setScene(loadingScene);
        
    	mEngine.registerUpdateHandler(new TimerHandler(0.1F, new ITimerCallback() {
    		@Override
    		public void onTimePassed(final TimerHandler pTimerHandler) {
    			mEngine.unregisterUpdateHandler(pTimerHandler);
    			resourcesManager.loadLevelResources();
    			levelScene = new LevelScene();
    			setScene(levelScene);
    		}
    	}));
    	
    	if (splashScene != null) {
        	this.splashScene = null;
        }
        
        if (menuScene != null) {
        	this.menuScene = null;
        }
        
        if (optionsScene != null) {
        	optionsScene = null;
        }
        
        if (gameScene != null) {
        	this.gameScene = null;
        }
        
        if (loadingScene != null) {
        	loadingScene = null;
        }
        
    }
    
    public void loadGameScene(final Engine mEngine, final int id) {
    	this.loadingScene = new LoadingScene();
        setScene(loadingScene);
        
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                resourcesManager.loadGameResources(id);
                gameScene = new GameScene();
                setScene(gameScene);
            }
        }));
        
        if (splashScene != null) {
        	this.splashScene = null;
        }
        
        if (menuScene != null) {
        	this.menuScene = null;
        }
        
        if (optionsScene != null) {
        	optionsScene = null;
        }
        
        if (levelScene != null) {
        	this.levelScene = null;
        }
        
        if (loadingScene != null) {
        	loadingScene = null;
        }
    }

	public static SceneManager getInstance() {
		return instance;
	}

	public BaseScene getSplashScene() {
		return splashScene;
	}

	public void setSplashScene(BaseScene splashScene) {
		this.splashScene = splashScene;
	}

	public BaseScene getMenuScene() {
		return menuScene;
	}

	public void setMenuScene(BaseScene menuScene) {
		this.menuScene = menuScene;
	}

	public BaseScene getLevelScene() {
		return levelScene;
	}

	public void setLevelScene(BaseScene levelScene) {
		this.levelScene = levelScene;
	}

	public BaseScene getGameScene() {
		return gameScene;
	}

	public void setGameScene(BaseScene gameScene) {
		this.gameScene = gameScene;
	}

	public BaseScene getLoadingScene() {
		return loadingScene;
	}

	public void setLoadingScene(BaseScene loadingScene) {
		this.loadingScene = loadingScene;
	}

	public SceneType getCurrentSceneType() {
		return currentSceneType;
	}

	public void setCurrentSceneType(SceneType currentSceneType) {
		this.currentSceneType = currentSceneType;
	}

	public BaseScene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(BaseScene currentScene) {
		this.currentScene = currentScene;
	}
	
}