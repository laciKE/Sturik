package sk.estesadohodneme.sturik;

import android.app.Activity; 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

/**
 * Creates fragment for welcome screen with list of available games.
 */
public class WelcomeFragment extends Fragment {
	
	private OnClickListener mOnClickListener = null;
	
	/**
	 * Returns view for {@link WelcomeFragment}.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.welcome, container, false);

		int[] buttonIds = { R.id.button_snake, R.id.button_snake_mlyny,
				R.id.button_tetris, R.id.button_tetris_mlyny };
		for (int buttonId : buttonIds) {
			ImageButton button = (ImageButton) view.findViewById(buttonId);
			button.setOnClickListener(mOnClickListener);
			
			// set button press feedback based on transparency
			button.setAlpha(192);
			button.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					ImageButton imageButton = (ImageButton) v;
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						imageButton.setAlpha(255);
					}
					if(event.getAction() == MotionEvent.ACTION_UP){
						imageButton.setAlpha(192);
					}					
					return false;
				}
			});
		}
		
		return view;
	}
	
	/**
	 * Sets onClickListener of buttons to activity.
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnClickListener = (OnClickListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnClickListener interface");
		}
	}

}
