package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a characters aspects.
 */
public class CoreCharacterEditAspectsFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_aspects, container, false);

		final CoreCharacter character = getCharacter();

		// High Concept
		TextView view = (TextView) rootView.findViewById(R.id.high_concept);
		view.setText(character.getHighConcept());
		view.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				character.setHighConcept(((TextView)v).getText().toString());
				return false;
			}
		});
		// Trouble
		view = (TextView) rootView.findViewById(R.id.trouble);
		view.setText(character.getTrouble());
		view.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				character.setTrouble(((TextView)v).getText().toString());
				return false;
			}
		});

		// Aspects
		final DeletableStringArrayAdapter aspectListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_aspects_list_item, getCharacter().getAspects());
		((AdapterLinearLayout) rootView.findViewById(R.id.aspect_list)).setAdapter(aspectListAdapter);

		rootView.findViewById(R.id.add_aspect).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				character.getAspects().add("");
				aspectListAdapter.notifyDataSetChanged();
			}
		});

		return rootView;
	}
}