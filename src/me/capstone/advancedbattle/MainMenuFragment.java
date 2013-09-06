package me.capstone.advancedbattle;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainMenuFragment extends Fragment implements OnClickListener {
	
	public interface Listener {
		public void onSignInButtonClicked();
		public void onSignOutButtonClicked();
	}
	
	private Listener listener = null;
	private boolean showSignIn = true;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mainmenu, container, false);
		final int[] CLICKABLES = new int[] {
				R.id.sign_in_button, R.id.sign_out_button
		};
		for (int i : CLICKABLES) {
			v.findViewById(i).setOnClickListener(this);
		}
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		return v;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
		updateUi();
	}

	public boolean isShowSignIn() {
		return showSignIn;
	}

	public void setShowSignIn(boolean showSignIn) {
		this.showSignIn = showSignIn;
		updateUi();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		updateUi();
	}
	
	public void updateUi() {
		if (getActivity() == null) {
			return;
		}
		
		getActivity().findViewById(R.id.sign_in_bar).setVisibility(isShowSignIn() ? View.VISIBLE : View.GONE);
		getActivity().findViewById(R.id.sign_out_bar).setVisibility(isShowSignIn() ? View.GONE : View.VISIBLE);
		getActivity().findViewById(R.id.start_bar).setVisibility(isShowSignIn() ? View.GONE : View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.sign_in_button:
			getListener().onSignInButtonClicked();
			break;
		case R.id.sign_out_button:
			getListener().onSignOutButtonClicked();
			break;
		}
	}

}
