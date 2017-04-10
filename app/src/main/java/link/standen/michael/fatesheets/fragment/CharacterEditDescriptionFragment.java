package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;

/**
 * A fragment for managing a characters description.
 */
public class CharacterEditDescriptionFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.character_edit_description, container, false);
		return rootView;
	}
}