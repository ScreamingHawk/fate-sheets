package link.standen.michael.fatesheets.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import link.standen.michael.fatesheets.fragment.CharacterEditAspectsFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CharacterEditSectionAdapter extends FragmentPagerAdapter {

	public CharacterEditSectionAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class below).
		return new CharacterEditAspectsFragment();
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "SECTION 1";
			case 1:
				return "SECTION 2";
			case 2:
				return "SECTION 3";
		}
		return null;
	}
}