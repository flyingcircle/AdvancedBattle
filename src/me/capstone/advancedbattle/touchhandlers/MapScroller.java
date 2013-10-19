package me.capstone.advancedbattle.touchhandlers;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
 
import android.view.MotionEvent;
 
public class MapScroller implements IOnSceneTouchListener, IUpdateHandler
{
 
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
                        this.camera.setCenter((lastX ) / this.camera.getZoomFactor(), (lastY ) / this.camera.getZoomFactor());
                        this.lastX = evt.getRawX();
                        this.lastY = evt.getRawY();    
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