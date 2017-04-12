package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;

/**
 * A fragment for managing a characters stunts.
 */
public class CoreCharacterEditStuntsFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_stunts, container, false);
		return rootView;
	}
}