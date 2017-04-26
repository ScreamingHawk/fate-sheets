package link.standen.michael.fatesheets.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A class for approach information.
 */
public class Approach implements Serializable, Comparable<Approach> {

	private String description;
	private Integer value;

	public Approach(String description){
		this(description, 0);
	}

	public Approach(String description, Integer value){
		this.description = description;
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public int compareTo(@NonNull Approach other) {
		return this.description.compareTo(other.getDescription());
	}
}
