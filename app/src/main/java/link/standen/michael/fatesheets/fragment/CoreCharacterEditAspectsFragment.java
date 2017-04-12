package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;

/**
 * A fragment for managing a characters aspects.
 */
public class CoreCharacterEditAspectsFragment extends CoreCharacterEditAbstractFragment {

	private DeletableStringArrayAdapter listAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_aspects, container, false);

		// Initialise list view
		listAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_aspects_list_item, getCharacter().getAspects());
		ListView listView = (ListView) rootView.findViewById(R.id.aspect_list);
		listView.setAdapter(listAdapter);

		rootView.findViewById(R.id.add_aspect).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				CoreCharacterEditAspectsFragment.this.getCharacter().getAspects().add("");
				CoreCharacterEditAspectsFragment.this.listAdapter.notifyDataSetChanged();
			}
		});

		return rootView;
	}
}