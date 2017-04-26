package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.ConsequenceArrayAdapter;
import link.standen.michael.fatesheets.adapter.StressArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.Consequence;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.model.Stress;

/**
 * A fragment for managing a characters stress.
 */
public class CoreCharacterEditStressFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.core_character_edit_stress, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Physical
		Fragment childFragment = new PhysicalStressFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.physical_stress_container, childFragment).commit();
		// Mental
		childFragment = new MentalStressFragment();
		transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.mental_stress_container, childFragment).commit();
		// Consequence
		childFragment = new ConsequenceFragment();
		transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.consequence_container, childFragment).commit();
	}

	/**
	 * Class for managing physical stress.
	 */
	public static class PhysicalStressFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			final CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.core_character_edit_stress_physical, container, false);

			// Physical Stress
			final StressArrayAdapter physicalStressListAdapter = new StressArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.character_edit_stress_list_item, character.getPhysicalStress());
			((AdapterLinearLayout) rootView.findViewById(R.id.physical_stress_list)).setAdapter(physicalStressListAdapter);

			rootView.findViewById(R.id.add_physical_stress).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					int nextValue = character.getPhysicalStress().size() + 1;
					character.getPhysicalStress().add(new Stress(nextValue));
					physicalStressListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}

	/**
	 * Class for managing mental stress.
	 */
	public static class MentalStressFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			final CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.core_character_edit_stress_mental, container, false);

			final StressArrayAdapter mentalStressListAdapter = new StressArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.character_edit_stress_list_item, character.getMentalStress());
			((AdapterLinearLayout) rootView.findViewById(R.id.mental_stress_list)).setAdapter(mentalStressListAdapter);

			rootView.findViewById(R.id.add_mental_stress).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int nextValue = character.getMentalStress().size() + 1;
					character.getMentalStress().add(new Stress(nextValue));
					mentalStressListAdapter.notifyDataSetChanged();
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

			final CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.character_edit_stress_consequence, container, false);

			// Consequences
			final ConsequenceArrayAdapter consequenceListAdapter = new ConsequenceArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.character_edit_consequence_list_item, getCharacter().getConsequences());
			((AdapterLinearLayout) rootView.findViewById(R.id.consequence_list)).setAdapter(consequenceListAdapter);

			rootView.findViewById(R.id.add_consequence).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					int nextValue = character.getConsequences().size() * 2 + 2;
					character.getConsequences().add(new Consequence(nextValue));
					consequenceListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}