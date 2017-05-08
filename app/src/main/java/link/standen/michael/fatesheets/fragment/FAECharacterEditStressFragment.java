package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.ConsequenceArrayAdapter;
import link.standen.michael.fatesheets.adapter.StressArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.Consequence;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.FAECharacter;
import link.standen.michael.fatesheets.model.Stress;

/**
 * A fragment for managing a characters stress.
 */
public class FAECharacterEditStressFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fae_character_edit_stress, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Stress
		Fragment childFragment = new StressFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.stress_container, childFragment).commit();
		// Consequence
		childFragment = new ConsequenceFragment();
		transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.consequence_container, childFragment).commit();
	}

	/**
	 * Class for managing stress.
	 */
	public static class StressFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			FAECharacter character = getFAECharacter();

			View rootView = inflater.inflate(R.layout.fae_character_edit_stress_stress, container, false);

			// Stress
			final StressArrayAdapter stressListAdapter = new StressArrayAdapter(getContext(),
					R.layout.character_edit_stress_list_item, character.getStress());
			((AdapterLinearLayout) rootView.findViewById(R.id.stress_list)).setAdapter(stressListAdapter);

			rootView.findViewById(R.id.add_stress).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					int nextValue = getFAECharacter().getStress().size() + 1;
					getFAECharacter().getStress().add(new Stress(nextValue));
					stressListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}

	/**
	 * Class for managing consequences.
	 */
	public static class ConsequenceFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			Character character = getCharacter();

			View rootView = inflater.inflate(R.layout.character_edit_stress_consequence, container, false);

			// Consequences
			final ConsequenceArrayAdapter consequenceListAdapter = new ConsequenceArrayAdapter(getContext(),
					R.layout.character_edit_consequence_list_item, character.getConsequences());
			((AdapterLinearLayout) rootView.findViewById(R.id.consequence_list)).setAdapter(consequenceListAdapter);

			rootView.findViewById(R.id.add_consequence).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					int nextValue = getCharacter().getConsequences().size() * 2 + 2;
					getCharacter().getConsequences().add(new Consequence(nextValue));
					consequenceListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}