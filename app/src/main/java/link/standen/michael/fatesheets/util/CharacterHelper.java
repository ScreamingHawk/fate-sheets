package link.standen.michael.fatesheets.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.CoreCharacter;

import static android.content.Context.MODE_PRIVATE;

/**
 * A helper class for managing characters
 */
public final class CharacterHelper {

	private static final String CORE_PREFEX = "Core.";

	private CharacterHelper(){}

	/**
	 * Returns the default name for a character.
	 */
	public static String getCharacterDefaultName(Context context){
		return context.getResources().getString(R.string.character_name_default);
	}

	/**
	 * Saves the character to local storage
	 * @param activity
	 * @param character
	 * @return
	 */
	public static boolean saveCoreCharacter(Activity activity, CoreCharacter character) {
		SharedPreferences.Editor preferencesEditor = activity.getPreferences(MODE_PRIVATE).edit();
		Gson gson = new Gson();
		String json = gson.toJson(character);
		preferencesEditor.putString(CORE_PREFEX + character.getName(), json);
		return preferencesEditor.commit();
	}

	public static CoreCharacter getCoreCharacter(Activity activity, String name) {
		SharedPreferences preferences = activity.getPreferences(MODE_PRIVATE);

		Gson gson = new Gson();
		String json = preferences.getString(CORE_PREFEX + name, null);
		if (json == null){
			return null;
		}
		return gson.fromJson(json, CoreCharacter.class);
	}
}
