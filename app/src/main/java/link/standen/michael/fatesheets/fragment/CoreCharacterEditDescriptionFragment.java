package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;

/**
 * A fragment for managing a characters description.
 */
public class CoreCharacterEditDescriptionFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_description, container, false);

		// Update fields on key press
		rootView.findViewById(R.id.name).setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				getCharacter().setName(((TextView)v).getText().toString());
				return false;
			}
		});
		rootView.findViewById(R.id.description).setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				getCharacter().setDescription(((TextView)v).getText().toString());
				return false;
			}
		});

		return rootView;
	}
}