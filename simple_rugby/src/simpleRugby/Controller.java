package simpleRugby;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * <h1>Controller</h1>
 * This class is the controller part of the MVC. It contains the stored username, hashmaps of users, players, juniors and playerSkills, linkedList
 * of squads and an array of all positions. Controller is called by the Main and it starts by attempting to deserialise stored data.
 * <p>
 * If none is present, dummy data is used to ensure there is an admin in users. An eventQueue is setup to open Login.
 * <p>
 * There are also methods which can be called to display a message, check for valid number entered and check a valid string is entered
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

public class Controller {
	//setup public variables to be accessed by all classes, JPanel accessed by only Controller.
	private JPanel contentPane;
	public Model model = new Model();
	
	
	/**
	 * This is the Controller method which runs deserialization and sets up EventQueue to open Login
	 * 
	 */
	public Controller() {
		
		model.deserialization();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				init();
			}
		});
		
		
	}


	/**
	 * This is the init method which creates an instance of Login and sets JFrame to visible
	 * 
	 */
	private void init() {
		try {
			Login frame = new Login(this);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is the messageDialogue method which displays a desired message.
	 * @param message sent from class that calls method to be displayed in JOptionPane
	 */
	public void messageDialogue(String message) {
		
		JOptionPane.showMessageDialog(contentPane, message);
	}
	
	/**
	 * This is the checkNumber method which checks if string contains only digits and is exactly 6 digits long and doesn't start with 0. Throws exception if not
	 * @param checkNum sent from class that calls method to check string
	 */
	public void checkNumber(String checkNum) throws Exceptions {
		if (checkNum.substring(0,1).equals("0") || !checkNum.matches("\\d+") || checkNum.length() != 6) {
			throw new Exceptions("SRU Must be a six digit number that cannot begin with 0");
		}
	}
	
	/**
	 * This is the checkString method which checks if string length is greater than 0. Throws exception if not
	 * @param s sent from class that calls method check string
	 */
	public void checkString(String s) throws Exceptions{
		if (s.length() == 0) {
			throw new Exceptions();
		}
	}
	
	
	
}