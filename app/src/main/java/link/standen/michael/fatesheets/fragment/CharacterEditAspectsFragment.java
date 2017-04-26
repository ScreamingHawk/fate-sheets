package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.Character;

/**
 * A fragment for managing a characters aspects.
 */
public class CharacterEditAspectsFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.character_edit_aspects, container, false);
	}

	@Override
	public void onViewCreated(View rootView, Bundle savedInstanceState) {

		final Character character = getCharacter();

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

		// Skill
		Fragment childFragment = new AspectFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.aspect_container, childFragment).commit();
	}

	/**
	 * Class for managing aspects.
	 */
	public static class AspectFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			final Character character = getCharacter();

			View rootView = inflater.inflate(R.layout.character_edit_aspects_aspect, container, false);

			// Aspects
			final DeletableStringArrayAdapter aspectListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.character_edit_aspects_list_item, getCharacter().getAspects());
			((AdapterLinearLayout) rootView.findViewById(R.id.aspect_list)).setAdapter(aspectListAdapter);

			rootView.findViewById(R.id.add_aspect).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					character.getAspects().add("");
					aspectListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}