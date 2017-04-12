package link.standen.michael.fatesheets.model;

/**
 * A class for skill information.
 */
class Skill {

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
}
