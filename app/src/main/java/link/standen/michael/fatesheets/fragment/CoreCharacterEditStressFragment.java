package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.ConsequenceArrayAdapter;
import link.standen.michael.fatesheets.model.Consequence;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a characters stress.
 */
public class CoreCharacterEditStressFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_stress, container, false);

		final CoreCharacter character = getCharacter();

		// Consequences
		final ConsequenceArrayAdapter consequenceListAdapter = new ConsequenceArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_consequence_list_item, getCharacter().getConsequences());
		((ListView) rootView.findViewById(R.id.consequence_list)).setAdapter(consequenceListAdapter);

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