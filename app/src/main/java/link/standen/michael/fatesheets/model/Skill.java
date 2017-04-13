package link.standen.michael.fatesheets.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A class for skill information.
 */
public class Skill implements Serializable, Comparable<Skill> {

	private Integer value;
	private String description;

	public Skill(Integer value, String description){
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(@NonNull Skill other) {
		if (this.getValue().equals(other.getValue())){
			return this.getDescription().compareTo(other.getDescription());
		}
		return this.getValue().compareTo(other.getValue());
	}
}
