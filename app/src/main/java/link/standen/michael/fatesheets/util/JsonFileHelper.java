package link.standen.michael.fatesheets.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A helper class for managing JSON files
 */
public abstract class JsonFileHelper {

	private static final String TAG = JsonFileHelper.class.getName();

	/**
	 * Saves JSON to a file.
	 * @return True if the write was successful, false otherwise.
	 */
	public static boolean saveJsonToFile(Context context, String json, String filename) {
		try {
			FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(json.getBytes());
			fos.flush();
			fos.close();
			return true;
		} catch (IOException e) {
			Log.e(TAG, "Error writing JSON", e);
		}
		return false;
	}

	/**
	 * Loads JSON from a file.
	 */
	@Nullable
	public static String getJsonFromFile(Context context, String filename) {
		try {
			return getJsonFromFile(context.openFileInput(filename), filename);
		} catch (FileNotFoundException e) {
			Log.e(TAG, String.format("JSON file not found: %s", filename), e);
		}
		return null;
	}

	/**
	 * Loads JSON from a file.
	 */
	@Nullable
	public static String getJsonFromFile(Context context, Uri uri) {
		try {
			return getJsonFromFile(context.getContentResolver().openInputStream(uri), uri.getLastPathSegment());
		} catch (FileNotFoundException e) {
			Log.e(TAG, String.format("JSON file not found: %s", uri), e);
		}
		return null;
	}

	/**
	 * Loads JSON from a file.
	 */
	@Nullable
	private static String getJsonFromFile(InputStream fis, String filename){
		String json = null;

		try {
			StringBuilder bob = new StringBuilder("");
			byte[] buff = new byte[1024];
			int n;
			while ((n = fis.read(buff)) != -1) {
				bob.append(new String(buff, 0, n));
			}
			fis.close();

			json = bob.toString();
		} catch (IOException e) {
			Log.e(TAG, String.format("Error reading file %s", filename), e);
		}

		return json;
	}

	/**
	 * Deletes a file.
	 * @return True if the delete was successful, false otherwise.
	 */
	public static boolean deleteFile(Context context, String filename) {
		return context.deleteFile(filename);
	}

}
