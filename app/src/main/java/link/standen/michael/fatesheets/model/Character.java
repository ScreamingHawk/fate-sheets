package link.standen.michael.fatesheets.model;

import java.io.Serializable;

/**
 * An abstract class for various character types.
 */

public abstract class Character implements Serializable {

	public static final String INTENT_EXTRA_NAME = "Character";

	private transient CharacterViewHolder holder;
	private String name;

	public Character(String name){
		this.name = name;
	}

	public CharacterViewHolder getHolder() {
		return holder;
	}

	public void setHolder(CharacterViewHolder holder) {
		this.holder = holder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
