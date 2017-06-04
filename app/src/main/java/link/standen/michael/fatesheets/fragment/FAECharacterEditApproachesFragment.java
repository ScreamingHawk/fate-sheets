package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.ApproachArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.Approach;

/**
 * A fragment for managing a characters Approaches.
 */
public class FAECharacterEditApproachesFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fae_character_edit_approaches, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Approach
		Fragment childFragment = new ApproachFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.approach_container, childFragment).commit();
	}

	/**
	 * Class for managing approaches.
	 */
	public static class ApproachFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fae_character_edit_approaches_approach, container, false);

			// Approaches
			final ApproachArrayAdapter approachListAdapter = new ApproachArrayAdapter(getContext(),
					R.layout.fae_character_edit_approaches_list_item, getFAECharacter().getApproaches());
			((AdapterLinearLayout) rootView.findViewById(R.id.approaches_list)).setAdapter(approachListAdapter);

			rootView.findViewById(R.id.add_approach).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getFAECharacter().getApproaches().add(new Approach(""));
					approachListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}