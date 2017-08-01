package link.standen.michael.fatesheets.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.util.CharacterHelper;
import link.standen.michael.fatesheets.util.DiceClickListener;
import link.standen.michael.fatesheets.util.JsonFileHelper;

/**
 * An abstract class that handles the menu items shared across activities.
 */
public abstract class SharedMenuActivity extends AppCompatActivity {

	private static final String TAG = SharedMenuActivity.class.getSimpleName();

	private static final long FOCUS_PAUSE = 500;

	private static final int REQUEST_CODE_IMPORT = 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_shared_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_credits) {
			startActivity(new Intent(this, CreditsActivity.class));
			return true;
		} else if (id == R.id.action_docs) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://fate-srd.com/")));
			return true;
		} else if (id == R.id.action_edit_skills) {
			startActivity(new Intent(this, EditSkillsActivity.class));
			return true;
		} else if (id == R.id.action_import_sheet) {
			Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
			intent.setType("*/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, REQUEST_CODE_IMPORT);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
		if (requestCode == REQUEST_CODE_IMPORT && resultCode == Activity.RESULT_OK) {
			// The document selected by the user won't be returned in the intent.
			// Instead, a URI to that document will be contained in the return intent
			// provided to this method as a parameter.
			// Pull that URI using resultData.getData().
			if (resultData != null) {
				Uri uri = resultData.getData();
				Log.i(TAG, "Importing: " + uri.toString());
				String json = JsonFileHelper.getJsonFromFile(this, uri);
				//getContentResolver().openInputStream(uri, );
				CharacterHelper.createCharacterSaveFromJson(this, json);
				//TODO Refresh list view
			}
		}
	}

	protected void setupDiceFAB() {
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dice_fab);
		fab.setOnClickListener(new DiceClickListener(getResources()));
	}

	/**
	 * Clears the focus and runs the callback.
	 */
	protected void clearFocus(Runnable callback) {
		try {
			getCurrentFocus().clearFocus();

		} catch (NullPointerException e){
			// Nothing in focus. Ignore
		}
		if (callback != null) {
			new Handler().postDelayed(callback, FOCUS_PAUSE);
		}
	}
}
