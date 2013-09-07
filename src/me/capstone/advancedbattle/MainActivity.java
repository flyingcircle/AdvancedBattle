package me.capstone.advancedbattle;

import com.google.example.games.basegameutils.BaseGameActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends BaseGameActivity implements MainMenuFragment.Listener {
	MainMenuFragment mmFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mmFrag = new MainMenuFragment();
		mmFrag.setListener(this);
		
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mmFrag).commit();
	}

	public void switchToFragment(Fragment newFrag) {
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFrag).commit();
	}
	
	boolean verifyPlaceholderIdsReplaced() {
		if (getString(R.string.app_id).equalsIgnoreCase("ReplaceMe")) {
			return false;
		}
        return true;
    }

	@Override
	public void onSignInFailed() {
		mmFrag.setShowSignIn(true);
	}

	@Override
	public void onSignInSucceeded() {
		mmFrag.setShowSignIn(false);
	}

	@Override
	public void onSignInButtonClicked() {
		if (!verifyPlaceholderIdsReplaced()) {
            showAlert("App ID is not valid.");
            return;
        }
		
		beginUserInitiatedSignIn();
	}

	@Override
	public void onSignOutButtonClicked() {
		signOut();
		mmFrag.setShowSignIn(true);
	}

}
