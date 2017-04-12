package link.standen.michael.fatesheets.model;

import java.io.Serializable;

/**
 * A class for stress information.
 */
class Stress implements Serializable {

	private Integer value;
	private Boolean active;

	public Stress(Integer value){
		this(value, false);
	}

	public Stress(Integer value, Boolean active){
		this.value = value;
		this.active = active;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
