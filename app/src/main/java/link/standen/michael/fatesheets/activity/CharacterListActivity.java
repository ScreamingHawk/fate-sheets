package link.standen.michael.fatesheets.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.CharacterArrayAdapter;
import link.standen.michael.fatesheets.util.CharacterHelper;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.CoreCharacter;

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
		characters.addAll(CharacterHelper.listCharacterNames(this));
		listAdapter = new CharacterArrayAdapter(this, R.layout.character_list_list_item, characters);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);

		// FAB
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Create new character by entering data screen
				Intent intent = new Intent(CharacterListActivity.this, CoreCharacterEditActivity.class);
				CharacterListActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_character_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
