package simpleRugby;

import java.util.LinkedList;

/**
 * <h1>Player</h1>
 * This class is a constructor used in the creation of players to be stored in the app. It extends the Member class.
 * <p>
 * When creating a player, first name, last name, position and SRU number are required.
 * <p>
 * Skill categories should get added once player has been created, each including the relevant skills, ratings and notes
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class Player extends Member {
	
	private String position;
	private int sruNumber;
	private LinkedList<SkillCategory> skillCategories = new LinkedList<>();

	public Player(String firstName, String lastName, String position, int sruNumber) {
		super(firstName, lastName);
		this.position = position;
		this.sruNumber = sruNumber;
		// TODO Auto-generated constructor stub
	}

	public String getPosition() {
		return position;
	}

	public int getSruNumber() {
		return sruNumber;
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
		return "Player [position=" + position + ", skillCategories=" + skillCategories + ", getName()=" + getName()
				+ "]";
	}

	
	
}
