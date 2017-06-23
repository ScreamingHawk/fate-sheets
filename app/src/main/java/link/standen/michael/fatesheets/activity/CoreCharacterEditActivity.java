package link.standen.michael.fatesheets.activity;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.CoreCharacterEditSectionAdapter;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.util.CharacterHelper;

public class CoreCharacterEditActivity extends SharedMenuActivity implements CharacterEditActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private CoreCharacterEditSectionAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	private CoreCharacter character;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.core_character_edit_activity);

		// Store the character
		String name = (String) getIntent().getSerializableExtra(Character.INTENT_EXTRA_NAME);
		character = CharacterHelper.getCoreCharacter(this, name);
		if (character == null){
			// New
			if (name == null){
				name = CharacterHelper.getCharacterDefaultName(this);
			}
			character = new CoreCharacter(name);
		}

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new CoreCharacterEditSectionAdapter(getSupportFragmentManager(), this);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

		// FAB
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				clearFocus(new Runnable() {
					@Override
					public void run() {
						if (CharacterHelper.saveCoreCharacter(CoreCharacterEditActivity.this, getCharacter())) {
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

	public CoreCharacter getCharacter(){
		return character;
	}

}
