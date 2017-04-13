package link.standen.michael.fatesheets.util;

import android.content.Context;

import link.standen.michael.fatesheets.R;

/**
 * A helper class for managing characters
 */
public final class CharacterHelper {

	private CharacterHelper(){}

	public static String getCharacterDefaultName(Context context){
		return context.getResources().getString(R.string.character_name_default);
	}
}
