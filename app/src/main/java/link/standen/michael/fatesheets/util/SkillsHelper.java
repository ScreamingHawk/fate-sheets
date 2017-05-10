package link.standen.michael.fatesheets.util;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import link.standen.michael.fatesheets.R;

/**
 * A helper class for managing characters
 */
public final class SkillsHelper extends JsonFileHelper {

	private static final String SKILLS_STORAGE = "SKILLS_LIST";

	private SkillsHelper(){}

	/**
	 * Saves skills to local storage.
	 * @return True if the write operation was successful, false otherwise.
	 */
	public static boolean saveSkills(Context context, List<String> skills) {
		return saveJsonToFile(context, new Gson().toJson(skills), SKILLS_STORAGE);
	}

	/**
	 * Gets the saved skills list.
	 * If no skills list has been saved, gets the default skill list.
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static List<String> getSkills(Context context) {
		String json = getJsonFromFile(context, SKILLS_STORAGE);

		if (json == null) {
			return getDefaultSkills(context);
		}

		return new Gson().fromJson(json, List.class);
	}

	/**
	 * Gets the default skill list.
	 */
	public static List<String> getDefaultSkills(Context context){
		return Arrays.asList(context.getResources().getStringArray(R.array.core_skills));
	}

}
