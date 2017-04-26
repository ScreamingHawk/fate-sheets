package link.standen.michael.fatesheets.fragment;

import android.support.v4.app.Fragment;

import link.standen.michael.fatesheets.activity.CharacterEditActivity;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.model.FAECharacter;

/**
 * A fragment for managing a Fate Core character's information.
 */
public abstract class CharacterEditAbstractFragment extends Fragment {

	protected Character getCharacter(){
		return ((CharacterEditActivity)getContext()).getCharacter();
	}

	/**
	 * Get the Core character from the activity.
	 */
	protected CoreCharacter getCoreCharacter(){
		Character character = getCharacter();
		if (character instanceof CoreCharacter){
			return (CoreCharacter)character;
		}
		return null;
	}

	/**
	 * Get the FAE character from the activity.
	 */
	protected FAECharacter getFAECharacter(){
		Character character = getCharacter();
		if (character instanceof FAECharacter){
			return (FAECharacter)character;
		}
		return null;
	}
}