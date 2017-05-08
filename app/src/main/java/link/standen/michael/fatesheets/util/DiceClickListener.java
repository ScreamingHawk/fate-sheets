package link.standen.michael.fatesheets.util;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import link.standen.michael.fatesheets.R;

/**
 * A utility class for handling clicks to display a dice roll action.
 */
public final class DiceClickListener implements View.OnClickListener {

	private final Resources resources;
	private final Random random;

	/**
	 * A shared list of rolls for persistence.
	 */
	private static List<int[]> rolls = new ArrayList<>();

	public DiceClickListener(Resources resources){
		this.resources = resources;
		this.random = new Random();
	}

	@Override
	public void onClick(View view) {
		// Roll dice
		int[] roll = new int[4];
		int total = 0;
		StringBuilder bob = new StringBuilder("[");
		for (int i = 0; i < roll.length; i++) {
			roll[i] = random.nextInt(3) - 1;
			total += roll[i];
			// Display string
			if (i > 0){
				bob.append(" ");
			}
			bob.append(formatRoll(roll[i]));
		}
		bob.append("]");
		rolls.add(roll);

		Snackbar.make(view, resources.getString(R.string.toast_rolled, formatRoll(total),
				ladderResult(total), bob.toString()), Snackbar.LENGTH_INDEFINITE)
				.setAction("Action", null).show();
	}

	/**
	 * Convert the integer to a string for display purposes.
	 */
	private String formatRoll(int roll){
		if (roll > 0){
			return "+"+roll;
		}
		return ""+roll;
	}

	private String[] ladder;

	/**
	 * Get the ladder string result from the roll.
	 */
	private String ladderResult(int roll){
		// Init ladder
		if (ladder == null){
			ladder = resources.getStringArray(R.array.roll_ladder);
		}
		// Convert roll to array index location
		roll += 2;
		if (roll < 0){
			// Lowest result
			roll = 0;
		} else if (roll >= ladder.length){
			// Highest result
			roll = ladder.length - 1;
		}
		return ladder[roll];
	}
}