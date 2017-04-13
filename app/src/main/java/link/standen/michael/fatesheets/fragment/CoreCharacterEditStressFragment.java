package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
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
public class CoreCharacterEditStressFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_stress, container, false);

		final CoreCharacter character = getCharacter();

		// Physical Stress
		final StressArrayAdapter physicalStressListAdapter = new StressArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_stress_list_item, getCharacter().getPhysicalStress());
		((AdapterLinearLayout) rootView.findViewById(R.id.physical_stress_list)).setAdapter(physicalStressListAdapter);

		rootView.findViewById(R.id.add_physical_stress).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				int nextValue = character.getPhysicalStress().size() + 1;
				character.getPhysicalStress().add(new Stress(nextValue));
				physicalStressListAdapter.notifyDataSetChanged();
			}
		});

		// Physical Stress
		final StressArrayAdapter mentalStressListAdapter = new StressArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_stress_list_item, getCharacter().getMentalStress());
		((AdapterLinearLayout) rootView.findViewById(R.id.mental_stress_list)).setAdapter(mentalStressListAdapter);

		rootView.findViewById(R.id.add_mental_stress).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				int nextValue = character.getMentalStress().size() + 1;
				character.getMentalStress().add(new Stress(nextValue));
				mentalStressListAdapter.notifyDataSetChanged();
			}
		});

		// Consequences
		final ConsequenceArrayAdapter consequenceListAdapter = new ConsequenceArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_consequence_list_item, getCharacter().getConsequences());
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