package simpleRugby;
import java.io.Serializable;

/**
 * <h1>User</h1>
 * This class is a constructor used in the creation of users to be stored in the app.
 * <p>
 * When creating a user, first name, last name, username, password and user type are required.
 * <p>
 * Only first name, last name, and password can be changed for user
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int userType;
	
	
	public User(String firstName, String lastName, String userName, String password, int userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getUserType() {
		return userType;
	}

	//toString to return string representations of details
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", userType=" + userType + "]";
	}
	

}
