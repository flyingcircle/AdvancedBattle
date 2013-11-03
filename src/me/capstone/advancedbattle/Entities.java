package me.capstone.advancedbattle;

import java.util.HashMap;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.Shape;

import android.app.Activity;

public class Entities {
	

	public static final Object LEVEL_TYPE_WALL = "wall";

	public static void addEntity(Activity activity,
			Scene scene, int x, int y, int width, int height,
			String type, HashMap<String, String> properties) {
		if(type.equals(Entities.LEVEL_TYPE_WALL)){
			//Entities.addWall(activity, scene, x, y, width, height, properties);
		}
	}

//	private static void addWall(Activity activity, Scene scene, int x, int y,
//			int width, int height, HashMap<String, String> properties) {
//		final Shape wall = new Rectangle(x, y, width, height);
//		wall.setVisible(false);
//		if(properties.containsKey("rotate")){
//			wall.setRotation(Float.parseFloat(properties.get("rotate")));
//
//		}
//		
//		scene.getFirstChild().attachChild(wall);
//	}
//	

}
