package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a characters description.
 */
public class CoreCharacterEditDescriptionFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_description, container, false);

		final CoreCharacter character = getCharacter();

		// Name
		TextView view = (TextView) rootView.findViewById(R.id.name);
		view.setText(character.getName());
		view.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				character.setName(((TextView)v).getText().toString());
				return false;
			}
		});
		// Description
		view = (TextView) rootView.findViewById(R.id.description);
		view.setText(character.getDescription());
		view.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				character.setDescription(((TextView)v).getText().toString());
				return false;
			}
		});

		return rootView;
	}
}