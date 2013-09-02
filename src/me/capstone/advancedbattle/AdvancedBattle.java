package me.capstone.advancedbattle;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

// Main class - Not sure how this is done in Android
public class AdvancedBattle extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_layout);
		ImageView iv = new ImageView(this);
		try {
			InputStream ims = getAssets().open("/AW1/Images/Terrain/plains.bmp");
			Drawable d = Drawable.createFromStream(ims, null);
			iv.setImageDrawable(d);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(32, 32);
		params.leftMargin = 0;
		params.rightMargin = 0;
		rl.addView(iv, params);
	}
	
}
