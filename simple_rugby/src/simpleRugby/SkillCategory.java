package simpleRugby;

import java.util.LinkedList;
import java.io.Serializable;

/**
 * <h1>SkillCategory</h1>
 * This class is a constructor used in the creation of skill categories to be stored in the app.
 * <p>
 * When creating a skill category, category name is required. SkillCategory name cannot be changed after creation.
 * <p>
 * Skills will be added to linked list in skill category
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class SkillCategory implements Serializable {
	
	private String category;
	private LinkedList<Skill> skills = new LinkedList<>();
	
	public SkillCategory(String category) {
		this.category = category;
	}
	
	
	public String getCategory() {
		return category;
	}

	public LinkedList<Skill> getSkills(){
		return skills;
	}
	
	public void addSkill(Skill skill) {
		skills.add(skill);
	}
	
	//method to search through skill category for skillName and return skill if found
	public Skill getSkill(String skillName) {
		
		
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getSkillName().equals(skillName)) {
				return skills.get(i);
			}
		}
		return null;
	}
}
