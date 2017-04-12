package link.standen.michael.fatesheets.fragment;

import android.support.v4.app.Fragment;

import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.model.CoreCharacter;

/**
 * A fragment for managing a Fate Core character's information.
 */
public abstract class CoreCharacterEditAbstractFragment extends Fragment {

	protected CoreCharacter getCharacter(){
		return ((CoreCharacterEditActivity)getContext()).getCharacter();
	}
}