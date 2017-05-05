package link.standen.michael.fatesheets.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.CharacterArrayAdapter;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.util.CharacterHelper;

public class CharacterListActivity extends AppCompatActivity {

	private static final String TAG = CharacterListActivity.class.getName();

	private final List<String> characters;
	private ArrayAdapter listAdapter;

	public CharacterListActivity() {
		this.characters = new ArrayList<>();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character_list_activity);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Initialise list view
		listAdapter = new CharacterArrayAdapter(this, R.layout.character_list_list_item, characters);
		final ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);

		// FAB
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final Resources resources = CharacterListActivity.this.getResources();
				final CharSequence types[] = new CharSequence[] {
						resources.getString(R.string.fate_core),
						resources.getString(R.string.fate_accelerated_edition)
				};

				// Dialog to select which sheet type to create
				AlertDialog.Builder builder = new AlertDialog.Builder(CharacterListActivity.this);
				builder.setTitle(resources.getString(R.string.select_sheet_type));
				builder.setItems(types, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Lookup based on string value so we aren't relying on position
						String selected = types[which].toString();
						Intent intent = null;
						if (selected != null) {
							if (selected.equals(resources.getString(R.string.fate_core))) {
								intent = new Intent(CharacterListActivity.this, CoreCharacterEditActivity.class);
							} else if (selected.equals(resources.getString(R.string.fate_accelerated_edition))) {
								intent = new Intent(CharacterListActivity.this, FAECharacterEditActivity.class);
							}
						}
						if (intent != null) {
							CharacterListActivity.this.startActivity(intent);
						} else {
							// Log and alert error
							Log.e(TAG, String.format("Error creating sheet. which: %d selected: %s", which, selected));
							Snackbar.make(listView, resources.getString(R.string.toast_sheet_select_error), Snackbar.LENGTH_LONG)
									.setAction("Action", null).show();
						}
					}
				});
				builder.show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		refreshCharacterList();
	}

	/**
	 * Refreshes the character list.
	 */
	private void refreshCharacterList(){
		characters.clear();
		characters.addAll(CharacterHelper.listCharacterFileNames(this));
		listAdapter.notifyDataSetChanged();
		checkEmptyCharacterList();
	}

	/**
	 * Shows or hides the empty list layout as required.
	 */
	public void checkEmptyCharacterList(){
		if (characters.isEmpty()){
			findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
		} else {
			findViewById(android.R.id.empty).setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_character_activity, menu);
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
		}

		return super.onOptionsItemSelected(item);
	}
}
