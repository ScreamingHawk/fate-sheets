package link.standen.michael.fatesheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Fate Core character.
 */

public class CoreCharacter extends Character {

	private String highConcept;
	private String trouble;
	private List<Skill> skills;
	private List<String> extras;
	private List<Stress> physicalStress;
	private List<Stress> mentalStress;

	public CoreCharacter(String name) {
		super(name);
		skills = new ArrayList<>();
		extras = new ArrayList<>();
		// Init stresses
		physicalStress = new ArrayList<>();
		physicalStress.add(new Stress(1));
		physicalStress.add(new Stress(2));
		mentalStress = new ArrayList<>();
		mentalStress.add(new Stress(1));
		mentalStress.add(new Stress(2));
	}

	public String getHighConcept() {
		return highConcept;
	}

	public void setHighConcept(String highConcept) {
		this.highConcept = highConcept;
	}

	public String getTrouble() {
		return trouble;
	}

	public void setTrouble(String trouble) {
		this.trouble = trouble;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<String> getExtras() {
		return extras;
	}

	public void setExtras(List<String> extras) {
		this.extras = extras;
	}

	public List<Stress> getPhysicalStress() {
		return physicalStress;
	}

	public void setPhysicalStress(List<Stress> physicalStress) {
		this.physicalStress = physicalStress;
	}

	public List<Stress> getMentalStress() {
		return mentalStress;
	}

	public void setMentalStress(List<Stress> mentalStress) {
		this.mentalStress = mentalStress;
	}
}
