package link.standen.michael.fatesheets.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.FAECharacterEditSectionAdapter;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.FAECharacter;
import link.standen.michael.fatesheets.util.CharacterHelper;

public class FAECharacterEditActivity extends SharedMenuActivity implements CharacterEditActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private FAECharacterEditSectionAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	private FAECharacter character;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fae_character_edit_activity);

		// Store the character
		String name = (String) getIntent().getSerializableExtra(Character.INTENT_EXTRA_NAME);
		character = CharacterHelper.getFAECharacter(this, name);
		if (character == null){
			// New
			if (name == null){
				name = CharacterHelper.getCharacterDefaultName(this);
			}
			character = new FAECharacter(name, this);
		}

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new FAECharacterEditSectionAdapter(getSupportFragmentManager(), this);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				clearFocus(new Runnable() {
					@Override
					public void run() {
						if (CharacterHelper.saveFAECharacter(FAECharacterEditActivity.this, getCharacter())) {
							Snackbar.make(view, getResources().getString(R.string.toast_character_saved_successful), Snackbar.LENGTH_LONG)
									.setAction("Action", null).show();
						} else {
							Snackbar.make(view, getResources().getString(R.string.toast_character_saved_error), Snackbar.LENGTH_LONG)
									.setAction("Action", null).show();
						}
					}
				});
			}
		});

		setupDiceFAB();
	}

	public FAECharacter getCharacter(){
		return character;
	}

}
