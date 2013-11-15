package me.capstone.advancedbattle.touch;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
import android.view.MotionEvent;
 
public class PinchZoomDetector implements IOnSceneTouchListener {
	private float lastSpacing = 0;
	private ZoomCamera cam;
	private MapScroller scroller;

	public PinchZoomDetector(ZoomCamera cam, MapScroller scroller) {
		this.cam = cam;
		this.scroller = scroller;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		int action = pSceneTouchEvent.getAction();
		if (action == MotionEvent.ACTION_UP) {
			this.lastSpacing = 0;
			return false;
		}
		if (pSceneTouchEvent.getMotionEvent().getPointerCount() == 2) {
			handleMultitouchGesture(pSceneTouchEvent.getMotionEvent());
			return true;
		}
		return false;
	}

	private void handleMultitouchGesture(MotionEvent evt) {
		scroller.cancelCurScrolling();
		final float curSpacing = spacing(evt);
		if (lastSpacing > 0) {
			cam.setZoomFactor(cam.getZoomFactor() * (curSpacing / lastSpacing));
			if (cam.getZoomFactor() > 2) {
				cam.setZoomFactor(2);
			}
			if (cam.getZoomFactor() < 1) {
				cam.setZoomFactor(1);
			}
		}
		this.lastSpacing = curSpacing;
	}

	private float spacing(MotionEvent evt) {
		float x = evt.getX(0) - evt.getX(1);
		float y = evt.getY(0) - evt.getY(1);
		return (float) Math.sqrt(x * x + y * y);
	}

	public float getLastSpacing() {
		return lastSpacing;
	}

	public void setLastSpacing(float lastSpacing) {
		this.lastSpacing = lastSpacing;
	}

	public ZoomCamera getCam() {
		return cam;
	}

	public void setCam(ZoomCamera cam) {
		this.cam = cam;
	}

	public MapScroller getScroller() {
		return scroller;
	}

	public void setScroller(MapScroller scroller) {
		this.scroller = scroller;
	}
}
