package simpleRugby;

import java.util.LinkedList;

/**
 * <h1>Junior Player</h1>
 * This class is a constructor used in the creation of junior players to be stored in the app. It extends the Player class.
 * <p>
 * When creating a junior player, first name, last name, position, SRU number and guardian name are required.
 * <p>
 * Skill categories should get added once junior player has been created, each including the relevant skills, ratings and notes
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class JuniorPlayer extends Player {
	
	
	private String guardianName;
	private LinkedList<SkillCategory> skillCategories = new LinkedList<>();
	
	public JuniorPlayer (String firstName, String lastName, String position, int sruNumber, String guardianName) {
		super(firstName, lastName, position, sruNumber);
		this.guardianName = guardianName;
		
	}

	

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianName() {
		return guardianName;
	}
	
	public void addSkillCategory(SkillCategory cat) {
		skillCategories.add(cat);
	}
	
	public LinkedList<SkillCategory> getSkillCategories(){
		return skillCategories;
	}
	
	//method loops through skillCategories and if skillName is in category, return rating of skill
	public int getSkillRating(String skillName) {
		for (SkillCategory skillCategory : skillCategories) {
			int rating = skillCategory.getSkill(skillName).getRating();
			return rating;
		}
		return 0;
		
	}
	
	//method loops through skillCategories and if skillName is in category, setRating to rating
	public void setSkillRating(String skillName, int rating) {
		for (SkillCategory skillCategorie : skillCategories) {
			skillCategorie.getSkill(skillName).setRating(rating);
		}
	}
	
	
	//toString to return string representations of details
	@Override
	public String toString() {
		return "JuniorPlayer [guardianName=" + guardianName + ",  getPosition()="
				+ getPosition() + ", getName()=" + getName() + "]";
	}

	
}
