package link.standen.michael.fatesheets.model;

import java.io.Serializable;

/**
 * A class for consequence information.
 */
class Consequence implements Serializable {

	private Integer stress;
	private String description;

	public Consequence(Integer stress){
		this(stress, null);
	}

	public Consequence(Integer stress, String description){
		this.stress = stress;
		this.description = description;
	}

	public Integer getStress() {
		return stress;
	}

	public void setStress(Integer stress) {
		this.stress = stress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
