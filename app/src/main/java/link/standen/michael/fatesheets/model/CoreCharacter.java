package link.standen.michael.fatesheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Fate Core character.
 */
public class CoreCharacter extends Character {

	@Override
	public String getSheetType() {
		return "CORE";
	}

	private List<Skill> skills;
	private List<String> extras;
	private List<Stress> physicalStress;
	private List<Stress> mentalStress;

	public CoreCharacter(String name) {
		super(name);
		skills = new ArrayList<>();
		// Init skills (1 at 5, 2 at 4...)
		for (int i = 5; i > 0; i--){
			for (int j = 5; j >= i; j--){
				skills.add(new Skill(i));
			}
		}
		extras = new ArrayList<>();
		// Init stresses
		physicalStress = new ArrayList<>();
		physicalStress.add(new Stress(1));
		physicalStress.add(new Stress(2));
		mentalStress = new ArrayList<>();
		mentalStress.add(new Stress(1));
		mentalStress.add(new Stress(2));
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
