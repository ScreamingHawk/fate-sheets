package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.Character;

/**
 * A fragment for managing a characters aspects.
 */
public class CharacterEditAspectsFragment extends CharacterEditAbstractFragment {

	private TextWatcher highConceptTextWatcher;
	private TextWatcher troubleTextWatcher;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.character_edit_aspects, container, false);
	}

	@Override
	public void onViewCreated(View rootView, Bundle savedInstanceState) {

		Character character = getCharacter();

		// High Concept
		TextView view = (TextView) rootView.findViewById(R.id.high_concept);
		view.setText(character.getHighConcept());
		if (highConceptTextWatcher == null){
			highConceptTextWatcher = new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					getCharacter().setHighConcept(s.toString());
				}
			};
			view.addTextChangedListener(highConceptTextWatcher);
		}
		// Trouble
		view = (TextView) rootView.findViewById(R.id.trouble);
		view.setText(character.getTrouble());
		if (troubleTextWatcher == null){
			troubleTextWatcher = new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					getCharacter().setTrouble(s.toString());
				}
			};
			view.addTextChangedListener(troubleTextWatcher);
		}

		// Aspects
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

			View rootView = inflater.inflate(R.layout.character_edit_aspects_aspect, container, false);

			// Aspects
			final DeletableStringArrayAdapter aspectListAdapter = new DeletableStringArrayAdapter(getContext(),
					R.layout.character_edit_aspects_list_item, getCharacter().getAspects());
			((AdapterLinearLayout) rootView.findViewById(R.id.aspect_list)).setAdapter(aspectListAdapter);

			rootView.findViewById(R.id.add_aspect).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getCharacter().getAspects().add("");
					aspectListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}