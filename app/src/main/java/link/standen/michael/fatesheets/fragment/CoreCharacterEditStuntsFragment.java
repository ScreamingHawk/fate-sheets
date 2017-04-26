package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.layout.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a characters stunts.
 */
public class CoreCharacterEditStuntsFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.core_character_edit_stunts, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Stunt
		Fragment childFragment = new StuntFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.stunt_container, childFragment).commit();
		// Extra
		childFragment = new ExtraFragment();
		transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.extra_container, childFragment).commit();
	}

	/**
	 * Class for managing stunts.
	 */
	public static class StuntFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			final CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.character_edit_stunts_stunt, container, false);

			// Stunts
			final DeletableStringArrayAdapter stuntListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.character_edit_stunts_list_item, getCharacter().getStunts());
			((AdapterLinearLayout) rootView.findViewById(R.id.stunts_list)).setAdapter(stuntListAdapter);

			rootView.findViewById(R.id.add_stunt).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					character.getStunts().add("");
					stuntListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}

	/**
	 * Class for managing extras.
	 */
	public static class ExtraFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			final CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.core_character_edit_stunts_extra, container, false);

			// Extras
			final DeletableStringArrayAdapter extraListAdapter = new DeletableStringArrayAdapter((CoreCharacterEditActivity) getContext(),
					R.layout.core_character_edit_extras_list_item, character.getExtras());
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
}