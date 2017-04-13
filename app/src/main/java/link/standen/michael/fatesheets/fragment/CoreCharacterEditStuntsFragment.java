package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a characters stunts.
 */
public class CoreCharacterEditStuntsFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_stunts, container, false);

		final CoreCharacter character = getCharacter();

		// Stunts
		final DeletableStringArrayAdapter stuntListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_stunts_list_item, getCharacter().getStunts());
		((AdapterLinearLayout) rootView.findViewById(R.id.stunts_list)).setAdapter(stuntListAdapter);

		rootView.findViewById(R.id.add_stunt).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				character.getStunts().add("");
				stuntListAdapter.notifyDataSetChanged();
			}
		});

		// Extras
		final DeletableStringArrayAdapter extraListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_extras_list_item, getCharacter().getExtras());
		((AdapterLinearLayout) rootView.findViewById(R.id.extras_list)).setAdapter(extraListAdapter);

		rootView.findViewById(R.id.add_extra).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				character.getExtras().add("");
				extraListAdapter.notifyDataSetChanged();
			}
		});

		return rootView;
	}
}