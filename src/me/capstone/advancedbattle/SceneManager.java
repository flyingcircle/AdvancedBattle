package me.capstone.advancedbattle;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {
	private static SceneManager instance = new SceneManager();
    
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
        
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    
    private BaseScene currentScene;
    
    private Engine engine = ResourcesManager.getInstance().getEngine();
    
    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
    
    public void setScene(BaseScene scene) {
        getEngine().setScene(scene);
        setCurrentScene(scene);
        setCurrentSceneType(scene.getSceneType());
    }
    
    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(getMenuScene());
                break;
            case SCENE_GAME:
                setScene(getGameScene());
                break;
            case SCENE_SPLASH:
                setScene(getSplashScene());
                break;
            case SCENE_LOADING:
                setScene(getLoadingScene());
                break;
            default:
                break;
        }
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        setSplashScene(new SplashScene());
        setCurrentScene(getSplashScene());
        pOnCreateSceneCallback.onCreateSceneFinished(getSplashScene());
    }
    
    public void createMenuScene() {
        ResourcesManager.getInstance().loadMenuResources();
        setMenuScene(new MainMenuScene());
        setLoadingScene(new LoadingScene());
        SceneManager.getInstance().setScene(getMenuScene());
        disposeSplashScene();
    }

    
    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        getSplashScene().disposeScene();
        setSplashScene(null);
    }
    
    public void loadGameScene(final Engine mEngine) {
        setScene(getLoadingScene());
        ResourcesManager.getInstance().unloadMenuTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadGameResources();
                setGameScene(new GameScene());
                setScene(getGameScene());
            }
        }));
    }
    
    public void loadMenuScene(final Engine mEngine) {
        setScene(getLoadingScene());
        getGameScene().disposeScene();
        ResourcesManager.getInstance().unloadGameTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMenuTextures();
                setScene(getMenuScene());
            }
        }));
    }

	public static SceneManager getInstance() {
		return instance;
	}

	public static void setInstance(SceneManager instance) {
		SceneManager.instance = instance;
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

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}