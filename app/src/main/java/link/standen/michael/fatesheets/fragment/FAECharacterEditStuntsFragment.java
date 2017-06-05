package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.view.AdapterLinearLayout;

/**
 * A fragment for managing a characters stunts.
 */
public class FAECharacterEditStuntsFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fae_character_edit_stunts, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Stunt
		Fragment childFragment = new StuntFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.stunt_container, childFragment).commit();
	}

	/**
	 * Class for managing stunts.
	 */
	public static class StuntFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.character_edit_stunts_stunt, container, false);

			// Stunts
			final DeletableStringArrayAdapter stuntListAdapter = new DeletableStringArrayAdapter(getContext(),
					R.layout.character_edit_stunts_list_item, getCharacter().getStunts());
			((AdapterLinearLayout) rootView.findViewById(R.id.stunts_list)).setAdapter(stuntListAdapter);

			rootView.findViewById(R.id.add_stunt).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getCharacter().getStunts().add("");
					stuntListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}

}