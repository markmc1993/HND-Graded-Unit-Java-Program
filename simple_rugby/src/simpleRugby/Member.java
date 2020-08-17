package simpleRugby;

import java.io.Serializable;

/**
 * <h1>Member</h1>
 * This class is a constructor used in the creation of members to be stored in the app.
 * <p>
 * When creating a member, first name and last name are required.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class Member implements Serializable {
	
	
	private String firstName;
	private String lastName;
	
	public Member(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//getName combines firstName and secondName into a full name
	public String getName() {
		return (firstName + " " + lastName);
	}
	
	//toString to return string representations of details
	@Override
	public String toString() {
		return "Member [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
