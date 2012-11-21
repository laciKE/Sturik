package sk.estesadohodneme.sturik;

import sk.estesadohodneme.sturik.game.GameEngine;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Creates fragment for game.
 */
public class MainActivity extends FragmentActivity {

	private GameEngine mGameEngine = null;
	
	/**
	 * Creates fragment for game, creates gameEngine and sets its parameters.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * Sets parameters of game engine;
		 */
		mGameEngine = new GameEngine();
		mGameEngine.setGame(GameEngine.GAME_SNAKE);
		mGameEngine.setImageGenerator(GameEngine.IMAGE_GENERATOR_SIMPLE);
		//mGameEngine.setImageSize(320, 240);
		
		Bundle args = new Bundle();
		args.putSerializable(GameFragment.GAME_ENGINE, mGameEngine);
		Fragment fragment = new GameFragment();
		fragment.setArguments(args);
		
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame, fragment);
		//fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
*/
}
