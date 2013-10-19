package me.capstone.advancedbattle.touchhandlers;

import java.util.ArrayList;
 
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;
 
/**
 *
 *
 * @author Frank
 *
 */
public class TouchDistributor implements IOnSceneTouchListener {
       
       
        private ArrayList<IOnSceneTouchListener> touchListeners;
       
        public TouchDistributor(){
                ArrayList<IOnSceneTouchListener> touchListeners = new ArrayList<IOnSceneTouchListener>();
                this.touchListeners = touchListeners;
        }
       
        public void addTouchListener(IOnSceneTouchListener IOsceneTouchListener){
                this.touchListeners.add(IOsceneTouchListener);
        }
       
        public void removeTouchListener(IOnSceneTouchListener IOsceneTouchListener){
                this.touchListeners.remove(IOsceneTouchListener);
        }
 
        @Override
        public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
                for(int i=0; i < this.touchListeners.size(); i++){
                        if (this.touchListeners.get(i).onSceneTouchEvent(pScene, pSceneTouchEvent))
                                return true;
                }
                return false;
        }
}