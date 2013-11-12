package me.capstone.advancedbattle.touchhandlers;
import me.capstone.advancedbattle.ResourcesManager;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
 
import android.view.MotionEvent;
 
public class MapScroller implements IOnSceneTouchListener, IUpdateHandler {
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
        private ZoomCamera camera;
        private boolean touchDown = false;
        private float speedX;
        private float speedY;
        private float lastX;
        private float lastY;
       
        public MapScroller(ZoomCamera camera)
        {
                this.camera = camera;
        }
       
        @Override
        public boolean onSceneTouchEvent(Scene pScene, TouchEvent touchEvent)
        {
                MotionEvent evt = touchEvent.getMotionEvent();
               
                if(evt.getAction() == MotionEvent.ACTION_DOWN)
                {
                        this.touchDown = true;
                        this.lastX = evt.getRawX();
                        this.lastY = evt.getRawY();    
                        this.speedX = 0;
                        this.speedY = 0;
                }
                if(this.touchDown)
                {
                	this.camera.setCenter(camera.getCenterX() + (lastX - evt.getRawX()) / this.camera.getZoomFactor(), camera.getCenterY() + (lastY - evt.getRawY()) / this.camera.getZoomFactor());
                    this.lastX = evt.getRawX();
                    this.lastY = evt.getRawY();
                    
                    System.out.println(camera.getZoomFactor());
                    System.out.println(this.lastX);
                    System.out.println(this.lastY);
                    
                    // Get the layer for the cursor
                    TMXLayer layer = resourcesManager.game_background_tmx.getTMXLayers().get(3);
                    // Get the tile of where the cursor currently is
                	TMXTile tile = layer.getTMXTile(resourcesManager.cursorColumn, resourcesManager.cursorRow);
                	// Set the id of that tile to null (id of 97 is sort of hardcoded in right now from the XML file; this may change) 
                	tile.setGlobalTileID(resourcesManager.game_background_tmx, 97);
                	// Draw the null
                	layer.draw(tile.getTextureRegion(), tile.getTileX(), tile.getTileY(), tile.getTileWidth(), tile.getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
                	// Get the tile that was clicked
                	tile = layer.getTMXTileAt(this.lastX, this.lastY);
                	// Set the new cursor column
                	this.resourcesManager.cursorColumn = tile.getTileColumn();
                	// Set the new cursor row
                    this.resourcesManager.cursorRow = tile.getTileRow();
                    // Set the global id of the clicked tile to the cursor (id of 96 is sort of hardcoded in right now from the XML file; this may change)
                    tile.setGlobalTileID(resourcesManager.game_background_tmx, 96);
                    // Draw the cursor at the new spot
                	layer.draw(tile.getTextureRegion(), tile.getTileX(), tile.getTileY(), tile.getTileWidth(), tile.getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
                	// Submit
                	layer.submit();
                }
                if(evt.getAction() == MotionEvent.ACTION_UP)
                {
                        this.touchDown = false;
                }
                return false;
        }
 
 
        @Override
        public void onUpdate(float pSecondsElapsed)
        {
                if(!this.touchDown && (this.speedX != 0 || this.speedY != 0))
                {
                        //Log.v("AndEngine", "SpeedX: " + String.valueOf(this.speedX) + " SpeedY: " + String.valueOf(this.speedY));
                        this.camera.setCenter(camera.getCenterX() - speedX * pSecondsElapsed / this.camera.getZoomFactor(), camera.getCenterY() - speedY * pSecondsElapsed / this.camera.getZoomFactor());
                       
                        this.speedX *= (1.0f - 1.2f * pSecondsElapsed);
                        this.speedY *= (1.0f - 1.2f * pSecondsElapsed);
                       
                        if(speedX < 10 && speedX > -10) speedX = 0;
                        if(speedY < 10 && speedY > -10) speedY = 0;
                }              
        }
 
        @Override
        public void reset()
        {
               
        }
 
        public float getSpeedX()
        {
                return speedX;
        }
 
        public void setSpeedX(float speedX)
        {
                this.speedX = speedX;
        }
 
        public float getSpeedY()
        {
                return speedY;
        }
 
        public void setSpeedY(float speedY)
        {
                this.speedY = speedY;
        }
       
        public void cancelCurScrolling()
        {
                this.touchDown = false;
        }
}