package me.capstone.advancedbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		
		final Button button = (Button) findViewById(R.id.beginBattle);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent goToBattle = new Intent(MainActivity.this,AdvancedBattle.class);
				startActivity(goToBattle);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.help:
	        return true;
		}
		return false;
	}

}
