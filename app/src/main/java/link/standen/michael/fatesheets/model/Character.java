package link.standen.michael.fatesheets.model;

import java.io.Serializable;
import java.util.List;

/**
 * An abstract class for various character types.
 */
public abstract class Character implements Serializable {

	public static final String INTENT_EXTRA_NAME = "Character";

	private transient CharacterViewHolder holder;

	private String name;
	private String description;
	private List<String> aspects;
	private List<String> stunts;
	private List<Consequence> consequences;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAspects() {
		return aspects;
	}

	public void setAspects(List<String> aspects) {
		this.aspects = aspects;
	}

	public List<String> getStunts() {
		return stunts;
	}

	public void setStunts(List<String> stunts) {
		this.stunts = stunts;
	}

	public List<Consequence> getConsequences() {
		return consequences;
	}

	public void setConsequences(List<Consequence> consequences) {
		this.consequences = consequences;
	}
}
