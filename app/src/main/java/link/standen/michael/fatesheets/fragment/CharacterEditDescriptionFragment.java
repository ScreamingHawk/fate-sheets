package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Character;

/**
 * A fragment for managing a characters description.
 */
public class CharacterEditDescriptionFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.character_edit_description, container, false);

		final Character character = getCharacter();

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
		// Fate Points
		final TextView fatePoints = (TextView) rootView.findViewById(R.id.fate_points);
		fatePoints.setText(character.getFatePoints().toString());
		rootView.findViewById(R.id.fate_points_up).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				character.incrementFatePoints();
				fatePoints.setText(character.getFatePoints().toString());
			}
		});
		rootView.findViewById(R.id.fate_points_down).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				character.decrementFatePoints();
				fatePoints.setText(character.getFatePoints().toString());
			}
		});

		return rootView;
	}
}