package simpleRugby;
import java.io.Serializable;

/**
 * <h1>Skill</h1>
 * This class is a constructor used in the creation of skills to be stored in the app.
 * <p>
 * When creating a skill, skill name is required or skill name, rating and notes are required. If only skill name is set, rating and notes
 * are set to defaults. Skill name cannot be changed after creation.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class Skill implements Serializable{
	
	static final int DEFAULT_RATING = 0;
	static final String DEFAULT_NOTES = "";
	private String skillName;
	private int rating;
	private String notes;
	
	//method creates skill of skillName, default rating and default notes
	public Skill(String skillName) {
		this.skillName = skillName;
		this.rating = DEFAULT_RATING;
		this.notes = DEFAULT_NOTES;
	}	
	
	//method creates skill of skillName, rating and notes
	public Skill(String skillName, int rating, String notes) {
		this.skillName = skillName;
		this.rating = rating;
		this.notes = notes;
	}

	public String getSkillName() {
		return skillName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	//toString to return string representations of details
	@Override
	public String toString() {
		return "Skill [skillName=" + skillName + ", rating=" + rating + "]";
	}
	
	
}
