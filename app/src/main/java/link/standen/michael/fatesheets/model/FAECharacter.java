package link.standen.michael.fatesheets.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;

/**
 * A Fate Accelerated Edition character.
 */
public class FAECharacter extends Character {

	@Override
	public String getSheetType() {
		return "FAE";
	}

	private List<Approach> approaches;
	private List<Stress> stress;

	public FAECharacter(String name, Context context) {
		super(name);
		approaches = new ArrayList<>();
		// Init each approach
		if (context != null) {
			for (String approach : context.getResources().getStringArray(R.array.fae_approaches)) {
				if (!approach.isEmpty()) {
					approaches.add(new Approach(approach));
				}
			}
		}
		// Init stress
		stress = new ArrayList<>();
		stress.add(new Stress(1));
		stress.add(new Stress(2));
		stress.add(new Stress(3));
	}

	public List<Approach> getApproaches() {
		return approaches;
	}

	public void setApproaches(List<Approach> approaches) {
		this.approaches = approaches;
	}

	public List<Stress> getStress() {
		return stress;
	}

	public void setStress(List<Stress> stress) {
		this.stress = stress;
	}
}
