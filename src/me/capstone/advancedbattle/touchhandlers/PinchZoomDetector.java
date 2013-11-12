package me.capstone.advancedbattle.touchhandlers;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
import android.view.MotionEvent;
 
/**
 * PinchZoomDetector is only usable on multitouch-enalbed versions (2.1+)
 * @author BrR
 *
 */
public class PinchZoomDetector implements IOnSceneTouchListener
{      
        private float lastSpacing;
        private ZoomCamera cam;
        private MapScroller scroller;
       
        public PinchZoomDetector(ZoomCamera cam, MapScroller scroller)
        {
                this.cam = cam;
                this.scroller = scroller;
        }
       
        @Override
        public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
        {
                int action = pSceneTouchEvent.getAction();
                if(action == MotionEvent.ACTION_UP)
                {
                        this.lastSpacing = 0;
                        return false;
                }
                if(pSceneTouchEvent.getMotionEvent().getPointerCount() == 2)
                {
                        this.handleMultitouchGesture(pSceneTouchEvent.getMotionEvent());
                        return true;
                }
                return false;
        }
       
        private void handleMultitouchGesture(MotionEvent evt)
        {
                this.scroller.cancelCurScrolling();
                final float curSpacing = this.spacing(evt);
                if(this.lastSpacing > 0)
                {
                	this.cam.setZoomFactor(this.cam.getZoomFactor() * (curSpacing / this.lastSpacing)); 
                	if(this.cam.getZoomFactor() > 2)
                    {
                        this.cam.setZoomFactor(2);
                    }
                    if(this.cam.getZoomFactor() < 1)
                    {
                        this.cam.setZoomFactor(1);
                    }    
                }
                this.lastSpacing = curSpacing;
        }
       
        private float spacing(MotionEvent evt)
        {
                float x = evt.getX(0) - evt.getX(1);
                float y = evt.getY(0) - evt.getY(1);
                return (float) Math.sqrt(x * x + y * y);
        }      
}
