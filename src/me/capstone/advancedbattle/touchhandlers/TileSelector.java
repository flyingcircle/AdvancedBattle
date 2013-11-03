package me.capstone.advancedbattle.touchhandlers;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.Constants;

import android.view.MotionEvent;

public class TileSelector implements IOnSceneTouchListener, IUpdateHandler
{
	TMXLayer baseLayer;
	
	TileSelector(TMXLayer baseLayer){
		this.baseLayer = baseLayer;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			final TMXTile targetTile = baseLayer.getTMXTileAt(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
		}
		return false;
	}
	
}
