package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.fragment.CharacterEditAspectsFragment;
import link.standen.michael.fatesheets.fragment.CharacterEditDescriptionFragment;
import link.standen.michael.fatesheets.fragment.CoreCharacterEditSkillsFragment;
import link.standen.michael.fatesheets.fragment.CoreCharacterEditStressFragment;
import link.standen.michael.fatesheets.fragment.CoreCharacterEditStuntsFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections.
 */
public class CoreCharacterEditSectionAdapter extends FragmentPagerAdapter {

	private final Context context;

	public CoreCharacterEditSectionAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class below).
		switch (position) {
			case 0:
				return new CharacterEditDescriptionFragment();
			case 1:
				return new CharacterEditAspectsFragment();
			case 2:
				return new CoreCharacterEditSkillsFragment();
			case 3:
				return new CoreCharacterEditStuntsFragment();
			case 4:
				return new CoreCharacterEditStressFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getResources().getString(R.string.section_title_description);
			case 1:
				return context.getResources().getString(R.string.section_title_aspects);
			case 2:
				return context.getResources().getString(R.string.section_title_skills);
			case 3:
				return context.getResources().getString(R.string.section_title_stunts);
			case 4:
				return context.getResources().getString(R.string.section_title_stress);
		}
		return null;
	}
}