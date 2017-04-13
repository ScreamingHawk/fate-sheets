package link.standen.michael.fatesheets.model;

import java.io.Serializable;

/**
 * A class for consequence information.
 */
public class Consequence implements Serializable {

	private Integer value;
	private String description;

	public Consequence(Integer value){
		this(value, null);
	}

	public Consequence(Integer value, String description){
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
}
