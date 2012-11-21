package sk.estesadohodneme.sturik;

import sk.estesadohodneme.sturik.game.GameEngine;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Creates fragment for game.
 */
public class GameFragment extends Fragment {

	public static final String GAME_ENGINE = "game engine";
	
	private GameEngine mGameEngine = null;
	
	@Override
	public void setArguments(Bundle args){
		mGameEngine = (GameEngine)args.getSerializable(GAME_ENGINE);
	}

	/**
	 * Returns view for {@link GameFragment}.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.game, container, false);

		int[][] actions = { { R.id.button_left, GameEngine.ACTION_LEFT },
				{ R.id.button_right, GameEngine.ACTION_RIGHT },
				{ R.id.button_down, GameEngine.ACTION_DOWN },
				{ R.id.button_up, GameEngine.ACTION_UP }, };
		for (int[] action : actions) {
			int buttonId = action[0];
			final int userAction = action[1];
			ImageButton button = (ImageButton) view.findViewById(buttonId);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mGameEngine.setUserAction(userAction);
				}
			});

		}
			
		return view;
	}

	/**
	 * Starts game on resume.
	 */
	@Override
	public void onResume(){
		super.onResume();
		mGameEngine.start();
		Log.d("STURIK", "GameFragment start");
	}

	
	/**
	 * Stops game on pause.
	 */
	@Override
	public void onPause(){
		Log.d("STURIK", "GameFragment stop");
		mGameEngine.stop();
		super.onPause();
	}
}
