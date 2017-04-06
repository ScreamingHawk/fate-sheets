package link.standen.michael.fatesheets.model;

import android.widget.TextView;

/**
 * View holder for a character.
 */

public class CharacterViewHolder {

	private Character character;
	private TextView nameView;

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public TextView getNameView() {
		return nameView;
	}

	public void setNameView(TextView nameView) {
		this.nameView = nameView;
	}


}
