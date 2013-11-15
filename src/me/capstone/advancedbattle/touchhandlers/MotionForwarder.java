package me.capstone.advancedbattle.touchhandlers;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
 
import android.view.GestureDetector;
 
public class MotionForwarder implements IOnSceneTouchListener {
	private GestureDetector detector;

	public MotionForwarder(GestureDetector detector) {
		this.detector = detector;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		detector.onTouchEvent(pSceneTouchEvent.getMotionEvent());
		return false;
	}
}