package sk.estesadohodneme.sturik;

//import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Creates fragment for game.
 */
public class GameFragment extends Fragment {

	/**
	 * Returns view for {@link GameFragment}.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.game, container, false);
		//Context context = getActivity();

		ImageButton button = (ImageButton) view.findViewById(R.id.button_left);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		return view;
	}
}
