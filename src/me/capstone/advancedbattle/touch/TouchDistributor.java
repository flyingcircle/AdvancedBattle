package me.capstone.advancedbattle.touch;

import java.util.ArrayList;
 
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
 
public class TouchDistributor implements IOnSceneTouchListener {

	private ArrayList<IOnSceneTouchListener> touchListeners;

	public TouchDistributor() {
		ArrayList<IOnSceneTouchListener> touchListeners = new ArrayList<IOnSceneTouchListener>();
		this.touchListeners = touchListeners;
	}

	public void addTouchListener(IOnSceneTouchListener IOsceneTouchListener) {
		touchListeners.add(IOsceneTouchListener);
	}

	public void removeTouchListener(IOnSceneTouchListener IOsceneTouchListener) {
		touchListeners.remove(IOsceneTouchListener);
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		boolean error = false;
		for (int i = 0; i < touchListeners.size(); i++) {
			IOnSceneTouchListener touch = touchListeners.get(i);
			if (!touch.onSceneTouchEvent(pScene, pSceneTouchEvent)) {
				error = true;
			}
		}
		if (error) {
			return false;
		}
		return true;
	}
}