package link.standen.michael.fatesheets.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.model.FAECharacter;

/**
 * A helper class for managing characters
 */
public final class CharacterHelper extends JsonFileHelper {

	private static final String TAG = CharacterHelper.class.getName();

	private static final String CORE_PREFIX = "Core_";
	private static final String FAE_PREFIX = "FAE_";

	private CharacterHelper(){}

	/**
	 * Returns the default name for a character.
	 */
	@NonNull
	public static String getCharacterDefaultName(Context context){
		return context.getResources().getString(R.string.character_name_default);
	}

	/**
	 * Saves the Core character to local storage.
	 * @return True if the write operation was successful, false otherwise.
	 */
	public static boolean saveCoreCharacter(Context context, CoreCharacter character) {
		return saveJsonToFile(context, new Gson().toJson(character), CORE_PREFIX + character.getName());
	}

	/**
	 * Saves the FAE character to local storage.
	 * @return True if the write operation was successful, false otherwise.
	 */
	public static boolean saveFAECharacter(Context context, FAECharacter character) {
		return saveJsonToFile(context, new Gson().toJson(character), FAE_PREFIX + character.getName());
	}

	/**
	 * Get a saved Core character.
	 */
	@Nullable
	public static CoreCharacter getCoreCharacter(Context context, String name) {
		if (name == null) {
			return null;
		}
		String json = getJsonFromFile(context, CORE_PREFIX + name);

		if (json == null) {
			return null;
		}
		return new Gson().fromJson(json, CoreCharacter.class);
	}

	/**
	 * Get a saved FAE character.
	 */
	@Nullable
	public static FAECharacter getFAECharacter(Context context, String name) {
		if (name == null) {
			return null;
		}
		String json = getJsonFromFile(context, FAE_PREFIX + name);

		if (json == null) {
			return null;
		}
		return new Gson().fromJson(json, FAECharacter.class);
	}

	/**
	 * Get a list of all saved characters names.
	 */
	public static List<String> listCharacterFileNames(Context context) {
		List<String> names = new ArrayList<>();

		for (String name : context.fileList()){
			if (name.startsWith(CORE_PREFIX) ||
					name.startsWith(FAE_PREFIX)){
				names.add(name);
			}
		}

		return names;
	}

	/**
	 * Get a list of all saved characters names.
	 */
	public static List<String> listCharacterNames(Context context) {
		List<String> names = new ArrayList<>();

		for (String name : context.fileList()){
			if (name.startsWith(CORE_PREFIX)){
				names.add(name.replace(CORE_PREFIX, ""));
			} else if (name.startsWith(FAE_PREFIX)){
				names.add(name.replace(FAE_PREFIX, ""));
			}
		}

		return names;
	}

	/**
	 * Gets the characters name from the filename by stripping the prefix.
	 * @return The characters name if prefixed correctly, null otherwise.
	 */
	public static String getCharacterNameFromFilename(String filename){
		if (filename.startsWith(CORE_PREFIX)){
			return filename.replace(CORE_PREFIX, "");
		} else if (filename.startsWith(FAE_PREFIX)){
			return filename.replace(FAE_PREFIX, "");
		}
		return null;
	}

	public static boolean filenameIsCore(String filename){
		return filename.startsWith(CORE_PREFIX);
	}

	public static boolean filenameIsFAE(String filename){
		return filename.startsWith(FAE_PREFIX);
	}

	/**
	 * Deletes a core character with the given name.
	 * @return True if the delete was successful, false otherwise.
	 */
	public static boolean deleteCoreCharacter(Context context, String name) {
		return deleteFile(context, CORE_PREFIX + name);
	}

	/**
	 * Deletes a core character with the given name.
	 * @return True if the delete was successful, false otherwise.
	 */
	public static boolean deleteFAECharacter(Context context, String name) {
		return deleteFile(context, FAE_PREFIX + name);
	}

	/**
	 * Deletes a character that already has the correctly prefixed filename.
	 * @return True if the delete was successful, false otherwise.
	 */
	public static boolean deleteCharacterFile(Context context, String filename) {
		return deleteFile(context, filename);
	}

	public static void createCharacterSaveFromJson(Context context, String json){
		Gson gson = new Gson();

		Log.d(TAG, "JSON: "+json);

		Character cha = gson.fromJson(json, Character.class);
		if (CoreCharacter.SHEET_TYPE.equals(cha.getSheetType())){
			Log.d(TAG, "Creating Core Character: "+cha.getName());
			saveCoreCharacter(context, gson.fromJson(json, CoreCharacter.class));
		} else if (FAECharacter.SHEET_TYPE.equals(cha.getSheetType())){
			Log.d(TAG, "Creating FAE Character: "+cha.getName());
			saveFAECharacter(context, gson.fromJson(json, FAECharacter.class));
		} else {
			Log.d(TAG, String.format("Failed to create character %s of type %s", cha.getName(), cha.getSheetType()));
		}
	}

}
