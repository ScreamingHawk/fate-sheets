package link.standen.michael.fatesheets.model;

/**
 * An abstract class for various character types.
 */

public abstract class Character {

	private CharacterViewHolder holder;
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
